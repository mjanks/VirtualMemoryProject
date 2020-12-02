package pagingSchemes;

import java.util.ArrayList;

public class FIFO extends Pager {


    // check if page is in memory
    public boolean isPageInMemory(int page) {
        for(int i=0; i < memoryState.size(); i++) {
            if (page == memoryState.get(i)) {
//                System.out.println("matched: " + page);
//                System.out.println("move on to next page. This page already in memory");
//                System.out.println(memoryState);

                // set up for print
                pageArr.add(page);
                print(page);
                return true;
            }
        }
        return false;
    }

    // call this if isPageInQueue returns false; need to replace the page
    public ArrayList<Integer> replacePage(int page) {
        if (memoryState.size() < numFrames) {
//            System.out.println("Current memory state:");
//            System.out.println(memoryState);
//            System.out.println("No match and space available. Add page " + page + " to memory.");
            memoryState.add(page);



            // set up for print
            pageArr.add(page);
            print(page);


            numFaults++;
//            System.out.println("numFaults: " + numFaults);
//            System.out.println("Current memory state:");
//            System.out.println(memoryState);
            return memoryState;
        }

        // if no match AND memoryState FULL (size = numFrames),
        // remove the first element of the ArrayList memoryState
//        System.out.println("Current memory state:");
//        System.out.println(memoryState);
//        System.out.println("All frames full. Remove page " + memoryState.get(0) + " from memory.");
        memoryState.remove(0);
//        System.out.println("Current memory state:");
//        System.out.println(memoryState);

        // add page to the memoryState
//        System.out.println("Add page " + page + " to memory.");
        memoryState.add(page);

        // setup for print
        pageArr.add(page);
        print(page);


        numFaults++;
//        System.out.println("numFaults: " + numFaults);
//        System.out.println("Current memory state:");
//        System.out.println(memoryState);
        return memoryState;
    }



    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    @Override
    public void setAccesses(int n) {
        this.accesses = n;
    }

    public void print(int p) {
        System.out.print("Page: " + p);
        System.out.println(" -- Memory State: " + memoryState);
//        System.out.println("*********************** FIFO **********************************************************");
//        System.out.println("FIFO numFrames: " + numFrames);
//        System.out.println("FIFO memoryState: " + memoryState);
//        System.out.println("FIFO numFaults: " + numFaults);
//        System.out.println("*********************** FIFO **********************************************************");
    }


}
