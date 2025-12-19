package MarioBros;

public class StateFalling implements PlayerState {
    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.FALLING);
        player.moveDown();
    }

    @Override
    public void update(Player player) {
        if (player.isGrounded()) {
            player.updateState(new StateIdle());
        }
    }


    @Override
    public void exit(Player player) {
        player.setAnimation(PlayerStates.IDLE);
    }

    @Override
    public PlayerState getCurrentState() {
        return this;
    }
}
