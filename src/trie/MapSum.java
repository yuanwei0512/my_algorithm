package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/15 20:24
 * Description：TODO
 */
public class MapSum {

    private Node root;

    public void insert(String key, int val) {

        Node cur = root;
        for(int i = 0 ; i < key.length() ; i ++){
            char c = key.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node){

        int sum = 0;
        for (Character character : node.next.keySet()) {
            sum += sum(node.next.get(character));
        }
        return sum;
    }


    /** Initialize your data structure here. */
    public MapSum() {

        root = new Node();
    }

    private class Node{

        private int value;
        private Map<Character, Node> next;

        public Node(int value){
            this.value = value;
            next = new HashMap<>();
        }
        public Node(){
            this(0);
        }
    }



}
