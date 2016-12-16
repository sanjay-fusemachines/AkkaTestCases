package com.akkatest.config;


import org.springframework.context.ApplicationContext;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;


public class ActorConfig {	
	
	
	
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
		
		SpringExtension x = this.getApplicationContext().getBean(SpringExtension.class);
		
		this.setSpringExtension(x);
		
		this.getSpringExtension().initialize(this.getApplicationContext());
	
	}
	
	public String startActorConfig(){
		return "Actor Config Started";
	}
	
	
	
}