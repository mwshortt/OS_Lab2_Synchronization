public class Agent extends Thread{
	
	public String getMessage(Espionage espionage) throws InterruptedException{
		try {

			// use espionage's synchronized method to get the message
			return espionage.agentPickMsg();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}