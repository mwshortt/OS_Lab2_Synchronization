public class Questions3 {

	public static void main(String args[]) throws InterruptedException{

		//method call to test one agent and one spy
		oneMsgTest();

		// number of messages for the multi agent problem
		// number of agents = number of spies = number of messages
		int numMessages=7;
		
		//method call to test multiple spies and agents
		multiMsgTest(numMessages);
	}

	public static void oneMsgTest() throws InterruptedException{

		// new espionage for one msg test
		Espionage espionage=new Espionage();
		Agent agent=new Agent();
		Spy spy=new Spy();

		// pass message through spy
		spy.sendMessage("Hello",espionage);

		// agent recieves message
		System.out.println(agent.getMessage(espionage));
		
	}

	public static void multiMsgTest(int numAgents) throws InterruptedException{

		// new espionage for multi-msg test
		Espionage espionage_multi=new Espionage();

		// num of agents = num of spies 
		Agent[] agent=new Agent[numAgents];
		Spy[] spy=new Spy[numAgents];
		

		for(int i=0;i<spy.length;i++){
			// create a spy and agent for each element of array
			spy[i]=new Spy();
			agent[i]=new Agent();

			// send a message through spy
			spy[i].sendMessage(Integer.toString(i), espionage_multi);

			// receive the message through agent
			System.out.println(agent[i].getMessage(espionage_multi));
		}
		
		
	}
}







