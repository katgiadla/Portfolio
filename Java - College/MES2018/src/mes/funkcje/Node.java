package mes.funkcje;

import java.util.Objects;

public class Node {
    private int id;
    private double x,y,temperature;
    private boolean borderCondition;
    private static final int UNKNOWN_TEMPERATURE = -900000000;

    public Node() {
        id = -1;
        x=-1;
        y=-1;
        temperature=UNKNOWN_TEMPERATURE;
        borderCondition=false;
    }

    public Node(double x, double y) {
        id = -1;
        this.x = x;
        this.y = y;
        temperature=UNKNOWN_TEMPERATURE;
        borderCondition=false;
    }

    public Node(int id, double x, double y, double temperature, boolean borderCondition) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.temperature = temperature;
        this.borderCondition = borderCondition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setBorderCondition(boolean borderCondition) {
        this.borderCondition = borderCondition;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isBorderCondition() {
        return borderCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, x, y, temperature, borderCondition);
    }

    @Override
    public String toString() {
        return "Node{"+ id +
                "," + x +
                "," + y +
                '}';
    }
}
