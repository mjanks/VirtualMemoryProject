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
                System.out.println("match!");
                System.out.println("move on to next page. Current page already in memory");
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (fifoQueue.size() < numFrames) {
            System.out.println("no match and space available");
            fifoQueue.add(page);
            System.out.println(fifoQueue);
            return fifoQueue;
        }
        // if no match AND fifoQueue FULL (size = numFrames),
        // remove the first element of the ArrayList fifoQueue
        fifoQueue.remove(0);
        System.out.println(fifoQueue);
        // add page to the fifoQueue
        fifoQueue.add(page);
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
    }

}
