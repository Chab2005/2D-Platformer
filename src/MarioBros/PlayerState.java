package MarioBros;

public interface PlayerState {

    void enter(Player player);
    void update(Player player);
    void exit(Player player);
    PlayerState getCurrentState();
}
