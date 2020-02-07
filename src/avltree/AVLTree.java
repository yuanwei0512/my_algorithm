package avltree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        private K key;
        private V value;
        private Node left, right;
        private int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    //获取高度
    private int getHeight(Node node){
        if (node != null){
            return 0;
        }
        return node.height;

    }
    //平衡因子，，，左子树减去右子树
    private int getBalanceFactor(Node node){
        if (node != null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBST(){
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1; i < keys.size(); i++){
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> list){
        if (node == null){
            return;
        }

        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }


    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        node.height = 1 + Math.max(node.left.height, node.right.height);

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced : " + balanceFactor);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            //LL
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.left) <= 0){
            //RR
            return leftRotate(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) <= 0){
            //LR
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < 1 && getBalanceFactor(node.left) >= 0){
            //RL
            node.right = leftRotate(node.right);
            return rightRotate(node);
        }

        return node;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x ---------RR
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    private Node leftRotate(Node y) {

        Node x = y.right;
        Node t2 = x.left;

        x.left = y;
        y.right = t2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x------LL
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    //右旋转
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }



    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }


}
