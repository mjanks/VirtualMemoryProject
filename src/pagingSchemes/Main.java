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

        // FIFO
//        FIFO fifo = new FIFO();
//        fifo.setNumFrames(3); // part of refString, first integer of reference string
//        fifo.print();
//        for(int i=0; i < refString.size(); i++) {
//            if(!fifo.isPageInMemory(refString.get(i))) {
//                fifo.replacePage(refString.get(i));
//            }
//        }
//        fifo.print();

        // LRU

        // LFU

        // OPT
        OPT opt = new OPT();
        opt.setNumFrames(3);
        for(int i=0; i < refString.size(); i++) {
            //System.out.println(refString);
            if(!opt.isPageInMemory(refString.get(i))) {
                //System.out.println("here");
                //System.out.println(opt.memoryState);
                opt.replacePage(refString.get(i), refString, i);
            }
        }
        opt.print();


    }

}

