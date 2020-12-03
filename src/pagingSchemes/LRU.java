package pagingSchemes;

import java.util.ArrayList;
import java.util.LinkedList;

public class LRU extends Pager {
    // replace the page that has not been used for the longest period of time.
    // This approach is the least recently used (LRU) algorithm.
    LinkedList<Integer> lruStack= new LinkedList<Integer>();
    int pageToRemove;
    int removed; // for testing

    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                // Update stack because the page was accessed
                if(lruStack.contains(page)) { // if stack contains page
                    lruStack.remove(lruStack.indexOf(page)); // remove it
                }
                lruStack.addFirst(page); // then add page to head of list
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
            memoryState.add(page); // add to memory
            numFaults++; // increment faults

            // Stack using a linked list.
            // Whenever a page is referenced, it is removed from the stack
            // and put on the top.
            if(lruStack.contains(page)) { // if stack contains page
                lruStack.remove(lruStack.indexOf(page)); // remove it
            }
            lruStack.addFirst(page); // then add page to head of list
            return memoryState;
        }

        // remove LRU page from memoryState
        pageToRemove = lruStack.getLast(); // page to remove
        removed = lruStack.removeLast(); // remove last element from lruStack
        for(int i=0; i < memoryState.size(); i++) {
            if(memoryState.get(i) == pageToRemove) {
                memoryState.remove(i);
            }
        }
        memoryState.add(page); // add new page to memoryState
        numFaults++;

        // update the stack
        if(lruStack.contains(page)) { // if stack contains page
            lruStack.remove(lruStack.indexOf(page)); // remove it
        }
        lruStack.addFirst(page); // then add page to head of list
        return memoryState;
    }

    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    @Override
    public void print(int p) {
        System.out.print("Page: " + p);
        System.out.println(" -- Memory State: " + memoryState);
    }
}
