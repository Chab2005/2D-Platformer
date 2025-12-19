package MarioBros;

public enum PlayerStates implements State {
    IDLE(0,36,1),
    RUN(0,36,3),
    FALLING(0,0,1),
    DEAD(0,1024,1);
    //TALL_IDLE,
    //TALL_RUN,
    //TALL_AIRBORNE,
    //TALL_GROUNDED,
    //TALL_FALLING,
    //CROUCHING,

    private int x;
    private int y;
    private int frameAmmount;

    PlayerStates(int x, int y, int frameAmmount) {
        this.x = x;
        this.y = y;
        this.frameAmmount = frameAmmount;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getFrameAmmount() {
        return frameAmmount;
    }

}
