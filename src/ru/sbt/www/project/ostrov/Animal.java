package ru.sbt.www.project.ostrov;


import java.util.*;

interface Animal {
    void nextStep(int xMax, int yMax);

    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    //byte getHungryDay();
    byte decrementHungry();

    void gorge();


    class Behavior {

        static public int[] step(int x, int y, int xMax, int yMax) {
            int rangeStep = 1;
            final byte TOP = 1, BOTTOM = 3, LEFT = 0, RIGTH = 2, TOP_RIGTH = 5, TOP_LEFT = 4, BOTTOM_LEFT = 6, BOTTOM_RIGTH = 7;
            Set<Byte> legalStep = new HashSet<>();

            boolean resultStep ;
            if ("wolf".equalsIgnoreCase(Thread.currentThread().getStackTrace()[Thread.activeCount()].getClassName())){
                resultStep = true;
            } else {
                resultStep = false;
            }


            do {
                if ((legalStep.size() == 8)) return new int[]{-1, -1};
                Byte course;
                do {
                     course = (byte) new Random().nextInt(8);
                } while (legalStep.contains(course));

                switch (course) {
                    case LEFT:
                        if (x - rangeStep >= 0) {
                            x--;
                            resultStep = true;
                            legalStep.add(LEFT);
                            break;
                        }
                    case RIGTH:
                        if (x < xMax - rangeStep) {
                            x++;
                            resultStep = true;
                            legalStep.add(RIGTH);
                            break;
                        }
                    case TOP:
                        if (y - rangeStep >= 0) {
                            y--;
                            resultStep = true;
                            legalStep.add(TOP);
                            break;
                        }
                    case BOTTOM:
                        if (y < yMax - rangeStep) {
                            y++;
                            resultStep = true;
                            legalStep.add(BOTTOM);
                            break;
                        }
                    case BOTTOM_LEFT:
                        if ((y < yMax - rangeStep) && (x - rangeStep >= 0)) {
                            x--;
                            y++;
                            resultStep = true;
                            legalStep.add(BOTTOM_LEFT);
                            break;
                        }
                    case BOTTOM_RIGTH:
                        if ((y < yMax - rangeStep) && (x < xMax - rangeStep)) {
                            x++;
                            y++;
                            resultStep = true;
                            legalStep.add(BOTTOM_RIGTH);
                            break;
                        }
                    case TOP_RIGTH:
                        if ((y - rangeStep >= 0) && (x < xMax - rangeStep)) {
                            x++;
                            y--;
                            resultStep = true;
                            legalStep.add(TOP_RIGTH);
                            break;
                        }
                    case TOP_LEFT:
                        if ((y - rangeStep >= 0) && (x - rangeStep >= 0)) {
                            x--;
                            y--;
                            resultStep = true;
                            legalStep.add(TOP_LEFT);
                            break;
                        }

                }
            } while (!resultStep);
            return new int[]{x, y};
        }

    }






}
