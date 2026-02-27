// File: UseCase12PalindromeCheckerApp.java

import java.util.*;

public class UseCase12_PalindromeCheckerApp {

    // ================= UC1 =================
    private static final String APP_NAME = "Palindrome Checker App";
    private static final String APP_VERSION = "5.0";

    // =====================================================
    // ================= UC12 STRATEGY =====================
    // =====================================================

    interface PalindromeStrategy {
        boolean check(String input);
    }

    static class StackStrategy implements PalindromeStrategy {
        public boolean check(String input) {
            String normalized = normalize(input);
            Stack<Character> stack = new Stack<>();
            for (char c : normalized.toCharArray()) stack.push(c);
            for (char c : normalized.toCharArray())
                if (c != stack.pop()) return false;
            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean check(String input) {
            String normalized = normalize(input);
            Deque<Character> deque = new LinkedList<>();
            for (char c : normalized.toCharArray())
                deque.addLast(c);
            while (deque.size() > 1)
                if (deque.removeFirst() != deque.removeLast()) return false;
            return true;
        }
    }

    static class PalindromeContext {
        private PalindromeStrategy strategy;
        public PalindromeContext(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }
        public void setStrategy(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }
        public boolean execute(String input) {
            return strategy.check(input);
        }
    }

    // =====================================================
    // ================= UC11 SERVICE ======================
    // =====================================================

    static class PalindromeChecker {
        public boolean checkPalindrome(String input) {
            String normalized = normalize(input);
            char[] arr = normalized.toCharArray();
            int left = 0, right = arr.length - 1;
            while (left < right) {
                if (arr[left] != arr[right]) return false;
                left++;
                right--;
            }
            return true;
        }
    }

    // =====================================================
    // ================= UC8 NODE ==========================
    // =====================================================

    static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    // =====================================================
    // ================= MAIN ===============================
    // =====================================================

    public static void main(String[] args) {

        displayWelcomeMessage(); // UC1

        checkPalindromeUsingStringBuilder("madam");     // UC2
        checkPalindromeUsingLoop("racecar");            // UC3
        checkPalindromeUsingCharArray("level");         // UC4
        checkPalindromeUsingStack("deified");           // UC5
        checkPalindromeUsingQueueAndStack("rotator");   // UC6
        checkPalindromeUsingDeque("civic");             // UC7
        checkPalindromeUsingLinkedList("noon");         // UC8
        checkPalindromeUsingRecursion("refer");         // UC9
        checkPalindromeIgnoringCase("A man a plan a canal Panama"); // UC10

        // UC11
        PalindromeChecker service = new PalindromeChecker();
        printResult("UC11 (OOP Service)",
                "Was it a car or a cat I saw",
                service.checkPalindrome("Was it a car or a cat I saw"));

        // UC12
        System.out.println("\nUC12 (Strategy Pattern)");
        String test = "Never odd or even";
        PalindromeContext context = new PalindromeContext(new StackStrategy());
        printResult("Stack Strategy", test, context.execute(test));

        context.setStrategy(new DequeStrategy());
        printResult("Deque Strategy", test, context.execute(test));
    }

    // =====================================================
    // ================= NORMALIZER ========================
    // =====================================================

    private static String normalize(String input) {
        return input.toLowerCase()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-z]", "");
    }

    // =====================================================
    // ================= UC2 ===============================
    // =====================================================

    private static void checkPalindromeUsingStringBuilder(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        printResult("UC2 (StringBuilder)", input, input.equals(reversed));
    }

    // UC3
    private static void checkPalindromeUsingLoop(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--)
            reversed += input.charAt(i);
        printResult("UC3 (Loop)", input, input.equals(reversed));
    }

    // UC4
    private static void checkPalindromeUsingCharArray(String input) {
        char[] arr = input.toCharArray();
        int l = 0, r = arr.length - 1;
        boolean result = true;
        while (l < r) {
            if (arr[l] != arr[r]) { result = false; break; }
            l++; r--;
        }
        printResult("UC4 (Char Array)", input, result);
    }

    // UC5
    private static void checkPalindromeUsingStack(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        String reversed = "";
        while (!stack.isEmpty()) reversed += stack.pop();
        printResult("UC5 (Stack)", input, input.equals(reversed));
    }

    // UC6
    private static void checkPalindromeUsingQueueAndStack(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }
        boolean result = true;
        while (!queue.isEmpty())
            if (queue.remove() != stack.pop()) { result = false; break; }
        printResult("UC6 (Queue + Stack)", input, result);
    }

    // UC7
    private static void checkPalindromeUsingDeque(String input) {
        Deque<Character> deque = new LinkedList<>();
        for (char c : input.toCharArray()) deque.addLast(c);
        boolean result = true;
        while (deque.size() > 1)
            if (deque.removeFirst() != deque.removeLast()) { result = false; break; }
        printResult("UC7 (Deque)", input, result);
    }

    // UC8
    private static void checkPalindromeUsingLinkedList(String input) {
        Node head = null, tail = null;
        for (char c : input.toCharArray()) {
            Node node = new Node(c);
            if (head == null) head = tail = node;
            else { tail.next = node; tail = node; }
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
        Node first = head, second = prev;
        boolean result = true;
        while (second != null) {
            if (first.data != second.data) { result = false; break; }
            first = first.next;
            second = second.next;
        }
        printResult("UC8 (Linked List)", input, result);
    }

    // UC9
    private static void checkPalindromeUsingRecursion(String input) {
        printResult("UC9 (Recursion)", input,
                recursiveCheck(input, 0, input.length() - 1));
    }

    private static boolean recursiveCheck(String str, int l, int r) {
        if (l >= r) return true;
        if (str.charAt(l) != str.charAt(r)) return false;
        return recursiveCheck(str, l + 1, r - 1);
    }

    // UC10
    private static void checkPalindromeIgnoringCase(String input) {
        String normalized = normalize(input);
        printResult("UC10 (Ignore Case & Spaces)", input,
                normalized.equals(new StringBuilder(normalized).reverse().toString()));
    }

    // COMMON RESULT
    private static void printResult(String method, String input, boolean result) {
        if (result)
            System.out.println(method + " → \"" + input + "\" is a palindrome.");
        else
            System.out.println(method + " → \"" + input + "\" is NOT a palindrome.");
    }

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("Welcome to " + APP_NAME);
        System.out.println("Version: " + APP_VERSION);
        System.out.println("=====================================\n");
    }
}