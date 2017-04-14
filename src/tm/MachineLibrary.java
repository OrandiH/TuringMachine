package tm;

public final class MachineLibrary {
	private MachineLibrary() {
	}

	public static TuringMachine DocumentChecker()
	{
		//Create machine
		TuringMachine newTM = new TuringMachine();
		//Add states
		newTM.addState("q0");
		newTM.addState("q1");
		newTM.addState("q2");
		newTM.addState("q3");
		newTM.addState("q4");
		newTM.addState("q5");
		newTM.addState("q6");
		newTM.addState("q7");
		newTM.addState("q8");
		newTM.addState("q9");
		newTM.addState("q10");
		newTM.addState("q11");
		newTM.addState("q12");
		newTM.addState("q13");
		newTM.addState("q14");
		newTM.addState("q15");
		newTM.addState("q16");
		newTM.addState("q17");
		newTM.addState("q18");
		newTM.addState("q19");
		newTM.addState("q20");
		newTM.addState("q21");
		newTM.addState("q22");
		newTM.addState("q23");
		newTM.addState("q24");
		newTM.addState("q25");
		newTM.addState("q26");
		newTM.addState("q27");
		newTM.addState("q28");
		newTM.addState("q29");
		newTM.addState("q30");
		newTM.addState("q31");
		newTM.addState("q32");
		newTM.addState("q33");
		newTM.addState("q34");
		newTM.addState("q35");
		newTM.addState("q36");
		newTM.addState("q37");
		newTM.addState("q38");
		newTM.addState("q39");
		newTM.addState("q40");
		newTM.addState("q41");
		
		//Setting accept and reject states
		
		newTM.addState("qA");
		newTM.addState("qR");
		newTM.setStartState("q0");
		newTM.setAcceptState("qA");
		newTM.setRejectState("qR");
		
		
		//Adding transitions
		//Transition that searches for key words

		//Start search for key word insurance
		
		newTM.addTransition("q0", 'i', "q27", 'I', true);

		newTM.addTransition("q27", 'n', "q28", 'n', true);

		newTM.addTransition("q28", 's', "q29", 's', true);

		newTM.addTransition("q29", 'u', "q30", 'u', true);

		newTM.addTransition("q30", 'r', "q31", 'r', true);

		newTM.addTransition("q31", 'a', "q32", 'a', true);

		newTM.addTransition("q32", 'n', "q33", 'n', true);

		newTM.addTransition("q33", 'c', "q34", 'c', true);

		newTM.addTransition("q34", 'e', "q35", 'E', true);

		newTM.addTransition("q35", '_', "qA", '_', true);
		newTM.addTransition("qA", '_', "qA", '_', false);
			
		// Search for the keyword registration
		newTM.addTransition("q0", 'r', "q15", 'R', true);
		newTM.addTransition("q15", 'e', "q16", 'e', true);
		newTM.addTransition("q16", 'g', "q17", 'g', true);
		newTM.addTransition("q17", 'i', "q18", 'i', true);
		newTM.addTransition("q18", 's', "q19", 's', true);
		newTM.addTransition("q19", 't', "q20", 't', true);
		newTM.addTransition("q20", 'r', "q21", 'r', true);
		newTM.addTransition("q21", 'a', "q22", 'a', true);
		newTM.addTransition("q22", 't', "q23", 't', true);
		newTM.addTransition("q23", 'i', "q24", 'i', true);
		newTM.addTransition("q24", 'o', "q25", 'o', true);
		newTM.addTransition("q25", 'n', "q26", 'N', true);
		newTM.addTransition("q26", '_', "qA", '_', true);
		newTM.addTransition("qA", '_', "qA", '_', true);
		

	return newTM;
}

}