package pagingSchemes;

/*
Virtual Memory Program Assignment
Created by: Michael Janks
COSC 423
Prof: Matt Evett
Fall 2020
Eastern Michigan University
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class OPT extends Pager {
    int count = 0;
    int distance = 0;
    boolean found = false;
    int max = 0;
    int bigNum = 1000000000;

    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> replacePage(int page, ArrayList<Integer> refString, int index) {
        if (memoryState.size() < numFrames) {
            memoryState.add(page);
            numFaults++;
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
                bigNum++; // does not address the ambiguity
                distance =  bigNum;
            }

            // add distance/page pair to hashmap
            distancePageMap.put(distance, memoryState.get(i));
            count = 0;
            found = false;
        }

        // find the distance/page pair with the greatest distance. Distance is the key, page is the value
        max = 0;
        Set<Integer> keys = distancePageMap.keySet();
        Object[] keysArr = keys.toArray();
        for(int i=0; i < keysArr.length; i++) {
            if((Integer)keysArr[i] > max)
                max = (Integer)keysArr[i];
        }

        for(int i=0; i < memoryState.size(); i++) {
            if(memoryState.get(i) == distancePageMap.get(max)) { // key is max key
                memoryState.set(i, page);
                numFaults++;
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
