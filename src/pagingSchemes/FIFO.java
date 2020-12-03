package pagingSchemes;

import java.util.ArrayList;

public class FIFO extends Pager {

    // check if page is in memory
    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
            memoryState.add(page);
            numFaults++;
            return memoryState;
        }

        // if no match AND memoryState FULL (size = numFrames),
        memoryState.remove(0); // remove the first element of the ArrayList memoryState
        memoryState.add(page); // add page to the memoryState
        numFaults++;
        return memoryState;
    }

    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    public void print(int p) {
        System.out.print("Page: " + p);
        System.out.println(" -- Memory State: " + memoryState);
    }


}
