package Doctrina;

import MarioBros.Enemy;
import MarioBros.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RenderingRepository implements Iterable<StaticEntity> {

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

    public void unregisterEntities(int index) {
        staticEntities.remove(index);
    }

    public void drawRepository(Canvas canvas) {
        for (StaticEntity staticEntity : staticEntities) {
            staticEntity.draw(canvas);
        }
    }

    public void update() {
        for (StaticEntity entity : staticEntities) {
            if (entity instanceof MovableEntity) {
                ((MovableEntity) entity).update();
            }
        }
    }


    public List<StaticEntity> getStaticEntities() {
        return staticEntities;
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return staticEntities.iterator();
    }

    private RenderingRepository() {
        staticEntities = new ArrayList<>();
    }
}
