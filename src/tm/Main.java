package tm;

public class Main {
	public static void main(String[] args) 
	{
		TuringMachine TM1 = MachineLibrary.DocumentChecker();
		String input = "registration";
		input = input.replaceAll("\\s+","");
		
		
		boolean done = TM1.Run(input, false);
		if (done==true)
		{
			System.out.println("The input was accepted.");
		}
		else
		{
			System.out.println("The input was rejected.");
		}
	}

}
