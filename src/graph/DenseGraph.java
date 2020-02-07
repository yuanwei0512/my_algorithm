package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/10 14:04
 * Description：稠密图，，用邻接矩阵,,,无向图
 */
public class DenseGraph implements Graph {

    //顶点个数
    private int n;
    //边个数
    private int m;

    private int[][]g;

    public DenseGraph(int n) {
        this.n = n;
        this.m = m;
        g = new int[3][5];
    }

    public Set getAdjacentEdge(int v){

        Set<Integer> result = new HashSet<>();
        for (int i : g[v]) {
            if (g[v][i] == 1){
                result.add(g[v][i]);
            }
        }
        return result;
    }

    public void addEdge(int v, int w){

        if (!hasEdge(v,w)){
            g[v][w] = 1;
            g[w][v] = 1;
            m++;
        }
    }

    public boolean hasEdge(int v, int w){
        return g[v][w] == 0 ? false : true;
    }

    public int v(){
        return n;
    }
    public int e(){
        return m;
    }
}
