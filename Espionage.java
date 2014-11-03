public class Espionage {
	
	private String message;
	
	public Espionage(){
		this.message="";
	}
	
	public synchronized void dropSpyMsg(String message) throws InterruptedException{
		while(!this.message.equals("")){
			wait();
		}
		
		// A spy writes the message on a piece of paper 
		// and drops it at a predetermined location
		this.message=message;
	}
	
	public synchronized String agentPickMsg () throws InterruptedException{
		while(this.message.equals("")){
			wait();
		}
		String returnMessage=this.message;
		this.message="";
		return returnMessage;
	}
}