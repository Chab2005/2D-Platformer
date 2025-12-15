package MarioBros;

public interface EntityState {

    void enter(Player player);
    void update(Player player);
    void exit(Player player);
    EntityState getCurrentState();
}
