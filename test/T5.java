
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dmitriy.mamishev
 */
import java.util.*;

public class T5 {

    // Please do not change this interface
    public static interface Node {
        int getValue();
        List<Node> getChildren();
    }
     public static double getAverage(Node root) {
        /*
          Please implement this method to
          return the average of all node values (Node.getValue()) in the tree.
         */
        List<Node> n = root.getChildren();
        int sum = 0;
        for (Node node : n) {
            sum += node.getValue();
        }
        return new Double(sum / n.size());
    }

    class NodeC implements Node {

        private int value;
        List<Node> node;

        @Override
        public int getValue() {
            return this.value;
        }

        @Override
        public List<Node> getChildren() {
            return this.node;
        }

    }
}
