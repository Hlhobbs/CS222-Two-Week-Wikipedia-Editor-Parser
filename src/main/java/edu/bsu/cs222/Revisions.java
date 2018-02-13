package edu.bsu.cs222;


public class Revisions {
    private String id;
    private String timestamp;
    private int count=0;

    public String getId(){
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void addCount(int number) {
        this.count = count+number;
    }

    public int getCount() {
        return count;
    }
}
