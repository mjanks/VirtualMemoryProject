package pagingSchemes;

import java.util.ArrayList;

public class FIFO extends Pager {
    ArrayList<Integer> fifoQueue = new ArrayList<Integer>();  // size = numFrames
    int count = 0;
    boolean match = false;

    // check if page is in queue
    public boolean isPageInQueue(int page) {
        for(int i=0; i < fifoQueue.size(); i++) {
            if (page == fifoQueue.get(i)) {
                System.out.println("matched: " + page);
                System.out.println("move on to next page. This page already in memory");
                System.out.println(fifoQueue);
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (fifoQueue.size() < numFrames) {
            System.out.println("Current memory state:");
            System.out.println(fifoQueue);
            System.out.println("No match and space available. Add page " + page + " to memory.");
            fifoQueue.add(page);
            numFaults++;
            System.out.println("numFaults: " + numFaults);
            System.out.println("Current memory state:");
            System.out.println(fifoQueue);
            return fifoQueue;
        }

        // if no match AND fifoQueue FULL (size = numFrames),
        // remove the first element of the ArrayList fifoQueue
        System.out.println("Current memory state:");
        System.out.println(fifoQueue);
        System.out.println("All frames full. Remove page " + fifoQueue.get(0) + " from memory.");
        fifoQueue.remove(0);
        System.out.println("Current memory state:");
        System.out.println(fifoQueue);

        // add page to the fifoQueue
        System.out.println("Add page " + page + " to memory.");
        fifoQueue.add(page);
        numFaults++;
        System.out.println("numFaults: " + numFaults);
        System.out.println("Current memory state:");
        System.out.println(fifoQueue);
        return fifoQueue;
    }

    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    @Override
    public void setAccesses(int n) {
        this.accesses = n;
    }

    @Override
    public void print() {
        System.out.println("numFrames: " + numFrames);
        System.out.println("numFaults: " + numFaults);
    }

}
