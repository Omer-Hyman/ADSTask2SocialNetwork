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
    public SocialNetwork(){

    }
    /**
     * Loading social network data from files.
     * The dataset contains two separate files for user names (NameList.csv) and
     * the network distributions (SocialNetworkData.csv).
     * Use file I/O functions to load the data.You need to choose suitable data
     * structure and algorithms for an effective loading function
     */

    //private String[] userNetwork = new String[965];     //user network values are - node 1,(connects to) node 2, weight
    //private String[] userNames = new String[100];//can maybe take out new
    HashMap map= new HashMap();
    ADS2Graph graph = new ADS2Graph(101);
    int asciiValue, i = 1;
    public void Load(){
        try (Scanner scanner = new Scanner(new File("NameList.csv"))) {
            String currentLine;
            scanner.useDelimiter("\r\n");
            while(scanner.hasNext()){
                asciiValue = 0;
                currentLine = scanner.next();
                HashPair pair = new HashPair(i++, currentLine);
                map.addItem(pair);
            }
            map.ViewMap();
            map.HitTest();
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
            //graph.PrintMatrix();
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

        int person = map.SearchItem(fullName);
        if (person != -1) {
            System.out.println("USER FOUND!");
            return person;
        }
        else
            return -1;
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
    public String[] GetMyFriends(String currentUserName){
        String[] myFriends = new String[20];//WOULD BE BETTER TO GET INDEX STRAIGHT FROM WHEN IT'S WRITTEN TO HASH MAP

        int person = FindUserID(currentUserName);
        int j = 0;
        for (int i = 0; i < 100; i++)
        {
            if (graph.IsConnected(person, i))
                myFriends[j++] = map.FindName(i);
        }
        return myFriends;
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
        String[] recommended = {"Dummy X","Dummy Y","Dummy Z"};//TO Be replaced by the requested algorithm
        return recommended;
    }
}
