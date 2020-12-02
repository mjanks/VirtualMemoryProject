package pagingSchemes;

import java.util.ArrayList;

public abstract class Pager {
    ArrayList<Integer> memoryState = new ArrayList<Integer>();  // size = numFrames
    ArrayList<Integer> pageArr = new ArrayList<>();
    int numFrames;
    int numFaults;
    int accesses; // total number of page requests so far

    public int getNumFrames() {
        return numFrames;
    }

    public int getAccesses() { return accesses; }

    public int getNumFaults() {
        return numFaults;
    }

    public abstract void setNumFrames(int n);

    public abstract void setAccesses(int n);

    public abstract void print(int p);

    //public abstract boolean isPageInQueue(int page);

    //public abstract ArrayList<Integer> replacePage(int page);

}