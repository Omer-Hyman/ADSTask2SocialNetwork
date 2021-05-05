import java.util.Arrays;

public class ADS2Graph {

    private double[][] AdjMatrix;
    private boolean[] visitedNodes = new boolean[101];
    private double[] tentativeDistance = new double[101];
    private int[] fromList = new int[101];

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

    public void RemoveFriendship(int node1, int node2)
    {
        AdjMatrix[node1][node2] = 0;
        AdjMatrix[node2][node1] = 0;

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

    private void InitializeLists(int startNode)
    {
        for (int i = 0; i < 101; i++)
        {
            tentativeDistance[i] = Double.MAX_VALUE;
            fromList[i] = -1;
            visitedNodes[i] = false;
        }
        tentativeDistance[startNode] = 0;
        fromList[startNode] = startNode;
    }

    public double FindClosestNode(int startNode, int destination)
    {
        InitializeLists(startNode);
        int currentNode = startNode;

        while (currentNode != destination && tentativeDistance[currentNode] != Double.MAX_VALUE) {
            visitedNodes[currentNode] = true;
            for (int i = 0; i < AdjMatrix.length; i++) {//i is next node
                if (    !visitedNodes[i]                //if next node is not visited, friends with current, and next's current tentative is more than new offer
                        && IsConnected(currentNode, i)
                        && tentativeDistance[i] > (tentativeDistance[currentNode] + AdjMatrix[currentNode][i]))
                {
                    tentativeDistance[i] = tentativeDistance[currentNode] + AdjMatrix[currentNode][i];
                    fromList[i] = currentNode;
                }
            }
            currentNode = FindNewCurrent(destination);
        }//runs Dijkstra on random node

        return tentativeDistance[destination];
    }

    private int FindNewCurrent(int destination){
        double min_tentative = Double.MAX_VALUE;
        int newCurrent = destination;

        for (int i = 0; i < tentativeDistance.length; i++) {
            if (!visitedNodes[i] && min_tentative>tentativeDistance[i])//find unvisited node with smallest tent distance
            {
                min_tentative = tentativeDistance[i];
                newCurrent = i;
            }
        }
        return newCurrent;
    }
}
