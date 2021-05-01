import java.lang.reflect.Array;
import java.util.Arrays;

public class HashMap {

    private HashPair[] userNames = new HashPair[100];//can maybe take out new
    private int[] hits = new int[100];

    public HashMap() {
        Arrays.fill(hits, 0);
    }

    //TODO: need to do load factor if want to resize hash map
    //TODO: improve hash function

    public int SearchItem(int key, String name)
    {
        int id = HashFunction(key);
        OpenAddressing(id);
        if (userNames[id] != null && userNames[id].getValue().equals(name))
            return id;
        System.out.println(userNames[id].getValue() + " " +userNames[id].getKey() + "\n"
                            + name + " " + id + "\n\n");
        return -1;
    }

    private int HashFunction(int key)
    {
        int hash = 0;
        hash = (key % 100);
        //System.out.println(Math.abs(hash));
        return (Math.abs(hash)) % userNames.length;
    }

    private void OpenAddressing(int id)
    {
        while(userNames[id] != null)//if its full - open addressing
        {
            if (id == 100)
                id = 0;
            id++;
        }
    }

    public void addItem(HashPair pair)
    {
        int id = HashFunction(pair.getKey());
        if (userNames[id] != null) {
            ++hits[id];
        }
        OpenAddressing(id);
        userNames[id] = pair;
    }

    public void ViewMap(){
        System.out.println("\nVIEWING MAP!\n");
        for (int i = 0; i < userNames.length; i++) {
            if (userNames[i] != null)
                System.out.println(i + ": " + userNames[i].getKey() + " " + userNames[i].getValue());
        }
    }

    public void HitTest(){
        System.out.println("\nHIT TEST!\n");
        for (int i = 0; i < hits.length; i++)
        {
            if (hits[i] > 1)
                System.out.println(i + ": " + hits[i]);
            if (userNames[i] == null)
            {
                if (hits[i] > 1)
                    System.out.println(i + ": " + hits[i]);
            }
        }
    }
}
