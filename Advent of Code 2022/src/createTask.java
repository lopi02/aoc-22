

import java.io.File;
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
		
		// create the task runner
		createTaskRunner(daynumber, "src/");
		
		
		

	}
	static void createClass(String daynumber, String path) throws FileNotFoundException {

		String filename = "src/Day" + daynumber + ".java";
		// exists already a class?
		File file = new File(filename);
		if (file.exists()) {
		   return;
		}
		PrintStream out = new PrintStream(filename);
		
		String basecode = 
				+ "import java.util.Scanner;\n"
				+ "\n"
				+ "public class Day0 implements AoCTask {\n"
				+ "	\n"
				+ "	int n = 100; // input size\n"
				+ "	\n"
				+ "	int[] input = new int[n];\n"
				+ "	\n"
				+ "	@Override\n"
				+ "	public void readInput(Scanner scan) {\n"
				+ "		for(int i = 0; i < n; i++) {\n"
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
				+ "};
		out.println(basecode);
		out.close();
	}
	static void createInput(String daynumber, String path) throws FileNotFoundException {
		String filename = path + "Day" + daynumber + "";
		
		// exists already an input?
		File file = new File(filename);
		if (file.exists()) {
		   return;
		}
		PrintStream out = new PrintStream(filename);
		String basecode = "";
		System.out.println(basecode);
		out.println(basecode);
		out.close();
	}
	static void createTaskRunner(String daynumber, String path) throws FileNotFoundException {
		String filename = "src/TaskRunner.java";
		PrintStream out = new PrintStream(filename);
		int number = Integer.valueOf(daynumber);
		
		String classes = "";
		
		for (int i = 1; i <= number; i++) {
			if(i == 1) classes = "new Day01()";
			else if (i < 10) classes = classes + ", new Day0" + i + "()";
			else classes = classes + ", new Day" + i + "()";
		}
		
		String basecode = "import java.io.File;\n"
				+ "import java.io.FileNotFoundException;\n"
				+ "import java.util.Scanner;\n"
				+ "\n"
				+ "public class TaskRunner {\n"
				+ "\n"
				+ "	static AoCTask[] tasks = new AoCTask[] { \n"
				+ classes 
				+ "\n"
				+ "											};\n"
				+ "	\n"
				+ "	public static void main(String[] args) throws FileNotFoundException {\n"
				+ "		for(AoCTask task : tasks) {			\n"
				+ "			Scanner input = null;\n"
				+ "			try {\n"
				+ "				input = new Scanner(new File(\"inputs/\" + task.getClass().getName()));\n"
				+ "				System.out.println(\"---- \" + task.getClass().getName().replace(\"Day\", \"Day \") + \": ----\");\n"
				+ "			} catch(FileNotFoundException e) {\n"
				+ "				continue;\n"
				+ "			}\n"
				+ "			\n"
				+ "			task.readInput(input);\n"
				+ "			System.out.println(\"Task1:\\t\" + task.task1());\n"
				+ "			System.out.println(\"Task2:\\t\" + task.task2());\n"
				+ "			System.out.println();\n"
				+ "		}\n"
				+ "	}\n"
				+ "\n"
				+ "}";
		out.println(basecode);
		out.close();
	}
}
