Group Members: Madeline Shortt and Mina Khan

Instructions for running programs: 

Compile and run the Thirsty Persons Problem with the following commands:
javac ThirstyPersons.java
java ThirstyPersons

Compile and run the Lazy Dentist Problem with the following commands where N is  the number of patients as an integer 
javac LazyDentist.java
java LazyDentist N

Compile and run Problem # 3 with the following commands:
javac Espionage.java
java Espionage

The default number of messages in multi-agent/spy test is 3. Thus, "java Espionage
" would give the following output:

START: Single agent and single spy
Starting agent: 0
Agent: 0 is waiting
END: Single agent and single spy
Starting spy: 0
Spy: 0 is leaving message: Message: 0
Agent: 0 is picking message: Message: 0

START: Multi-agent and multi-spy
Number of agents and spies is 3
Starting agent: 1
Agent: 1 is waiting
Starting spy: 1
Spy: 1 is leaving message: Message: 1
Agent: 1 is picking message: Message: 1
Starting agent: 2
Agent: 2 is waiting
Starting spy: 2
Spy: 2 is leaving message: Message: 2
END: Multi-agent and multi-spy
Agent: 2 is picking message: Message: 2
Starting agent: 3
Agent: 3 is waiting
Starting spy: 3
Spy: 3 is leaving message: Message: 3
Agent: 3 is picking message: Message: 3

You may give a valid non-zero number of agents for example "java Espionage 5”, which would give the following output:

START: Single agent and single spy
END: Single agent and single spy
Starting spy: 0
Spy: 0 is leaving message: Message: 0
Starting agent: 0
Agent: 0 is picking message: Message: 0

START: Multi-agent and multi-spy
Number of agents and spies is 5
Starting agent: 1
Agent: 1 is waiting
Starting spy: 1
Spy: 1 is leaving message: Message: 1
Starting agent: 2
Agent: 1 is picking message: Message: 1
Agent: 2 is waiting
Starting spy: 2
Spy: 2 is leaving message: Message: 2
Starting agent: 3
Agent: 2 is picking message: Message: 2
Starting spy: 3
Spy: 3 is leaving message: Message: 3
Starting agent: 4
Agent: 3 is picking message: Message: 3
Starting spy: 4
Spy: 4 is leaving message: Message: 4
END: Multi-agent and multi-spy
Starting agent: 5
Agent: 4 is picking message: Message: 4
Starting spy: 5
Spy: 5 is leaving message: Message: 5
Agent: 5 is picking message: Message: 5