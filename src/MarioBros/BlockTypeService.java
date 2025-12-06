package MarioBros;

public class BlockTypeService {
    private BlockType blockType;

    public BlockTypeService() {
        blockType = BlockType.AIR;
    }

    public boolean isAir(int index) {
        return BlockType.AIR.isAir(index);
    }

    public boolean isDecoration(int index) {
        return blockType.isDecoration(index);
    }

    public int getPosX(int index) {
        return blockType.getPosX(index);
    }

    public int getPosY(int index) {
        return blockType.getPosY(index);
    }

}
