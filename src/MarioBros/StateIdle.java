package MarioBros;

public class StateIdle implements PlayerState {



    @Override
    public void enter(Player player) {
        //player.setDimension(12,);
    }

    @Override
    public void update(Player player) {
        if (player.isMovingUp()) {
            player.changeState(new StateJump());
        }
        if (player.isMovingSides()) {
            player.changeState(new StateRun());
        }
        if (player.isMoving()) {
            player.changeState(new StateFalling());
        }

    }

    @Override
    public void exit(Player player) {

    }
}
