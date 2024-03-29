package org.study.actor;

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
	public void onReceive(Object message) {
		if (message instanceof String) {
			String msg = (String) message;
			if ("work".equals(msg)) {
				child.tell(msg, getSelf());
			} else if ("done".equals(msg)) {
				if (count == 0) {
					count++;
				} else {
					log.info("all works are completed.");
					count = 0;
				}
			}
		}
	}
}
