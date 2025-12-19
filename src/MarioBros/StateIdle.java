package MarioBros;

public class StateIdle implements PlayerState {

    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.IDLE);
    }

    @Override
    public void update(Player player) {
        if (player.isMovingSides() && player.isMoving()) {
            player.updateState(new StateRun());
        }
        if (player.isJumping()) {
            player.updateState(new StateJump());
            SoundEffect.JUMP.playOnce();
        }
    }

    @Override
    public void exit(Player player) {

    }

    @Override
    public PlayerState getCurrentState() {
        return this;
    }
}
