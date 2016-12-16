package com.akkatest.akka;

import com.akkatest.ContextContainer;
import com.akkatest.config.ActorConfig;
import com.akkatest.config.SpringExtension;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaInitializer {
	
public SpringExtension ext = ContextContainer.getContext().getBean(SpringExtension.class);

static ActorConfig actorConfig = new ActorConfig(ContextContainer.getContext());

static ActorSystem system = ActorSystem.create("Hello");
	
	public AkkaInitializer() {
		this.ext.initialize(ContextContainer.getContext());
	}
	
	public ActorRef getHelloWorld(){
		ActorRef helloWorld = system.actorOf(actorConfig.getSpringExtension().props("HelloWorld"), "helloActor");
		return helloWorld;
	}
	
	public ActorRef getChildActor(){
		ActorRef childActor = system.actorOf(actorConfig.getSpringExtension().props("Child"), "childActor");
		return childActor;
	}
}


