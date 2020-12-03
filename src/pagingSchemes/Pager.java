package pagingSchemes;

import java.util.ArrayList;

public abstract class Pager {
    ArrayList<Integer> memoryState = new ArrayList<Integer>();
    int numFrames;
    float numFaults;

    public int getNumFrames() {
        return numFrames;
    }

    public float getNumFaults() {
        return numFaults;
    }

    public abstract void setNumFrames(int n);

    public abstract void print(int p);
}