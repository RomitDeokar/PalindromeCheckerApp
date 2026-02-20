// File: UseCase6PalindromeCheckerApp.java

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class UseCase6PalindromeCheckerApp {

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
        System.out.println();

        // UC5: Using stack to check palindrome
        String testStringUC5 = "deified";
        System.out.println("UC5 check (using stack):");
        checkPalindromeUsingStack(testStringUC5);
        System.out.println();

        // UC6: Using queue + stack to demonstrate FIFO vs LIFO
        String testStringUC6 = "rotator";
        System.out.println("UC6 check (using queue + stack):");
        checkPalindromeUsingQueueAndStack(testStringUC6);
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

    private static void checkPalindromeUsingStack(String input) {
        Stack<Character> stack = new Stack<>();

        // Push all characters onto stack
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        // Pop characters to build reversed string
        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        if (input.equals(reversed)) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }

    private static void checkPalindromeUsingQueueAndStack(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        // Enqueue and push all characters
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            queue.add(c);  // FIFO
            stack.push(c); // LIFO
        }

        boolean isPalindrome = true;

        // Compare dequeued vs popped characters
        while (!queue.isEmpty() && !stack.isEmpty()) {
            char fromQueue = queue.remove();
            char fromStack = stack.pop();
            if (fromQueue != fromStack) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }
}