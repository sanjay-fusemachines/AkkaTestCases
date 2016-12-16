package com.fusemachines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;

@Component("Child")
@Scope("prototype")
public class Child extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Throwable {
		
		System.out.println("Inside child");
		getSender().tell(message + " from child.", getSelf());
		
	}

}
	