package com.cpfa.fundtransfer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
public class FundTransferEventProducer {

	private Source txSource;

	public FundTransferEventProducer(Source txSource) {

		super();
		this.txSource = txSource;
	}

	public Source getTxSource() {

		return txSource;
	}

	public void setTxSource(Source txSource) {

		this.txSource = txSource;
	}

}
