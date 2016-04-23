
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy.mamishev
 */
public class T2 {

    // Please do not change this interface
    interface ListNode {

        int getItem();

        ListNode getNext();

        void setNext(ListNode next);
    }

    public static ListNode reverse2(ListNode node) {
        /*
          Please implement this method to
          reverse a given linked list.
         */
        ListNode index = node;
        List<ListNode> lista = new ArrayList<T2.ListNode>();
        lista.add(index);
        while (index.getNext() != null) {
            index = index.getNext();
            lista.add(index);
        }
        for (int i = lista.size() - 1; i > 0; i--) {
            lista.get(i).setNext(lista.get(i - 1));
        }
        lista.get(0).setNext(null);
        return lista.get(lista.size() - 1);
    }

    public static ListNode reverse(ListNode node) {
        /*
          Please implement this method to
          reverse a given linked list.
         */
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.getNext();
            node.setNext(prev);
            prev = node;
            node = next;
        }
        return prev;
    }

    class ListNode_ implements ListNode {

        int item;
        private ListNode next;

        @Override
        public int getItem() {
            return item;
        }

        @Override
        public ListNode getNext() {
            return next;
        }

        @Override
        public void setNext(ListNode next) {
            this.next = next;
        }

    }

}
