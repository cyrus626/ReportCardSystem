package ReportCardSystem;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Student Report Card System.
		String choice;
		do {
			intro();
			choice = input.nextLine();
			switch (choice) {
			case "1":
				// Add Student.
				AddStudent();
				
				break;
			case "2":
				Student.DisplayStudent();
				//Student.DisplayStudent();
				break;
			case "3":
				
				String studentId = messagePrompt("Enter student Id");
				
				SearchStudentById(studentId.toUpperCase());
				break;
			case "4":
				messagePrompt("Enter StudentId or Name");
				String filePath = "C:\\Users\\Staff\\Desktop\\Java programs\\FilePractice\\ReportCard.txt";
				try {
					FileWriter writer = new FileWriter(filePath);
					writer.write(Student.AllStudents + "\n" + Student.AllSubjects);
					
					writer.close();
				}catch(IOException e) {
					println("Error storing in file");
					e.printStackTrace();
				}
				
				break;
			case "5":
				print("Exiting application");
				break;
			default:
				println("You've entered incorrect option\nPress enter to continue");
			}
		}while(!choice.equals("5"));
	}
	
	static void SearchStudentById(String searchId) {
		
		for(String name: Student.AllStudents.keySet()) {
			String id = Student.AllStudents.get(name);
			if(id.equals(searchId)) {
				print(name);
				Student.DisplayScore(name, id);
				break;
			}
		}
		messagePrompt("Press enter to continue");
	}
	
	static void AddStudent() {
		String name = messagePrompt("Enter your name: ");
		String subjectTitle = messagePrompt("Enter Subject: ");
		double score = Double.parseDouble(messagePrompt("Enter Score: "));
		Student student = new Student(name, subjectTitle, score);
	}	
	
	static String messagePrompt(String message) {
		print(message);
		return input.nextLine();
	}
	
	static void intro() {
		println("==========STUDENT REPORT CARD SYSTEM==========");
		println("1. Add Student");
		println("2. Display All Students");
		println("3. Search Student by ID");
		println("4. Save to file");
		println("5. Exit");
		println("==============================================");
		print("Enter your choice: ");
	}
	
	public static void print(String text) {
		System.out.print(text);
	}
	
	public static void println(String text) {
		System.out.println(text);
	}
}