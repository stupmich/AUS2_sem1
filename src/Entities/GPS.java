package Entities;

public class GPS {
    private char y;
    private double positionY;
    private char x;
    private double positionX;

    public GPS(char y, double positionY, char x, double positionX) {
        this.y = y;
        this.positionY = positionY;
        this.x = x;
        this.positionX = positionX;
    }

    public GPS(String stringFromFile) {
        String[] parts = stringFromFile.split(",");
        this.y = parts[0].charAt(0);
        this.positionY = Double.parseDouble(parts[1]);
        this.x = parts[2].charAt(0);
        this.positionX = Double.parseDouble(parts[3]);
    }

    public char getY() {
        return y;
    }

    public void setY(char y) {
        this.y = y;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    @Override
    public String toString() {
        return "Width=" + y +
                " " + positionY +
                ", Length=" + x +
                " " + positionX;
    }

    public String toStringCSV() {
        return y +
                "," + positionY +
                "," + x +
                "," + positionX;
    }

}
