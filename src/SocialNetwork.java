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

    HashMap map= new HashMap();
    ADS2Graph graph = new ADS2Graph(101);
    int[] friends = new int[20];
    String[] people = new String[101];

    public int[] getFriends() {
        return friends;
    }

    int asciiValue, i = 1, j =0;
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
        String[] myFriends = new String[20];//WOULD BE BETTER TO GET INDEX STRAIGHT FROM WHEN IT'S WRITTEN TO HASH MAP
        int person = FindUserID(currentUserName);
        int j = 0;
        for (int i = 0; i < 100; i++)
        {
            if (graph.IsConnected(person, i))//can probs be optimised with binary search or something
            {
                myFriends[j] = map.FindName(i);
                friends[j] = i;
                j++;
            }
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
        String[] recommended = new String[10];
        int[] recommendedInt = new int[101];
        double[] recommendedDouble = new double[101];
        for (int i = 1; i < 101; i++) {
            recommendedDouble[i] = graph.FindClosestNodeJings(FindUserID(currentUserName), i);
        }
        int k = 0;
        for (int j = 0; j < 10; j++) {
            Double min = Double.MAX_VALUE;
            for (int i = 0; i < recommendedDouble.length; i++) {
                //System.out.println(i + " " + distances[i]);
                if (recommendedDouble[i] < min //&& recommendedDouble[i] != 0
                ) {
                    min = recommendedDouble[i];
                    k = i;
                    recommendedDouble[i] = Double.MAX_VALUE;
                }
            }
            recommendedInt[j] = k;
        }
        for (int i = 0; i < 10; i++) {
            recommended[i] = FindUserID(recommendedInt[i]);
        }
        return recommended;
    }
}
