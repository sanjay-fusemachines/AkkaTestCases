package com.fusemachines;


import org.springframework.context.ApplicationContext;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;


public class ActorConfig {	
	
	public ActorConfig(ApplicationContext applicationContext){
		ActorSystem system = ActorSystem.create("Hello");
		SpringExtension x = applicationContext.getBean(SpringExtension.class);
		
		
		x.initialize(applicationContext);
		
		ActorRef actorRef = system.actorOf(x.props("HelloWorld"),"helloWorldActor");
		
		actorRef.tell("Hello World", null);
	}
	
	public String startActorConfig(){
		return "Actor Config Started";
	}
	
}