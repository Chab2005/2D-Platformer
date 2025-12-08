package MarioBros;

public class StateRun implements PlayerState{

    @Override
    public void enter(Player player) {

    }

    @Override
    public void update(Player player) {
        if (!player.isMoving()) {
            player.changeState(new StateIdle());
        }

    }

    @Override
    public void exit(Player player) {

    }
}
