import java.util.*;

//my implementation of a graph
public class Graph {
    // So here, we have a list of adjacency lists
    // the size of this list will become the number of vertices the graph has
    // Each adjacency list object has the value represented by the vertex
    // and a list of all the values that the vertex is connected to
    LinkedList<AdjacencyList> graphList = new LinkedList<>();

    // 1 - vertex you want to add to
    // 2 -value that should be connected to that vertex
    // if bidirectional, that value as vertex will have the vertex as a value
    // so 1 -> 2 and 2 -> 1 would be connected bidirectionally
    // but if we add 1 -> 2 and its unidirectional, than 2 -> will not have 1 bc you
    // cannot travel back
    public void addEdge(int vertex, int value) {
        // check to see if the vertex exists
        AdjacencyList vertexAList = null, valueAList = null;
        for (AdjacencyList adjList : graphList) {
            if (adjList.vertexValue == vertex) {
                vertexAList = adjList;
                break;
            }
        }

        // check to see if the value exists
        for (AdjacencyList adjList : graphList) {
            if (adjList.vertexValue == value) {
                valueAList = adjList;
                break;
            }
        }

        // if the vertex does not exist, create a new one
        if (vertexAList == null) {
            vertexAList = new AdjacencyList(vertex);
            // add the vertex to the graph
            graphList.add(vertexAList);
        }

        // if the value does not exist, create a new vertex for that value
        if (valueAList == null) {
            valueAList = new AdjacencyList(value);
            // add the value as a vertex to the graph
            graphList.add(valueAList);
        }

        // Since it is bidirectional, add the vertex to the value adj list. If it was
        // not, dont
        // this is bc adding vertex to value adj list makes it so that you can travel
        // from the value to the vertex
        // and the vertex to the value. Linking it both ways makes it bidirectional

        // add the value to the vertex adjacency list
        vertexAList.aList.add(value);
        // add the vertex to the value adjacency list
        valueAList.aList.add(vertex);
    }

    public int getMaxVertex() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < graphList.size(); i++) {
            max = max < graphList.get(i).vertexValue ? graphList.get(i).vertexValue : max;
        }

        return max;
    }

    public void traverse(int start) {
        boolean[] visited = new boolean[this.getMaxVertex() + 1];
        dfs(visited, start);
    }

    public void dfs(boolean[] visited, int vertex) {
        System.out.print(vertex + " -> ");
        visited[vertex] = true;
        // find the adj list with the inputted vertex
        AdjacencyList temp = null;
        for (AdjacencyList adjList : graphList) {
            if (adjList.vertexValue == vertex) {
                temp = adjList;
            }
        }

        // iterate through the adj values of the found vertex
        for (Integer v : temp.aList) {
            // if not visited an adj value, go there
            if (!visited[v]) {
                dfs(visited, v);
            }
        }
    }

    public void bfs(int start) {
        System.out.println();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[getMaxVertex() + 1];
        visited[start] = true;
        int next = start;
        while (!queue.isEmpty()) {
            next = queue.poll();
            System.out.print(next + " -> ");
            AdjacencyList temp = null;
            // find next vertex alist
            for (AdjacencyList adjList : graphList) {
                if (adjList.vertexValue == next) {
                    temp = adjList;
                }
            }

            // iterate through adjacent vertices of vertex
            for (Integer i : temp.aList) {
                // if not fisited, add it to the queue
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void printGraph() {
        Collections.sort(graphList, new Comparator<AdjacencyList>() {
            public int compare(AdjacencyList o1, AdjacencyList o2) {
                return o1.vertexValue - o2.vertexValue;
            }
        });
        for (AdjacencyList adjList : graphList) {
            System.out.print("Vertex: " + adjList.vertexValue + " | ");
            System.out.println(adjList.aList);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(8, 6);
        g.addEdge(8, 10);
        g.addEdge(1, 8);
        g.addEdge(10, 7);
        g.addEdge(7, 0);
        g.addEdge(0, 10);
        g.addEdge(0, 9);
        g.printGraph();
        g.traverse(7);
        g.bfs(7);
    }
}

class AdjacencyList {
    LinkedList<Integer> aList;
    int vertexValue;

    AdjacencyList(int value) {
        aList = new LinkedList<>();
        vertexValue = value;
    }
}