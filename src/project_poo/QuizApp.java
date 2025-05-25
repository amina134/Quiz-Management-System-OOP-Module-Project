package project_poo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class QuizApp {

	private static HashMap<Integer, Student> studentDatabase = new HashMap<>();
	
    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz App!");
        Scanner scanner = new Scanner(System.in);

        /// * TEACHER PART * ///
        System.out.println("Enter teacher's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter teacher's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter teacher's ID:");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        // Create a teacher object
        Teacher teacher = new Teacher(firstName, lastName, teacherId);

        while (true) {
            System.out.println("\nAre you a:");
            System.out.println("1 - Teacher");
            System.out.println("2 - Student");
            System.out.println("3 - Exit");

            int userType = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            switch (userType) {
                case 1: // Teacher part
                    while (true) {
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1 - Create Quiz");
                        System.out.println("2 - Visualize Quiz");
                        System.out.println("3 - Print All Quizzes");
                        System.out.println("4 - Delete Quiz");
                        System.out.println("5 - Modify Quiz");
                        System.out.println("6 - View Students' Scores for Quiz");
                        System.out.println("7 - View Correct and Incorrect Response Rates");
                        System.out.println("8 - Back");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume leftover newline

                        switch (choice) {
                            case 1:
                                teacher.createQuiz();
                                break;
                            case 2:
                                teacher.visualizeQuiz();
                                break;
                            case 3:
                                teacher.printQuizList();
                                break;
                            case 4:
                                teacher.deleteQuiz();
                                break;
                            case 5:
                                teacher.modifyQuiz();
                                break;
                            case 6:
                               
                            	teacher.visualizeStudentScores(studentDatabase);
                                break;
                            case 7:
                               teacher.visualizeQuizRate();
                                System.out.println("Returning to main menu...");
                                break;
                            case 8:
                                System.out.println("Returning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }

                        if (choice == 8) {
                            break;
                        }
                    }
                    break;

                case 2: // Student part
                    // Student identification
                	
                     System.out.println("Enter your first name:");
                     String firstName1 = scanner.nextLine();
                     System.out.println("Enter your last name:");
                     String lastName1 = scanner.nextLine();
                     System.out.println("Enter your group:");
                     String group = scanner.nextLine();
                     System.out.println("Enter your ID:");
                     int id = scanner.nextInt();
                     scanner.nextLine();

                    // Create a Student object and add it to the map
                    Student student = new Student();
                    student.setName(firstName1);
                    student.setSurname(lastName1);
                    student.setId(id);
                    student.setGroup(group);
                    studentDatabase.put(id, student);
                   
                    while (true) {
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1 - Visualize Quizzes");
                        System.out.println("2 - Take a Quiz");
                        System.out.println("3 - View Scores");
                        System.out.println("4 - View Corrections");
                        System.out.println("5 - Back");

                        int studentChoice = scanner.nextInt();
                        scanner.nextLine(); 
                        switch (studentChoice) {
                            case 1:
                                student.visualizeStudent(teacher);
                                break;
                            case 2:
                                student.chooseAndTakeQuiz(teacher);
                                break;
                            case 3:
                                student.viewScores();
                                break;
                            case 4:
                                student.viewCorrection();
                                break;
                            case 5:
                                System.out.println("Returning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }

                        if (studentChoice == 5) {
                            break;
                        }
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}