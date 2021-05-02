public class ADS2Graph {

    //create adjacency matrix[][]
    //[node1][node2] = weight

    //watched S2W6
    //watch S2W7
    //implement Dijkstra maybe first but A* better if can figure it out

    private double[][] AdjMatrix;

    ADS2Graph(int numberOfVertices)
    {
        if (numberOfVertices > 0)
            AdjMatrix = new double[numberOfVertices][numberOfVertices];
        else
            System.err.println("Number of vertices should be more than zero!");
    }

    public void AddEdge(int node1, int node2, double weight)
    {
        AdjMatrix[node1][node2] = weight;
        AdjMatrix[node2][node1] = weight;
    }

    public boolean IsConnected(int node1, int node2)
    {
        return AdjMatrix[node1][node2] != 0 && AdjMatrix[node2][node1] != 0;
    }

    public void PrintMatrix()
    {
        for (int i = 0; i< AdjMatrix.length; i++)
        {
            for (int j = 0; j < AdjMatrix[i].length; j++)
            {
                System.out.print(AdjMatrix[i][j] + ", ");
            }
            System.out.println();
        }
    }

}
