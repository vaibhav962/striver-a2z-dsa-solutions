package linkedList;

public class Main {
    public static void main(String[] args) {
        // Reverse a DLL
//        DLL.ListNode myList = DLL.makeDLL(new int[]{1, 2, 3, 4});
//        DLL.printDLL(myList);
//        myList = DLL.reverseDLL(myList);
//        DLL.printDLL(myList);

        // Sort a linked list of 0s, 1s and 2s
//        LL.ListNode list =  LL.makeLL(new int[]{1, 2, 2, 1, 2, 0, 2, 2});
//        LL.printLL(list);
//        list = EasyLL.segregate(list);
//        LL.printLL(list);

        // Remove Nth Node From End of List
        LL.ListNode list =  LL.makeLL(new int[]{1, 2, 2, 1, 2, 0, 2, 2});
        LL.printLL(list);
        list = EasyLL.removeNthFromEnd(list, 3);
        LL.printLL(list);
    }
}
