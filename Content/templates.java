import java.util.*;

public class CodeTemplates {

    public static void main(String[] args) {
        // You can add test calls here
    }

    // Array Operations
    void arrayOperations() {
        int[] arr = new int[10];          // Initialize an array of size 10
        int[] arr2 = {1, 2, 3, 4, 5};     // Initialize and assign values
        arr[0] = 5;                       // Access and modify element

        // Traversing
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        for (int num : arr2) {
            System.out.println(num);
        }
    }

    // String Operations
    void stringOperations() {
        String str = "Hello";
        char c = str.charAt(0);            // Access character at index 0

        // Traversing
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
        for (char ch : str.toCharArray()) {
            System.out.println(ch);
        }

        // Character Frequency Map
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char ch : str.toCharArray()) {
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }

        // Substring Extraction
        String substr = str.substring(0, 5); // "Hello"

        // Finding Index of a Character or Substring
        int index = str.indexOf('o');           // 4 (first occurrence)
        int lastIndex = str.lastIndexOf('o');   // 8 (last occurrence)
        int substrIndex = str.indexOf("world"); // 7

        // Character to Integer Conversion
        char charNum = '5';
        int num = Character.getNumericValue(charNum); // 5
        int num2 = charNum - '0'; // 5 (another way)

        // Integer to Character Conversion
        int intNum = 5;
        char intToChar = (char) (intNum + '0'); // '5'

        // String Reversal
        String reversed = new StringBuilder(str).reverse().toString(); // "olleH"

        // Check if String Contains a Substring
        boolean contains = str.contains("world"); // true

        // Split String by a Delimiter
        String[] fruits = str.split(","); // ["apple", "banana", "orange"]

        // Join Array Elements into a Single String
        String[] fruitsArr = {"apple", "banana", "orange"};
        String joined = String.join(", ", fruitsArr); // "apple, banana, orange"

        // Convert String to UpperCase/LowerCase
        String upper = str.toUpperCase(); // "HELLO"
        String lower = str.toLowerCase(); // "hello"
    }

    // List and Set Operations
    void listSetOperations() {
        // Array to ArrayList Conversion
        String[] array = {"A", "B", "C"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));

        // ArrayList to Array Conversion
        String[] arrayFromList = arrayList.toArray(new String[0]);

        // Initialize HashSet with ArrayList
        HashSet<Integer> hashSetFromList = new HashSet<>(Arrays.asList(1, 2, 3));

        // Initialize ArrayList with HashSet
        ArrayList<Integer> arrayListFromSet = new ArrayList<>(hashSetFromList);

        // List Reversal
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Collections.reverse(list); // [5, 4, 3, 2, 1]
    }

    // HashSet Operations
    void hashSetOperations() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.contains(1);  // Returns true
        set.remove(2);
    }

    // HashMap Operations
    void hashMapOperations() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.get("a");     // Returns 1
        map.containsKey("a"); // Returns true
        map.remove("b");
    }

    // Stack Operations
    void stackOperations() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();        // Removes and returns 2
        stack.peek();       // Returns 1 without removing
    }

    // Queue Operations
    void queueOperations() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.poll();       // Removes and returns 1
        queue.peek();       // Returns 2 without removing
    }

    // Priority Queue Operations
    void priorityQueueOperations() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(5);
        pq.poll();          // Removes and returns 5 (smallest element)
        pq.peek();          // Returns 10 (next smallest element)
    }

    // Linked List Operations
    void linkedListOperations() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.addFirst(0);   // Adds 0 at the beginning
        linkedList.addLast(3);    // Adds 3 at the end
        linkedList.get(1);        // Returns element at index 1
        linkedList.remove(2);     // Removes element at index 2
    }

    // Common Algorithms
    // Binary Search
    int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Target not found
    }

    // Depth-First Search (DFS) using Stack
    void dfs(int start, List<List<Integer>> adjList, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                for (int neighbor : adjList.get(node)) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // Breadth-First Search (BFS) using Queue
    void bfs(int start, List<List<Integer>> adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // Character Utilities
    void characterUtilities() {
        // Check if Character is a Digit
        char c = '5';
        boolean isDigit = Character.isDigit(c); // true

        // Check if Character is a Letter
        char letter = 'A';
        boolean isLetter = Character.isLetter(letter); // true
    }
}
