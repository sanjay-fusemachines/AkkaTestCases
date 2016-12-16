package com.fusemachines;


import org.springframework.context.ApplicationContext;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;


public class ActorConfig {	
	
	static ActorSystem system = ActorSystem.create("Hello");
	
	private ApplicationContext applicationContext;
	private SpringExtension springExtension;
	
	public void setApplicationContext(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext(){
		return this.applicationContext;
	}
	
	public void setSpringExtension(SpringExtension springExtension){
		this.springExtension = springExtension;
	}
	
	public SpringExtension getSpringExtension(){
		return this.springExtension;
	}
	
	
	public ActorConfig(ApplicationContext applicationContext){
		
		this.setApplicationContext(applicationContext);
		
		SpringExtension x = applicationContext.getBean(SpringExtension.class);
		
		this.setSpringExtension(x);
		
		this.getSpringExtension().initialize(this.getApplicationContext());
		
		//ActorRef actorRef = system.actorOf(x.props("HelloWorld"),"helloWorldActor");
		
		//actorRef.tell("Hello World", null);
	}
	
	public String startActorConfig(){
		return "Actor Config Started";
	}
	
	public ActorRef getChildActor(){
		ActorRef childActor = system.actorOf(this.getSpringExtension().props("Child"), "childActor");
		return childActor;
	}
	
}