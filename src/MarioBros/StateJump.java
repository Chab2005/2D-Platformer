package MarioBros;

public class StateJump implements EntityState {
    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.FALLING);
    }

    @Override
    public void update(Player player) {
        if (!player.playerJumpFinished()) {
            player.moveUp();
        }
        if (player.playerJumpFinished() && player.canMoveDown() || !player.canMoveUp()) {
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
