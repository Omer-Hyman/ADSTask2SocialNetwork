public class HashPair {
    private int key, originalIndex =1;
    private String value;

    public int getOriginalIndex() {
        return originalIndex;
    }

    public int getKey() { return key;}
    public String getValue() { return value;}

    HashPair(int id, String value)
    {
        this.key = id;
        this.value = value;
        this.originalIndex++;
    }
}
