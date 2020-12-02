package pagingSchemes;

import java.util.ArrayList;
import java.util.HashMap;

public class LFU extends Pager {
    // Make memoryState a HashMap with key=page no and value=count
    int count;
    HashMap<Integer, Integer> memoryStateMap = new HashMap<>();
    ArrayList<Integer> memoryState = new ArrayList<>();



    // check if page is in memory
    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                System.out.println("matched: " + page);
                System.out.println("move on to next page. This page already in memory");
                System.out.println(memoryState);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
            System.out.println("Current memory state:");
            System.out.println(memoryState);
            System.out.println("No match and space available. Add page " + page + " to memory.");
            memoryState.add(page); // add to memory
            System.out.println("Just added page " + page + " memoryState: " + memoryState);
            numFaults++; // increment faults

            // need to increment the counter for all pages in memoryState
            for(int i=0; i < memoryState.size(); i++) {
                // check if page is in HashMap
                System.out.println("memoryStateMap: " + memoryStateMap);
                System.out.println("memoryStateMap.containsKey(memoryState.get(i)): " +
                        memoryStateMap.containsKey(memoryState.get(i)));
                if(!memoryStateMap.containsKey(memoryState.get(i))) {
                    // add to hash map and initialize value(count) to 1
                    memoryStateMap.put(memoryState.get(i), 1); // first time so count starts at 1
                    System.out.println("DOES NOT contain the page, value should be initialized to 1, " +
                            "memoryStateMap after add: " + memoryStateMap);
                } else {
                    // increment count for page
                    count = memoryStateMap.get(memoryState.get(i));
                    System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
                            memoryStateMap);
                    count++;
                    memoryStateMap.put(memoryState.get(i), count);
                    System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
                            "memoryStateMap " +
                            "after increment: " + memoryStateMap);
                }
            }

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
        System.out.println("*********************** LFU ***********************");
        System.out.println("LFU numFrames: " + numFrames);
        System.out.println("LFU memoryState: " + memoryState);
        System.out.println("LFU numFaults: " + numFaults);
        System.out.println("*********************** LFU ***********************");
    }



}
