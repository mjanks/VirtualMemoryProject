package pagingSchemes;

import java.util.ArrayList;

public abstract class Pager {
    ArrayList<Integer> memoryState = new ArrayList<Integer>();  // size = numFrames
    int numFrames;
    int numFaults;
    int accesses; // total number of page requests so far

    public int getNumFrames() {
        return numFrames;
    }

    public int getAccesses() { return accesses; }

    public abstract void setNumFrames(int n);

    public abstract void setAccesses(int n);

    public abstract void print();

    //public abstract boolean isPageInQueue(int page);

    //public abstract ArrayList<Integer> replacePage(int page);

}