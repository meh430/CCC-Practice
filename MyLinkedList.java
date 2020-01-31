import java.util.Arrays;
public class MyLinkedList {
    LinkedList<String> list = new LinkedList<>();

    static class Node {
        String info;
        Node next;

        Node(String s) {
            info = s;
            next = null;
        }
    }

    Node head = null;

    void addEnd(Node dataCont) {
        if(head == null) {
            head = dataCont;
        } else {
            Node iter = head;
            while(iter.next != null) {
                iter = iter.next;
            }

            iter.next = dataCont;
        }
    }

    void printList() {
        Node iter = head;

        while(iter != null) {
            System.out.print(iter.info + ", ");
            iter = iter.next;
        }
    }

    public static void main(String[]args) {
        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addEnd(new Node("this"));
        myLinkedList.addEnd(new Node("is"));
        myLinkedList.addEnd(new Node("so"));
        myLinkedList.addEnd(new Node("cool"));

        myLinkedList.printList();
    }
}
