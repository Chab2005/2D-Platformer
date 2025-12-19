package MarioBros;

import Doctrina.MovableEntity;
import Doctrina.RenderingRepository;
import Doctrina.StaticEntity;

import java.awt.*;
import java.util.ArrayList;

public class EntityCollision {
    private RenderingRepository instance;
    private int lastStaticEntity;
    private ArrayList<StaticEntity> enemies ;

    public EntityCollision() {
        this.instance = RenderingRepository.getInstance();
        this.lastStaticEntity = -1;
        initListEnemies();
    }

    private void initListEnemies() {
        enemies = new ArrayList<>();
        for (StaticEntity entity : instance.getStaticEntities()) {
            if (entity instanceof Enemy) {
                this.enemies.add(entity);
            }
            if (entity instanceof FlagPole) {
                this.enemies.add(entity);
            }
        }
    }

    public Boolean isPlayerInCollision(Player player) {
        for (StaticEntity enemy : enemies) {
            if (player.intersectsWith(enemy)) {
                lastStaticEntity = RenderingRepository.getInstance().getStaticEntities().indexOf(enemy);
                return true;
            }
        }
        return false;
    }

    public int getLastStaticEntity() {
        return lastStaticEntity;
    }

    public void setLastStaticEntity() {
        lastStaticEntity = -1;
    }
}
