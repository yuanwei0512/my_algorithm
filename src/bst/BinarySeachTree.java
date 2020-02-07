package bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/9 13:30
 * Description：TODO
 */
public class BinarySeachTree<V> {

    private Node root;
    private int count;

    public BinarySeachTree() {
        root = null;
        count = 0;
    }
    //前序遍历
    void preOrder(){
        preOrder(root);
    }

    //中序遍历
    void inOrder(){
        inOrder(root);
    }

    //后续遍历
    void postOrder(){
        postOrder(root);
    }

    //层次遍历
    void levelOrder(){
        levelOrder(root);


    }



    //插入
    public void  insert(int key, V value){
        root = insert(root, key, value);
        count++;
    }
    //查询是否包含
    public boolean contains(int key){
        return contains(root, key);
    }

    //查询
    public V search(int key){
        return search(root, key);
    }

    //获取大小
    public int size(){
        return count;
    }

    //判断是否为空
    public boolean isEmpty(){
        return count == 0 ? true : false;
    }

    //查找最大值
    public Node maxmum(){
        return maxmum(root);
    }
    //删除最大值
    public void removeMax(){
        removeMax(root);
    }

    //查找最小值
    public Node minmum(){
        return minmum(root);
    }
    //删除最小值
    public void removeMin(){
        removeMin(root);
    }
    //根据key进行删除操作
    public void remove(int key){
        remove(root, key);
    }

    public void floorAndCeil(int key){
        floorAndCeil(root, key);
    }



    private V search(Node node, int key) {
        if (node == null){
            return null;
        }
        if (node.key == key){
            return node.value;
        }
        if (key < node.key){
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }

    }

    private boolean contains(Node node, int key) {
        if (node == null){
            return false;
        }
        if (node.key == key){
            return true;
        }
        if (key < node.key){
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }
    }

    //查找
    private Node insert(Node node, int key, V value) {
        //判断节点是否为空
        if (null == node){
            return new Node(key, value);
        }
        //如果节点相等直接替换
        if (node.key == key){
            node.value = value;

        }
        //如果key节点小就放到左边，否则放到右边
        if (key < node.key){
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }
        return node;
    }


    private void preOrder(Node node) {

        if (node != null){
            System.out.print(node.value + "  ");
            preOrder(node.left);
            preOrder(node.right);
        }

    }
    private void inOrder(Node node) {
        if (node != null){
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    private void postOrder(Node node) {
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    private void levelOrder(Node node) {
        Queue<Node> queue = new LinkedList<>();

        if (node != null){
            queue.offer(node);
        }
        //当队列不为空时循环
        while (!queue.isEmpty()){
            //取出队列里的内容进行遍历
            Node pollNode = queue.poll();
            System.out.print(pollNode.value + "  ");
            //将node的左孩子和右孩子添加到队列中
            if (pollNode.left != null){
                queue.offer(pollNode.left);
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
            }
        }

    }

    private Node maxmum(Node node) {
        if (node.right == null){
            return node;
        } else {
            return maxmum(node.right);
        }
    }


    private Node minmum(Node node) {
        if (node.left == null){
            return node;
        } else {
            return minmum(node.left);
        }
    }

    private Node removeMax(Node node) {

        if (node.right == null){
            return node.left;
        }
        node.right = removeMax(node.right);
        count--;
        return node;
    }

    private Node removeMin(Node node) {

        if (node.left == null){
            return node.right;
        }
        node.left = removeMin(node.left);
        count--;
        return node;

    }

    private Node remove(Node node, int key) {
        if (node == null){
            return null;
        }
        //当key小于当前node时往左边继续地柜
        if (key < node.key){
            node.left = remove(node.left, key);
        }
        //当key大于当前node时往左边继续地柜
        if (key > node.key){
            node.right = remove(node.right, key);
        }
        ///当找到key时
        if (key == node.key){
            //当左子树为空时让右子树覆盖
            if (node.left == null){
                node = node.right;
                count--;
                return node;
            }
            //当右子树为空时让左子树覆盖
            if (node.right == null){
                node = node.left;
                count--;
                return node;
            }

            //当左右两边都有子树时   用hubbard deletion进行删除，，就是用右子树种最小的来覆盖当前的node
            Node successor = minmum(node.right);
            removeMin(node.right);
            successor.left = node.left;
            successor.right = node.right;
            return successor;
        }
        return null;

    }

    private void floorAndCeil(Node node, int key) {
        if (node != null){
            if (node.left !=null && node.left.key < key && node.key > key){
                System.out.println("floor is " + node.left.value + "    ceil is" + node.value);
            }
            floorAndCeil(node.left, key);
            if (node.right !=null && node.key < key && node.right.key > key){
                System.out.println("floor is " + node.value + "    ceil is" + node.right.value);
            }
            floorAndCeil(node.right, key);

        }


    }

    private class Node{
        public int key;
        public V value;
        public Node left;
        public Node right;

        public Node(Integer key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right =null;
        }
    }

    public static void main(String[] args) {
        BinarySeachTree<String> tree = new BinarySeachTree();
        tree.insert(41, "41");
        tree.insert(22, "22");
        tree.insert(33, "33");
        tree.insert(37, "37");
        tree.insert(58, "58");
        tree.insert(50, "50");
        tree.insert(42, "42");
        tree.insert(53, "53");
        tree.insert(60, "60");
        tree.insert(59, "59");
        tree.insert(63, "63");
        tree.floorAndCeil(45);
    }

}
