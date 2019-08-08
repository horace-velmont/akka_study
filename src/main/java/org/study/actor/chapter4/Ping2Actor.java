package org.study.actor.chapter4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class Ping2Actor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public Ping2Actor() {
		log.info("Ping2Actor constructor");
	}
	
	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		log.info("Ping2Actor preRestart");
	}
	
	@Override
	public void onReceive(Object message) {
		if (message instanceof String) {
			String msg = (String) message;
			if ("good".equals(msg)) {
				goodWork();
			} else if ("bad".equals(msg)) {
				badWork();
			}
		} else {
			unhandled(message);
		}
	}
	
	private void badWork() {
		int a = 1 / 0;
	}
	
	private void goodWork() {
		log.info("goodwork is good");
	}
}
