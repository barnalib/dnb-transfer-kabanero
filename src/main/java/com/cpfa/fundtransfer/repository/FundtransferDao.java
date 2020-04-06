package com.cpfa.fundtransfer.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cpfa.fundtransfer.entity.FundTransfer;


public interface FundtransferDao extends CrudRepository<FundTransfer, Integer>{

		@Modifying
		@Transactional
		@Query(value ="update fund_transfer  set status=?1,notification='Y' where tx_id=?3 and status=?2 limit 1", nativeQuery = true)
		void updateStatus(String status,String prevStatus, int acctNumber);
		@Modifying
		@Transactional
		@Query(value ="update fund_transfer  set notification='N' where account_num=?1", nativeQuery = true)
		void updateNotification(int acctNumber);
		@Query("from FundTransfer where accountNum=?1")
		List<FundTransfer> findByAcctNumber(int acctNumber);

}
