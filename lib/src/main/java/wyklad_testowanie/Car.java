package wyklad_testowanie;

public interface Car {
    static Car getInstance(Infotainment infotainment) {
        throw new UnsupportedOperationException();
    }

    void turnKey(Direction direction);

    IgnitionState getIgnitionState();
}
