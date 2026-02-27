// File: UseCase10PalindromeCheckerApp.java

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class UseCase10_PalindromeCheckerApp {

    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "2.0";

    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node leftPointer;

    public static void main(String[] args) {

        displayWelcomeMessage();

        // UC2
        checkPalindromeUsingStringBuilder("madam");

        // UC3
        checkPalindromeUsingLoop("racecar");

        // UC4
        checkPalindromeUsingCharArray("level");

        // UC5
        checkPalindromeUsingStack("deified");

        // UC6
        checkPalindromeUsingQueueAndStack("rotator");

        // UC7
        checkPalindromeUsingDeque("civic");

        // UC8
        checkPalindromeUsingLinkedList("noon");

        // UC9
        System.out.println("\nUC9 check (using recursion):");
        checkPalindromeUsingRecursion("refer");

        // ===== UC10 =====
        System.out.println("\nUC10 check (Case-Insensitive & Space-Ignored):");
        checkPalindromeIgnoringCaseAndSpaces("Madam");
        checkPalindromeIgnoringCaseAndSpaces("nurses run");
        checkPalindromeIgnoringCaseAndSpaces("A man a plan a canal Panama");
    }

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME);
        System.out.println("Version: " + APP_VERSION);
        System.out.println("=====================================\n");
    }

    // ================= UC2 =================
    private static void checkPalindromeUsingStringBuilder(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        printResult("UC2 (StringBuilder)", input, input.equals(reversed));
    }

    // ================= UC3 =================
    private static void checkPalindromeUsingLoop(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        printResult("UC3 (Loop)", input, input.equals(reversed));
    }

    // ================= UC4 =================
    private static void checkPalindromeUsingCharArray(String input) {
        char[] chars = input.toCharArray();
        int left = 0, right = chars.length - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (chars[left] != chars[right]) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        printResult("UC4 (Char Array)", input, isPalindrome);
    }

    // ================= UC5 =================
    private static void checkPalindromeUsingStack(String input) {
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        printResult("UC5 (Stack)", input, input.equals(reversed));
    }

    // ================= UC6 =================
    private static void checkPalindromeUsingQueueAndStack(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }

        boolean isPalindrome = true;

        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                isPalindrome = false;
                break;
            }
        }

        printResult("UC6 (Queue + Stack)", input, isPalindrome);
    }

    // ================= UC7 =================
    private static void checkPalindromeUsingDeque(String input) {
        Deque<Character> deque = new LinkedList<>();

        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }

        boolean isPalindrome = true;

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                isPalindrome = false;
                break;
            }
        }

        printResult("UC7 (Deque)", input, isPalindrome);
    }

    // ================= UC8 =================
    private static void checkPalindromeUsingLinkedList(String input) {

        Node head = null, tail = null;

        for (char c : input.toCharArray()) {
            Node newNode = new Node(c);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node prev = null;
        while (slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

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

        printResult("UC8 (Linked List)", input, isPalindrome);
    }

    // ================= UC9 =================
    private static void checkPalindromeUsingRecursion(String input) {
        boolean result = recursiveCheck(input, 0, input.length() - 1);
        printResult("UC9 (Recursion)", input, result);
    }

    private static boolean recursiveCheck(String str, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        return recursiveCheck(str, left + 1, right - 1);
    }

    // ================= UC10 =================
    private static void checkPalindromeIgnoringCaseAndSpaces(String input) {

        // Step 1: Normalize string
        String normalized = input
                .toLowerCase()                // ignore case
                .replaceAll("\\s+", "")       // remove spaces using regex
                .replaceAll("[^a-z]", "");    // remove special characters (optional improvement)

        // Step 2: Apply previous logic (Char Array method)
        char[] chars = normalized.toCharArray();
        int left = 0, right = chars.length - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (chars[left] != chars[right]) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        printResult("UC10 (Ignore Case & Spaces)", input, isPalindrome);
    }

    // ================= COMMON RESULT =================
    private static void printResult(String method, String input, boolean isPalindrome) {
        if (isPalindrome) {
            System.out.println(method + " → \"" + input + "\" is a palindrome.");
        } else {
            System.out.println(method + " → \"" + input + "\" is NOT a palindrome.");
        }
    }
}