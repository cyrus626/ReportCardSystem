package ReportCardSystem;
import java.util.ArrayList;
import java.util.HashMap;
class Student {
	// Properties of student
	public String Id;
	public String Name;
	
	// Storage for all records studentId and StudentSubjects
	static HashMap<String, ArrayList<Subject>> AllSubjects = new HashMap<String, ArrayList<Subject>>();
	
	// Storage for all Students and their ID AllStudents<Name, Id>
	static HashMap<String, String> AllStudents = new HashMap<String, String>();
	
	// The beginning of generating Id number
	private static int idNumber = 0;
	
	public Student(String name, String subjectTitle, double score) {
		// Temporarily stores current or new student subjects here.
				ArrayList<Subject> studentSubjects;
		
		if(AllStudents.containsKey(name)) {
			Id = AllStudents.get(name); // Gets a student's Id
			Main.messagePrompt(name + ", you've registered before." + "\n press enter to continue"); // idNumber remains the same.
			
			// Get previously stored record
			studentSubjects = AllSubjects.get(Id);
			
		}else {// else create a new id
			// Creating Student Id number.
			idNumber = idNumber + 1;
			
			//generateId();
			if(idNumber < 10) {
				Id = "ST00" + idNumber; // for numbers between 0-9 an example of studentId would be ST001
			}else if(idNumber < 100) {
				Id = "ST0" + idNumber; // for numbers between 10-99 an example of studentId would be ST011
			}else {
				Id = "ST" + idNumber; // for numbers above 99 an example of studentId would be ST101
			}
			// populate the student record
			AllStudents.put(name, Id);
			
			// Create a new instance of studentSubjects
			studentSubjects = new ArrayList<Subject>();
			
		}
		
		// temporarily store student's subjects
		studentSubjects.add(new Subject(subjectTitle, score));
		
		// final insert
		AllSubjects.put(Id, studentSubjects);
		Main.println("Student saved successfully");
		Main.messagePrompt(name + ", your student id is: " + Id + "\n press enter to continue");
	}
	
	public static void DisplayStudent() {
		
		
		// Display information
		for(String name: AllStudents.keySet()) {
			
			String studentId = AllStudents.get(name);
			Main.println( name + ":");
			DisplayScore(name, studentId);
		}
		if(AllStudents.isEmpty()) {
			Main.println("No data entry, type 1 to enter data when asked 'Enter your choice'");
		}
		Main.print("Press enter to continue");
		Main.input.nextLine();
	}
	
	// Showing one student's record
	public static void DisplayScore(String studentName, String studentId) {
		double totalScore = 0;
		char grade;
		ArrayList<Subject> studentSubjects = AllSubjects.get(studentId);
		int noOfSubjects = studentSubjects.size();
		for(Subject subject: studentSubjects) {
			// Getting total Score
			totalScore += subject.Score;
			
			Main.println("\t" + subject.Name + ": " + subject.Score);
			
		}
		double averageScore = totalScore / noOfSubjects;
		if(averageScore > 70) {
			grade = 'A';
		}else if (averageScore > 60){
			grade = 'B';
		}else if (averageScore > 50){
			grade = 'C';
		}else if (averageScore > 40){
			grade = 'D';
		}else {
			grade = 'F';
		}
		Main.println("Total Score: " + totalScore + "\tAverage Score: " + averageScore + "\tGrade: " + grade);
	}

}