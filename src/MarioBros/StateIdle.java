package MarioBros;

public class StateIdle implements EntityState {

    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.IDLE);
    }

    @Override
    public void update(Player player) {
        if (player.isMovingSides() ) {
            player.updateState(new StateRun());
        }
        if (player.isJumping()) {
            SoundEffect.JUMP.play();
            player.updateState(new StateJump());
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
