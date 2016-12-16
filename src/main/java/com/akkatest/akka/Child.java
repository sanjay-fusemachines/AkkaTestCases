package com.akkatest.akka;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;

@Component("Child")
@Scope("prototype")
public class Child extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Throwable {
		
		//Termination of program if message is test from child
		if(message.equals("Test from child.")){
			System.out.println(message);
			System.out.println("Program terminated");
		} else{
			getSender().tell(message + " from child.", getSelf());
		}
	}

}
	