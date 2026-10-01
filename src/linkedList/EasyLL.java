package linkedList;
import linkedList.LL.ListNode;

public class EasyLL {
    // 2. Add Two Numbers
    // TC: O(max(N1, N2)) | SC: O(max(N1, N2))
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1, t2 = l2;
        ListNode dummyNode = new ListNode(-1);
        ListNode currentNode = dummyNode;
        int carry = 0;

        while(t1 != null || t2 != null){
            int sum = carry;
            if(t1 != null) sum += t1.val;
            if(t2 != null) sum += t2.val;
            ListNode newNode = new ListNode(sum%10);
            carry = sum / 10;
            currentNode.next = newNode;
            currentNode = currentNode.next;

            if(t1 != null) t1 = t1.next;
            if(t2 != null) t2 = t2.next;
        }
        if(carry > 0){
            currentNode.next = new ListNode(carry);
        }
        return dummyNode.next;
    }

    // 328. Odd Even Linked List
    // TC: O(N) | SC: O(1)
    public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head, even = head.next, evenHead = head.next;
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    // Sort a linked list of 0s, 1s and 2s
    // TC: O(N) | SC: O(1)
    public static ListNode segregate(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummyNode0 = new ListNode(-1);
        ListNode zero = dummyNode0;
        ListNode dummyNode1 = new ListNode(-1);
        ListNode one = dummyNode1;
        ListNode dummyNode2 = new ListNode(-1);
        ListNode two = dummyNode2;

        ListNode temp = head;
        while(temp != null){
            if(temp.val == 0){
                zero.next = temp;
                zero = zero.next;
            }
            else if(temp.val == 1){
                one.next = temp;
                one = one.next;
            }
            else{
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }
        if(dummyNode1.next != null) zero.next = dummyNode1.next;
        else zero.next = dummyNode2.next;
        one.next = dummyNode2.next;
        two.next = null;
        return dummyNode0.next;
    }

    // 19. Remove Nth Node From End of List
    // TC: O(len) | SC: O(1)
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++) fast = fast.next;

        if(fast == null) return head.next;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // TC: O(len) + O(len - N). Worst Case TC: O(2 * len) | SC: O(1)
    public static ListNode removeNthFromEndW(ListNode head, int n) {
        int ctr = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            ctr++;
        }
        int k = ctr - n;
        if(k == 0) return head = head.next;

        temp = head;
        for (int i = 0; i < k - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    // 206. Reverse Linked List
    // TC: O(N) | SC: O(1)
    public static ListNode reverseList(ListNode head) {
        ListNode back = null;
        ListNode currentNode = head;
        while(currentNode != null){
            ListNode front = currentNode.next;
            currentNode.next = back;
            back = currentNode;
            currentNode = front;
        }
        return back;
    }

    // TC: O(N) | SC: O(N) (Recursion Stack Space Only)
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = reverseListRecursive(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }
}
