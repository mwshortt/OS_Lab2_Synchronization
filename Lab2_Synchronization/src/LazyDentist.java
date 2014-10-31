import java.util.concurrent.Semaphore;

/**
 * System has patient threads and a dentist thread
 * There are N chairs for waiting patients in the dental clinic. 
 * If there are no free chairs, the patient leaves. 
 * If the dentist is sleeping, the patient wakes her up and consults her. 
 * Otherwise the patient takes a seat a waits. 
 * If the dentist finishes with a patient and there are waiting patients, then 
 * she takes the next patient. If there are no more patients, then she goes to sleep. 
 * 
 * @author mshortt and mkhan
 *
 * Answers to the blanks:
 * 
 * Dentist blanks
 * 1. Wait( patientReady )
 * 2. Wait( seatCountWriteAccess )
 * 3. Signal( dentistReady )
 * 4. Signal( seatCountWriteAccess )
 * 
 * Customer blanks
 * 5. Wait( seatCountWriteAccess )
 * 6. Signal( patientReady )
 * 7. Signal( seatCountWriteAccess )
 * 8. Wait( dentistReady )
 * 9. Signal( seatCountWriteAccess )
 * 
 */
public class LazyDentist {

	//Declare semaphores
	
	//Semaphores for dentistReady and seatCountWriteAccess are mutexes (can only be 0 or 1)
	
	//Semaphore for the dentist
	Semaphore dentistReady = new Semaphore(0);
	
	//Semaphore for accessing the number of seats in the waiting room
	//if 1, the number of seats can be changed
	Semaphore seatCountWriteAccess = new Semaphore(1);
	
	//Semaphore for the number of waiting patients
	Semaphore patientReady = new Semaphore(0);
	
	//Instance variables
	
	//Total number of seats in the waiting room 
	//For this example we are assuming that this will always be 3
	int numberOfFreeWRSeats = 3;
	
	//An array of threads to hold the threads of the patients 
	Thread[] patients;
	
	//The thread of the dentist
	Thread dentist;
	
	/**
	 * The main method
	 * This method takes in the number of patients (as one integer) 
	 * and creates a new instance of the LazyDentist which calls all the logic
	 * for this exercise
	 * 
	 * We have tested this with 1 patient, 2 patients, 3 patients, and 5 patients
	 * 
	 * @param args - takes in one integer as the number of patients
	 */
	public static void main(String[] args) {
		//check to make sure that the appropriate number of parameters were passed in
		if(args.length != 1)
		{
			System.out.println("Please pass the number of patients as a parameter.");
			System.exit(0);
		}
		
		//local variable for the number of patients (use 1 as a default value)
		int numP = 1;
		
		//try to parse the argument as an integer and throw an exception if it is not one
		try {
			numP = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("Please pass the number of patients as an integer.");
			System.exit(0);
		}
		
		//creates an instance of LazyDentist with the number of patients passed 
		LazyDentist ld = new LazyDentist(numP);
	}
	
	/**
	 * Constructor for LazyDentist
	 * This method takes in the number of patients, initializes the dentist and the patient threads,
	 * and starts their execution
	 * 
	 * @param numPatients
	 */
	public LazyDentist(int numPatients) {
		//initialize dentist
		initDentist();
		//start dentist thread
		dentist.start();
		
		//initialize the length of the patient array
		patients = new Thread[numPatients];
		//initialize patients
		for(int i=0; i < patients.length; i++)
		{
			initPatient(i+1);
		}
		//start patient threads
		for(int i=0; i < patients.length; i++)
		{
			patients[i].start();
		}
	}
	
	/**
	 * This method initializes the dentist's thread
	 */
	public void initDentist() {
		dentist = new Thread(){
			public void run() {
				//run an infinite loop
				while(true) {
					try {
						//try to acquire a patient (if none is available, dentist remains sleeping)
						patientReady.acquire();
						//try to get access to modify the number of available seats (if cannot, remain sleeping)
						seatCountWriteAccess.acquire();
						//increase the number of chairs free because the dentist is going to see a patient
						numberOfFreeWRSeats += 1;
						//the dentist is now ready to consult! Wake up!
						dentistReady.release();
						//release the lock of the chairs
						seatCountWriteAccess.release();
						//write to console that the dentist is talking to a patient
						System.out.println("Dentist is talking to a patient.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	/**
	 * This method initializes the patients' threads, taking as a parameter the number of the patient
	 * @param patientNum - the number of the patient 
	 */
	public void initPatient(final int patientNum) {
		//the patient number starts at 1, so we have to subtract one for the index in the patient's thread array
		patients[patientNum-1] = new Thread() {
			public void run() {
				//run in an infinite loop to simulate multiple patients
				while(true) {
					try {
						//try to get access to the waiting room chairs
						seatCountWriteAccess.acquire();
						//if there are any free seats
						if( numberOfFreeWRSeats > 0)
						{
							//sit down in a chair, meaning there is one less available
							numberOfFreeWRSeats -= 1;
							//notify the dentist that there is a patient
							patientReady.release();
							//release the lock of the seats
							seatCountWriteAccess.release();
							//wait until the dentist is ready
							dentistReady.acquire();
							//write to console that this patient is consulting the dentist
							System.out.println("Patient "+patientNum+" is consulting with the dentist!");
						}
						//otherwise, if there are no free seats, then the patient leaves
						else
						{
							//but before leaving the patient must give up the lock of the seats
							seatCountWriteAccess.release();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

}
