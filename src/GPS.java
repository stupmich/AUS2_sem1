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
}
