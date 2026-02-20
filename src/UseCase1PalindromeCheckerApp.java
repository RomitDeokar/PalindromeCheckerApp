// File: UseCase2PalindromeCheckerApp.java

public class UseCase2PalindromeCheckerApp {

    // Application metadata
    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "1.0";

    // Entry point of the application
    public static void main(String[] args) {
        displayWelcomeMessage();

        // UC2: Hardcoded palindrome check
        String testString = "madam"; // You can change this string to test other words
        checkPalindrome(testString);
    }

    // Method to display the welcome message
    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME + "!");
        System.out.println("Version: " + APP_VERSION);
        System.out.println("This application checks whether a string is a palindrome.");
        System.out.println("=====================================");
        System.out.println();
    }

    // Method to check if a string is a palindrome
    private static void checkPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();

        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }
}