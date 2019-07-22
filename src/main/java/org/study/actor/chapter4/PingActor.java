package org.study.actor.chapter4;

import org.study.actor.Ping1Actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	int count = 0;
	
	public PingActor() throws Exception {
		this.child = context().actorOf(Props.create(Ping1Actor.class), "ping1Actor");
	}
	
	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof String) {
			String msg = (String) message;
			if ("good".equals(msg)) {
				child.tell(msg, getSelf());
			} else if ("done".equals(msg)) {
				log.info("all works are successfully completed.");
			}
		}else {
			unhandled(message);
		}
	}
}
