package pagingSchemes;

import java.util.ArrayList;

public class LRU extends Pager {
    // replace the page that has not been used for the longest period of time.
    // This approach is the least recently used (LRU) algorithm.

    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                System.out.print("Current memory state: ");
                System.out.println(memoryState);
                System.out.println("Matched: " + page + " Move on to next page, this page already in memory,");
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
            System.out.println("Current memory state:");
            System.out.println(memoryState);
            System.out.println("No match and space available. Add page " + page + " to memory.");
            memoryState.add(page);
            numFaults++;
            System.out.println("numFaults: " + numFaults);
            System.out.println("Current memory state:");
            System.out.println(memoryState);
            return memoryState;
        }

        // ********** IMPLEMENT! REPLACE THE PAGE! ************
        // Implement a stack using a doubly linked list



        return memoryState;
    }

    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    @Override
    public void setAccesses(int n) {

    }

    @Override
    public void print() {
        System.out.println("*********************** LRU ***********************");
        System.out.println("OPT numFrames: " + numFrames);
        System.out.println("OPT memoryState: " + memoryState);
        System.out.println("OPT numFaults: " + numFaults);
        System.out.println("*********************** LRU ***********************");

    }




}
