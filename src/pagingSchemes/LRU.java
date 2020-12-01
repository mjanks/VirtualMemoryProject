package pagingSchemes;

import java.util.ArrayList;
import java.util.LinkedList;

public class LRU extends Pager {
    // replace the page that has not been used for the longest period of time.
    // This approach is the least recently used (LRU) algorithm.
    LinkedList<Integer> lruStack= new LinkedList<Integer>();



    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                System.out.print("Current memory state: ");
                System.out.println(memoryState);
                System.out.println("Matched: " + page + " Move on to next page, this page already in memory,");

                // Update stack because the page was accessed
                System.out.println("lruStack before stack check: " + lruStack);
                if(lruStack.contains(page)) { // if stack contains page
                    lruStack.remove(lruStack.indexOf(page)); // remove it
                    System.out.println("lruStack after remove adjustment: " + lruStack);
                }
                lruStack.addFirst(page); // then add page to head of list
                System.out.println("lruStack after add adjustment: " + lruStack);

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
            memoryState.add(page); // add to memory
            numFaults++; // increment faults

            // ********** IMPLEMENT LRU! REPLACE THE PAGE! ************
            // Implement a stack using a doubly linked list.
            // Whenever a page is referenced, it is removed from the stack
            // and put on the top.
            System.out.println("lruStack before stack check: " + lruStack);
            if(lruStack.contains(page)) { // if stack contains page
                lruStack.remove(lruStack.indexOf(page)); // remove it
                System.out.println("lruStack after remove adjustment: " + lruStack);
            }
            lruStack.addFirst(page); // then add page to head of list
            System.out.println("lruStack after add adjustment: " + lruStack);
            System.out.println("numFaults: " + numFaults);
            System.out.println("Current memory state:");
            System.out.println(memoryState);
            return memoryState;
        }







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
