import java.lang.reflect.Array;
import java.util.Arrays;

public class HashMap {

    private HashPair[] userNames = new HashPair[100];//can maybe take out new
    private int[] hits = new int[100];

    public HashMap() {
        Arrays.fill(hits, 0);
    }

    public int SearchItem(int key, String name)
    {
        int id = HashFunction(key);
        if (userNames[id] != null && userNames[id].getValue().equals(name))
            return id;
        return -1;
    }

    //TODO: ADD OPEN ADDRESSING FUNCTION

    private int HashFunction(int key)
    {
        int hash;
        hash = key%100;
        //System.out.println(Math.abs(hash));
        return (Math.abs(hash)) % userNames.length;
    }

    public void addItem(HashPair pair)
    {
        int id = HashFunction(pair.getKey());
        userNames[id] = pair;
        ++hits[id];
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
        int maxids[]=new int[10];
        for (int i=0; i<maxids.length; i++)
        {
            int max=-1;
            for (int j=0; j<hits.length; j++)
            {
                if (hits[j]>max)
                {
                    boolean found=false;
                    for (int k=0; k<i && !found; k++) {
                        if (maxids[k] == j)
                            found = true;
                    }
                    if (!found)
                    {
                        max=hits[j];
                        maxids[i]=j;
                    }
                }
            }
        }
        for (int i=0; i<maxids.length; i++)
            System.out.println(maxids[i]+": "+hits[maxids[i]]);
    }
}
