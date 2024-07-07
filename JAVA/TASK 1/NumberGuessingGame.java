import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts =5;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean userWon = false;

            System.out.printf("I'm thinking of a number between %d and %d.%n", minRange, maxRange);
            System.out.printf("You have %d attempts to guess the number.%n%n", maxAttempts);

            while (attempts < maxAttempts && !userWon) {
                System.out.printf("Attempt %d: Enter your guess: ", attempts + 1);
                int guess = scanner.nextInt();

                if (guess < minRange || guess > maxRange) {
                    System.out.printf("Please enter a number between %d and %d.%n", minRange, maxRange);
                    continue;
                }

                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the correct number.");
                    userWon = true;
                }
            }

            if (!userWon) {
                System.out.printf("Sorry, you've used all %d attempts. The correct number was %d.%n", maxAttempts, numberToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
            System.out.println();
        }

        System.out.println("Thanks for playing the Number Guessing Game! Goodbye!");
        scanner.close();
    }
}
