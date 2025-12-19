package MarioBros;

public enum BlockType {
    AIR(0,0,0,true),
    GROUND(1,0,0,false),
    BRICK(2,32,0,false),
    HARD_BRICK(30,0,32,false),
    PIPE_TOP_LEFT(233,0,256,false),
    PIPE_TOP_RIGHT(234,32,256,false),
    PIPE_BODY_LEFT(262,0,288,false),
    PIPE_BODY_RIGHT(263,32,288,false),
    QUESTION_BLOCK(24,736,0,false),

    CASTLE_BRICK(14,416,0,true),
    CASTLE_DOOR_TOP(42,384,32,true),
    CASTLE_DOOR_BOTTOM(43,416,32,true),
    CASTLE_TOP_BRICK(41,352,32,true),
    CASTLE_TOP(12,352,0,true),
    CASTLE_WINDOW_LEFT(13,384,0,true),
    CASTLE_WINDOW_RIGHT(15,448,0,true),

    FLAG_POLE_BODY(210, 192,224,true),
    FLAG_POLE_TOP(249,512,256,true),
    MOUNTAIN_START_SLOPE(241,256,256,true),
    MOUNTAIN_END_SLOPE(243,320,256,true),
    MOUNTAIN_EYE_RIGHT(270,256,288,true),
    MOUNTAIN_EYE_LEFT(272,320,288,true),
    MOUNTAIN_BODY(271,288,288,true),
    MOUNTAIN_TOP(242,288,256,true),
    BUSH_START(273,352,288,true),
    BUSH_BODY(274,384,288,true),
    BUSH_END(275,416,288,true)
    ;

    private final int index;
    private final int posX;
    private final int posY;
    private final boolean isDecoration;

    BlockType(int index, int posX, int posY, boolean isDecoration) {
        this.index = index;
        this.posX = posX;
        this.posY = posY;
        this.isDecoration = isDecoration;
    }


    public boolean isAir(int index) {
        return AIR.index == index;
    }

    public boolean isDecoration(int index) {
        return getBlockType(index).isDecoration;
    }

    public int getPosX(int index) {
        return getBlockType(index).posX;
    }

    public int getPosY(int index) {
        return getBlockType(index).posY;
    }

    public int getIndex() {
        return getBlockType(index).index;
    }

    private BlockType getBlockType(int index) {
        BlockType blockToReturn = BlockType.AIR;
        for (BlockType blockType : BlockType.values()) {
            if (isSameBlockType(blockType, index)) {
                blockToReturn = blockType;
            }
        }
        return blockToReturn;
    }

    private boolean isSameBlockType(BlockType blockType,int index) {
        return blockType.index == index;
    }
}
