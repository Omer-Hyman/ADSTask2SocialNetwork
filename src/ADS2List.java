import java.lang.reflect.Array;
import java.util.Arrays;

public class ADS2List {

    private HashPair[] userNames = new HashPair[100];//can maybe take out new
    private int[] hits = new int[100];

    public ADS2List() {
        Arrays.fill(hits, 0);
    }


    public void addItem(HashPair pair)
    {
        int index = 0;
        for(int i=0; i < pair.getName().length(); i++) {
            index += pair.getName().charAt(i);
        }
        index = HashFunction(index);
        if (userNames[index] != null)
            ++hits[index];
        while (userNames[index] != null)
            index = OpenAddressing(index);
        userNames[index] = pair;
    }

    private int HashFunction(int key)
    {
        int hash = 0;
        hash = (key % 100);
        hash = (Math.abs(hash)) % userNames.length;
        return hash;
    }

    private int OpenAddressing(int index)
    {
        if (index == 99)
            index = 0;
        index++;
        return index;
    }

    public void ViewMap(){
        System.out.println("\nVIEWING MAP!\n");
        for (int i = 0; i < userNames.length; i++) {
            if (userNames[i] != null)
                System.out.println(i + ": " + userNames[i].getIndex() + " " + userNames[i].getName());
        }
    }

    public void HitTest(){
        System.out.println("\nHIT TEST!\n");
        for (int i = 0; i < hits.length; i++)
        {
            if (hits[i] > 1)
                System.out.println(i + ": " + hits[i]);
        }
        for (int i = 0; i < userNames.length; i++)
            if (userNames[i] == null)
                System.out.println("Hash empty cell: " + i);
    }
}
