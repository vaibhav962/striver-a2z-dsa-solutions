package linkedList;
import linkedList.LL.ListNode;
import static linkedList.EasyLL.reverseList;

public class MediumLL {
    // Add one to a number represented by LL
    // TC: O(N) | SC: O(N) (Recursion Stack Space Only)
    public ListNode addOne(ListNode head) {
        int carry = helperAddOne(head);
        if(carry == 1){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }
    private int helperAddOne(ListNode temp) {
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
    public ListNode addOneItr(ListNode head) {
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
    public boolean isPalindrome(ListNode head) {
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
}
