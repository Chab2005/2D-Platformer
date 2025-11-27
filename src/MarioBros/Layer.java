package MarioBros;

import java.util.ArrayList;

public class Layer {


    private ArrayList<Chunks> chunks;

    public Layer(){
        chunks = new ArrayList<>();
    }

    public ArrayList<Chunks> getChunks() {
        return chunks;
    }

    public void setChunks(ArrayList<Chunks> chunks) {
        this.chunks = chunks;
    }

    public void add(Chunks chunk) {
        chunks.add(chunk);
    }


}
