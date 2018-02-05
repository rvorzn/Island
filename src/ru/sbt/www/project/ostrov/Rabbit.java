package ru.sbt.www.project.ostrov;


public class Rabbit implements Animal {
    private static int count = 0;
    private byte hungryDay = 5;
    private int x, y;

    Rabbit(){
        ++count;
    }

    public static int getCount() {
        return count;
    }

    public static void die(){
        --count;
    }

    @Override
    public byte decrementHungry() {
        return --hungryDay;
    }

    @Override
    public void gorge() {
        hungryDay = 5;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }

    @Override
    public void nextStep(int xMax, int yMax) {
        int[] arXY  = Behavior.step(x,y,xMax,yMax);
        x = arXY[0];
        y = arXY[1];
    }
}
