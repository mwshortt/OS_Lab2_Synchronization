public class Questions3 {

	public static void main(String args[]) throws InterruptedException{

		//method call to test one agent and one spy
		oneMsgTest();

		// number of messages for the multi agent problem
		// number of agents = number of spies = number of messages
		int numMessages=7;
		if (args.length >0){
			try{
				int num_msg=Integer.parseInt(args[0]);
				if (num_msg > 0)
					numMessages=num_msg;
			}
			catch(NumberFormatException e){
				System.out.println("Invalid: using default value, i.e. 7 for number of messages");
			}
		}
		//method call to test multiple spies and agents
		multiMsgTest(numMessages);
	}

	public static void oneMsgTest() throws InterruptedException{

		// new espionage for one msg test
		Espionage espionage=new Espionage();
		Agent agent=new Agent();
		Spy spy=new Spy();

		System.out.println("START: Single agent and single spy");
		// pass message through spy
		spy.sendMessage("Hello",espionage);

		// agent recieves message
		System.out.println(agent.getMessage(espionage));

		System.out.println("END: Single agent and single spy");
		
	}

	public static void multiMsgTest(int numAgents) throws InterruptedException{

		// new espionage for multi-msg test
		Espionage espionage_multi=new Espionage();

		// num of agents = num of spies 
		Agent[] agent=new Agent[numAgents];
		Spy[] spy=new Spy[numAgents];

		System.out.println("START: Multi-agent and multi-spy");
		System.out.println("Number of agents and spies is "+numAgents);

		for(int i=0;i<spy.length;i++){
			// create a spy and agent for each element of array
			spy[i]=new Spy();
			agent[i]=new Agent();

			// send a message through spy; msg is the spy number
			spy[i].sendMessage(Integer.toString(i), espionage_multi);

			// receive the message through agent; msg is the spy number
			System.out.println(agent[i].getMessage(espionage_multi));
		}
		System.out.println("END: Multi-agent and multi-spy");

		
	}
}







