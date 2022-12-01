import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class createTask {
	public static void main(String[] args) throws FileNotFoundException {
		
		
		// Get the day
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
		LocalDate localDate = LocalDate.now();
		String daynumber = dtf.format(localDate);
		System.out.println(daynumber);
		
		// create the class for the day
		createClass(daynumber, "src/");
		
		// create the input file for the day 
		createInput(daynumber, "inputs/");
		
		
		

	}
	static void createClass(String daynumber, String path) throws FileNotFoundException {
		String filename = "src/Day" + daynumber + ".java";
		PrintStream out = new PrintStream(filename);
		
		String basecode = 
				"import java.util.Scanner;\n"
				+ "\n"
				+ "public class Day"
				+ daynumber 
				+ " implements AoCTask {\n"
				+ "	\n"
				+ "	int[] input = new int[2000];\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public void readInput(Scanner scan) {\n"
				+ "		for(int i = 0; i < 2000; i++) {\n"
				+ "			input[i] = scan.nextInt();\n"
				+ "		}\n"
				+ "	}\n"
				+ "\n"
				+ "	@Override\n"
				+ "	public String task1() {\n"
				+ "		return \"\";\n"
				+ "	}\n"
				+ "\n"
				+ "	@Override\n"
				+ "	public String task2() {\n"
				+ "		return \"\";\n"
				+ "	}\n"
				+ "\n"
				+ "}";
		out.println(basecode);
		out.close();
	}
	static void createInput(String daynumber, String path) throws FileNotFoundException {
		String filename = path + "Day" + daynumber + "";
		PrintStream out = new PrintStream(filename);
		
		String basecode = "";
		System.out.println(basecode);
		out.println(basecode);
		out.close();
	}
}
