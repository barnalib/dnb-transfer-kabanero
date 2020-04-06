package com.cpfa.fundtransfer.service;

import java.util.List;

import com.cpfa.fundtransfer.domain.FundTransferDto;
import com.cpfa.fundtransfer.entity.FundTransfer;
/**
 *
 * Service class for loan request
 *
 */
public interface FundtransferService {


	List<FundTransfer> getFundTransfers(int acctNumber);

	void saveFundTransfer(FundTransferDto fundTransferDto);

}
