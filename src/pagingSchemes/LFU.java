package pagingSchemes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LFU extends Pager {
    // Make memoryState a HashMap with key=page no and value=count
    int count;
    int smallestValue;
    int pageToReplace;
    boolean pageReplacementSet = false;
    HashMap<Integer, Integer> memoryStateMap = new HashMap<>();
    ArrayList<Integer> memoryState = new ArrayList<>();



    // check if page is in memory
    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                System.out.println("matched: " + page);
                System.out.println("move on to next page. This page already in memory");
                // increment count for page
                count = memoryStateMap.get(page);
                System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
                        memoryStateMap);
                count++;
                memoryStateMap.put(page, count);
                System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
                        "memoryStateMap " +
                        "after increment: " + memoryStateMap);

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

            // need to increment count for single page
            // check if page is in HashMap
            System.out.println("memoryStateMap: " + memoryStateMap);
            System.out.println("memoryStateMap.containsKey(memoryState.get(i)): " +
                    memoryStateMap.containsKey(page));
            if (!memoryStateMap.containsKey(page)) {
                // add to hash map and initialize value(count) to 1
                memoryStateMap.put(page, 1); // first time so count starts at 1
                System.out.println("DOES NOT contain the page, value should be initialized to 1, " +
                        "memoryStateMap after add: " + memoryStateMap);
            } else {
                // increment count for page
                count = memoryStateMap.get(page);
                System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
                        memoryStateMap);
                count++;
                memoryStateMap.put(page, count);
                System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
                        "memoryStateMap " +
                        "after increment: " + memoryStateMap);
            }


            // need to increment the counter for all pages in memoryState
//            for(int i=0; i < memoryState.size(); i++) {
//                // check if page is in HashMap
//                System.out.println("memoryStateMap: " + memoryStateMap);
//                System.out.println("memoryStateMap.containsKey(memoryState.get(i)): " +
//                        memoryStateMap.containsKey(memoryState.get(i)));
//                if(!memoryStateMap.containsKey(memoryState.get(i))) {
//                    // add to hash map and initialize value(count) to 1
//                    memoryStateMap.put(memoryState.get(i), 1); // first time so count starts at 1
//                    System.out.println("DOES NOT contain the page, value should be initialized to 1, " +
//                            "memoryStateMap after add: " + memoryStateMap);
//                } else {
//                    // increment count for page
//                    count = memoryStateMap.get(memoryState.get(i));
//                    System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
//                            memoryStateMap);
//                    count++;
//                    memoryStateMap.put(memoryState.get(i), count);
//                    System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
//                            "memoryStateMap " +
//                            "after increment: " + memoryStateMap);
//                }
//            }

            System.out.println("numFaults: " + numFaults);
            System.out.println("Current memory state:");
            System.out.println(memoryState);
            return memoryState;
        }

        // Need to replace a page. Replace the page in memoryState with the key
        // of the smallest value in memoryStateMap
        // Referenced:
        // https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        System.out.println("memoryStateMap.size(): " + memoryStateMap.size());
        smallestValue = 1000000000;
        for (Map.Entry mapElement : memoryStateMap.entrySet()) {
            int key = (Integer) mapElement.getKey();
            int value = (Integer) mapElement.getValue();
            //smallestValue = value;
            if (value < smallestValue)
                smallestValue = value;

            System.out.println(key + " : " + value);
        }
        System.out.println("smallestValue: " + smallestValue);
        System.out.println("page: " + page);

//        for(int i=0; i <= memoryStateMap.size(); i++) {
//            System.out.println("memoryStateMap.get(i): " + memoryStateMap.get(i));
//            if(memoryStateMap.get(i) > greatestValue) {
//                greatestValue = memoryStateMap.get(i);
//            }
//        }

        // get key of smallest value from memoryStateMap
        // check that key is in memoryState
        pageReplacementSet = false;
        while(!pageReplacementSet) {
            for (Map.Entry mapElement : memoryStateMap.entrySet()) {
                int key = (Integer) mapElement.getKey();
                int value = ((Integer) mapElement.getValue());

                System.out.println("memoryState: " + memoryState + "key: " + key + " value: " + value);

                if (value == smallestValue) {
                    System.out.println("value == smallest value: " + value + " " + smallestValue);
                    for (int i = 0; i < memoryState.size(); i++) {
                        System.out.println("memoryState.get(i): " + memoryState.get(i));
                        System.out.println("key: " + key);
                        if (memoryState.get(i) == key) { //
                            pageToReplace = key;
                            System.out.println("******key: " + key);
                            System.out.println("****pageToReplace: " + pageToReplace);
                            pageReplacementSet = true;
                        }
                    }
                }


    //            if(value == smallestValue && memoryState.contains(key)) {
    //                pageToReplace = key;
    //            }

                //System.out.println(key + " : " + value);
            }
            smallestValue++;
        }



//        for(int i=0; i < memoryStateMap.size(); i++) {
//            if(memoryStateMap.get(i).equals(greatestValue)) {
//                key = i;
//            }
//        }

        // remove the page 'key' from memoryState
        System.out.println("About to remove page " + pageToReplace + " memoryState: " + memoryState);
        System.out.println("pageToReplace " + pageToReplace);
        memoryState.remove(memoryState.indexOf(pageToReplace));
        System.out.println("Just removed page " + pageToReplace + " memoryState: " + memoryState);

        // add new page to memory state
        System.out.println("About to add page " + page + " memoryState: " + memoryState);
        memoryState.add(page);
        System.out.println("Just added page " + page + " memoryState: " + memoryState);
        numFaults++; // increment faults

        // need to increment count for single page
        // check if page is in HashMap
        System.out.println("memoryStateMap: " + memoryStateMap);
        System.out.println("memoryStateMap.containsKey(memoryState.get(i)): " +
                memoryStateMap.containsKey(page));
        if(!memoryStateMap.containsKey(page)) {
            // add to hash map and initialize value(count) to 1
            memoryStateMap.put(page, 1); // first time so count starts at 1
            System.out.println("DOES NOT contain the page, value should be initialized to 1, " +
                    "memoryStateMap after add: " + memoryStateMap);
        } else {
            // increment count for page
            count = memoryStateMap.get(page);
            System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
                    memoryStateMap);
            count++;
            memoryStateMap.put(page, count);
            System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
                    "memoryStateMap " +
                    "after increment: " + memoryStateMap);
        }



//        // need to increment the counter for all pages in memoryState
//        for(int i=0; i < memoryState.size(); i++) {
//            // check if page is in HashMap
//            System.out.println("memoryStateMap: " + memoryStateMap);
//            System.out.println("memoryStateMap.containsKey(memoryState.get(i)): " +
//                    memoryStateMap.containsKey(memoryState.get(i)));
//            if(!memoryStateMap.containsKey(memoryState.get(i))) {
//                // add to hash map and initialize value(count) to 1
//                memoryStateMap.put(memoryState.get(i), 1); // first time so count starts at 1
//                System.out.println("DOES NOT contain the page, value should be initialized to 1, " +
//                        "memoryStateMap after add: " + memoryStateMap);
//            } else {
//                // increment count for page
//                count = memoryStateMap.get(memoryState.get(i));
//                System.out.println("memoryStateMap before increment, page ALREADY IN map: " +
//                        memoryStateMap);
//                count++;
//                memoryStateMap.put(memoryState.get(i), count);
//                System.out.println("memoryStateMap ALREADY CONTAINS page, so increment count, " +
//                        "memoryStateMap " +
//                        "after increment: " + memoryStateMap);
//            }
//        }

        System.out.println("numFaults: " + numFaults);
        System.out.println("Current memory state:");
        System.out.println(memoryState);


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
