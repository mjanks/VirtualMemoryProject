package pagingSchemes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // need to read in from 'pages.dat' and parse
        // first integer is numFrames
        // the rest go into 'refString'
        // read in upto '-1', delimiter

        // for testing
        ArrayList<Integer> refString = new ArrayList<Integer>();
        refString.add(7);
        refString.add(0);
        refString.add(1);
        refString.add(7);
        refString.add(2);
        refString.add(2);
        refString.add(5);

        // FIFO
        FIFO f = new FIFO();
        f.setNumFrames(3); // part of refString, first integer of reference string
        for(int i=0; i < refString.size(); i++) {
            if(!f.isPageInQueue(refString.get(i))) {
                f.replacePage(refString.get(i));
            }
        }



    }

}

