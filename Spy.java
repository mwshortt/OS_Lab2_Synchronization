public class Spy extends Thread{
	
	public void sendMessage(String msg, Espionage espionage) throws InterruptedException{
		
		try {
			// use espionage to drop message
			espionage.dropSpyMsg(msg);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
