Pager class
    • public
    • abstract
    • the four pager classes will implement this interface
    • what things do ALL types of Pagers do?
    • int nunFrames = number of frames
    • int numFaults = number of faults
    • int accesses = number of page requests handled so far. Can be considered the “time” of the simulation
    • Here is a C++ header file for a class Pager

FIFO class
    • First-in, first-out. Should be relatively straightforward to implement

LRU class
    • TO-DO

LFU class
    • TO-DO

OPT class
    • TO-DO

Input file
    • how to handle the input file
    • This link takes you to a brief tutorial on reading input text files in Java.
    • F = int, number of frames of memory in the OS
    • reference string = list of integers (0-99) representing page numbers
    • -1: delimiter, end of reference string

Output
    • handle the printing of the data
    • Here is a C++ header file for PrintBuffer
    • output lines should never be longer than 80 characters
    • printPageTable()
    • printDetailTable()
