package com.cpfa.fundtransfer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
public class FundTransferProducer {

	private Source ftNotifSource;

	public FundTransferProducer(Source ftNotifSource) {

		super();
		this.setFtNotifSource(ftNotifSource);
	}

	public Source getFtNotifSource() {
		return ftNotifSource;
	}

	public void setFtNotifSource(Source ftNotifSource) {
		this.ftNotifSource = ftNotifSource;
	}



}
