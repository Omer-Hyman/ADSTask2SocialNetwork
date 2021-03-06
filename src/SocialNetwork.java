import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * You can also develop help functions and new classes for your system. You
 * can change the skeleton code if you need but you do not allow to remove the
 * methods provided in this class.
 */

public class SocialNetwork {
    public SocialNetwork(){ }
    /**
     * Loading social network data from files.
     * The dataset contains two separate files for user names (NameList.csv) and
     * the network distributions (SocialNetworkData.csv).
     * Use file I/O functions to load the data.You need to choose suitable data
     * structure and algorithms for an effective loading function
     */

    ADS2List map= new ADS2List();
    ADS2Graph graph = new ADS2Graph(101);
    int[] friends = new int[20];
    String[] people = new String[101];
    int asciiValue, i = 1, j =1;

    public void Load(){
        try (Scanner scanner = new Scanner(new File("NameList.csv"))) {
            String currentLine;
            scanner.useDelimiter("\r\n");
            while(scanner.hasNext()){
                asciiValue = 0;
                currentLine = scanner.next();
                people[j++] = currentLine;
                HashPair pair = new HashPair(i++, currentLine);
                map.addItem(pair);
            }
    //            map.ViewMap();
    //            map.HitTest();
      }

      catch (FileNotFoundException e)
      {
        System.err.println("File error: couldn't find NameList.csv");
      }


    try (Scanner scanner = new Scanner(new File("SocialNetworkData.csv"))) {
        String[] currentLine;
        int[] nodes = new int[2];
        double weight = 0;
        while(scanner.hasNext()){
            currentLine = scanner.next().split(",");
            nodes[0] = Integer.parseInt(currentLine[0]);
            nodes[1] = Integer.parseInt(currentLine[1]);
            weight = Double.parseDouble(currentLine[2]);
            graph.AddEdge(nodes[0], nodes[1], weight);
        }
    }
    catch (FileNotFoundException e)
    {
        System.err.println("File error: couldn't find SocialNetworkData.csv");
    }
}
    /**
     * Locating a user from the network
     * @param fullName users full name as a String
     * @return return the ID based on the user list. return -1 if the user do not exist
     * based on your algorithm, you may also need to locate the reference
     * of the node from the graph.
     */

    public int FindUserID(String fullName){
        for (int i = 1; i < 101; i++) {
            if (fullName.equals(people[i]))
                return i;
        }
        System.err.println("User Not Found!");
        return -1;
    }

    public String FindUserID(int index){
        return people[index];
    }

    /**
     * Listing ALL the friends belongs to the user
     * You need to retrieve all the directly linked nodes and return their full names.
     * Current Skeleton only have some dummy data. You need to replace the output
     * by using your own algorithms.
     * @param currentUserName use FindUserID or other help functions to locate
     * the user in the graph first.
     * @return You need to return all the user names in a String Array directly
     * linked to the users node.
     */

    public String[] GetMyFriends(String currentUserName){//potentially overload to accept id or name - i.e. make two implementations of the method
        String[] myFriends = new String[20];
        Arrays.fill(friends, 0);
        int person = FindUserID(currentUserName);
        int j = 0;
        for (int i = 1; i < 101; i++)
        {
            if (graph.IsConnected(person, i))//do binary search
            {
                myFriends[j] = people[i];
                friends[j] = i;
                j++;
            }
        }
        return myFriends;
    }

    public void BlockFriend(String user, String friend)
    {
        graph.RemoveFriendship(FindUserID(user), FindUserID(friend));
    }

    public void AddFriend(String user, String friend)
    {
        graph.AddFriendship(FindUserID(user), FindUserID(friend));
    }

    /**
     * Listing the top 10 recommended friends for the user
     * In the task, you need to calculate the shortest distance between the
     * current user and all other non-directly linked users. Pick up the top three
     * closest candidates and return their full names.
     * Use some help functions for sorting and shortest distance algorithms
     * @param currentUserName use FindUserID or other help functions to locate
     * the user in the graph first.
     * @return You need to return all the user names in a String Array containing
     * top 3 closest candidates.
     */
    public String[] GetRecommended (String currentUserName){
        System.out.println("In recommended method");
        int z=0;
        String[] recommended = new String[10];
        int[] recommendedInt = new int[10];
        double[] recommendedDouble = new double[101];

        for (int i = 1; i < 101; i++) {
            z++;
            if (!graph.IsConnected(FindUserID(currentUserName), i))
            {
                recommendedDouble[i] = graph.FindClosestNode(FindUserID(currentUserName), i);
            }
        }
        int k = 0;
        for (int j = 0; j < 10; j++) {
            Double min = Double.MAX_VALUE;
            for (int i = 1; i < 101; i++) {
                if (recommendedDouble[i] < min && recommendedDouble[i] != 0) {
                    min = recommendedDouble[i];
                    k = i;
                }
            }
            recommendedDouble[k] = Double.MAX_VALUE;
            recommendedInt[j] = k;
            recommended[j] = FindUserID(recommendedInt[j]);
        }

        return recommended;
    }
}
