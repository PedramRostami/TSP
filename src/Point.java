public class Point {
    private int x;
    private int y;
    private boolean isVisited;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        isVisited = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public double distance(Point o) {
        int dX = x - o.x, dY = y - o.y;
        return Math.sqrt(dX*dX + dY*dY);
    }
}
