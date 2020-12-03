package pagingSchemes;

/*
Virtual Memory Program Assignment
Created by: Michael Janks
COSC 423
Prof: Matt Evett
Fall 2020
Eastern Michigan University

This program simulates four different paging schemes: FIFO, LRU, LFU, and OPT. The data is read in from
the file pages.dat and then the four paging schemes are run. The reference string and memory frames are displayed
in a table. At the end, a brief summary including the number of page faults for each scheme and the optimal
percentage is displayed.
*/


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
        ArrayList<Integer> memoryState = new ArrayList<>();
        int row = 0;
        int col = 0;

        while(scan.hasNextInt()) {
            x = scan.nextInt();
            if (x == -1) {

                // run all of the pagers, print the data tables
                // FIFO  ********************************************************************************************
                row = 0;
                col = 0;
                System.out.println("FIFO:");
                FIFO fifo = new FIFO();
                fifo.setNumFrames(try1.get(0)); // part of refString, first integer of reference string

                int[][] memoryTableFIFO = new int[fifo.getNumFrames()][try1.size()];
                for (int i = 1; i < try1.size(); i++) {
                    if (!fifo.isPageInMemory(try1.get(i))) {
                        memoryState = fifo.replacePage(try1.get(i));
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableFIFO[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    } else {
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableFIFO[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    }
                }
                for(int i=1; i < try1.size(); i++) {
                    System.out.printf("%-3s", try1.get(i) + " ");
                }
                System.out.println();
                for(int i=0; i < try1.size(); i++) {
                    System.out.print("___");
                }
                System.out.println();
                for(int i=0; i < fifo.getNumFrames(); i++) {
                    for(int j=0; j < try1.size(); j++) {
                        System.out.printf("%-3s", memoryTableFIFO[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

                // LRU ********************************************************************************************
                row = 0;
                col = 0;
                System.out.println("LRU:");
                LRU lru = new LRU();
                lru.setNumFrames(try1.get(0));

                int[][] memoryTableLRU = new int[lru.getNumFrames()][try1.size()];
                for (int i = 1; i < try1.size(); i++) {
                    if (!lru.isPageInMemory(try1.get(i))) {
                        memoryState = lru.replacePage(try1.get(i));
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableLRU[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    } else {
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableLRU[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    }
                }
                for(int i=1; i < try1.size(); i++) {
                    System.out.printf("%-3s", try1.get(i) + " ");
                }
                System.out.println();
                for(int i=0; i < try1.size(); i++) {
                    System.out.print("___");
                }
                System.out.println();
                for(int i=0; i < lru.getNumFrames(); i++) {
                    for(int j=0; j < try1.size(); j++) {
                        System.out.printf("%-3s", memoryTableLRU[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

                // LFU ********************************************************************************
                row = 0;
                col = 0;
                System.out.println("LFU:");
                LFU lfu = new LFU();
                lfu.setNumFrames(try1.get(0));

                int[][] memoryTableLFU = new int[lfu.getNumFrames()][try1.size()];
                for (int i = 1; i < try1.size(); i++) {
                    if (!lfu.isPageInMemory(try1.get(i))) {
                        memoryState = lfu.replacePage(try1.get(i));
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableLFU[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    } else {
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableLFU[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    }
                }
                for(int i=1; i < try1.size(); i++) {
                    System.out.printf("%-3s", try1.get(i) + " ");
                }
                System.out.println();
                for(int i=0; i < try1.size(); i++) {
                    System.out.print("___");
                }
                System.out.println();
                for(int i=0; i < lfu.getNumFrames(); i++) {
                    for(int j=0; j < try1.size(); j++) {
                        System.out.printf("%-3s", memoryTableLFU[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

                // OPT ********************************************************************************************
                // Have not addressed the ambiguity
                row = 0;
                col = 0;
                System.out.println("OPT:");
                OPT opt = new OPT();
                opt.setNumFrames(try1.get(0));

                int[][] memoryTableOPT = new int[opt.getNumFrames()][try1.size()];
                for (int i = 1; i < try1.size(); i++) {
                    if (!opt.isPageInMemory(try1.get(i))) {
                        memoryState = opt.replacePage(try1.get(i), try1, i);
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableOPT[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    } else {
                        for(int j=0; j < memoryState.size(); j++) {
                            memoryTableOPT[row][col] = memoryState.get(j);
                            row++;
                        }
                        row = 0;
                        col++;
                    }
                }


                for(int i=1; i < try1.size(); i++) {
                    System.out.printf("%-3s", try1.get(i) + " ");
                }
                System.out.println();
                for(int i=0; i < try1.size(); i++) {
                    System.out.print("___");
                }
                System.out.println();
                for(int i=0; i < opt.getNumFrames(); i++) {
                    for(int j=0; j < try1.size(); j++) {
                        System.out.printf("%-3s", memoryTableOPT[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println();

                // printing the end summary
                float fifoOptimal = (fifo.getNumFaults()/opt.getNumFaults()) * 100;
                float lruOptimal =  (lru.getNumFaults()/opt.getNumFaults()) * 100;
                float lfuOptimal =  (lfu.getNumFaults()/opt.getNumFaults()) * 100;
                System.out.println("Using " + fifo.getNumFrames() + " frames, the reference string yielded:");
                System.out.printf("%-10s", "Scheme");
                System.out.printf("%-10s", "#Faults");
                System.out.printf("%-20s", "%Optimal");
                System.out.println();
                System.out.printf("%-10s", "FIFO");
                System.out.printf("%-10s", fifo.getNumFaults());
                System.out.printf("%-20s", fifoOptimal + "%");
                System.out.println();
                System.out.printf("%-10s", "LRU");
                System.out.printf("%-10s", lru.getNumFaults());
                System.out.printf("%-20s", lruOptimal + "%");
                System.out.println();
                System.out.printf("%-10s", "LFU");
                System.out.printf("%-10s", lfu.getNumFaults());
                System.out.printf("%-20s", lfuOptimal + "%");
                System.out.println();
                System.out.printf("%-10s", "OPT");
                System.out.printf("%-10s", opt.getNumFaults());
                System.out.printf("%-20s", "100%");
                System.out.println();
                System.out.println();

                // clear contents of try1, then go to next try
                try1.clear();
            } else {
                try1.add(x);
            }
        }
    }
}
