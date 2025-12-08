package MarioBros;

public class StateFalling implements PlayerState{
    @Override
    public void enter(Player player) {
        player.moveDown();

    }

    @Override
    public void update(Player player) {
        System.out.println("IS MOVING DOWN");
        if (!player.canMoveDown()) {
            player.changeState(new StateIdle());
        } else {
            player.changeState(new StateFalling());
        }
        if (player.isMovingSides()) {
            player.changeState(new StateRun());
        }
        if (player.isMovingUp()) {
            player.changeState(new StateJump());
        }


    }

    @Override
    public void exit(Player player) {

    }
}
