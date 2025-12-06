package MarioBros;

public enum BlockType {
    AIR(0,0,0,true),
    GROUND(1,0,0,false),
    BRICK(2,16,0,false),
    HARD_BRICK(30,0,16,false),
    PIPE_TOP_LEFT(233,0,128,false),
    PIPE_TOP_RIGHT(234,16,128,false),
    PIPE_BODY_LEFT(262,0,144,false),
    PIPE_BODY_RIGHT(263,16,144,false),
    QUESTION_BLOCK(24,368,0,false),
    CASTLE_BRICK(14,208,0,false),
    CASTLE_DOOR_TOP(42,192,16,false),
    CASTLE_DOOR_BOTTOM(43,208,16,false),
    CASTLE_TOP_BRICK(41,176,16,true),
    CASTLE_TOP(12,176,0,true),
    CASTLE_WINDOW_LEFT(13,192,0,true),
    CASTLE_WINDOW_RIGHT(15,224,0,true),
    FLAG_POLE_BODY(210,96,112,true),
    FALG_POLE_TOP(249,256,128,true),
    MOUNTAIN_START_SLOPE(241,128,128,true),
    MOUNTAIN_END_SLOPE(243,160,128,true),
    MOUNTAIN_EYE_RIGHT(270,128,144,true),
    MOUNTAIN_EYE_LEFT(272,160,144,true),
    MOUNTAIN_BODY(271,144,144,true),
    MOUNTAIN_TOP(242,144,128,true),
    BUSH_START(273,176,144,true),
    BUSH_BODY(274,192,144,true),
    BUSH_END(275,208,144,true)
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
