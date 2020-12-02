package pagingSchemes;

import java.util.ArrayList;

public abstract class Pager {
    ArrayList<Integer> memoryState = new ArrayList<Integer>();  // size = numFrames
    ArrayList<Integer> pageArr = new ArrayList<>();
    int numFrames;
    int numFaults;

    public int getNumFrames() {
        return numFrames;
    }

    public int getNumFaults() {
        return numFaults;
    }

    public abstract void setNumFrames(int n);


    public abstract void print(int p);
}