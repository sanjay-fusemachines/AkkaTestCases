package testCases;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.akkatest.Application;
import com.akkatest.ContextContainer;
import com.akkatest.akka.HelloWorld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes={Application.class})
public class HelloWorldTest{
	
	static ActorSystem system;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create();
	}
	
	
	@Test
	public void testCases(){
		ApplicationContext context =  SpringApplication.run(Application.class);
		ContextContainer.setContext(context);
		new JavaTestKit(system){
			{
				final Props props = Props.create(HelloWorld.class);
				final ActorRef testRef = system.actorOf(props);
				
				testRef.tell("Create child", getRef());
				
				expectMsgEquals(duration("3 seconds"), "Test from child.");
				
				testRef.tell("Test", getRef());
				
				expectMsgEquals(duration("3 seconds"), "Test");
			}
			
		};
		
	}
	
}

