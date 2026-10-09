package linkedList;

public class MultiLevelLinkedList {
    private static class Node {
        int data;
        Node next;
        Node child;

        Node(int x) {
            data = x;
            next = null;
            child = null;
        }
    }

    // Flattening a Multi Level Linked List
    // TC: O(2(N * M)) | SC: O(1)
    public Node flatten(Node head) {
        if(head == null || head.next == null) return head;
        Node mergedHead = flatten(head.next);
        return merge2Lists(head, mergedHead);
    }

    public Node merge2Lists(Node list1, Node list2){
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        while(list1 != null && list2 != null){
            if(list1.data < list2.data){
                res.child = list1;
                res = list1;
                list1 = list1.child;
            }
            else{
                res.child = list2;
                res = list2;
                list2 = list2.child;
            }
            res.next = null;
        }
        if(list1 != null) res.child = list1;
        else res.child = list2;

        return dummyNode.child;
    }
}
