package MarioBros;

public enum PlayerStates {
    IDLE(0,36),
    RUN(0,105),
    AIRBORNE(0,142),
    GROUNDED(0,72),
    FALLING(64,0);
    //TALL_IDLE,
    //TALL_RUN,
    //TALL_AIRBORNE,
    //TALL_GROUNDED,
    //TALL_FALLING,
    //CROUCHING,

    private int x;
    private int y;

    PlayerStates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
