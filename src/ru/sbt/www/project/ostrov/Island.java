package ru.sbt.www.project.ostrov;

import java.util.*;

public class Island {
    private int day = 0;
    private int xSize, ySize;
    private Animal[][] area;
    private List <Animal> listAnimals = new LinkedList<>();

    private int countWolf;
    private int countRabbit;

    private int maxWolf =0;
    private int maxRabbit = 0;

    private HashMap<Integer, Map<String, Integer> > statisticAnaimalEveryDay = new HashMap<>();

    //--------------------------------------------- constructor ------------------------------------------------------------
    public Island(int xSize, int ySize, int startWolf, int startRabbit) {
        this.xSize = xSize;
        this.ySize = ySize;
        area = new Animal[xSize][ySize];

        for (int i = 0; i < startWolf ; i++) {
            newAnimal(new Wolf());
        }
        for (int i = 0; i < startRabbit; i++) {
            newAnimal(new Rabbit());
        }
    }

    //--------------------------------------------- get set ------------------------------------------------------------
    public int getDay() {
        return day;
    }

    public void newAnimal(Animal animal){
        if (!(listAnimals.size() == xSize*ySize)) {
            int x, y;
            do {
                x = new Random().nextInt(xSize);
                y = new Random().nextInt(ySize);
            } while (area[x][y] != null);
            animal.setX(x);
            animal.setY(y);

            listAnimals.add(animal);

            if ("wolf".equalsIgnoreCase(animal.getClass().getSimpleName())) {countWolf++;};
            if ("rabbit".equalsIgnoreCase(animal.getClass().getSimpleName())) {countRabbit++;};
        }

    }

    public int getCountWolf() {
        return countWolf;
    }

    public int getCountRabbit() {
        return countRabbit;
    }

    public int getMaxWolf() {
        return maxWolf;
    }

    public int getMaxRabbit() {
        return maxRabbit;
    }

    //--------------------------------------------- methods ------------------------------------------------------------

    public void newDay(){
        ++day;
        beginFor:
        for (int i = 0; i < listAnimals.size() ; i++) {
            area[listAnimals.get(i).getX()][listAnimals.get(i).getY()] = null;
            if (listAnimals.get(i).decrementHungry() == 0) {
                area[listAnimals.get(i).getX()][listAnimals.get(i).getY()] = null;
                if ("wolf".equalsIgnoreCase(listAnimals.get(i).getClass().getSimpleName())){
                    countWolf--;
                    Wolf.die();
                    listAnimals.remove(i);}
                //if ("rabbit".equalsIgnoreCase(listAnimals.get(i).getClass().getSimpleName())){ Rabbit.die();}
                //listAnimals.remove(i);
                continue ;
            }
            int oldX = listAnimals.get(i).getX();
            int oldY = listAnimals.get(i).getY();
            boolean danger = true;
            do {
                listAnimals.get(i).nextStep(xSize, ySize);
                int newX = listAnimals.get(i).getX();
                int newY = listAnimals.get(i).getY();
                if (newX == -1 && newY == -1){
                    listAnimals.get(i).setX(oldX);
                    listAnimals.get(i).setY(oldY);
                    continue beginFor;
                }

                String checkField = chekField(newX, newY);
                if (checkField.equals("isEmpty")) {
                    area[newX][newY] = listAnimals.get(i);
                    danger = false;
                } else if (listAnimals.get(i).getClass().getSimpleName().equals(checkField)) { //размножение
                    switch (listAnimals.get(i).getClass().getSimpleName()) {
                        case "Rabbit":
                            if (!(listAnimals.size() == xSize * ySize)) {
                                newAnimal(new Rabbit());
                            }
                            danger = false;
                            break;
                        case "Wolf":
                            if (!(listAnimals.size() == xSize * ySize)) {
                                newAnimal(new Wolf()); //нужно передавать константу
                            }
                            danger = false;
                            break;
                    }
                } else {
                    switch (listAnimals.get(i).getClass().getSimpleName()) { // поедание
                        case "Wolf":
                            Animal objForRemove = (area[newX][newY]); // определение обьекта для удаления из списка
                            area [newX][newY] = listAnimals.get(i); // перемещение текщего обьекта

                            listAnimals.get(i).gorge(); // процес поедания
                            listAnimals.remove(objForRemove); // удаление сьеденного обьекта из списка
                            countRabbit--;
                            Rabbit.die(); // удаление из счетчика

                            danger = false;
                            break;
                        case "Rabbit":
//                            listAnimals.remove(i);
//                            Rabbit.decrementCount();
                            danger = true;
                            break;
                    }

                }
                if (danger){
                    listAnimals.get(i).setX(oldX);
                    listAnimals.get(i).setY(oldY);
                }
            } while (danger);
        }
        statisticsEveryDayAdd();
    }

    public String chekField(int x, int y){
        if (area[x][y] == null){
            return "isEmpty";
        }else{
            return area[x][y].getClass().getSimpleName();
        }

    }

    public boolean isEnd(){
        if (countRabbit == 0 || countWolf == 0){
            return true;
        }
        return false;
    }

    public boolean isEnd(int countDay){
        if (day >= countDay){
            return true;
        }
        return false;
    }


    public void staticsMaxAnimals(){
        maxWolf = countWolf > maxWolf ?  countWolf : maxWolf;
        maxRabbit = countRabbit > maxRabbit ?  countRabbit : maxRabbit;
    }

    public void statisticsEveryDayAdd(){
        Map<String, Integer> animal = new HashMap<>();
        animal.put("Wolf", countWolf);
        animal.put("Rabbit", countRabbit);
        statisticAnaimalEveryDay.put(day, animal);
        staticsMaxAnimals();
    }

    public HashMap<Integer, Map<String, Integer> > getStatistics(){
        return statisticAnaimalEveryDay;
    }



}
