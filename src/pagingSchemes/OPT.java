package pagingSchemes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OPT extends Pager{
    //int index = 0;
    int count = 0;
    int distance = 0;
    int highest = 0;
    boolean found = false;
    //ArrayList<Integer> distances = new ArrayList<Integer>();

    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
                System.out.print("Current memory state: ");
                System.out.println(memoryState);
                //System.out.println("Current refString index: " + index);
                System.out.println("Matched: " + page + " Move on to next page, this page already in memory,");
                //index++;
                //System.out.println();
                //System.out.println("Current memory state:");
                //System.out.println(memoryState);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Integer> replacePage(int page, ArrayList<Integer> refString, int index) {
        //System.out.println("In method replacePage: " + memoryState);
        //System.out.println("numFrames: " + numFrames);
        if (memoryState.size() < numFrames) {
            System.out.print("replacePage() - Current memory state: ");
            System.out.println(memoryState);
            System.out.println("Current refString index: " + index);
            System.out.println("No match and space available. Add page " + page + " to memory. " +
                    " refString.get(index) = " + refString.get(index));
            memoryState.add(page);
            //index++;
            //refString.remove(0);
            //System.out.println("refString after remove " + page + " : " + refString);
            numFaults++;
            System.out.println("numFaults: " + numFaults);
            //System.out.println("Current memory state:");
            //System.out.println(memoryState);
            return memoryState;
        }

        // Need to replace a page, choose the page in memoryState to remove that is
        // closest to the end of refString list.
        // replace the page with refString.get(index)
        // start checking refString locations at index+1

        HashMap<Integer, Integer> distancePageMap = new HashMap<>();
        for(int i=0; i < memoryState.size(); i++) { // [7, 0, 1]
            for(int j=(index+1); j < refString.size(); j++) {
                if (memoryState.get(i) == refString.get(j) && !found) {
                    // found the page
                    distance = count; // set the distance, if found again, distance will be updated again
                    found = true;
                }
                count++;
            }

            // add distance/page pair to hashmap
            System.out.println("distance: " + distance);
            distancePageMap.put(distance, memoryState.get(i));
            count = 0;
            found = false;
        }

        // find the distance/page pair with the greatest distance. Distance is the key, page is the value
        System.out.println(distancePageMap);
        int key = 13; // find the highest key! ***** NEED TO IMPLEMENT! *******



        for(int i=0; i < memoryState.size(); i++) {
            if(memoryState.get(i) == distancePageMap.get(key)) {
                System.out.println("Adding page " + page + ". State before page replace: " + memoryState);
                System.out.println("index: " + index);
                memoryState.set(i, page);
                System.out.println("Memory State after page replace: " + memoryState);
            }
        }












//        int indexToReplace = 0;
//        highest = 0;
//        for(int i=0; i < distances.size(); i++) {
//            if (distances.get(i) > highest) {
//                indexToReplace = i;
//                highest = distances.get(i);
//            }
//
//        }


        //index++;
        //distances = new ArrayList<Integer>();

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
        System.out.println("OPT memoryState: " + memoryState);
        System.out.println("OPT numFaults: " + numFaults);
    }
}
