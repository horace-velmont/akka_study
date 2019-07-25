package org.study.actor.chapter5;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class));
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("reset", ActorRef.noSender());
	}
}
