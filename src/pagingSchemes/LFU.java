package pagingSchemes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LFU extends Pager {
    // Make memoryState a HashMap with key=page no and value=count
    int count;
    int pageToReplace;
    float smallestFreq = 1000000000;
    float currentFreq;
    HashMap<Integer, Integer> memoryStateMap = new HashMap<>();
    HashMap<Integer, Integer> memoryAccesses = new HashMap<>();
    ArrayList<Integer> memoryState = new ArrayList<>();

    // check if page is in memory
    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                // increment count for page
                count = memoryStateMap.get(page);
                count++;
                memoryStateMap.put(page, count);

                // page has been accessed, need to increment values of all keys in memoryAccess
                // and possibly add the current page to memoryAccesses if it's not already there

                // if memoryAccesses is empty, add the page and initialize
                if(memoryAccesses.isEmpty()) {
                    memoryAccesses.put(page, 1);
                } else {
                    // increment the values for all keys in memoryAccesses
                    Set<Integer> keys = memoryAccesses.keySet();
                    Object[] keysArr = keys.toArray();
                    for(int j=0; j < keysArr.length; j++) {
                        // increment value of key
                        int count = memoryAccesses.get(keysArr[j]);
                        count++;
                        memoryAccesses.put((Integer) keysArr[j], count);
                    }
                    // check if current page needs to be added
                    if(!memoryAccesses.containsKey(page)) {
                        memoryAccesses.put(page, 1);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
            memoryState.add(page); // add to memory
            numFaults++; // increment faults

            // need to increment count for single page
            // check if page is in HashMap
            if (!memoryStateMap.containsKey(page)) {
                // add to hash map and initialize value(count) to 1
                memoryStateMap.put(page, 1); // first time so count starts at 1
            } else {
                // increment count for page
                count = memoryStateMap.get(page);
                count++;
                memoryStateMap.put(page, count);
            }

            // page has been accessed, need to increment values of all keys in memoryAccess
            // and possibly add the current page to memoryAccesses if it's not already there

            // if memoryAccesses is empty, add the page and initialize
            if(memoryAccesses.isEmpty()) {
                memoryAccesses.put(page, 1);
            } else {
                // increment the values for all keys in memoryAccesses
                Set<Integer> keys = memoryAccesses.keySet();
                Object[] keysArr = keys.toArray();
                for(int i=0; i < keysArr.length; i++) {
                    // increment value of key
                    int count = memoryAccesses.get(keysArr[i]);
                    count++;
                    memoryAccesses.put((Integer) keysArr[i], count);
                }
                // check if current page needs to be added
                if(!memoryAccesses.containsKey(page)) {
                    memoryAccesses.put(page, 1);
                }
            }
            return memoryState;
        }

        // NEED TO REPLACE A PAGE!
        // swap out the smallest frequency
        // loop through the keys to find the smallest frequency
        smallestFreq = 1000000000;
        for(int i=0; i < memoryState.size(); i++) {
            currentFreq = (float) memoryStateMap.get(memoryState.get(i)) /
                    (float) memoryAccesses.get(memoryState.get(i));
            if(currentFreq < smallestFreq) {
                smallestFreq = currentFreq;
                pageToReplace = memoryState.get(i); // set the key of the pageToReplace
            }
        }

        // remove the page pageToReplace from memoryState
        memoryState.remove(memoryState.indexOf(pageToReplace));
        memoryState.add(page); // add new page to memory state
        numFaults++; // increment faults

        // need to increment count for single page
        // check if page is in HashMap
        if(!memoryStateMap.containsKey(page)) {
            // add to hash map and initialize value(count) to 1
            memoryStateMap.put(page, 1); // first time so count starts at 1
        } else {
            // increment count for page
            count = memoryStateMap.get(page);
            count++;
            memoryStateMap.put(page, count);
        }

        // page has been accessed, need to increment values of all keys in memoryAccess
        // and possibly add the current page to memoryAccesses if it's not already there

        // if memoryAccesses is empty, add the page and initialize
        if(memoryAccesses.isEmpty()) {
            memoryAccesses.put(page, 1);
        } else {
            // increment the values for all keys in memoryAccesses
            Set<Integer> keys = memoryAccesses.keySet();
            Object[] keysArr = keys.toArray();
            for(int i=0; i < keysArr.length; i++) {
                // increment value of key
                int count = memoryAccesses.get(keysArr[i]);
                count++;
                memoryAccesses.put((Integer) keysArr[i], count);
            }
            // check if current page needs to be added
            if(!memoryAccesses.containsKey(page)) {
                memoryAccesses.put(page, 1);
            }
        }
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
