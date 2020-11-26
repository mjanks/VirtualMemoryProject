package pagingSchemes;

public class FIFO extends Pager {

    @Override
    public void setNumFrames(int n) {
        this.numFrames = n;
    }

    @Override
    public void print() {
        System.out.println("numFrames: " + numFrames);
    }

    




}
