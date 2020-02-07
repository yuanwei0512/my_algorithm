package graph;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/10 15:02
 * Description：TODO
 */
public class Test {

    public static void main(String[] args) {
        Graph graph = new SparseGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 4);
        graph.addEdge(3, 0);
        graph.addEdge(3, 4);

        graph.addEdge(4, 2);
        graph.addEdge(4, 3);

        graph.addEdge(5, 6);
        graph.addEdge(6, 5);
        System.out.println(graph.getAdjacentEdge(0));
        System.out.println(graph.getAdjacentEdge(4));

        Component component = new Component(graph);
        System.out.println(component.getCcount());
    }

}
