package MarioBros;

import Doctrina.Camera;
import Doctrina.Canvas;
import Doctrina.Direction;
import Doctrina.MovableEntity;

public class Enemy extends MovableEntity {

    private Animation animation;
    private Direction direction;
    private Camera camera;

    private int speed;
    private boolean path1 = true;
    private boolean path2 = false;
    private boolean path3 = false;
    private boolean path4 = false;


    public Enemy(Camera camera){
        this.camera = camera;
        setDimension(16,16);
        moveTo(100,100);
        setSpeed(3);
        this.animation = new Animation(this);
        animation.load();
        speed = 3;
    }

    @Override
    public void update() {



        if (!hasMoved()) {
            animation.idle();
        } else {
            animation.entityAnimation();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFrame(getDirection(),canvas);
    }
}
