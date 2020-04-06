package com.cpfa.fundtransfer;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.cpfa.fundtransfer.domain.FTReqStatus;
import com.cpfa.fundtransfer.repository.FundtransferDao;



@EnableBinding(Sink.class)
public class FTConsumer {

	private static final Logger logger = LoggerFactory.getLogger(FTConsumer.class);
	@Autowired
	private FundtransferDao fundtransferDao;
	private static long counter=0;


	@StreamListener(target = Sink.INPUT, condition = "headers['type']=='fundtransfer'")
	public void processFt(@Payload int acctNum) {

		logger.info("recieved a message",  acctNum);
		updateStatus(acctNum);

	}
	private void updateStatus(int acctNum){
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		executor.execute(new Runnable(){public void run() {
			try {
				Thread.sleep(40000);
			} catch (InterruptedException e) {
				logger.error("exception in updateStatus:",e);
			}
			counter++;
			if(counter%3==0){
				fundtransferDao.updateStatus(FTReqStatus.DECLINED.name(),FTReqStatus.PENDING.name(), acctNum);
				fundtransferDao.updateStatus(FTReqStatus.DECLINED.name(),FTReqStatus.PENDING_RECEIVE.name(), acctNum);
			}
			else{
				fundtransferDao.updateStatus(FTReqStatus.SENT.name(),FTReqStatus.PENDING.name(), acctNum);
				fundtransferDao.updateStatus(FTReqStatus.RECEIVED.name(),FTReqStatus.PENDING_RECEIVE.name(), acctNum);
			}
		  }
		});
	}

}
