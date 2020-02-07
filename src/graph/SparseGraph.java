package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/10 14:03
 * Description：稀疏无向图，，用邻接表
 */
public class SparseGraph implements Graph{
    //顶点个数
    private int n;
    //边个数
    private int m;

    private List<Set<Integer>> g;

    public SparseGraph(int n) {
        this.n = n;
        this.m = 0;
        this.g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            this.g.add(new HashSet<>());
        }
    }

    public Set getAdjacentEdge(int v){
        return g.get(v);
    }
    public void addEdge(int v, int w){

       if (!hasEdge(v, w)){
           this.g.get(v).add(w);
           this.g.get(w).add(v);
           m++;
       }
    }

    public boolean hasEdge(int v, int w){
        return this.g.get(v).contains(w);
    }

    public int v(){
        return n;
    }
    public int e(){
        return m;
    }

}
