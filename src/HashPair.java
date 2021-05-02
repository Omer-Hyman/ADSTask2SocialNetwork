public class HashPair {
    private int index, originalIndex =1;
    private String value;

    public int getIndex() { return index;}
    public String getName() { return value;}

    HashPair(int id, String value)
    {
        this.index = id;
        this.value = value;
    }
}
