package com.akkatest.akka;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.DeadLetterActorRef;
import akka.actor.UntypedActor;

@Component("HelloWorld")
@Scope("prototype")

public class HelloWorld extends UntypedActor{
	
	private ActorRef parent;

	public ActorRef getParent() {
		return parent;
	}

	public void setParent(ActorRef parent) {
		this.parent = parent;
	}

	@Override
	public void onReceive(Object message){
		if(message.equals("Create child")){
			
			//If actor is deadletter then don't set parent otherwise set parent
			if(getSender() instanceof DeadLetterActorRef ){
				this.setParent(null);
			}
			else{
				this.setParent(getSender());
			}
			AkkaInitializer akkaInitializer = new AkkaInitializer();
			ActorRef child = akkaInitializer.getChildActor();
			child.tell("Test", getSelf());
		} else{
			
			//If parent actor is null then set parent
			if(this.getParent() == null){
				this.setParent(getSender());
			}
			
			//If parent is instance of actor reference then tell message to parent otherwise print out the message
			if(this.getParent() instanceof ActorRef){
				this.getParent().tell(message, getSelf());
			}else{
				System.out.println(message);
			}
		}
	}

}
