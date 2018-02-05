package ru.sbt.www.project.ostrov;


public class Wolf implements Animal {
    final byte HANGRY = 10;

    private static int count = 0;
    private int x, y;
    private byte hungryDay = HANGRY ;

    Wolf(){
        ++count;

    }

    public static int getCount() {
        return count;
    }


    @Override
    public void nextStep(int xMax, int yMax) {
        int[] arXY  = Behavior.step(x,y,xMax,yMax);
        x = arXY[0];
        y = arXY[1];
    }



    @Override
    public void gorge() {
        hungryDay = HANGRY;
    }


    public static void die() {
        --count;
    }

    //--- Set and Get Functions --
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


    public byte decrementHungry() {
        return --hungryDay;
    }


}
