package com.cpfa.fundtransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpfa.fundtransfer.domain.FTReqStatus;
import com.cpfa.fundtransfer.domain.FundTransferDto;
import com.cpfa.fundtransfer.entity.FundTransfer;
import com.cpfa.fundtransfer.repository.FundtransferDao;
import com.cpfa.fundtransfer.service.FundtransferService;

@Service
public class FundtransferServiceImpl implements FundtransferService{
	@Autowired
	private FundtransferDao fundtransferDao;

	@Override
	public List<FundTransfer> getFundTransfers(int acctNumber) {
		List<FundTransfer> transfers=fundtransferDao.findByAcctNumber(acctNumber);
		fundtransferDao.updateNotification(acctNumber);
		return transfers;
	}
	@Override
	public void saveFundTransfer(FundTransferDto fundTransferDto) {
		FundTransfer fundTransfer=new FundTransfer(fundTransferDto.getAccountNumber(),fundTransferDto.getPayeeAccount(),fundTransferDto.getPayeeName(),
				fundTransferDto.getTransactionDesc(),fundTransferDto.getAmount(),fundTransferDto.getRequestStatus(),fundTransferDto.getAccountNumber());
		fundtransferDao.save(fundTransfer);
		fundTransfer=new FundTransfer(fundTransferDto.getPayeeAccount(),fundTransferDto.getAccountNumber(),fundTransferDto.getPayeeName(),
				fundTransferDto.getTransactionDesc(),fundTransferDto.getAmount(),FTReqStatus.PENDING_RECEIVE.name(),fundTransferDto.getAccountNumber());
		fundtransferDao.save(fundTransfer);
	}

}
