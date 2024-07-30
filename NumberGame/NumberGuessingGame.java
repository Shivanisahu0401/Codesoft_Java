import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 5;
        int rounds = 0;
        int score = 0;
        
        boolean playAgain = true;
        while (playAgain) {
            rounds++;
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("Round " + rounds + ":");
            System.out.println("I have generated a number between 1 and 100. Can you guess it?");
            
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == targetNumber) {
                    guessedCorrectly = true;
                    score += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    System.out.println("Congratulations! You guessed the correct number.");
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + targetNumber);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }
        
        System.out.println("Game over! You played " + rounds + " rounds and your score is: " + score);
        scanner.close();
    }
}
