
public class Espionage extends Thread{
	
	private static String message="";
	private String name="";
	private static final Object monitor= new Object();
	private int ID;
	
	public Espionage(String name, int ID){
		// if there is a message, it's a spy
		this.name=name;
		this.ID=ID;
	}
	
	public void run(){
		if (name.equals("agent")){
			try {
				System.out.println("Starting agent: "+ID);
				synchronized(monitor){
                    agentPickMsg();}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (name.equals("spy")){
			try {
				synchronized(monitor){
                    System.out.println("Starting spy: "+ID);
                    dropSpyMsg();}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
    public synchronized void dropSpyMsg() throws InterruptedException{
		
		// wait until there is no previous message
		while(!message.isEmpty()){
			synchronized(monitor){
                System.out.println("Spy: "+ID +" is waiting");
                monitor.wait();}
		}
		
		message="Message: "+ID;
		
		// A spy writes the message on a piece of paper
		// and drops it at a predetermined location
		System.out.println("Spy: "+ID +" is leaving message: "+ message);
		synchronized(monitor){
			monitor.notify();
		}
	}
	
	public synchronized void agentPickMsg() throws InterruptedException{
		
		// wait until there is a message
		while(message.isEmpty()){
			synchronized(monitor){
                System.out.println("Agent: "+ID +" is waiting");
                monitor.wait();}
		}
		System.out.println("BEFORE: "+message.isEmpty());
		// use the message that was updated in dropSpyMsg
		String saveMessage=message;
		
		//reset the message to empty
		message="";
		
		System.out.println("Agent: "+ID +" is picking messgae: "+ saveMessage);
		
		System.out.println("AFTER: "+message.isEmpty());
		synchronized(monitor){
			monitor.notify();
        }
	}
	
	public static void main(String args[]) throws InterruptedException{
        
		//method call to test one agent and one spy
		oneMsgTest();
		Thread.sleep(2000);
		System.out.println("");
		// number of messages for the multi agent problem
		// number of agents = number of spies = number of messages
		int numMessages=3;
		if (args.length >0){
			try{
				int num_msg=Integer.parseInt(args[0]);
				if (num_msg > 0)
					numMessages=num_msg;
			}
			catch(NumberFormatException e){
				System.out.println("Invalid: using default value, i.e. 3 for number of messages");
			}
		}
		// method call to test multiple spies and agents
		multiMsgTest(numMessages);
	}
	
	public static void oneMsgTest() throws InterruptedException{
        
		// new espionage for one msg test
		Espionage agent=new Espionage("agent",0);
		
		System.out.println("START: Single agent and single spy");
		// pass message through spy
		Espionage spy=new Espionage("spy",0);
        
		// agent receives message
		agent.start();
		spy.start();
        
		System.out.println("END: Single agent and single spy");
		
	}
    
	public static void multiMsgTest(int numAgents) throws InterruptedException{
        
		// num of agents = num of spies
		Espionage[] agent=new Espionage[numAgents];
		Espionage[] spy=new Espionage[numAgents];
        
		System.out.println("START: Multi-agent and multi-spy");
		System.out.println("Number of agents and spies is "+numAgents);
        
		for(int i=0;i<spy.length;i++){
			// create a spy and agent for each element of array
			spy[i]=new Espionage("spy", i+1);
			agent[i]=new Espionage("agent", i+1);
		}
		
		for(int i=0;i<spy.length;i++){
			agent[i].start();
			spy[i].start();
		}
		System.out.println("END: Multi-agent and multi-spy");
        
		
	}
    
}
