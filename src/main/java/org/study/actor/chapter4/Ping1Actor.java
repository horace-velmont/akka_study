package org.study.actor.chapter4;

import org.study.actor.Ping2Actor;
import org.study.actor.Ping3Actor;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

public class Ping1Actor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child1;
	private ActorRef child2;
	
	public Ping1Actor() {
		child1 = context().actorOf(Props.create(Ping2Actor.class), "ping2Actor");
		child2 = context().actorOf(Props.create(Ping3Actor.class), "ping3Actor");
	}
	
	@Override
	public void onReceive(Object message) {
		if (message instanceof String) {
			String msg = (String) message;
			if ("good".equals(msg) || "bad".equals(msg)) {
				log.info("Ping1Actor received {}", msg);
				child1.tell(msg, getSender());
				child2.tell(msg, getSender());
			}
		} else {
			unhandled(message);
		}
	}
	
	private static SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.create("1 minute"),
			new Function<Throwable, Directive>() {
				@Override
				public Directive apply(Throwable t) throws Exception {
					if (t instanceof ArithmeticException) {
						//return resume();
					} else if (t instanceof NullPointerException) {
						//return restart();
					} else if (t instanceof IllegalArgumentException) {
						//return stop();
					} else {
						//return escalate();
					}
					return null;
				}
			});
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
}
