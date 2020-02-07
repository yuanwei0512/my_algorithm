package unionfind;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/9 20:04
 * Description：TODO
 */
public class UnionFind2 {

    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind2(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = 0;
            parent[i] = i;
        }
    }

    public int find(int p){
        if (parent[p] == p){
            return p;
        }

        parent[p] = find(p);

        return parent[p];

    }
    public boolean isConnection(int p, int q){
        return find(p) == find(q);
    }

    public void unionElement(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;

        } else if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;

        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] ++;
        }

    }

}
