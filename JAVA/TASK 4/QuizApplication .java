import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    String[] options;
    char correctAnswer;

    public QuizQuestion(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class QuizApplication {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static char[] userAnswers;
    private static Timer timer;
    private static boolean timeUp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizQuestion[] questions = new QuizQuestion[] {
            new QuizQuestion("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'),
            new QuizQuestion("What is 2 + 2?", new String[]{"A. 3", "B. 4", "C. 5", "D. 6"}, 'B'),
            new QuizQuestion("Who wrote 'Romeo and Juliet'?", new String[]{"A. Charles Dickens", "B. Mark Twain", "C. William Shakespeare", "D. Jane Austen"}, 'C'),
            new QuizQuestion("What is the largest planet in our solar system?", new String[]{"A. Earth", "B. Jupiter", "C. Mars", "D. Saturn"}, 'B'),
            new QuizQuestion("Which element has the chemical symbol 'O'?", new String[]{"A. Gold", "B. Oxygen", "C. Silver", "D. Zinc"}, 'B')
        };
        userAnswers = new char[questions.length];
        System.out.println("Welcome to the Quiz Application!");

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            displayQuestion(questions[currentQuestionIndex]);
            timeUp = false;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                    submitAnswer(' ');
                }
            }, 10000);
            System.out.print("Enter your answer (A, B, C, or D): ");
            char userAnswer = scanner.next().toUpperCase().charAt(0);
            if (!timeUp) {
                submitAnswer(userAnswer);
                timer.cancel();
            }
        }
        displayResults(questions);
        scanner.close();
    }

    private static void displayQuestion(QuizQuestion question) {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
    }

    private static void submitAnswer(char userAnswer) {
        userAnswers[currentQuestionIndex] = userAnswer;
        if (userAnswer == ' ') {
            System.out.println("No answer submitted.");
        } else {
            System.out.println("Answer submitted: " + userAnswer);
        }
    }

    private static void displayResults(QuizQuestion[] questions) {
        System.out.println("\nQuiz Completed!");
        for (int i = 0; i < questions.length; i++) {
            System.out.print("Question " + (i + 1) + ": ");
            if (userAnswers[i] == questions[i].correctAnswer) {
                System.out.println("Correct");
                score++;
            } else if (userAnswers[i] == ' ') {
                System.out.println("No answer submitted. Correct answer: " + questions[i].correctAnswer);
            } else {
                System.out.println("Incorrect. Your answer: " + userAnswers[i] + ". Correct answer: " + questions[i].correctAnswer);
            }
        }
        System.out.println("Your final score: " + score + " out of " + questions.length);
    }
}
