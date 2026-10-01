package linkedList;

import java.sql.SQLOutput;

public class DLL {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode() {}
        ListNode(int val){
            this.val = val;
            next = null;
            prev = null;
        }
        ListNode(int val, ListNode next, ListNode prev){
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public static ListNode makeDLL(int[] arr){
        if(arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode previousNode = head;
        for (int i = 1; i < arr.length; i++) {
            previousNode.next = new ListNode(arr[i], null, previousNode);
            previousNode = previousNode.next;
        }
        return head;
    }

    public static ListNode insertBeforeHead(ListNode head, int element){
        ListNode newHead = new ListNode(element, head, null);
        if (head != null) {
            head.prev = newHead;
        }
        return newHead;
    }

    public static ListNode insertBeforeTail(ListNode head, int element){
        if(head == null){
            return new ListNode(element);
        }

        ListNode tail = head;
        while(tail.next != null) tail = tail.next;

        ListNode prevNode = tail.prev;
        ListNode newNode = new ListNode(element, tail, prevNode);
        tail.prev = newNode;
        if (prevNode == null) {   // tail was the only node, so newNode is the new head
            return newNode;
        }

        prevNode.next = newNode;
        return head;
    }

    public static void insertBeforeNode(ListNode n, int element){
        ListNode prevNode = n.prev;
        ListNode newNode = new ListNode(element, n, prevNode);
        prevNode.next = newNode;
        n.prev = newNode;
    }

    public static ListNode insertBeforeKthNode(ListNode head, int element, int k){
        if(k == 1) return insertBeforeHead(head, element);
        if(head == null || k < 1) return head;

        ListNode temp = head;
        int ctr = 1;

        while(temp != null && ctr < k){
            temp = temp.next;
            ctr++;
        }
        if (temp == null) return head;

        ListNode prevNode = temp.prev;
        ListNode newNode = new ListNode(element, temp, prevNode);
        prevNode.next = newNode;
        temp.prev = newNode;
        return head;
    }

    public static ListNode removeHead(ListNode head){
        if(head == null) return head;
        ListNode temp = head;
        head = head.next;
        if(head != null) head.prev = null;
        temp.next = null;
        return head;
    }

    public static ListNode removeTail(ListNode head){
        if(head == null || head.next == null) return null;

        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
        }
        ListNode previousNode = tail.prev;
        tail.prev = null;
        previousNode.next = null;

        return head;
    }

    public static ListNode removeKthNode(ListNode head, int k){
        if(head == null || k < 1) return head;

        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;    // k > length: nothing to remove

        ListNode back = temp.prev;
        ListNode front = temp.next;


        if(back == null) head = front;    // removing the head
        else back.next = front;
        if(front != null) front.prev = back;

        // detach removed node
        temp.next = null;
        temp.prev = null;

        return head;
    }

    public static void removeNode(ListNode temp){
        ListNode prevNode = temp.prev;
        ListNode nextNode = temp.next;

        if(nextNode == null){
            prevNode.next = null;
            temp.prev = null;
            return;
        }

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        temp.prev = temp.next = null;
    }

    public static void removeNodeShorter(ListNode temp){
        temp.prev.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        temp.prev = temp.next = null;
    }

    // TC: O(N) | SC: O(1)
    public static ListNode reverseDLL(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode current = head;
        ListNode prevNode = null;

        while(current != null){
            prevNode = current.prev;
            current.prev = current.next;
            current.next = prevNode;
            current = current.prev;
        }
        head = prevNode.prev;
        return head;
    }

    public static void printDLL(ListNode head) {
        StringBuilder sb = new StringBuilder("X <--> ");
        for (ListNode node = head; node != null; node = node.next) {
            sb.append(node.val).append(" <--> ");
        }
        sb.append("X");
        System.out.println(sb);
    }
}
