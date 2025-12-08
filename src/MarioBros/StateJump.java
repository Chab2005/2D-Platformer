package MarioBros;

public class StateJump implements PlayerState{
    @Override
    public void enter(Player player) {

    }

    @Override
    public void update(Player player) {
        if (player.canMoveDown()) {
            player.changeState(new StateFalling());
        }
    }

    @Override
    public void exit(Player player) {

    }
}
