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

        ArrayList<Integer> testData = new ArrayList<>();
        testData.add(1);
        testData.add(2);
        testData.add(3);
        testData.add(4);
        testData.add(2);
        testData.add(7);
        testData.add(5);
        testData.add(1);
        testData.add(1);
        testData.add(6);
        testData.add(4);
        testData.add(7);
        testData.add(2);
        testData.add(1);
        testData.add(2);
        testData.add(5);

        // FIFO
//        FIFO fifo = new FIFO();
//        fifo.setNumFrames(4); // part of refString, first integer of reference string
//        for(int i=0; i < testData.size(); i++) {
//            if(!fifo.isPageInMemory(testData.get(i))) {
//                fifo.replacePage(testData.get(i));
//            }
//        }
//        fifo.print();


        // OPT
        // Have not addressed the ambiguity
//        OPT opt = new OPT();
//        opt.setNumFrames(4);
//        for(int i=0; i < testData.size(); i++) {
//            if(!opt.isPageInMemory(testData.get(i))) {
//                opt.replacePage(testData.get(i), testData, i);
//            }
//        }
//        opt.print();

        // LRU
//        LRU lru = new LRU();
//        lru.setNumFrames(4);
//        for(int i=0; i < testData.size(); i++) {
//            if (!lru.isPageInMemory(testData.get(i))) {
//                lru.replacePage(testData.get(i));
//
//            }
//        }
//        lru.print();

        // LFU
        LFU lfu = new LFU();
        lfu.setNumFrames(4);
        for(int i=0; i < testData.size(); i++) {
            if (!lfu.isPageInMemory(testData.get(i))) {
                lfu.replacePage(testData.get(i));

            }
        }
        lfu.print();




    }

}

