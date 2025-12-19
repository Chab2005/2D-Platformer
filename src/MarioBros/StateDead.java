package MarioBros;

import Doctrina.CollidableRepository;

public class StateDead implements PlayerState {
    @Override
    public void enter(Player player) {
        player.setAnimation(PlayerStates.DEAD);
    }

    @Override
    public void update(Player player) {
        CollidableRepository.getInstance().unregisterEntity(player);
        player.deadFall();
    }

    @Override
    public void exit(Player player) {

    }

    @Override
    public PlayerState getCurrentState() {
        return null;
    }
}
