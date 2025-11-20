package Doctrina;

public abstract class ControllableEntity extends MovableEntity {

    private final MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveWithController() {
        move(controller.getDirection());
    }

    public boolean isMoving() {
        return controller.isMoving();
    }
}
