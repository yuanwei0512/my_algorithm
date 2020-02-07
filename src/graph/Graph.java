package graph;

import java.util.Set;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/10 14:53
 * Description：TODO
 */
public interface Graph {

    Set getAdjacentEdge(int v);

    void addEdge(int v, int w);

    boolean hasEdge(int v, int w);

    int v();

    int e();

}
