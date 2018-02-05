package ru.sbt.www.project.ostrov;

public class Main {


    public static void main(String[] args) {
        final int  X_SIZE = 10;
        final int  Y_SIZE = 10;
        int START_WOLF = 5;
        int START_RABBIT = 10;

        //1)------------------------------------------------------
        Island gaiti = new Island(X_SIZE, Y_SIZE, START_WOLF, START_RABBIT);


        do {
            gaiti.newDay();
        } while (!gaiti.isEnd(100));

        System.out.println("Прошло дней: " + gaiti.getDay());
        System.out.println("Максимальное кол-во зайцев: " + gaiti.getMaxRabbit());
        System.out.println("Максимальное кол-во Волков: " + gaiti.getMaxWolf());
        System.out.println("-------------------------");

        //2)------------------------------------------------------
        START_WOLF += 5;
        START_RABBIT = START_RABBIT;

        Island gaiti1 = new Island(X_SIZE, Y_SIZE, START_WOLF, START_RABBIT);



        do {
            gaiti1.newDay();
        } while (!gaiti1.isEnd(100));

        System.out.println("Прошло дней: " + gaiti1.getDay());
        System.out.println("Максимальное кол-во зайцев: " + gaiti1.getMaxRabbit());
        System.out.println("Максимальное кол-во Волков: " + gaiti1.getMaxWolf());
        System.out.println("-------------------------");
        //3)-------------------------------------------------------
        START_WOLF += 10;
        START_RABBIT = START_RABBIT;

        Island gaiti2 = new Island(X_SIZE, Y_SIZE, START_WOLF, START_RABBIT);


        do {
            gaiti2.newDay();
        } while (!gaiti2.isEnd(100));


        System.out.println("Прошло дней: " + gaiti2.getDay());
        System.out.println("Максимальное кол-во зайцев: " + gaiti2.getMaxRabbit());
        System.out.println("Максимальное кол-во Волков: " + gaiti2.getMaxWolf());
        System.out.println("-------------------------");
        // -------------------------------------------------------

        // * Отрисовка графиков *
        new PrintGraphics(gaiti, "Остров 1").setVisible(true);
        new PrintGraphics(gaiti1, "Остров 2").setVisible(true);
        new PrintGraphics(gaiti2, "Остров 3").setVisible(true);

    }

}

