package pagingSchemes;

public class Main {
    public static void main(String[] args) {
        FIFO f = new FIFO();

        f.setNumFrames(3);
        f.print();
        System.out.println(f.getNumFrames());


    }
}
