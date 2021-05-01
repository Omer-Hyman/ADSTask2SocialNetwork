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

    private String[] userNetwork = new String[965];     //user network values are - node 1,(connects to) node 2, weight
    //private String[] userNames = new String[100];//can maybe take out new
    private HashPair[] userNames = new HashPair[100];//can maybe take out new
    HashMap map= new HashMap();
    int asciiValue;
    public void Load(){

        String currentLine;
        try (Scanner scanner = new Scanner(new File("NameList.csv"))) {
            scanner.useDelimiter("\r\n");
            while(scanner.hasNext()){
                asciiValue = 0;
                currentLine = scanner.next();
                for(int i=0; i < currentLine.length(); i++)
                {
                    asciiValue += currentLine.charAt(i);
                }
                HashPair pair = new HashPair(asciiValue, currentLine);
                map.addItem(pair);
            }
            map.ViewMap();
            map.HitTest();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File error: couldn't find NameList.csv");
        }
//        try (Scanner scanner = new Scanner(new File("SocialNetworkData.csv"))) {
//            scanner.useDelimiter("\r\n");
//            i = 0;
//            while(scanner.hasNext()){
//                currentLine = scanner.next();
//                System.out.println(currentLine);
//                userNetwork[i++]=currentLine;
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            System.err.println("File error: couldn't find NameList.csv");
//        }
    }
    /**
     * Locating a user from the network
     * @param fullName users full name as a String
     * @return return the ID based on the user list. return -1 if the user do not exist
     * based on your algorithm, you may also need to locate the reference
     * of the node from the graph.
     */

    public int FindUserID(String fullName){
        asciiValue = 0;
        for(int i=0; i < fullName.length(); i++)
        {
            asciiValue += fullName.charAt(i);
        }
        if (map.SearchItem(asciiValue, fullName) != -1) {
            System.out.println("USER FOUND!");
            return 0;
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
        String[] myFriends = {"Dummy 1","Dummy 2","Dummy 3","Dummy 4","Dummy 5"};//TO Be replaced by the requested algorithm
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
