package tm;

public class MachineLibrary {

	
	public static TuringMachine DocumentChecker()
	{
		//Terms we are looking for, machine library
		String key_term1 = "license"; 
		String key_term2 = "registration"; 
		String key_term3 = "insurance"; 
		String key_term4 = "fitness"; 
		
		
		//Create TM
		TuringMachine TM = new TuringMachine();
		
		
		//Add states q0 to q8 
		for(int x = 0; x <= 8; x++)
			{
				String state = "q";
				state = state + x;
				TM.addState(state);
					
				if(x == 0)
				TM.setStartState(state);
			}
		
		
		//Adds states for the acceptance of first key term
				for(int t = 1; t <= key_term1.length(); t++)
				{
					String state = "qKT1";
					state = state + t;
					TM.addState(state);
				}

		//Adds states for the acceptance of second key term
				for(int t = 1; t <= key_term2.length(); t++)
				{
					String state = "qKT2";
					state = state + t;
					TM.addState(state);
				}
				
				//Adds states for the acceptance of key term 3
				for(int t = 1; t <= key_term3.length(); t++)
				{
					String state = "qKT3";
					state = state + t;
					TM.addState(state);
				}
				
				//Adds states for the acceptance of key term 4
				for(int t = 1; t <= key_term4.length(); t++)
				{
					String state = "qKT4";
					state = state + t;
					TM.addState(state);
				}
		
		TM.addState("qaccept");//accept state
		TM.addState("qcheck"); //check that all keywords have been found
		TM.addState("qchecka"); //check before accepting
		TM.addState("qreject");//reject state
		
		TM.addState("q4-1a"); //scans the string once kw1 has been marked off to go to q4-1z
		TM.addState("q4-1s"); //scans the string if a L blocks q4's transition 
		TM.addState("q4-1r"); //reverses to the original string from beyond '_'
		TM.addState("q4-1z"); //sets L at the end of the string to confirm key term 1 has been marked off
		
		TM.addState("q4-2a"); //scans the string once kw2 has been marked off to go to q4-2z
		TM.addState("q4-2r"); //reverses to the original string from beyond '_'
		TM.addState("q4-2s"); //scans the string if a L blocks q4's transition
		TM.addState("q4-2z"); //sets L at the end of the string to confirm kw2 has been marked off
		
		TM.addState("q4-3a"); //scans the string once kw3 has been marked off to go to q4-3z
		TM.addState("q4-3r"); //reverses to the original string from beyond '_'
		TM.addState("q4-3s"); //scans the string if a L blocks q4's transition
		TM.addState("q4-3z"); //sets L at the end of the string to confirm kw3 has been marked off
		
		TM.addState("q4-4a"); //scans the string once kw3 has been marked off to go to q4-4z
		TM.addState("q4-4r"); //reverses to the original string from beyond '_'
		TM.addState("q4-4s"); //scans the string if a L blocks q4's transition
		TM.addState("q4-4z"); //sets L at the end of the string to confirm kw4 has been marked off
		
		TM.addState("qrev1"); //reverses for marking kw1
		TM.addState("qrev2"); //reverses for marking kw2
		TM.addState("qrev22"); 
		TM.addState("qrev3"); //reverses for marking kw3
		TM.addState("qrev4"); //reverses for marking kw4
		
		TM.addState("qrev1a"); //marks end of marking for keyword1
		TM.addState("qrev2a"); //marks end of marking for keyword2
		TM.addState("qrev3a"); //marks end of marking for keyword3
		TM.addState("qrev4a"); //marks end of marking for keyword4
		
		
		
		
		
		//Setting accept and reject states
		TM.setAcceptState("qaccept");
		TM.setRejectState("qreject");
		
		//actual machine starts here
				//q0 gets the start symbol and transitions to q1
				TM.addTransition("q0", '>', "q1", '>', true );
				
				//verifies that the string is in the language, and sends to q4 where q4 will start to search for the keywords
				TM.addTransition("q1", '0' , "q1", '0' , true );
				TM.addTransition("q1", '\'', "q1", '\'', true );
				TM.addTransition("q1", '_' , "q2", '_' , false);
				TM.addTransition("q2", '0' , "q2", '0' , false);
				TM.addTransition("q2", '\'', "q2", '\'', false);
				TM.addTransition("q2", '>' , "q3", '>' , false);
				TM.addTransition("q3", '>' , "q4", '>' , true);
				
				
				//Use 97 & 122 to find letters of alphabet a - z
				for(int i = 97; i <= 122; i++)
				{
					TM.addTransition("q1", (char) i, "q1", (char) i, true );
					TM.addTransition("q2", (char) i, "q2", (char) i, false);
				}
				
				//The search for keywords begin here
				//for loop to facilitate all members of the alphabet, starts at 96 to allow manipulation of the counter variable
				for(int i = 97; i <= 122; i++)
				{	
					//q0 will search for "license", sub set of states will be 7 sub-states			
					if((char) i == 'l' )
						TM.addTransition("q0" , 'l' , "q1" , 'l' , true );
					else
						TM.addTransition("q1" , (char) i , "q0"  	 , (char) i , true );
					if((char) i == 'i' )
						TM.addTransition("q2", 'i' , "q3" , 'i' 		, true );
					else
						TM.addTransition("qkw1-10", (char) i , "q1"  	 , (char) i , true );
					if((char) i == 'c' )
						TM.addTransition("qkw1-11", 'c'      , "qkw1-12" , 'c' 		, true );
					else
						TM.addTransition("qkw1-11", (char) i , "q1"  	 , (char) i , true );
					if((char) i == 'e' )
						TM.addTransition("qkw1-12", 'e'      , "qkw1-13" , 'e' 		, true );
					else
						TM.addTransition("qkw1-12", (char) i , "q1"  	 , (char) i , true );
					if((char) i == 'n' )
						TM.addTransition("qkw1-13", 'n'      , "qkw1-14" , 'n' 	 	, true );
					else
						TM.addTransition("qkw1-13", (char) i , "q1"  	 , (char) i , true );
					if((char) i == 's' )
						TM.addTransition("qkw1-14", 's'      , "qkw1-15" , 's'      , true );
					else
						TM.addTransition("qkw1-14", (char) i , "q1"      , (char) i , true );
					
					
					
					
					if((char) i == 'e' )
					{
						TM.addTransition("qkw1-15" , 'e'     , "qrev1"   , '1'		, false);			
						TM.addTransition("qrev1"   , 's'     , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'n' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'e' 	 , "qrev1"   , '1' 		, false);  
						TM.addTransition("qrev1"   , 'c' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'i' 	 , "qrev1"   , '1'		, false);
						TM.addTransition("qrev1"   , 'l' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , '0' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 's' 	 , "qrev1"   , '1' 		, false); 
						TM.addTransition("qrev1"   , '\''	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'r' 	 , "qrev1"   , '1' 		, false); 
						TM.addTransition("qrev1"   , 'e' 	 , "qrev1"   , '1' 		, false); 
						TM.addTransition("qrev1"   , 'v' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'i' 	 , "qrev1"   , '1' 		, false); 
						TM.addTransition("qrev1"   , 'r' 	 , "qrev1"   , '1' 		, false);
						TM.addTransition("qrev1"   , 'd' 	 , "qrev1a"  , '1' 		, false);
						TM.addTransition("qrev1a"  , '0' 	 , "q4-1a"   , '0' 		, true );
						TM.addTransition("qrev1a"  , '»' 	 , "q4-1a"   , '»' 		, true );
						TM.addTransition("q4-1a"   , (char) i, "q4-1a"   , (char) i , true );
						TM.addTransition("q4-1a"   , '_' 	 , "q4-1z"   , '_' 	 	, true );
						TM.addTransition("q4-1z"   , '1' 	 , "q4-1z"   , 'x' 		, true );
						TM.addTransition("q4-1z"   , '2' 	 , "q4-1z"   , '2' 		, true );
						TM.addTransition("q4-1z"   , '3' 	 , "q4-1z"   , '3' 		, true );
						TM.addTransition("q4-1z"   , '4' 	 , "q4-1z"   , '4' 		, true );
						TM.addTransition("q4-1z"   , 'x' 	 , "q4-1z"   , 'y' 		, true );
						TM.addTransition("q4-1z"   , 'y' 	 , "q4-1z"   , 'y' 		, true );
						TM.addTransition("q4-1r"   , 'y' 	 , "q4-1r"   , 'x' 		, false); 
						TM.addTransition("q4-1z"   , '_' 	 , "q5"   	 , '1' 		, true );
					}
					else
						TM.addTransition("qkw1-15" , (char) i, "q4"      , (char) i , true );
					
					//qkw2 will search for "registration", subset of states will be 12 sub-states 
					if((char) i == 'r' )
						TM.addTransition("q4"     , 'r'		, "qkw2-1" 	, 'r'	  , true);
					if((char) i == 'e' )
						TM.addTransition("qkw2-1" , 'e'		, "qkw2-2" 	, 'e'	  , true);
					else
						TM.addTransition("qkw2-1" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'g' )
						TM.addTransition("qkw2-2" , 'g'		, "qkw2-3" 	, 'g'	  , true);
					else
						TM.addTransition("qkw2-2" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'i' )
						TM.addTransition("qkw2-3" , 'i'		, "qkw2-4" 	, 'i'	  , true);
					else
						TM.addTransition("qkw2-3" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 's' )
						TM.addTransition("qkw2-4" , 's'		, "qkw2-5" 	, 's'	  , true);
					else
						TM.addTransition("qkw2-4" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 't' )
						TM.addTransition("qkw2-5" , 't'		, "qkw2-6" 	, 't'	  , true);
					else
						TM.addTransition("qkw2-5" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'r' )
						TM.addTransition("qkw2-6" , 'r'		, "qkw2-7" 	, 'r'	  , true);
					else
						TM.addTransition("qkw2-6" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'a' )
						TM.addTransition("qkw2-7" , 'a'		, "qkw2-8" 	, 'a'	  , true);
					else
						TM.addTransition("qkw2-7" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 't' )
						TM.addTransition("qkw2-8" , 't'		, "qkw2-9" 	, 't'	  , true);
					else
						TM.addTransition("qkw2-8" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'i' )
						TM.addTransition("qkw2-9" , 'i'		, "qkw2-10" , 'i'	  , true);
					else
						TM.addTransition("qkw2-9" , (char) i, "q4" 		, (char) i, true);
					if((char) i == 'o' )
						TM.addTransition("qkw2-10", 'o'		, "qkw2-11"	, 'o'	  , true);
					else
						TM.addTransition("qkw2-10", (char) i, "q4" 	  	, (char) i, true);
					if((char) i == 'n' )
					{
						TM.addTransition("qkw2-11", 'n'		, "qrev2" , '2'		, false);				
						TM.addTransition("qrev2"  , 'o'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'i'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 't'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'a'		, "qrev22", '2'		, false);
						TM.addTransition("qrev22" , 'r'		, "qrev2" , '2'		, false); 
						TM.addTransition("qrev2"  , 't'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 's'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'i'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'g'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'e'		, "qrev2" , '2'		, false);
						TM.addTransition("qrev2"  , 'r'		, "qrev2a", '2'		, false);
						TM.addTransition("qrev2a" , '0'		, "q4-2a" , '0'		, true );				
						TM.addTransition("qrev2a" , '»'		, "q4-2a" , '»'		, true );
						TM.addTransition("q4-2a"  , (char) i, "q4-2a" , (char) i, true );
						TM.addTransition("q4-2a"  , '_' 	, "q4-2z" , '_' 	, true );
						TM.addTransition("q4-2z"  , '1' 	, "q4-2z" , '1' 	, true );
						TM.addTransition("q4-2z"  , '2' 	, "q4-2z" , 'x' 	, true );
						TM.addTransition("q4-2z"  , '3' 	, "q4-2z" , '3' 	, true );
						TM.addTransition("q4-2z"  , '4' 	, "q4-2z" , '4' 	, true );
						TM.addTransition("q4-2z"  , 'x' 	, "q4-2z" , 'y' 	, true );
						TM.addTransition("q4-2z"  , 'y' 	, "q4-2z" , 'y' 	, true );
						TM.addTransition("q4-2r"  , 'y' 	, "q4-2r" , 'x' 	, false); 
						TM.addTransition("q4-2z"  , '_' 	, "q5"    , '2' 	, true );
					}
					else
						TM.addTransition("qkw2-11", (char) i, "q4" 	  , (char) i, true );

					
					
					//qkw3- will search for "insurance", subset of states will be 9 sub-states
					if((char) i == 'i' )
						TM.addTransition("q4"     , 'i' 	, "qkw3-1", 'i'		, true );
					if((char) i == 'n' )
						TM.addTransition("qkw3-1" , 'n' 	, "qkw3-2", 'n'		, true );
					else
						TM.addTransition("qkw3-1" , (char) i, "q4"	  , (char) i, true );
					if((char) i == 's' )
						TM.addTransition("qkw3-2" , 's' 	, "qkw3-3", 's'		, true );
					else
						TM.addTransition("qkw3-2" , (char) i, "q4" 	  , (char) i, true );
					if((char) i == 'u' )
						TM.addTransition("qkw3-3" , 'u' 	, "qkw3-4", 'u'		, true );
					else
						TM.addTransition("qkw3-3" , (char) i, "q4"	  , (char) i, true );
					if((char) i == 'r' )
						TM.addTransition("qkw3-4" , 'r' 	, "qkw3-5", 'r'		, true );
					else
						TM.addTransition("qkw3-4" , (char) i, "q4" 	  , (char) i, true );
					if((char) i == 'a' )
						TM.addTransition("qkw3-5" , 'a' 	, "qkw3-6", 'a'		, true );
					else
						TM.addTransition("qkw3-5" , (char) i, "q4" 	  , (char) i, true );
					if((char) i == 'n' )
						TM.addTransition("qkw3-6" , 'n' 	, "qkw3-7", 'n'		, true );
					else
						TM.addTransition("qkw3-6" , (char) i, "q4" 	  , (char) i, true );
					if((char) i == 'c' )
						TM.addTransition("qkw3-7" , 'c' 	, "qkw3-8", 'c'		, true );
					else
						TM.addTransition("qkw3-7" , (char) i, "q4" 	  , (char) i, true );
					if((char) i == 'e' )
					{
						TM.addTransition("qkw3-8" , 'e' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'c' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'n' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'a' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'r' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'u' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 's' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'n' 	, "qrev3" , '3'		, false);
						TM.addTransition("qrev3"  , 'i' 	, "qrev3a", '3'		, false);
						TM.addTransition("qrev3a" , '0' 	, "q4-3a" , '0'		, true );
						TM.addTransition("qrev3a" , '»' 	, "q4-3a" , '»'		, true );
						TM.addTransition("q4-3a"  , (char) i, "q4-3a" , (char) i, true );
						TM.addTransition("q4-3a"  , '_' 	, "q4-3z" , '_'     , true );
						TM.addTransition("q4-3z"  , '1' 	, "q4-3z" , '1'     , true );
						TM.addTransition("q4-3z"  , '2' 	, "q4-3z" , '2'     , true );
						TM.addTransition("q4-3z"  , '3' 	, "q4-3z" , 'x' 	, true );
						TM.addTransition("q4-3z"  , 'x' 	, "q4-3z" , 'y' 	, true );
						TM.addTransition("q4-3z"  , 'y' 	, "q4-3z" , 'y' 	, true );
						TM.addTransition("q4-3z"  , '4' 	, "q4-3z" , '4' 	, true );
						TM.addTransition("q4-3r"  , 'y' 	, "q4-3r" , 'x' 	, false); 
						TM.addTransition("q4-3z"  , '_' 	, "q5"    , '3' 	, true );
					}
					else
						TM.addTransition("qkw3-8" , (char) i, "q4" 	  , (char) i, true );
					
					
					//qkw4 will search for "fitness", subset of states will be 7 sub-states
					if((char) i == 'f' )
						TM.addTransition("q4"    , 'f'	   , "qkw4-1", 'f'	   , true );
					if((char) i == 'i')
						TM.addTransition("qkw4-1", 'i'     , "qkw4-2", 'i'	   , true );
					else
						TM.addTransition("qkw4-1", (char) i, "q4" 	 , (char) i, true );
					if((char) i == 't')
						TM.addTransition("qkw4-2", 't'	   , "qkw4-3", 't'	   , true );
					else
						TM.addTransition("qkw4-2", (char) i, "q4" 	 , (char) i, true );
					if((char) i == 'n')
						TM.addTransition("qkw4-3", 'n'     , "qkw4-4", 'n'	   , true );
					else
						TM.addTransition("qkw4-3", (char) i, "q4" 	 , (char) i, true );
					if((char) i == 'e')
						TM.addTransition("qkw4-4", 'e'	   , "qkw4-5", 'e'	   , true );
					else
						TM.addTransition("qkw4-4", (char) i, "q4" 	 , (char) i, true );
					if((char) i == 's')
						TM.addTransition("qkw4-5", 's'	   , "qkw4-6", 's'	   , true );
					else
						TM.addTransition("qkw4-5", (char) i, "q4" 	 , (char) i, true );
					if((char) i == 's'){
						TM.addTransition("qkw4-6", 's'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 's'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 'e'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 'n'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 't'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 'i'	   , "qrev4" , '4'	   , false);
						TM.addTransition("qrev4" , 'f'	   , "qrev4a", '4'	   , false);
						TM.addTransition("qrev4a", '0'	   , "q4-4a" , '0'	   , true );
						TM.addTransition("qrev4a", '»'	   , "q4-4a" , '»'	   , true );
						TM.addTransition("q4-4a" , (char) i, "q4-4a" , (char) i, true );
						TM.addTransition("q4-4a" , '_'	   , "q4-4z" , '_' 	   , true );
						TM.addTransition("q4-4z" , '1'	   , "q4-4z" , '1' 	   , true );
						TM.addTransition("q4-4z" , '2'	   , "q4-4z" , '2' 	   , true );
						TM.addTransition("q4-4z" , '3'	   , "q4-4z" , '3' 	   , true );
						TM.addTransition("q4-4z" , '4'	   , "q4-4z" , 'x' 	   , true );
						TM.addTransition("q4-4z" , 'x'	   , "q4-4z" , 'y' 	   , true );
						TM.addTransition("q4-4z" , 'y'	   , "q4-4z" , 'y'     , true );
						TM.addTransition("q4-4z" , '_' 	   , "q5"    , '4'	   , true );
					}
					else
						TM.addTransition("qkw4-6", (char) i, "q4" 	 , (char) i, true );

					//q4 begins here, where it'll start to process the string and look for the key words.
					TM.addTransition("q4", (char) i, "q4", (char) i, true);
					
					//Ensures q4 transitions to the correct scan states for the kw's
					TM.addTransition("q4", '1', "q4-1s", '1', true);
					TM.addTransition("q4", '2', "q4-2s", '2', true);
					TM.addTransition("q4", '3', "q4-3s", '3', true);
					TM.addTransition("q4", '4', "q4-4s", '4', true);
					
					//Acceptance markers and scan transitions for kw1
					TM.addTransition("q4-1a", '1'     , "q4-1a", '1'     , true);
					TM.addTransition("q4-1a", (char) i, "q4-1a", (char) i, true);
					TM.addTransition("q4-1s", '1'     , "q4-1s", '1'     , true);
					TM.addTransition("q4-1s", '0'     , "q4"   , '0'     , true);
					
					//Acceptance markers and scan transitions for kw2
					TM.addTransition("q4-2a", '2'     , "q4-2a", '2'     , true);
					TM.addTransition("q4-2a", (char) i, "q4-2a", (char) i, true);
					TM.addTransition("q4-2s", '2'     , "q4-2s", '2'     , true);
					TM.addTransition("q4-2s", '0'     , "q4"   , '0'     , true);
					
					//Acceptance markers and scan transitions for kw3
					TM.addTransition("q4-3a", '3'     , "q4-3a", '3'     , true);
					TM.addTransition("q4-3s", '3'     , "q4-3s", '3'     , true);
					TM.addTransition("q4-3s", '0'     , "q4"   , '0'     , true);
					TM.addTransition("q4-3a", (char) i, "q4-3a", (char) i, true);
					
					//Acceptance markers and scan transitions for kw4
					TM.addTransition("q4-4a", '4'     , "q4-4a", '4'     , true);
					TM.addTransition("q4-4s", '4'     , "q4-4s", '4'     , true);
					TM.addTransition("q4-4s", '0'     , "q4"   , '0'     , true);
					TM.addTransition("q4-4a", (char) i, "q4-4a", (char) i, true);
					
					//q5 is a reverse state, ensures the tape goes right back to the beginning
					TM.addTransition("q5", (char) i, "q5", (char) i, false);
					
					//Ensures q5 keeps the tape going left
					TM.addTransition("q5", '_', "q5", '_', false);
					TM.addTransition("q5", '1', "q5", '1', false);
					TM.addTransition("q5", '2', "q5", '2', false);
					TM.addTransition("q5", '3', "q5", '3', false);
					TM.addTransition("q5", '4', "q5", '4', false);
					
					//Transitions to q4 once the start symbol has been read
					TM.addTransition("q5", '»', "q4", '»', true );
					
					if(i == 39) //Ensures transitions for '\'' are captured
						i = 47; //i will increment, thus allowing 0 to form its necessary transitions
					
					if(i == 48) //when i is 0, it will reset the loop to the correct number
						i = 96; //i will increment, thus allowing correct flow of the loop
				}
				
				//Checking if the string can be accepted from here below
				
				//Once q4 successfully reads the end of string character, it'll transition to 'qca' (Check accept)
				TM.addTransition("q4", '_', "qca", '_', true); 
				
				//States below ensure that at LEAST 1 of each key word was read before accepting
				//'x' and 'y' represents more than one instances of keywords after '_'
				TM.addTransition("qca", 'x', "qca", 'x', true);
				TM.addTransition("qca", 'y', "qca", 'y', true);
				TM.addTransition("q6" , 'x', "q6" , 'x', true);
				TM.addTransition("q6" , 'y', "q6" , 'y', true);
				TM.addTransition("q7" , 'x', "q7" , 'x', true);
				TM.addTransition("q7" , 'y', "q7" , 'y', true);
				TM.addTransition("q8" , 'x', "q8" , 'x', true);
				TM.addTransition("q8" , 'y', "q8" , 'y', true);
				
				//This for loop is in position so the order of the keywords do not matter.
				for(int i = 49; i <= 52; i++)
				{
					TM.addTransition("qca", (char) i, "q6", (char) i, true);
					TM.addTransition("q6" , (char) i, "q7", (char) i, true);
					TM.addTransition("q7" , (char) i, "q8", (char) i, true);
					TM.addTransition("q8" , (char) i, "qc", (char) i, true);		
				}
				
				//Once 'qc' (Check) is reached, it SHOULD read only one more character, which is the end of string character '_'
				TM.addTransition("qc", '_', "qa", '_', true);
				
			
		
		
		
		
		
		
		
		return TM;		
	}


}
