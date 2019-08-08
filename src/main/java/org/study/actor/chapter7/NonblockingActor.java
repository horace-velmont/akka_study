package org.study.actor.chapter7;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class NonblockingActor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child = context().actorOf(Props.create(CalculationActor.class));
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private final ExecutionContext ec = context().system().dispatcher();
	
	@Override
	public void onReceive(Object message) {
		if (message instanceof Integer) {
			Future<Object> future = Patterns.ask(child, message, timeout);
			
			future.onComplete(new SayComplete<Object>(), ec);
		} else if (message instanceof String) {
			log.info("NonblockingActor received a message : " + message);
		}
	}
	
	public final class SayComplete<T> extends OnComplete<T> {
		@Override
		public final void onComplete(Throwable t, T result) {
			log.info("Completed");
		}
	}
}
