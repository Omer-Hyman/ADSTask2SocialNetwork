public class HashPair {
//    private String id, value;
//
//    public String getID() { return id;}
//    public String getValue() { return value;}
//
//    HashPair(String id, String value)
//    {
//        this.id = id;
//        this.value = value;
//    }
    private int key;
    private String value;

    public int getKey() { return key;}
    public String getValue() { return value;}

    HashPair(int id, String value)
    {
        this.key = id;
        this.value = value;
    }
}
