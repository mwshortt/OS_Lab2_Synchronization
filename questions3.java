public class Questions3 {

	public static void main(String args[]) throws InterruptedException{

		//method call to test one agent and one spy
		oneSpyTest();

		//method call to test multiple spies and agents
		multiSpyTest(7);
	}

	public static void oneSpyTest() throws InterruptedException{
		Espionage espionage=new Espionage();
		Agent agent=new Agent(espionage);
		Spy spy=new Spy(espionage);
		spy.sendMessage("Hello");
		System.out.println(agent.getMessage());
		
	}

	public static void multiSpyTest(int numAgents) throws InterruptedException{
		Espionage espionage_multi=new Espionage();

		// num of agents is 
		Agent[] agent=new Agent[numAgents];
		Spy[] spy=new Spy[numAgents];
		
		for(int i=0;i<spy.length;i++){
			spy[i]=new Spy(espionage_multi);
			agent[i]=new Agent(espionage_multi);
			spy[i].sendMessage(Integer.toString(i));
			System.out.println(agent[i].getMessage());
		}
		
		
	}
}







