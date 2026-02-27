// File: UseCase8PalindromeCheckerApp.java

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class PalindromeCheckerApp {

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

        // UC3: Using for loop
        String testStringUC3 = "racecar";
        System.out.println("UC3 check (using for loop):");
        checkPalindromeUsingLoop(testStringUC3);
        System.out.println();

        // UC4: Using char array + two pointer
        String testStringUC4 = "level";
        System.out.println("UC4 check (using char array and two-pointer):");
        checkPalindromeUsingCharArray(testStringUC4);
        System.out.println();

        // UC5: Using stack
        String testStringUC5 = "deified";
        System.out.println("UC5 check (using stack):");
        checkPalindromeUsingStack(testStringUC5);
        System.out.println();

        // UC6: Using queue + stack
        String testStringUC6 = "rotator";
        System.out.println("UC6 check (using queue + stack):");
        checkPalindromeUsingQueueAndStack(testStringUC6);
        System.out.println();

        // UC7: Using deque
        String testStringUC7 = "civic";
        System.out.println("UC7 check (using deque):");
        checkPalindromeUsingDeque(testStringUC7);
        System.out.println();

        // UC8: Using Singly Linked List
        String testStringUC8 = "noon";
        System.out.println("UC8 check (using singly linked list):");
        checkPalindromeUsingLinkedList(testStringUC8);
    }

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME + "!");
        System.out.println("Version: " + APP_VERSION);
        System.out.println("=====================================\n");
    }

    // UC2
    private static void checkPalindromeUsingStringBuilder(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        printResult(input, input.equals(reversed));
    }

    // UC3
    private static void checkPalindromeUsingLoop(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        printResult(input, input.equals(reversed));
    }

    // UC4
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

        printResult(input, isPalindrome);
    }

    // UC5
    private static void checkPalindromeUsingStack(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        printResult(input, input.equals(reversed));
    }

    // UC6
    private static void checkPalindromeUsingQueueAndStack(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            queue.add(c);
            stack.push(c);
        }

        boolean isPalindrome = true;

        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                isPalindrome = false;
                break;
            }
        }

        printResult(input, isPalindrome);
    }

    // UC7
    private static void checkPalindromeUsingDeque(String input) {
        Deque<Character> deque = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            deque.addLast(input.charAt(i));
        }

        boolean isPalindrome = true;

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                isPalindrome = false;
                break;
            }
        }

        printResult(input, isPalindrome);
    }

    // ===== UC8: Singly Linked List Implementation =====

    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private static void checkPalindromeUsingLinkedList(String input) {

        // Convert string to linked list
        Node head = null;
        Node tail = null;

        for (int i = 0; i < input.length(); i++) {
            Node newNode = new Node(input.charAt(i));
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Find middle using fast & slow pointer
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node prev = null;
        Node current = slow;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        // Compare halves
        Node firstHalf = head;
        Node secondHalf = prev;

        boolean isPalindrome = true;

        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        printResult(input, isPalindrome);
    }

    // Common result printer
    private static void printResult(String input, boolean isPalindrome) {
        if (isPalindrome) {
            System.out.println("The string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The string \"" + input + "\" is NOT a palindrome.");
        }
    }
}