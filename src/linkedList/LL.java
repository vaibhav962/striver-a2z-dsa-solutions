package linkedList;

public class LL {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public ListNode makeLL(int[] arr){
        if(arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for(int i = 1; i < arr.length; i++){
            ListNode t = new ListNode(arr[i]);
            temp.next = t;
            temp = t;
        }
        return head;
    }

    public int lengthOfLL(ListNode head){
        int ctr = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            ctr++;
        }
        return ctr;
    }

    public static ListNode insertBeforeHead(ListNode head, int element){
        return new ListNode(element, head);
    }

    public static ListNode insertAtEnd(ListNode head, int element){
        if(head == null) return new ListNode(element);
        ListNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new ListNode(element);
        return head;
    }

    public static ListNode insertBeforeKthNode(ListNode head, int element, int k){
        if(head == null || k < 1) {
            if(k == 1) return new ListNode(element);
            else return null;
        }
        if(k == 1){
            return new ListNode(element, head);
        }
        int ctr = 0;
        ListNode temp = head;
        while(temp != null){
            ctr++;
            if(ctr == k-1){
                ListNode newNode = new ListNode(element, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    public static ListNode insertBeforeX(ListNode head, int element, int x){
        if(head == null) {
            return null;
        }
        if(head.val == x){
            return new ListNode(element, head);
        }
        ListNode temp = head;
        while(temp.next != null){
            if(temp.next.val == x){
                ListNode newNode = new ListNode(element, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    public static ListNode removeHead(ListNode head){
        if(head == null) return head;
        head = head.next;
        return head;
    }

    public static ListNode removeTail(ListNode head){
        if(head == null || head.next == null) return null;

        ListNode temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;

        return head;
    }

    public static ListNode removeKthNode(ListNode head, int k){
        if(head == null || k < 1) return head;
        if(k == 1){
            head = head.next;
            return head;
        }
        int ctr = 0;
        ListNode temp = head, prev = null;
        while(temp != null){
            ctr++;
            if(ctr == k){
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

    public static ListNode removeElement(ListNode head, int x){
        if(head == null) return head;
        if(head.val == x){
            head = head.next;
            return head;
        }
        ListNode temp = head, prev = null;
        while(temp != null){
            if(temp.val == x){
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

}
