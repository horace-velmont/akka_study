package org.study.actor.chapter8.node1;


import org.study.actor.chapter8.node1.http.HttpActor;
import org.study.actor.chapter8.node1.service.PingService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;

public class Main {
	
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("ClusterSystem");
		ActorRef router = actorSystem.actorOf(Props.create(PingService.class).withRouter(new FromConfig()), "serviceRouter");
		ActorRef httpActor = actorSystem.actorOf(Props.create(HttpActor.class, router), "httpActor");
	}
}
