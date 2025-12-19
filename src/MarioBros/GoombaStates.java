package MarioBros;

public enum GoombaStates implements State {
    RUN(0,0,3),
    DEAD(0,96,1);

    private int x;
    private int y;
    private int frameAmmount;

    GoombaStates(int x, int y, int frameAmmount) {
        this.x = x;
        this.y = y;
        this.frameAmmount = frameAmmount;
    }

    @Override
    public int getFrameAmmount() { return frameAmmount;}

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }
}
