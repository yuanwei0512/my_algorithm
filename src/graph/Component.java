package graph;

import java.util.Set;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/10 14:55
 * Description：TODO
 */
public class Component {

    private Graph graph;
    private boolean[] visited;
    //连通分量的个数
    private int ccount;

    public Component(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.v()];

        for (int i = 0; i < graph.v(); i++) {
            if (!visited[i]){
                dfs(i);
                ccount++;
            }
        }

    }

    public int getCcount(){
        return ccount;
    }

    private void dfs(int v) {
        Set<Integer> adjacentEdge = graph.getAdjacentEdge(v);
        adjacentEdge.forEach(x ->{
            if (!visited[x]){
                visited[x] = true;
                dfs(x);
            }

        });

    }


}
