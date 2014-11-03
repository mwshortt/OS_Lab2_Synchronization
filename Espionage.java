public class Espionage {
	
	private String message;
	
	public Espionage(){
		this.message="";
	}
	
	public synchronized void dropSpyMsg(String message) throws InterruptedException{
		
		// wait until there is no previous message
		while(!this.message.equals("")){
			wait();
		}
		
		// A spy writes the message on a piece of paper 
		// and drops it at a predetermined location
		this.message=message;
	}
	
	public synchronized String agentPickMsg () throws InterruptedException{
		
		// wait until there is a message
		while(this.message.equals("")){
			wait();
		}

		// use the message that was updated in dropSpyMsg
		String returnMessage=this.message;

		//reset the message to empty
		this.message="";
		
		return returnMessage;
	}
}