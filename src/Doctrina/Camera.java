package Doctrina;

import MarioBros.Player;

public class Camera {
    private final int START_SCREEN = 1;
    private final int MIDDLE_SCREEN = 600;

    private CollidableRepository collidableRepository;
    private RenderingRepository renderingRepository;
    private Player player;

    public Camera(Player player) {
        this.renderingRepository = RenderingRepository.getInstance();
        this.collidableRepository = CollidableRepository.getInstance();
        this.player = player;
    }

    public void follow() {
        moveEntities();
        negatePlayerMovement();
    }

    private void negatePlayerMovement() {
        cameraRightRestriction();
        cameraLeftRestriction();
    }


    private void cameraRightRestriction() {
        if (isPlayerAtHalfScreen()) {
            player.x -= player.getSpeed();
        }
    }

    private void cameraLeftRestriction() {
        if (isPlayerAtStartScreen()) {
            player.x += player.getSpeed();
        }
    }

    private void moveEntities() {
        for (StaticEntity entity : renderingRepository) {
            if (notThePlayer(entity)) {
                moveEntityLeft(entity);
                // Keeping this in the eventuality of wanting more camera movement
                //moveEntityDown(entity);
                //moveEntityUp(entity);
                //moveEntityRight(entity);
            }
        }
    }

    // This is here in the optic that we would allow the player to go back
    // in the level
    private void moveEntityRight(StaticEntity entity) {
        if (player.isMovingLeft()) {
            entity.x += player.getSpeed();
        }
    }

    private void moveEntityLeft(StaticEntity entity) {
        if (isPlayerAtHalfScreen() && player.isMovingRight()) {
            entity.x -= player.getSpeed();
        }
    }

    private void moveEntityDown(StaticEntity entity) {
        if (player.isMovingUp()) {
            entity.y += player.getSpeed();
        }
    }

    private void moveEntityUp(StaticEntity entity) {
        if (player.isMovingDown()) {
            entity.y -= player.getSpeed();
        }
    }

    private boolean notThePlayer(StaticEntity entity) {
        return !(entity instanceof Player);
    }

    private boolean isPlayerAtHalfScreen() {
        return player.x > MIDDLE_SCREEN;
    }

    private boolean isPlayerAtStartScreen() {
        return player.x < START_SCREEN;
    }
}
