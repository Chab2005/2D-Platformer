package MarioBros;

public class StateRun implements EntityState {

    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.RUN);
    }

    @Override
    public void update(Player player) {
        if (!player.isMoving() ) {
            player.updateState(new StateIdle());
        }
        if (player.isJumping()) {
            SoundEffect.JUMP.play();
            player.updateState(new StateJump());
        }
        if (!player.isGrounded()) {
            player.updateState(new StateFalling());
        }
    }

    @Override
    public void exit(Player player) {

    }

    @Override
    public EntityState getCurrentState() {
        return this;
    }
}
