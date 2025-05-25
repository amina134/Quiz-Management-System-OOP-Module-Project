package project_poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
public class Quiz {
  private String title;    
  private String theme;
  private String author;
  private int score= 0;
  private List<Qcm> questions;
  // added the students list arraylist
  private ArrayList<Integer> studentList;
  private HashMap<Integer, Integer> studentScores;
  private HashMap<Student, Map<Integer, Boolean>> studentAnswers; // Student answers for each question ID
  //constructor
  public Quiz(String theme,String author) {
	  this.title = title;
	  this.theme=theme;
	  this.author=author;
	  this.score=score;
	  this.questions=new ArrayList<>();
	  this.studentList = new ArrayList<>();
	  this.studentScores = new HashMap<>();
	  this.studentAnswers = new HashMap<>();
  }
  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  // this function to ass a qcm to the quiz
  public void addQcm(Qcm qcm) {
      this.questions.add(qcm);
  }
  public String getTheme() {
	  return theme;
  }
  public void setTheme(String theme) {
	  this.theme=theme;
  }
  public String getAuthor() {
	  return author;
  }
  public void setAuthor(String author) {
	  this.author=author;
  }
  public List<Qcm> getQuestions(){
	  return questions;
  }
  public void printQuiz() {
	  System.out.println("Quiz theme : "+theme);
	  System.out.println("Quiz author:  "+author);
	  System.out.println("QCM : ");
	  for (Qcm qcm:questions) {
		  qcm.printQcm();
	  }
	  
  }
  /////
  public ArrayList<Integer> getStudentList() {
      return studentList;
  }

  // Add a student ID to the passed list
  public void addStudent(int id) {
      if (!studentList.contains(id)) {
          studentList.add(id);
      } else {
          System.out.println("Student already passed the quiz");
      }
  }

  // Check if a student has taken the quiz
  public boolean takenQuiz(int id) {
      return studentList.contains(id);
  }

  // Setter for student list
  public void setStudentList(ArrayList<Integer> studentList) {
      this.studentList = studentList;
  }

  // ///////////// student part ////////////////////////
//Answer a quiz and update the score
  public int answerQuiz(int studentId) {
	    Scanner scanner = new Scanner(System.in);
	    int score1 = 0;  // Local score for the current attempt

	    
	    for (Qcm question : questions) {
	        System.out.println(question.getQuestionText());  // Display the question

	        // Loop through each option for the question
	        for (Option option : question.getOptions()) {
	            System.out.println("- " + option.getText());  // Display the option text
	            System.out.print("Is this option correct? (true/false): ");
	            String userAnswer = scanner.nextLine().toLowerCase();  // User input for correct/incorrect
	            
	            // Check if the user answer is either "true" or "false"
	            if (userAnswer.equals("true") || userAnswer.equals("false")) {
	                boolean userIsCorrect = (userAnswer.equals("true") == option.getIsValid());

	                if (userIsCorrect) {
	                    score1++;  // Increase score for correct answer
	                }
	            } else {
	                System.out.println("Invalid input. Please enter 'true' or 'false'.");
	            }
	        }
	    }

	    // Update the score for this quiz
	    this.score = score1;  // Store the calculated score in the attribute
	    studentScores.put(studentId, score1);
	    return score1;
	}


  // Getter for score
  public int getScore() {
      return score;
  }
  public int getStudentScore(int studentId) {
	    return studentScores.getOrDefault(studentId, 0); // Default to 0 if not found
	}


  public Map<Student, Map<Integer, Boolean>> getStudentAnswers() {
      return studentAnswers;
  }

  public void addStudentAnswers(Student student, Map<Integer, Boolean> answers) {
      studentAnswers.put(student, answers);
  }


}
