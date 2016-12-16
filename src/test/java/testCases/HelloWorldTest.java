package testCases;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fusemachines.Application;
import com.fusemachines.ContextContainer;
import com.fusemachines.HelloWorld;

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
				System.out.println("Testing");
				final Props props = Props.create(HelloWorld.class);
				final ActorRef testRef = system.actorOf(props);
				final JavaTestKit probe = new JavaTestKit(system);
				
				System.out.println("After declarations");
				
				testRef.tell(probe.getRef(), getRef());
				
				System.out.println("After Tell");
				
				expectMsgEquals(duration("20 seconds"), "Test from child.");
			}
			
		};
		
	}
	
}

