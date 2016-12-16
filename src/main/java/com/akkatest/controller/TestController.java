package com.akkatest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akkatest.akka.AkkaInitializer;

import akka.actor.ActorRef;

@RestController
public class TestController {
	
	@RequestMapping(value = "/begin", method = RequestMethod.GET)
	public void testStarted(){
		AkkaInitializer akkaInitializer = new AkkaInitializer();
		
		ActorRef helloWorld = akkaInitializer.getHelloWorld();
		
		helloWorld.tell("Create child", null);
	}
	
}
