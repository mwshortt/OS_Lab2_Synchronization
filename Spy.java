public class Spy extends Thread{
	
	Espionage espionage;
	public Spy(Espionage espionage){
		this.espionage= espionage;
	}
	
	public void sendMessage(String msg) throws InterruptedException{
		try {
			espionage.dropSpyMsg(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
