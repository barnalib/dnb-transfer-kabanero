package com.cpfa.fundtransfer.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cpfa.fundtransfer.FundTransferEventProducer;
import com.cpfa.fundtransfer.domain.FundTransferDto;
import com.cpfa.fundtransfer.domain.FundTransferResponse;
import com.cpfa.fundtransfer.domain.TransactionDto;
import com.cpfa.fundtransfer.domain.TransactionRequest;
import com.cpfa.fundtransfer.domain.FTReqStatus;
import com.cpfa.fundtransfer.service.FundtransferService;
/**
 * Controller class that serves loan application request
 */
@RestController
public class FundTransferController {

    private static final Logger logger = LoggerFactory.getLogger(FundTransferController.class);
	private FundTransferEventProducer producer;
	@Autowired
	private FundtransferService fundTransferService;
	@Value("${transaction.api.uri}")
	private String transactionUri;
	@Value("${account.api.uri}")
	private String accountUri;


	public FundTransferController(FundTransferEventProducer producer) {

		super();
		this.producer = producer;
	}
    @RequestMapping(value = "/api/v1/fund_transfer/liveness", method = RequestMethod.GET)
    public String liveness() {
        return "{\"status\":\"UP\"}";
    }

	/**
	 *
	 * @param fundTransferDto
	 * @return
	 * @throws URISyntaxException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/api/v1/fund_transfer", method = RequestMethod.POST)
	public ResponseEntity<FundTransferDto> postTransferRequest(@RequestBody FundTransferDto fundTransferDto) throws URISyntaxException, JSONException {

		fundTransferDto.setRequestStatus(FTReqStatus.PENDING.name());
		fundTransferService.saveFundTransfer(fundTransferDto);
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(transactionUri);
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("acctNumber", fundTransferDto.getAccountNumber());
	    ResponseEntity<String> accounts=restTemplate.getForEntity(accountUri, String.class, params);
	    JSONObject obj = new JSONObject(accounts.getBody());
	    Double balance=(Double) obj.get("accountBalance");
		TransactionRequest txTeq=new TransactionRequest();
		txTeq.setAccountNumber(fundTransferDto.getAccountNumber());
                if(fundTransferDto.getTransactionDesc()== null || "".equals(fundTransferDto.getTransactionDesc())){
                       fundTransferDto.setTransactionDesc("money transfer");
                }
		TransactionDto tx=new TransactionDto(fundTransferDto.getPayeeAccount(), "Bank-Transfer", Math.round(balance), fundTransferDto.getTransactionDesc(),
				Math.round(fundTransferDto.getAmount()), "fund transfer");
		logger.info("transaction posted:"+tx.toString());
		List<TransactionDto> transactions=new ArrayList<TransactionDto>();
		transactions.add(tx);
		txTeq.setTransactions(transactions);
		try{
			restTemplate.postForEntity(uri, txTeq, String.class);
		}catch(Exception e){
			logger.error("Error in fund transfer-transaction posting",e);
		}



		return ResponseEntity.ok()
					   .body(fundTransferDto);
	}

	/**
	 *
	 * @param acctNumber
	 * @return
	 */
	@RequestMapping(value = "/api/v1/fund_transfer/{acctNumber}", method = RequestMethod.GET)
	public ResponseEntity<FundTransferResponse> getFundTransfers(@PathVariable int acctNumber) {

		FundTransferResponse fundTransferResponse=new FundTransferResponse();
		fundTransferResponse.setAccountNumber(acctNumber);
		fundTransferResponse.setTransfers(fundTransferService.getFundTransfers(acctNumber));

		return ResponseEntity.ok()
				   .body(fundTransferResponse);

	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/v1/post-fund-transfer/{acctNumber}", method = RequestMethod.PUT)
	public void putFundTransfers(@PathVariable int acctNumber) {
		logger.info("post-fund-transfer posting");
		producer.getTxSource().output().send(MessageBuilder.withPayload(acctNumber).setHeader("type", "fundtransfer").build());
	}



}
