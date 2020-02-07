package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/15 18:48
 * Description：TODO
 */
public class Trie {

    private Node root;
    private int size;


    public Trie(Node root) {
        this.root = root;
        size = 0;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {

        if (index == word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);

        if (c != '.'){
            if (node.next.containsKey(c)){
                return match(node.next.get(c), word, index++);
            }
            return false;
        }


        for (Character character : node.next.keySet()) {
           if (match(node.next.get(character), word, index)){
               return true;
           }
        }
        return false;

    }

    public void add(String word){
        Node cur = root;
        //循环遍历word的字符，
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            //如果不包含就创建
            if (!cur.next.containsKey(c)){
                cur.next.put(c, new Node());
            }
            cur =cur.next.get(c);
        }
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 判断是否包含
     * @param word
     * @return
     */
    public boolean contain(String word){
        Node cur = root;
        //循环遍历word的字符，
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            //如果不包含就创建
            if (!cur.next.containsKey(c)){
               return false;
            }
            cur =cur.next.get(c);
        }
        return cur.isWord;
    }


    public void addRecursion(Node node,String word){
        //当word空时直接返回
        if (null == word || word.length() == 0){
            return;
        }
        //获取当前要插入的字符
        char c = word.charAt(0);
        //获取要插入的字符串
        String substring = word.substring(1, word.length() - 1);


        //判断是否有此键值对，没有就创建， 然后继续递归
        if (node.next.containsKey(c)) {
            addRecursion(node.next.get(c), substring);
        } else {
            node.next.put(c, new Node());
            addRecursion(node.next.get(c), substring);

        }

        //当剩余要遍历的字符为空时就说明是遍历到字符串最后一个的位置了
        //如果之前未被判定为单词就要设置为true，并且size++
        if ((null == substring || substring.length() == 0) && !node.next.get(c).isWord ){
            node.next.get(c).isWord = true;
            size++;
        }

    }


    public int getSize(){
        return size;
    }


    private class Node{
        private boolean isWord;
        private Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

}
