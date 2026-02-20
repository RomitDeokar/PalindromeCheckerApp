// File: UseCase1PalindromeCheckerApp.java

public class UseCase1PalindromeCheckerApp {

    // Application metadata
    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "1.0";

    // Entry point of the application
    public static void main(String[] args) {
        displayWelcomeMessage();
        // In the future, you can call the next use case here
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
}
