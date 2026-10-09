package linkedList;
import linkedList.DLL.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ProblemsDLL {
    // Delete All Occurrences in DLL
    // TC: O(N) | SC: O(1)
    public ListNode deleteAllOccurOfX(ListNode head, int x) {
        ListNode temp = head;
        while(temp != null){
            if(temp.val == x){
                if(temp == head) head = head.next;
                ListNode nextNode = temp.next;
                ListNode prevNode = temp.prev;
                if(nextNode != null) nextNode.prev = prevNode;
                if(prevNode != null) prevNode.next = nextNode;
                temp = nextNode;
            }
            else temp = temp.next;
        }
        return head;
    }

    // Pair Sum in Sorted Doubly Linked List
    // TC: O(2N) | SC: O(1)
    public ArrayList<ArrayList<Integer>> givenSumPairs(ListNode head, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (head == null) return res;
        ListNode left = head;
        ListNode right = head;
        while(right.next != null) right = right.next;

        while(left != right && right.next != left){
            if(left.val + right.val == target){
                res.add(new ArrayList<>(List.of(left.val, right.val)));
                left = left.next;
                right = right.prev;
            }
            else if(left.val + right.val < target){
                left = left.next;
            }
            else{
                right = right.prev;
            }
        }

        return res;
    }

    // Remove duplicates from a sorted DLL
    // TC: O(N) | SC: O(1)
    public ListNode removeDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            ListNode nextNode = temp.next;
            while (nextNode != null && nextNode.val == temp.val) {
                nextNode = nextNode.next;
            }
            temp.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = temp;
            }
            temp = nextNode;
        }
        return head;
    }
}
