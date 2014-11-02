import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * System has 3 thirsty threads and one server thread
 * To drink, thread needs water, ice and a cup
 * One of the threads has water, another has ice and a third has a cup
 * Server has an infinite supply of all three
 * Server places two ingredients randomly on the table:
 * Thread that has the remaining ingredient prepares and has a drink, 
 * signally the server on completion. 
 * Then the server puts out another two ingredients at random.
 * 
 * Watch which drinkers are currently drinking in the console!
 * 
 * @author mshortt and mkhan
 *
 * Answers to the blanks:
 * 
 * Server blanks
 * 1. Wait( server )
 * 2. Signal( a[k] )
 * 
 * Drinker blanks
 * 3. Wait( a[r] )
 * 4. Signal( server )
 *
 */
public class ThirstyPersons {
	//Declare semaphores
	
	//Semaphores for ingredients: a[0] water, a[1] ice, a[2] cup
	Semaphore[] a = new Semaphore[3];
	
	//Semaphore for server
	Semaphore server = new Semaphore(1);
	
	//Declare threads
	
	//Threads for drinkers: tD[0] water holder, tD[1] ice holder, tD[2] cup holder
	Thread[] tD = new Thread[3];
	
	//Thread for server
	Thread tS;
	
	/**
	 * The main method
	 * This method creates an instance of ThirstyPersons which launches 
	 * the server and the drinkers
	 * @param args
	 */
	public static void main(String[] args) {
		//create an instance of ThirstyPersons
		ThirstyPersons tp = new ThirstyPersons();
		//by creating an instance we are running the logic in ThirstyPersons
	}
	
	/**
	 * The constructor for ThirstyPersons
	 * This method launches all the logic and sets up the server and the drinkers
	 */
	public ThirstyPersons() {
		//Initialize ingredient semaphores to all be 0 initially
		for(int i=0; i<=2; i++)
		{
			a[i] = new Semaphore(0);
		}
		
		//initialize drinkers
		for(int i=0; i<=2; i++)
		{
			initDrinkers(i);
		}
		
		//start drinkers
		for(int i=0; i<=2; i++)
		{
			tD[i].start();
		}
		
		//initialize server
		initServer();
		
		//start server
		tS.start();
	}
	
	/**
	 * Initializes the server's thread
	 */
	public void initServer(){
		tS = new Thread() {
			public void run() {
				int i;
				int j;
				int k;
				Random random = new Random();
				while(true) {
					//for i get a random integer between 0 and 2 inclusive
					i = random.nextInt(3);
					//for j get a random integer between 0 and 2 inclusive
					j = random.nextInt(3);
					//i and j must be different
					if(i!=j)
					{
						try {
							//if the server semaphore is free it means that the drinker
							//is finished drinking so the server can put out new ingredients
							server.acquire();
							//identify the drinker with the k-th ingredient
							//this is the drinker that has the third ingredient not present 
							//on the table
							k = 3-(i+j);
							//we signal the drinker with the k-th ingredient because this
							//drinker can now drink because they can have all three ingredients
							a[k].release();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
	}
	
	/**
	 * Initializes the drinkers' threads
	 * 
	 * @param ingredient - the ingredient that the drinker is holding
	 */
	public void initDrinkers(final int ingredient){
		//initialize the tread for the drinker that is holding the passed in ingredient
		tD[ingredient] = new Thread() {
			public void run() {
				while(true) {
					try {
						//if the semaphore for this drinker's ingredient is free
						//it means that the other two ingredients needed to drink
						//are available!
						a[ingredient].acquire();
						//critical section
						drink(ingredient);
						//signal to the server that the drinker is done drinking
						//by releasing the server semaphore
						server.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	/**
	 * This method prints out which drinker is currently drinking 
	 * allowing the user to watch the progression of the program 
	 * 
	 * @param ingredient - the ingredient of the drinker that is currently drinking
	 */
	public void drink(int ingredient){
		//String to hold what kind of drinker this is
		String drinker = "";
		//Figure out which drinker this is
		switch(ingredient) {
		case 0:
			drinker = "water";
			break;
		case 1:
			drinker = "ice";
			break;
		case 2: 
			drinker = "cup";
			break;
		}
		//Print out which drinker is currently drinking
		System.out.println("The drinker that is holding the "+drinker+" is currently drinking!");
	}
	
}
