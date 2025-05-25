package project_poo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Teacher extends User{
private HashMap<String, Quiz> quizList;
private HashMap<Integer, Quiz> quizzes; // Store quizzes with unique IDs
public Teacher(String name, String surname , int id) {
	super();
	this.quizList=new HashMap<>();
	this.quizzes = new HashMap<>();
}
///// get the quizlist
public  HashMap<String, Quiz> getQuizList(){
	return quizList;
}

/////////////////////// for the teacher role/////////////////////
//0) visualize the quizlist
public   void printQuizList() {
	  if (quizList.isEmpty()) {
	        System.out.println("The quiz list is empty. No quizzes to display.");
	        return;
	    }
	   for (Map.Entry<String, Quiz> entry : quizList.entrySet()) {
           String key = entry.getKey();
           Quiz quiz = entry.getValue();
           System.out.println("Quiz Key: " + key);
           quiz.printQuiz(); }
	}

	
	

//1)create quiz////////////////////////////////////////////////////////////////////////////
	public void createQuiz() {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Enter the title of the quiz:");
     String title = scanner.nextLine();

     // Check if a quiz with the same title already exists
     if (quizList.containsKey(title.toLowerCase())) {
         System.out.println("A quiz with this title already exists.");
         return;
     }
	    // Get the theme
	    System.out.println("To create a quiz, donnez the module:");
	    String themeInput = scanner.nextLine();

	    // Get the author
	    System.out.println("To create a quiz, donnez the author:");
	    String authorInput = scanner.nextLine();

	    // Create a new quiz with the input of module and the author
	    Quiz newQuiz = new Quiz(themeInput, authorInput);

	    // Get the number of QCMs the teacher wants in their quiz
	    System.out.println("How many questions do you want?");
	    int numberOfQuestions = scanner.nextInt();
	    scanner.nextLine(); 

	    for (int i = 0; i < numberOfQuestions; i++) {
	        // Get the question
	        System.out.println("Give me the question for QCM " + (i + 1) + ":");
	        String question = scanner.nextLine();

	        // Create the QCM object
	        Qcm qcm = new Qcm(i + 1, question);

	        // Get the number of options
	        System.out.println("Give me the number of options for QCM " + (i + 1) + ":");
	        int numOptions = scanner.nextInt();
	        scanner.nextLine(); 

	        for (int j = 0; j < numOptions; j++) {
	            // Get the text of the option
	            System.out.println("Give me the text of Option " + (j + 1) + ":");
	            String textOfOption = scanner.nextLine();

	            // Get the answer of the option
	            System.out.println("Is Option " + (j + 1) + " true or false?");
	            boolean answerOfOption = scanner.nextBoolean();
	            scanner.nextLine(); 

	            // Create the Option object
	            Option option = new Option(textOfOption, answerOfOption);
	            qcm.addOption(option);
	        }

	        // Add the QCM to the quiz
	        newQuiz.addQcm(qcm);
	    }

	    // Add the quiz to the list
	    quizList.put(title.toLowerCase(), newQuiz);

	    System.out.println("Quiz created successfully for the theme " + themeInput);
	    
	}


////////////////////////////////////////////////////////////////////////////////////////
// 2) visualize quiz of the theme
	public void visualizeQuiz() {
		 Scanner scanner =new Scanner(System.in);
		 System.out.println("\nEnter the module of the quiz to visualize:");
	     String themeInput = scanner.nextLine();
	     
	  
		 System.out.println("\nEnter the title of the quiz "+themeInput+" to visualize:");
	     String titleInput = scanner.nextLine();
		
		
	    Quiz quiz = quizList.get(titleInput.toLowerCase());
	    if (quiz != null) {
	        // Check if the theme matches
	        if (quiz.getTheme().equalsIgnoreCase(themeInput)) {
	            System.out.println("Details for the quiz with the title: " + titleInput + " and theme: " + themeInput);
	            quiz.printQuiz();
	        } else {
	            System.out.println("The theme does not match the title for the quiz.");
	        }
	    } else {
	        System.out.println("No quiz found with the title: " + titleInput);
	    }
	
	}


////////////////////////////////////////////////////////////////////////////////////////

//3) delete quiz//////////////////////////////////////****//////////////////////////7

public void deleteQuiz() {
	 Scanner scanner =new Scanner(System.in);
	 System.out.println("\nEnter the module of the quiz to delete:");
     String themeInput = scanner.nextLine();
     
  
	 System.out.println("\nEnter the title of the quiz "+themeInput+" to delete:");
     String titleInput = scanner.nextLine();
	
	
	
	
	    // Find the quiz by title 
	    Quiz quiz = quizList.get(titleInput.toLowerCase());

	    if (quiz!= null) {
	        // Check if the theme matches
	        if (quiz.getTheme().equalsIgnoreCase(themeInput)) {
	            // Remove the quiz from the map
	            quizList.remove(titleInput.toLowerCase());
	            System.out.println("Quiz with the title: '" + titleInput + " 'and theme: '" + themeInput + "' has been deleted.");
	        } else {
	            System.out.println("The theme does not match the title for the quiz.");
	        }
	    } else {
	        System.out.println("No quiz found with the title: " + titleInput);
	    }
	  
	}
////////////////////////////////////////////////////////////////////////////////////////
//4)  get the theme of a quiz
	public String getQuizTheme(String quizTitle) {
		if(quizList.containsKey(quizTitle)) {
			// Get the quiz using the title
				Quiz quiz=quizList.get(quizTitle);
				
			// get the theme of quiz
				String theme=quiz.getTheme();
				return theme;
		}
		else {
			 System.out.println("Quiz with the title \"" + quizTitle + "\" not found.");
			 return null;
		}
		
	}

//5) modify the quiz of a module

	public void modifyQuiz() {
	    Scanner scanner = new Scanner(System.in);

	    // Get input of the title of the quiz
	    System.out.println("Please write the title of the quiz you want to modify:");
	    String titleInput = scanner.nextLine();

	    // Find the quiz by title
	    Quiz quiz = quizList.get(titleInput);

	    if (quiz == null) {
	        System.out.println("Quiz with title '" + titleInput + "' not found.");
	        return;
	    }
	    
	    // Display the current theme for the quiz
	    System.out.println("The title: " + titleInput + " is found with theme: " + quiz.getTheme());
	    
	    System.out.println("What do you want to modify?");
	    System.out.println("1 - Theme\n2 - Author\n3 - QCM");
	    int choice = scanner.nextInt();
	    scanner.nextLine(); 

	    switch (choice) {
	        case 1:
	            System.out.println("Enter the new theme:");
	            String newTheme = scanner.nextLine();
	            quiz.setTheme(newTheme);
	            System.out.println("Theme updated successfully!");
	            break;

	        case 2:
	            System.out.println("Enter the new author:");
	            String newAuthor = scanner.nextLine();
	            quiz.setAuthor(newAuthor);
	            System.out.println("Author updated successfully!");
	            break;

	        case 3:
	            modifyQCM(scanner, quiz);
	            break;

	        default:
	            System.out.println("Invalid choice. Please try again.");
	    }
	}

	private void modifyQCM(Scanner scanner, Quiz quiz) {
	    List<Qcm> questions = quiz.getQuestions();

	    if (questions.isEmpty()) {
	        System.out.println("No QCMs found in this quiz.");
	        return;
	    }

	    System.out.println("Enter the number of the QCM you want to modify:");
	    int qcmNumber = scanner.nextInt();
	    scanner.nextLine(); // Consume leftover newline

	    // Simplified QCM search
	    Qcm qcm = null;
	    for (Qcm question : questions) {
	        if (question.getQuestionNum() == qcmNumber) {
	            qcm = question;
	            break;
	        }
	    }

	    if (qcm == null) {
	        System.out.println("QCM with number " + qcmNumber + " not found.");
	        return;
	    }

	    System.out.println("What do you want to modify in the QCM?");
	    System.out.println("1 - Text of the QCM\n2 - Options");
	    int choice = scanner.nextInt();
	    scanner.nextLine(); // Consume leftover newline

	    switch (choice) {
	        case 1:
	            System.out.println("Enter the new descriptive text for the QCM:");
	            String newQuestionText = scanner.nextLine();
	            qcm.setQuestionText(newQuestionText);
	            System.out.println("QCM text updated successfully!");
	            break;

	        case 2:
	            modifyOptions(scanner, qcm);
	            break;

	        default:
	            System.out.println("Invalid choice. Please try again.");
	    }
	}

	private void modifyOptions(Scanner scanner, Qcm qcm) {
	    List<Option> options = qcm.getOptions();

	    if (options.isEmpty()) {
	        System.out.println("No options found for this QCM.");
	        return;
	    }

	    System.out.println("Enter the number of the option you want to modify:");
	    int optionNumber = scanner.nextInt();
	    scanner.nextLine(); 

	    if (optionNumber < 1 || optionNumber > options.size()) {
	        System.out.println("Invalid option number.");
	        return;
	    }

	    Option option = options.get(optionNumber - 1); 

	    System.out.println("What do you want to modify in the option?");
	    System.out.println("1 - Text\n2 - Validity");
	    int choice = scanner.nextInt();
	    scanner.nextLine(); 

	    switch (choice) {
	        case 1:
	            System.out.println("Enter the new text for the option:");
	            String newText = scanner.nextLine();
	            option.setText(newText);
	            System.out.println("Option text updated successfully!");
	            break;

	        case 2:
	            System.out.println("Enter 'true' for correct or 'false' for incorrect:");
	            boolean newValidity = scanner.nextBoolean();
	            option.setCorrect(newValidity);
	            System.out.println("Option validity updated successfully!");
	            break;

	        default:
	            System.out.println("Invalid choice. Please try again.");
	    }
	}
///////////////////////////////// question 5////////////////////////////////////////

///5- Visualiser la liste des étudiants ayant passé le Quiz d’un module, avec le score qu’ils
///	ont obtenu.
	
	public void visualizeStudentScores(HashMap<Integer, Student> studentDatabase) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("\nEnter the module of the quiz to visualize:");
	    String themeInput = scanner.nextLine();

	    System.out.println("\nEnter the title of the quiz under theme '" + themeInput + "' to visualize:");
	    String titleInput = scanner.nextLine();

	    Quiz quiz = quizList.get(titleInput.toLowerCase());
	    if (quiz == null) {
	        System.out.println("Quiz with title '" + titleInput + "' not found.");
	        return;
	    }

	    // Check if the theme matches
	    if (!quiz.getTheme().equalsIgnoreCase(themeInput)) {
	        System.out.println("The quiz '" + titleInput + "' does not belong to the theme '" + themeInput + "'.");
	        return;
	    }

	    // Display quiz details
	    System.out.println("\nThe quiz '" + titleInput + "' with theme '" + themeInput + "' is found.");
	    System.out.println("Students who took the quiz:");

	    // retrieve the student'ids who had took a quiz
	    ArrayList<Integer> studentIds = quiz.getStudentList();
	    if (studentIds.isEmpty()) {
	        System.out.println("No students have taken this quiz yet.");
	        return;
	    }

	    for (int studentId : studentIds) {
	        Student student = studentDatabase.get(studentId); 
	        if (student != null) {
	            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + " " + student.getSurname()+ ", score");
	        } else {
	            System.out.println("Student ID: " + studentId + " not found in the system.");
	        }
	    }
	}
/////////6 visualiser le taux de reponses de chaque question d'un quiz donné
	public void visualizeQuizRate() {
		Scanner scanner = new Scanner(System.in);
	    
	    // Prompt user for theme and title input
	    System.out.println("\nEnter the module of the quiz to visualize:");
	    String themeInput = scanner.nextLine();

	    System.out.println("\nEnter the title of the quiz under theme '" + themeInput + "' to visualize:");
	    String titleInput = scanner.nextLine();

	    // Retrieve the quiz by title
	    Quiz quiz = quizList.get(titleInput.toLowerCase());

	    // Check if the quiz exists
	    if (quiz == null) {
	        System.out.println("Quiz with title '" + titleInput + "' not found.");
	        return;
	    }

	    // Check if the quiz matches the theme
	    if (!quiz.getTheme().equalsIgnoreCase(themeInput)) {
	        System.out.println("The quiz '" + titleInput + "' does not belong to the theme '" + themeInput + "'.");
	        return;
	    }

	    // Display quiz details
	    System.out.println("\nThe quiz '" + titleInput + "' with theme '" + themeInput + "' is found.");
	    System.out.println("The response rate for each question:");
	    
	    
	}

    public void quizCorrectResponseRate() {
        Scanner scanner = new Scanner(System.in);

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available to evaluate.");
            return;
        }

        // Display available quizzes
        System.out.println("Available quizzes:");
        for (Map.Entry<Integer, Quiz> entry : quizzes.entrySet()) {
            System.out.println("Quiz ID: " + entry.getKey() + " - " + entry.getValue().getQuizName());
        }

        // Select quiz
        System.out.println("Enter the Quiz ID to view correct response rates:");
        int quizId = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        Quiz selectedQuiz = quizzes.get(quizId);
        if (selectedQuiz == null) {
            System.out.println("Invalid Quiz ID.");
            return;
        }

        // Calculate correct response rate
        int totalQuestions = selectedQuiz.getQuestions().size();
        if (totalQuestions == 0) {
            System.out.println("This quiz has no questions.");
            return;
        }

        int totalStudents = selectedQuiz.getStudentAnswers().size();
        if (totalStudents == 0) {
            System.out.println("No students have attempted this quiz.");
            return;
        }

        int totalCorrectResponses = 0;
        for (Map.Entry<Student, Map<Integer, Boolean>> studentEntry : selectedQuiz.getStudentAnswers().entrySet()) {
            Map<Integer, Boolean> answers = studentEntry.getValue();
            for (Boolean isCorrect : answers.values()) {
                if (isCorrect) {
                    totalCorrectResponses++;
                }
            }
        }

        double correctRate = (double) totalCorrectResponses / (totalQuestions * totalStudents) * 100;
        System.out.printf("Correct Response Rate for Quiz '%s': %.2f%%%n", selectedQuiz.getQuizName(), correctRate);
    }
}
