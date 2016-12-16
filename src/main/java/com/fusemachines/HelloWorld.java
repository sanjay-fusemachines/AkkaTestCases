package com.fusemachines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

@Component("HelloWorld")
@Scope("prototype")

public class HelloWorld extends UntypedActor{

	static ApplicationContext  context = ContextContainer.getContext();
	
	private ActorRef parent;

	public ActorRef getParent() {
		return parent;
	}

	public void setParent(ActorRef parent) {
		this.parent = parent;
	}

	@Override
	public void onReceive(Object message){
		System.out.println("Inside hello world");
		if(message instanceof ActorRef){
			System.out.println("Inside if ");
			this.setParent(getSender());
			System.out.println(getSender());
			System.out.println(this.getParent());
			ActorConfig actorConfig = new ActorConfig(context);
			ActorRef child = actorConfig.getChildActor();
			System.out.println("After creating child");
			child.tell("Test", getSelf());
			System.out.println("After telling child");
		} else{
			System.out.println("Inside else");
			System.out.println(message);
			System.out.println(this.getParent());
			this.getParent().tell(message, getSelf());
			System.out.println("After telling inside helloworld");
		}
	}

}
