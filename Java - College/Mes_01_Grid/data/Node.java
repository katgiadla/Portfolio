package data;

import java.util.Objects;

public class Node {
    double x,y, temperature;

    public Node(double x, double y, double temperature) {
        this.x = x;
        this.y = y;
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node Node = (Node) o;
        return Double.compare(Node.x, x) == 0 &&
                Double.compare(Node.y, y) == 0 &&
                Double.compare(Node.temperature, temperature) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y, temperature);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getID(){
        return "x: "+ x + " y: "+y;
    }

    @Override
    public String toString() {
        return "x=" + x +", y=" + y +", temperature=" + temperature +" \t";
    }
}