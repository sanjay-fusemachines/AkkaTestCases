package testCases;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fusemachines.HelloWorld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;

public class HelloWorldTest{
	
	static ActorSystem system;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create();
	}
	
	@Test
	public void testCases(){
		
		new JavaTestKit(system){
			{
				final Props props = Props.create(HelloWorld.class);
				final ActorRef testRef = system.actorOf(props);
				
				testRef.tell("Test", getRef());
				
				expectMsgEquals(duration("1 second"), "Test1");
				
			}
			
		};
		
	}
	
	@Test
	public void testCases1(){
		
		new JavaTestKit(system){
			{
				final Props props = Props.create(HelloWorld.class);
				final ActorRef testRef = system.actorOf(props);
				final JavaTestKit probe = new JavaTestKit(system);
								
				testRef.tell(probe.getRef(), getRef());
				
				expectMsgEquals(duration("1 second"), "Test Incomplete1");
			}
			
		};
		
	}
	
}

