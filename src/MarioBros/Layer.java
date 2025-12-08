package MarioBros;

import java.util.ArrayList;

public class Layer {


    private ArrayList<Chunk> chunks;

    public Layer(){
        chunks = new ArrayList<>();
    }

    public ArrayList<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
    }

    public void add(Chunk chunk) {
        chunks.add(chunk);
    }


}
