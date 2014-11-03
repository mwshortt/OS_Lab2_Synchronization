public class questions3 {
	public static void main(String args[]) throws InterruptedException{
		oneSpyTest();
		multiSpyTest();
	}

	public static void oneSpyTest() throws InterruptedException{
		Espionage espionage=new Espionage();
		Agent agent=new Agent(espionage);
		Spy spy=new Spy(espionage);
		spy.sendMessage("Hello");
		System.out.println(agent.getMessage());
		
	}

	public static void multiSpyTest() throws InterruptedException{
		Espionage espionage_multi=new Espionage();
		int numSpies=7;
		int numAgents=7;
		Agent[] agent=new Agent[numAgents];
		Spy[] spy=new Spy[numSpies];
		
		for(int i=0;i<spy.length;i++){
			spy[i]=new Spy(espionage_multi);
			spy[i].sendMessage(Integer.toString(i));
		}
		
		for(int i=0;i<agent.length;i++){
			agent[i]=new Agent(espionage_multi);
			System.out.println(agent[i].getMessage());
		}
		
	}
}







