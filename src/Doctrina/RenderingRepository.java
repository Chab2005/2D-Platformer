package Doctrina;

import MarioBros.Enemy;
import MarioBros.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RenderingRepository implements Iterable<StaticEntity> {

    private final int SIMULATION_DISTANCE = 700;
    private static RenderingRepository instance;
    private List<StaticEntity> staticEntities;

    public static RenderingRepository getInstance() {
        if (instance == null) {
            instance = new RenderingRepository();
        }
        return instance;
    }

    public void registerEntities(StaticEntity staticEntity) {
        staticEntities.add(staticEntity);
    }

    public void unregisterEntities(StaticEntity staticEntity) {
        staticEntities.remove(staticEntity);
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return staticEntities.iterator();
    }

    public void unregisterEntities(int index) {
        staticEntities.remove(index);
    }

    public void drawRepository(Canvas canvas) {
        for (StaticEntity staticEntity : staticEntities) {
            staticEntity.draw(canvas);
        }
    }

    public void update() {
        Player player = getPlayer();
        for (StaticEntity entity : staticEntities) {
            if (!(entity instanceof Player)) {
                if (entity instanceof Enemy && isEnemyInRange(entity,player) ) {
                    ((MovableEntity) entity).update();
                }
            } else {
                ((Player) entity).update();
            }
        }
    }

    public boolean isEnemyInRange(StaticEntity enemy,Player player) {
        return enemy.getX() < player.getX()+ SIMULATION_DISTANCE;
    }

    public List<StaticEntity> getStaticEntities() {
        return staticEntities;
    }

    private RenderingRepository() {
        staticEntities = new ArrayList<>();
    }

    private Player getPlayer() {
        for (StaticEntity entity : staticEntities) {
            if (entity instanceof Player) {
                return (Player) entity;
            }
        }
        return null;
    }
}
