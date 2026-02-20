// File: UseCase3PalindromeCheckerApp.java

public class UseCase3PalindromeCheckerApp {

    // Application metadata
    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "1.0";

    // Entry point of the application
    public static void main(String[] args) {
        displayWelcomeMessage();

        // UC2: Hardcoded palindrome check using StringBuilder (previous method)
        String testStringUC2 = "madam";
        System.out.println("UC2 check (using StringBuilder):");
        checkPalindromeUsingStringBuilder(testStringUC2);
        System.out.println();

        // UC3: Palindrome check using for loop to reverse string
        String testStringUC3 = "racecar";
        System.out.println("UC3 check (using for loop):");
        checkPalindromeUsingLoop(testStringUC3);
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

    // UC2 Method: check palindrome using StringBuilder reverse
    private static void checkPalindromeUsingStringBuilder(String input) {
        String reversed = new StringBuilder(input).reverse().toString();

        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }

    // UC3 Method: check palindrome by manually reversing using a for loop
    private static void checkPalindromeUsingLoop(String input) {
        String reversed = "";

        // Loop from the last character to the first
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);  // Concatenate characters (inefficient, but per UC3)
        }

        // Compare original and reversed strings using equals()
        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }
}