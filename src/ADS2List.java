import java.lang.reflect.Array;
import java.util.Arrays;

public class ADS2List {

    private HashPair[] userNames = new HashPair[100];//can maybe take out new
    private int[] hits = new int[100];

    public ADS2List() {
        Arrays.fill(hits, 0);
    }

    public int SearchItem(String name)
    {
        int index = 0, j =0;
        for(int i=0; i < name.length(); i++) {
            index += name.charAt(i);
        }
        index = HashFunction(index);
        while (!userNames[index].getName().equals(name)) {
            index = OpenAddressing(index);
            j++;
            if (j == 100)
                return -1;
        }
        return userNames[index].getIndex();
    }

    public void addItem(HashPair pair)
    {
        int index = 0;
        for(int i=0; i < pair.getName().length(); i++) {
            index += pair.getName().charAt(i);
        }
        index = HashFunction(index);
        while (userNames[index] != null)
            index = OpenAddressing(index);
        if (userNames[index] != null) {
            ++hits[index];
        }
        userNames[index] = pair;
    }

    private int HashFunction(int key)
    {
        int hash = 0;
        hash = (key % 100);
        hash = (Math.abs(hash)) % userNames.length;
        return hash;
    }

    public String FindName(int index)
    {
        for (HashPair userName : userNames) {
            if (userName != null && userName.getIndex() == index)
                return userName.getName();
        }
        return null;
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
