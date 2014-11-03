public class Agent extends Thread{
	public Agent(Espionage espionage){
		this.espionage= espionage;		
	}
	
	Espionage espionage;
	
	public String getMessage() throws InterruptedException{
		try {
			return espionage.agentPickMsg();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}