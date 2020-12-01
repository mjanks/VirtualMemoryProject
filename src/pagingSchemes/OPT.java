package pagingSchemes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class OPT extends Pager{
    int count = 0;
    int distance = 0;
    boolean found = false;
    int max = 0;
    int bigNum = 10000;

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

    public ArrayList<Integer> replacePage(int page, ArrayList<Integer> refString, int index) {
        if (memoryState.size() < numFrames) {
            System.out.print("replacePage() - Current memory state: ");
            System.out.println(memoryState);
            System.out.println("Current refString index: " + index);
            System.out.println("No match and space available. Add page " + page + " to memory. " +
                    " refString.get(index) = " + refString.get(index));
            memoryState.add(page);
            numFaults++;
            System.out.println("numFaults: " + numFaults);
            return memoryState;
        }

        // Need to replace a page, choose the page in memoryState to remove that is
        // closest to the end of refString list.

        HashMap<Integer, Integer> distancePageMap = new HashMap<>();
        for(int i=0; i < memoryState.size(); i++) {
            for(int j=(index+1); j < refString.size(); j++) {
                if (memoryState.get(i) == refString.get(j) && !found) {
                    // found the page
                    distance = count; // set the distance, if found again, distance will not be changed
                    found = true;
                }
                count++;
            }
            if(!found) { // deals with situation when the incoming page never again appears in the refString
                bigNum++;
                distance =  bigNum;
            }

            // add distance/page pair to hashmap
            System.out.println("distance: " + distance);
            distancePageMap.put(distance, memoryState.get(i));
            count = 0;
            found = false;
        }

        // find the distance/page pair with the greatest distance. Distance is the key, page is the value
        System.out.println(distancePageMap);
        max = 0;
        Set<Integer> keys = distancePageMap.keySet();
        Object[] keysArr = keys.toArray();
        for(int i=0; i < keysArr.length; i++) {
            System.out.println(keysArr[i]);
            if((Integer)keysArr[i] > max)
                max = (Integer)keysArr[i];

        }
        System.out.println("max: " + max);

        for(int i=0; i < memoryState.size(); i++) {
            if(memoryState.get(i) == distancePageMap.get(max)) { // key is max key
                System.out.println("Adding page " + page + ". State before page replace: " + memoryState);
                System.out.println("index: " + index);
                memoryState.set(i, page);
                numFaults++;
                System.out.println("Memory State after page replace: " + memoryState);
            }
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
        System.out.println("*********************** OPT ***********************");
        System.out.println("OPT numFrames: " + numFrames);
        System.out.println("OPT memoryState: " + memoryState);
        System.out.println("OPT numFaults: " + numFaults);
        System.out.println("*********************** OPT ***********************");
    }
}
