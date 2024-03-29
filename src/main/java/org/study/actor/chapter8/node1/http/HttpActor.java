package org.study.actor.chapter8.node1.http;

import akka.actor.ActorRef;
import akka.camel.CamelMessage;
import akka.camel.javaapi.UntypedConsumerActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HttpActor extends UntypedConsumerActor{
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private String uri;
	private ActorRef service;
	private ActorRef sender;
	
	public HttpActor(ActorRef service) {
		this.service = service;
		this.uri = "jetty:http://localhost:8877/akkaStudy";
	}

	public void onReceive(Object message) throws Exception {
		if (message instanceof CamelMessage) {
            this.sender = getSender();
            service.tell("Hello", getSelf());
		} else if (message instanceof String) {
			sender.tell(message, getSelf());
		} else {
			unhandled(message);
		}
	}

	public String getEndpointUri() {
		return uri;
	}

}