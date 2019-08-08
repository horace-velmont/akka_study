package org.study.actor.chapter8.node2;


import org.study.actor.chapter8.node2.service.PingService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("ClusterSystem");
		ActorRef pingService = actorSystem.actorOf(Props.create(PingService.class), "pingService");
	}
}
