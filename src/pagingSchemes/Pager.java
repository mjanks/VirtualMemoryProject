package pagingSchemes;

public abstract class Pager {
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

}