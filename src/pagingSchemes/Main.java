package pagingSchemes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        Scanner scan = null;
        try {
            scan = new Scanner(new File("pages.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> try1 = new ArrayList<>();
        while(scan.hasNextInt()) {
            x = scan.nextInt();
            if (x == -1) {
                // run all of the pagers

                System.out.println("FIFO:");
                FIFO fifo = new FIFO();
                fifo.setNumFrames(try1.get(0)); // part of refString, first integer of reference string
                for (int i = 1; i < try1.size(); i++) {
                    if (!fifo.isPageInMemory(try1.get(i))) {
                        fifo.replacePage(try1.get(i));
                    }
                }
                System.out.println("FIFO numFaults: " + fifo.getNumFaults());

                // OPT
                // Have not addressed the ambiguity
                System.out.println("OPT:");
                OPT opt = new OPT();
                opt.setNumFrames(try1.get(0));
                for(int i=1; i < try1.size(); i++) {
                    if(!opt.isPageInMemory(try1.get(i))) {
                        opt.replacePage(try1.get(i), try1, i);
                    }
                }
                System.out.println("OPT numFaults: " + opt.getNumFaults());

                // LRU
                System.out.println("LRU:");
                LRU lru = new LRU();
                lru.setNumFrames(try1.get(0));
                for(int i=1; i < try1.size(); i++) {
                    if (!lru.isPageInMemory(try1.get(i))) {
                        lru.replacePage(try1.get(i));
                    }
                }
                System.out.println("LRU numFaults: " + lru.getNumFaults());

                // LFU
                System.out.println("LFU:");
                LFU lfu = new LFU();
                lfu.setNumFrames(try1.get(0));
                for(int i=1; i < try1.size(); i++) {
                    if (!lfu.isPageInMemory(try1.get(i))) {
                        lfu.replacePage(try1.get(i));
                    }
                }
                System.out.println("LFU numFaults: " + lfu.getNumFaults());

                // clear contents of try1, then go to next try
                try1.clear();
            } else {
                try1.add(x);
            }
        }
    }
}
