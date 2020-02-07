package unionfind;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/9 20:04
 * Description：TODO
 */
public class UnionFind {

    private int[] id;
    private int count;

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int p){
        return id[p];
    }
    public boolean isConnection(int p, int q){
        return id[p] == id[q];
    }

    public void unionElement(int p, int q){
        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < count; i++) {
            if (id[i] == qId){
                id[i] = pId;
            }
        }
    }

}
