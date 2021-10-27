package utils;
public enum Compass {
    N, E, S, W;

    private static Compass[] points = values();

    public static Compass turnLeft(Compass bearing) {
        if(bearing.equals(N)) {
            return W;
        } else {
            return points[(bearing.ordinal() - 1) ];
        }
    }
    public static Compass turnRight(Compass bearing) {
        return points[(bearing.ordinal()+1) % points.length];
    }
}
