import java.io.*;
import java.util.*;

public class Main {
    LinkedList<AdjacencyList> graphList = new LinkedList<>();
    int vertices;

    public int getMaxVertex() {
        return vertices;
    }

    // single direction so do not add the vertex to the value's adj list
    public void addEdge(int vertex, int value) {
        AdjacencyList vertexList = null, valueList = null;
        for (AdjacencyList aList : graphList) {
            if (aList.vertexVal == vertex) {
                vertexList = aList;
            }
        }

        for (AdjacencyList aList : graphList) {
            if (aList.vertexVal == value) {
                valueList = aList;
            }
        }

        if (vertexList == null) {
            vertexList = new AdjacencyList(vertex);
            graphList.add(vertexList);
        }

        if (valueList == null) {
            valueList = new AdjacencyList(value);
            graphList.add(valueList);
        }

        vertexList.adjList.add(value);
    }

    public int possible(int start, int dest) {
        boolean[] visited = new boolean[getMaxVertex() + 1];
        return dfs(start, dest, visited, false);
    }

    public int dfs(int start, int dest, boolean[] visited, boolean hasChild) {
        hasChild = false;
        if (start == dest) {
            return 1;
        }

        visited[start] = true;

        AdjacencyList temp = null;
        for (AdjacencyList a : graphList) {
            if (a.vertexVal == start) {
                temp = a;
                break;
            }
        }

        if (temp.adjList.size() != 0) {
            hasChild = true;
        }

        for (Integer i : temp.adjList) {
            if (!visited[i]) {
                return dfs(i, dest, visited, hasChild);
            }
        }

        if (!hasChild) {
            for (AdjacencyList aList : graphList) {
                if (aList.adjList.contains(start) && aList.vertexVal == dest && !visited[aList.vertexVal]) {
                    return 0;
                }
            }
            return -1;
        } else {
            return 0;
        }
    }

    public void printGraph() {
        Collections.sort(graphList, new Comparator<AdjacencyList>() {
            public int compare(AdjacencyList o1, AdjacencyList o2) {
                return o1.vertexVal - o2.vertexVal;
            }
        });
        for (AdjacencyList adjList : graphList) {
            System.out.print("Vertex: " + adjList.vertexVal + " | ");
            System.out.println(adjList.adjList);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Main graph = new Main();
        String[] first_line = reader.readLine().trim().split(" ");
        graph.vertices = Integer.parseInt(first_line[0]);
        int M = Integer.parseInt(first_line[1]);
        for (int i = 0; i < M; i++) {
            String[] line = reader.readLine().trim().split(" ");
            graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        String[] possible = reader.readLine().trim().split(" ");
        int res = graph.possible(Integer.parseInt(possible[0]), Integer.parseInt(possible[1]));
        if (res == -1) {
            System.out.println("no");
        } else if (res == 0) {
            System.out.println("unknown");
        } else {
            System.out.println("yes");
        }
        // graph.addEdge(3, 8);
        // graph.addEdge(2, 8);
        // graph.addEdge(3, 4);
        // graph.printGraph();
        // System.out.println(graph.possible(3, 2));
    }
}

class AdjacencyList {
    int vertexVal;
    LinkedList<Integer> adjList;

    AdjacencyList(int val) {
        adjList = new LinkedList<>();
        vertexVal = val;
    }
}