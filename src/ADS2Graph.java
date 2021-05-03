public class ADS2Graph {


//    SocialNetwork network = new SocialNetwork();

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
        tentativeDistance[startNode] = 0;
        fromList[startNode] = startNode;
        for (int i = 1; i < 100; i++)
        {
            tentativeDistance[i] = Double.MAX_VALUE;
            fromList[i] = -1;
        }
    }

    private int[] FindFriends(int currentNode)
    {
        int[] friends = new int[20];
        int j = 0;
        for (int i = 0; i < 100; i++) {
            if (IsConnected(currentNode, i))//IF CURRENT NODE IS FRIENDS WITH I - 0-100
                friends[j++] = i;
        }
        return friends;
    }

    public void FindClosestNodeJings(int startNode, int destination, String[] nameList)
    {
        InitializeLists(startNode);

        int currentNode = startNode;
        while (currentNode != destination && tentativeDistance[currentNode] != Double.MAX_VALUE)
        {
            visitedNodes[currentNode] = true;
            for (int i = 0; i < AdjMatrix.length; i++) {//i is next node
                if ( !visitedNodes[i] //if next node is not visited, friends with current, and next's tentative is more than new offer
                        && IsConnected(currentNode, i)
                        && tentativeDistance[i] > (tentativeDistance[currentNode]+AdjMatrix[currentNode][i]))
                {
                    tentativeDistance[i] = tentativeDistance[currentNode] + AdjMatrix[currentNode][i];
                    fromList[i] = currentNode;
                }
            }
            currentNode = FindNewCurrent(destination);
        }
    }

    public int[] FindClosestNodeMine(int startNode) {
        InitializeLists(startNode);
       // System.out.println(AdjMatrix.length);
        int currentNode = startNode;
        for (int j = 0; j < AdjMatrix.length; j++)
        {
            visitedNodes[currentNode] = true;
            for (int i = 0; i < AdjMatrix.length; i++) {//i is next node
                if (!visitedNodes[i] //if next node is not visited, friends with current, and next's tentative is more than new offer
                        && IsConnected(currentNode, i)
                        && tentativeDistance[i] > (tentativeDistance[currentNode] + AdjMatrix[currentNode][i]))
                {
                    tentativeDistance[i] = tentativeDistance[currentNode] + AdjMatrix[currentNode][i];
                    fromList[i] = currentNode;
                }
            }
            //currentNode = FindNewCurrent();
        }

        int closestNode[] = new int[3];
        int k = 0, x =0;
        for (int i = 0; i < 3; i++) {
            double lowestTentative = Double.MAX_VALUE;
            for (int j = 0; j < tentativeDistance.length; j++) {
                if (tentativeDistance[j] < lowestTentative && !IsConnected(startNode, j))
                {
                    lowestTentative = tentativeDistance[j];
                    k = j;
                    tentativeDistance[j] = Double.MAX_VALUE;
                }
            }
            closestNode[x] = k;
            x++;
        }
        return closestNode;

    }

    private int FindNewCurrent(int destination){
        double min_tentative = Double.MAX_VALUE;
        int newCurrent = destination;

        for (int i = 0; i < tentativeDistance.length; i++) {
            if (!visitedNodes[i] && min_tentative>tentativeDistance[i])
            {
                min_tentative = tentativeDistance[i];
                newCurrent = i;
            }
        }//find unvisited node with smallest tent distance
        return newCurrent;
    }

    private void PrintPath(String[] nameList, int startID, int destination)
    {
        System.out.println(nameList[destination]);
        int previous = fromList[destination];
        while (previous != startID)
        {
            System.out.println(nameList[previous]);
            previous = fromList[previous];
        }
    }

    //    https://shu.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=c2674e2a-ea1a-4ecc-8de6-ace500f9ff6e



}
