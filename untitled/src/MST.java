
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class MST {
    private static final int[] l1 = findLengthAndWidth();
    private static final int W = l1[0];
    private static final int L = l1[1];
    private static final int quantityVertices = l1[2];
    private static final int quantityEdges = l1[3];
    private static final int V = L * W;

    /**
     * this function calculates Length,
     * Width,quantity of Vertices and quantity of Edges of square grid
     * read from file and calculates following expressions
     *
     * @return array
     */
    public static int[] findLengthAndWidth() {
        FileReader file = null;
        try {
            file = new FileReader("/home/onik/IdeaProjects/file.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Integer> arrayList = new ArrayList<>();
        assert file != null;
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            arrayList.add(input.nextInt());
        }
        input.close();
        int quantityVertices = 2;
        int width = arrayList.get(4);
        int quantityEdges = arrayList.size() / 3;
        int length = (quantityEdges + width) / (2 * width - 1);
        for (int i = 0; i < arrayList.size() - 3; i = i + 3) {
            if (!arrayList.get(i).equals(arrayList.get(i + 3)))
                quantityVertices++;
        }
        int[] find = new int[4];
        find[0] = width;
        find[1] = length;
        find[2] = quantityVertices;
        find[3] = quantityEdges;
        return find;
    }

    /**
     * this is specific method which reads graph (which describes square grid) from the file.
     *
     * @return Array from strings in given file
     * @throws Exception File not found
     */
    int[][] fileRead() throws Exception {
        FileReader file = new FileReader("/home/onik/IdeaProjects/file.txt");
        int[] integers = new int[3 * (L * (W - 1) + W * (L - 1))];
        int k = 0;
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                integers[k] = input.nextInt();
                k++;
            }
            input.close();
        } catch (ArrayIndexOutOfBoundsException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[][] Arr = new int[V][V];
        int[] newIntegers = new int[3];
        try {
            for (int i = 0; i < 3 * (L * (W - 1) + W * (L - 1)); i = i + 3) {
                newIntegers[0] = integers[i];
                newIntegers[1] = integers[1 + i];
                newIntegers[2] = integers[2 + i];
                Arr[newIntegers[0]][newIntegers[1]] = newIntegers[2];
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        for (int i = 0; i < Arr.length; i++) {
            for (int j = 0; j < Arr.length; j++) {
                if (Arr[i][j] == 0 && Arr[j][i] != 0)
                    Arr[i][j] = Arr[j][i];
            }
        }
        return Arr;
    }

    /**
     * Function that checks,does give graph is square grid or not:
     *
     * @param length           length of square grid
     * @param width            width of square grid
     * @param quantityEdges    quantity of edges of square grid
     * @param quantityVertices quantity of vertices of square grid
     * @return trues if given graph is square grid
     */
    boolean checkingSquareGridElse(int length, int width, int quantityEdges,
                                   int quantityVertices) {
        return width * length == quantityVertices ||
                (length * (width - 1) + width * (length - 1) == quantityEdges);
    }

    /**
     * Function that checks,does give graph is square grid or not:
     *
     * @param graph graph
     * @return true, if given graph square grid
     */
    boolean checkingSquareGrid(int[][] graph) {
        int quantityTwoEdge = 0;
        int quantityThreeEdge = 0;
        int quantityFourEdge = 0;
        int k = 0;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 0)
                    k++;
            }
            if (k == 2) {
                quantityTwoEdge++;
                k = 0;
            } else if (k == 3) {
                quantityThreeEdge++;
                k = 0;
            } else if (k == 4) {
                quantityFourEdge++;
                k = 0;
            } else
                return false;
        }
        System.out.println();
        return (V - 2 * (L + W - 4) - 4) == quantityFourEdge &&
                2 * (L + W - 4) == quantityThreeEdge && quantityTwoEdge == 4;

    }

    /**
     * A utility function to print the constructed MST stored in
     * parent[]
     *
     * @param parent parent
     * @param graph  graph
     */
    void printMST(int[] parent, int[][] graph) {
        int sumOfWeight = 0;
        System.out.println("Edge \t   Weight");
        for (int i = 1; i < V; i++) {
            System.out.println(i + "  -  " + parent[i] + "     " + graph[i][parent[i]]);
            sumOfWeight += graph[i][parent[i]];
        }
        System.out.println("The weight of minimum spanning tree = " + sumOfWeight);
    }

    /**
     * A utility function to find the vertex with minimum key
     * value, from the set of vertices not yet included in MST
     *
     * @param key    key
     * @param mstSet msSet
     * @return min index
     */
    int minKey(int[] key, Boolean[] mstSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    /**
     * Function to construct and print MST for a graph represented
     * using adjacency matrix representation
     *
     * @param graph graph
     */
    void primMST(int[][] graph) {
        // Array to store constructed MST
        int[] parent = new int[V];

        // Key values used to pick minimum weight edge in cut
        int[] key = new int[V];

        // To represent set of vertices included in MST
        Boolean[] mstSet = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -2;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is no zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, graph);
    }

    /**
     * This is testing method
     * Which tests our project
     *
     * @param args arguments
     * @throws Exception during reading file
     */
    public static void main(String[] args) throws Exception {
        MST mst = new MST();
        if (mst.checkingSquareGrid(mst.fileRead()) && mst.checkingSquareGridElse
                (L, W, quantityEdges, quantityVertices)) {
            System.out.println("The Width of square grid is " + W);
            System.out.println("The Length of square grid is " + L);
            System.out.println("The Quantity of Edges in square grid is " + quantityEdges);
            System.out.println("The Quantity of Vertices in square grid is " + quantityVertices);
            System.out.println();
            mst.primMST(mst.fileRead());
        } else
            System.out.println("This Graph is not Square grid,Please check your data in file");
    }

}