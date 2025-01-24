import java.util.*;

class Player {
    String name,mail;
    int age,phno;
    double amountWon;

    public Player(String name, int age,String mail,int phno) {
        this.name = name;
        this.age = age;
        this.mail=mail;
        this.phno=phno;
        this.amountWon = 0;
    }
}

public class quiz {

    static Scanner scanner = new Scanner(System.in);
    static boolean[] lifelines = {true, true}; // Two lifelines available

    public static void main(String[] args) {
        // Collect player details
        
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character
        System.out.println("Enter your mail id:");
        String mail= scanner.nextLine();
        System.out.println("Enter your phone no: ");
        int phno = scanner.nextInt();
        scanner.nextLine(); 
        

        Player player = new Player(name, age,mail,phno);

        System.out.println("Welcome " + player.name + ", are you ready to take the quiz? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("no")) {
            System.out.println("Thank you for your time, " + player.name + "! Goodbye.");
            return;
        }

        // Display rules
        displayRules();

        // Start quiz
        startQuiz(player);
    }

    static void displayRules() {
        System.out.println("Rules of the Quiz:");
        System.out.println("1. There will be multiple-choice questions with 4 options.");
        System.out.println("2. You have 2 lifelines, which can only be used once.");
        System.out.println("3. If you answer a question incorrectly, the game ends with the amount won.");
        System.out.println("4. If you use a lifeline, it will reveal the correct answer or eliminate options.");
        System.out.println("5. Good luck!");
    }

    static void startQuiz(Player player) {
        String[] questions = {
            "What is the capital of France?", // Question 1
            "Which planet is known as the Red Planet?",
             "Who wrote the play 'Romeo and Juliet'?", // Question 3
            "What is the smallest prime number?" // Question 2
            
        };

        String[][] options = {
            {"1. Paris", "2. London", "3. Berlin", "4. Rome"},
            {"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"},
            {"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. Jane Austen"},
            {"1. 0", "2. 1", "3. 2", "4. 3"}
        };

        int[] answers = {1, 2,2, 3}; // Correct answers for questions
        double[] rewards = {1000, 5000,10000,20000}; // Rewards for questions

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.println("Choose your answer (or type 'lifeline' to use a lifeline): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("lifeline")) {
                if (!lifelines[0] && !lifelines[1]) {
                    System.out.println("No lifelines left! Please choose an answer: ");
                    choice = scanner.nextLine();
                } else {
                    useLifeline(i, answers[i], options[i]);
                    System.out.println("Choose your answer after using the lifeline: ");
                    choice = scanner.nextLine();
                }
            }

            int answer = Integer.parseInt(choice);

            if (answer == answers[i]) {
                player.amountWon += rewards[i];
                System.out.println("Correct! You have won " + player.amountWon + " so far.");
            } else {
                System.out.println("Incorrect! Game over. You won " + player.amountWon + ".");
                break;
            }
        }

        System.out.println("Thank you for playing, " + player.name + "! You won " + player.amountWon + ".");
    }

    static void useLifeline(int questionIndex, int correctAnswer, String[] options) {
        System.out.println("Available lifelines:");
        if (lifelines[0]) {
            System.out.println("1. 50-50 (Eliminates two incorrect options)");
        }
        if (lifelines[1]) {
            System.out.println("2. Show Correct Answer");
        }

        System.out.println("Choose your lifeline: ");
        int lifelineChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        if (lifelineChoice == 1 && lifelines[0]) {
            lifelines[0] = false;
            System.out.println("50-50 Lifeline used! Remaining options are:");
            System.out.println(options[correctAnswer - 1]); // Correct answer
            System.out.println(options[(correctAnswer % 4)]); // One incorrect answer
        } else if (lifelineChoice == 2 && lifelines[1]) {
            lifelines[1] = false;
            System.out.println("The correct answer is: " + options[correctAnswer - 1]);
        } else {
            System.out.println("Invalid or already used lifeline! Choose another option.");
        }
    }
}

