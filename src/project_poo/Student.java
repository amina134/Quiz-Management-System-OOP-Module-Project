package project_poo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Student extends User {
    private String group;
    private HashMap<String, Quiz> quizzesTaken;
    private HashMap<String, Quiz> quizzesNotTaken;
    

    public Student() {
        super();
        this.group = "";
        this.quizzesNotTaken = new HashMap<>();
        this.quizzesTaken = new HashMap<>();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    // Identify student with input validation
    public void identifyStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer vos informations pour vous identifier :");
        System.out.print("Nom : ");
        this.setName(scanner.nextLine());

        System.out.print("Prénom : ");
        this.setSurname(scanner.nextLine());

        System.out.print("Groupe : ");
        this.setGroup(scanner.nextLine());

        System.out.print("NCIN : ");
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez entrer un numéro valide : ");
            scanner.next(); // discard invalid input
        }
        this.setId(scanner.nextInt());

        System.out.println("Identification réussie. Bienvenue, " + this.getSurname() + " " + this.getName() + " !");
    }

    // Visualize quizzes
    public void visualizeStudent(Teacher teacher) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Quiz> quizList = teacher.getQuizList();
        if (quizList.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Please type your student ID:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid ID: ");
            scanner.next();
        }
        int studentId = scanner.nextInt();

        quizzesTaken.clear();
        quizzesNotTaken.clear();

        for (Map.Entry<String, Quiz> entry : quizList.entrySet()) {
            Quiz quiz = entry.getValue();
            if (quiz.getStudentList().contains(studentId)) {
                quizzesTaken.put(entry.getKey(), quiz);
            } else {
                quizzesNotTaken.put(entry.getKey(), quiz);
            }
        }

        displayQuizzes("Quizzes taken", quizzesTaken);
        displayQuizzes("Quizzes not taken", quizzesNotTaken);
    }

    private void displayQuizzes(String title, HashMap<String, Quiz> quizzes) {
        System.out.println("\n" + title + ":");
        if (quizzes.isEmpty()) {
            System.out.println("  None");
        } else {
            for (String quizTitle : quizzes.keySet()) {
                System.out.println("  - " + quizTitle);
            }
        }
    }

    // Choose and take a quiz
    public void chooseAndTakeQuiz(Teacher teacher) {
        Scanner scanner = new Scanner(System.in);
        
////////////////////////////////////////////
        HashMap<String, Quiz> quizList = teacher.getQuizList();
        if (quizList.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Please type your student ID:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid ID: ");
            scanner.next();
        }
        int studentId = scanner.nextInt();

        quizzesTaken.clear();
        quizzesNotTaken.clear();

        for (Map.Entry<String, Quiz> entry : quizList.entrySet()) {
            Quiz quiz = entry.getValue();
            if (quiz.getStudentList().contains(studentId)) {
                quizzesTaken.put(entry.getKey(), quiz);
            } else {
                quizzesNotTaken.put(entry.getKey(), quiz);
            }
        }
 ////////////////////////////////////////////7
        // Check if there are quizzes available
        if (quizzesNotTaken.isEmpty()) {
            System.out.println("No quizzes available for this student.");
            return;
        }

        // Display the list of available quizzes
        displayQuizzes("Available quizzes to take", quizzesNotTaken);
        scanner.nextLine(); 
        // Prompt the student to select a quiz
        System.out.println("Please enter the title of the quiz you want to take:");
        
        String chosenQuizTitle = scanner.nextLine();
      
        // Retrieve the selected quiz from the map
        Quiz chosenQuiz = quizzesNotTaken.get(chosenQuizTitle);
        if (chosenQuiz != null) {
            // Pass the student ID to the answerQuiz method
            int score = chosenQuiz.answerQuiz(this.getId());
            chosenQuiz.addStudent(this.getId()); // Add the student to the quiz's list of participants
            quizzesTaken.put(chosenQuizTitle, chosenQuiz); // Move the quiz to the 'taken' list
            quizzesNotTaken.remove(chosenQuizTitle); // Remove it from the 'not taken' list
            System.out.println("You completed the quiz. Your score: " + score);
        } else {
            // Handle case where the quiz is not found
            System.out.println("Quiz not found. Please select a valid quiz.");
        }
    }

    // View scores
    public void viewScores() {
        // Vérifier si l'étudiant a passé des quizz
        if (quizzesTaken.isEmpty()) {
            System.out.println("Vous n'avez pas encore passé de quiz.");
            return;
        }

        // Afficher les scores des quizz passés
        System.out.println("Scores des quiz que vous avez passés :");
        for (Map.Entry<String, Quiz> entry : quizzesTaken.entrySet()) {
            String quizTitle = entry.getKey();
            Quiz quiz = entry.getValue();
            int score = quiz.getScore(); 
            // Supposons que la méthode answerQuiz() retourne le score d'un quiz.
            System.out.println("Quiz: " + quizTitle + " - Score: "+score);
        }
    }


    // View corrections for a taken quiz
    public void viewCorrection() {
        Scanner scanner = new Scanner(System.in);

        if (quizzesTaken.isEmpty()) {
            System.out.println("No corrections available. You haven't taken any quizzes yet.");
            return;
        }

        displayQuizzes("Available quizzes for corrections", quizzesTaken);

        System.out.println("Enter the title of the quiz to view corrections:");
        String chosenQuizTitle = scanner.nextLine();

        Quiz chosenQuiz = quizzesTaken.get(chosenQuizTitle);
        if (chosenQuiz != null) {
            System.out.println("Correction for quiz: " + chosenQuiz.getTitle());
            for (Qcm question : chosenQuiz.getQuestions()) {
                System.out.println("Question: " + question.getQuestionText());
                for (Option option : question.getOptions()) {
                    System.out.println("  - " + option.getText() + (option.getIsValid() ? " (Correct)" : " (Incorrect)"));
                }
            }
        } else {
            System.out.println("You haven't taken this quiz yet.");
        }
    }
}

