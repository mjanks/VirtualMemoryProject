package pagingSchemes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // need to read in from 'pages.dat' and parse
        // first integer is numFrames
        // the rest go into 'refString'
        // read in up to '-1', delimiter

        // for testing
        ArrayList<Integer> refString = new ArrayList<>();
        refString.add(7);
        refString.add(0);
        refString.add(1);
        refString.add(2);
        refString.add(0);
        refString.add(3);
        refString.add(0);
        refString.add(4);
        refString.add(2);
        refString.add(3);
        refString.add(0);
        refString.add(3);
        refString.add(2);
        refString.add(1);
        refString.add(2);
        refString.add(0);
        refString.add(1);
        refString.add(7);
        refString.add(0);
        refString.add(1);

        ArrayList<Integer> optTest = new ArrayList<>();
        optTest.add(1);
        optTest.add(2);
        optTest.add(3);
        optTest.add(4);
        optTest.add(2);
        optTest.add(7);
        optTest.add(5);
        optTest.add(1);
        optTest.add(1);
        optTest.add(6);
        optTest.add(4);
        optTest.add(7);
        optTest.add(2);
        optTest.add(1);
        optTest.add(2);
        optTest.add(5);

        // FIFO
        FIFO fifo = new FIFO();
        fifo.setNumFrames(4); // part of refString, first integer of reference string
        for(int i=0; i < optTest.size(); i++) {
            if(!fifo.isPageInMemory(optTest.get(i))) {
                fifo.replacePage(optTest.get(i));
            }
        }
        fifo.print();

        // LRU

        // LFU

        // OPT
        // Have not addressed the ambiguity
        OPT opt = new OPT();
        opt.setNumFrames(4);
        for(int i=0; i < optTest.size(); i++) {
            if(!opt.isPageInMemory(optTest.get(i))) {
                opt.replacePage(optTest.get(i), optTest, i);
            }
        }
        opt.print();



    }

}

