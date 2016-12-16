package com.fusemachines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

@Component("HelloWorld")
@Scope("prototype")

public class HelloWorld extends UntypedActor{

	@Override
	public void onReceive(Object message){
		if(message instanceof ActorRef){
			getSender().tell("Test Incomplete", getSelf());
		} else{
			getSender().tell(message, getSelf());
		}
	}

}
