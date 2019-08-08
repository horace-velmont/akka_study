package org.study.actor.chapter8.node2.service;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingService extends UntypedActor {
	
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private int count = 0;
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			String msg = (String) message;
			getSender().tell("PING: " + count++, getSelf());
		} else {
			unhandled(message);
		}
	}
	
}
