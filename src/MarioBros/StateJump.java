package MarioBros;

public class StateJump implements PlayerState {
    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.FALLING);
    }

    @Override
    public void update(Player player) {
        if (!player.playerJumpFinished()) {
            player.jumpEffect();
        }
        if (!player.playerJumpFinished() && !player.canMoveUp()) {
            SoundEffect.BUMP.playOnce();
        }
        if (player.playerJumpFinished() && player.canMoveDown() || !player.canMoveUp()) {
            player.updateState(new StateFalling());
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
