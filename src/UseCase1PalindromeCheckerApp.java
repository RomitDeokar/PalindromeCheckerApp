// File: UseCase4PalindromeCheckerApp.java

public class UseCase4PalindromeCheckerApp {

    // Application metadata
    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "1.0";

    public static void main(String[] args) {
        displayWelcomeMessage();

        // UC2: Using StringBuilder reverse
        String testStringUC2 = "madam";
        System.out.println("UC2 check (using StringBuilder):");
        checkPalindromeUsingStringBuilder(testStringUC2);
        System.out.println();

        // UC3: Using for loop to reverse string
        String testStringUC3 = "racecar";
        System.out.println("UC3 check (using for loop):");
        checkPalindromeUsingLoop(testStringUC3);
        System.out.println();

        // UC4: Using char array and two-pointer technique
        String testStringUC4 = "level";
        System.out.println("UC4 check (using char array and two-pointer):");
        checkPalindromeUsingCharArray(testStringUC4);
    }

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME + "!");
        System.out.println("Version: " + APP_VERSION);
        System.out.println("This application checks whether a string is a palindrome.");
        System.out.println("=====================================");
        System.out.println();
    }

    private static void checkPalindromeUsingStringBuilder(String input) {
        String reversed = new StringBuilder(input).reverse().toString();

        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }

    private static void checkPalindromeUsingLoop(String input) {
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }

    private static void checkPalindromeUsingCharArray(String input) {
        char[] chars = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (chars[left] != chars[right]) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        if (isPalindrome) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }
}