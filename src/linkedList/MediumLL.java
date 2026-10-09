package linkedList;
import linkedList.LL.ListNode;

import java.util.HashMap;

import static linkedList.EasyLL.reverseList;

public class MediumLL {
    // Add one to a number represented by LL
    // TC: O(N) | SC: O(N) (Recursion Stack Space Only)
    public static ListNode addOne(ListNode head) {
        int carry = helperAddOne(head);
        if(carry == 1){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }
    private static int helperAddOne(ListNode temp) {
        if(temp == null){
            return 1;
        }
        int carry = helperAddOne(temp.next);
        temp.val = temp.val + carry;
        if(temp.val < 10) return 0;
        temp.val = 0;
        return 1;
    }

    // TC: O(3N) | SC: O(1)
    public static ListNode addOneItr(ListNode head) {
        head = reverseList(head);
        ListNode temp = head;
        int carry = 1;
        while(temp != null){
            temp.val = temp.val + carry;
            if(temp.val < 10){
                carry = 0;
                break;
            }
            else{
                temp.val = 0;
                carry = 1;
                temp = temp.next;
            }
        }

        head = reverseList(head);
        if(carry == 1){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }

    // 876. Middle of the Linked List
    public static ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 2095. Delete the Middle Node of a Linked List
    public static ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        fast = fast.next.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // 234. Palindrome Linked List
    // TC: O(2N) | SC: O(1)
    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = reverseList(slow.next);

        ListNode first = head;
        ListNode second = newHead;
        while(second != null){
            if(first.val != second.val) {
                reverseList(newHead);
                return false;
            }
            first = first.next;
            second = second.next;
        }
        reverseList(newHead);
        return true;
    }

    // 160. Intersection of Two Linked Lists
    // // TC: O(N1 + N2) | SC: O(1)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode t1 = headA;
        ListNode t2 = headB;
        while(t1 != t2){
            t1 = t1.next;
            t2 = t2.next;

            if(t1 == t2) return t1;
            if(t1 == null) t1 = headB;
            if(t2 == null) t2 = headA;
        }
        return t1;
    }

    // TC: O(N1) * InsertionCost + O(N2) * ContainsCheckCost | SC: O(N1)
    public static ListNode getIntersectionNodeBrute(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> mpp = new HashMap<>();
        ListNode temp = headA;
        while(temp != null){
            mpp.put(temp, 1);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null){
            if(mpp.containsKey(temp))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // 141. Linked List Cycle
    // TC: O(N) | SC: O(1)
    public static boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

    // 142. Linked List Cycle II
    // TC: O(N) | SC: O(1)
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    // Length of loop in LL
    // TC: O(N) | SC: O(1)
    public static int findLengthOfLoop(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                fast = fast.next;
                int length = 1;
                while(fast != slow){
                    length++;
                    fast = fast.next;
                }
                return length;
            }
        }
        return 0;
    }

    // 25. Reverse Nodes in k-Group
    // TC: O(2N) | SC: O(1)
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode nextNode = null;
        ListNode prevNode = null;
        while(temp != null){
            ListNode kThNode = findKthNode(temp, k);
            if(kThNode == null){
                if(prevNode != null) prevNode.next = temp;
                break;
            }

            nextNode = kThNode.next;
            kThNode.next = null;

            reverseList(temp);

            if(temp == head){
                head = kThNode;
            }
            else{
                prevNode.next = kThNode;
            }
            prevNode = temp;
            temp = nextNode;
        }
        return head;
    }
    private static ListNode findKthNode(ListNode t, int k){
        k -= 1;
        while(t != null && k > 0){
            k--;
            t = t.next;
        }
        return t;
    }

    // 61. Rotate List
    // TC: O(N) | SC: O(1)
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 1;
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
            len++;
        }
        k = k % len;
        if(k == 0) return head;
        tail.next = head;
        ListNode newLastNode = head;
        for(int i = 0; i < len - k - 1; i++){
            newLastNode = newLastNode.next;
        }
        head = newLastNode.next;
        newLastNode.next = null;
        return head;
    }

    // 21. Merge Two Sorted Lists
    // TC: O(N1 + N2) | SC: O(1)
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dNode = new ListNode(-1);
        ListNode temp = dNode;
        ListNode l1 = list1, l2 = list2;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                temp.next = l1;
                temp = l1;
                l1 = l1.next;
            }
            else{
                temp.next = l2;
                temp = l2;
                l2 = l2.next;
            }
        }
        if(l1 != null){
            temp.next = l1;
        }
        else{
            temp.next = l2;
        }

        return dNode.next;
    }

    // 148. Sort List
    // TC: O((N + N/2) log N) | SC: O(log N)
    // here finding the middle element takes N/2 at each step
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode mid = middleNode1(head);
        ListNode right = mid.next;
        mid.next = null;
        ListNode left = head;

        left = sortList(left);
        right = sortList(right);

        return mergeTwoLists(left, right);
    }
    private ListNode middleNode1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next; // on even lists we need first middle not second middle

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static class Node {
            int val;
            Node next;
            Node random;

            public Node(int val) {
                this.val = val;
                this.next = null;
                this.random = null;
            }
        }

    // 138. Copy List with Random Pointer
    // TC: O(2N) | SC: O(N) + O(N)
    public static Node copyRandomList(Node head) {
        Node temp = head;
        HashMap<Node, Node> map = new HashMap<>();

        while(temp != null){
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
        while(temp != null){
            Node copyNode = map.get(temp);
            copyNode.next = map.get(temp.next);
            copyNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    // TC: O(3N) | SC: O(N) just to create a new list
    public static Node copyRandomListInplace(Node head){
        Node temp = head;
        // inserting copy nodes in between the nodes
        while(temp != null){
            Node copyNode = new Node(temp.val);
            copyNode.next = temp.next;
            temp.next = copyNode;
            temp = temp.next.next;
        }

        temp = head;
        // connecting random pointers to newly created nodes
        while(temp != null){
            Node copyNode = temp.next;
            if(temp.random != null)
                copyNode.random = temp.random.next;
            else copyNode.random = null;
            temp = temp.next.next;
        }

        // extracting the copied list
        Node dNode = new Node(-1);
        Node res = dNode;
        temp = head;
        while(temp != null){
            res.next = temp.next;
            temp.next = temp.next.next;
            res = res.next;
            temp = temp.next;
        }

        return dNode.next;
    }
}
