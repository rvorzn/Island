package ru.sbt.www.project.ostrov;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class DrawingComp extends JPanel {
    PrintGraphics printGraphics;
    DrawingComp(PrintGraphics printGraphics){
        this.printGraphics = printGraphics;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;

        int[] x = printGraphics.x;
        int[] y = printGraphics.y;
        int[] z = printGraphics.z;
        int xLength = x.length;

        int maxWolf = printGraphics.maxWolf;
        int maxRabbit  = printGraphics.maxRabbit;

        double k = ( maxWolf > maxRabbit) ? (double)270/(double)maxWolf : (double)270/(double)maxRabbit;
        double kx = (xLength > 350) ? ((double)350/(double)xLength) : 1/(double)xLength*(double)350;

        //* Вывод информации  *
        drp.drawString("---------------------------------------------------", 470, 30);
        drp.drawString(" Прошло дней: " + printGraphics.ostrov.getDay(), 470, 50);
        drp.drawString(" Максимальное кол-во Зайцев " + printGraphics.ostrov.getMaxRabbit(), 470, 70);
        drp.drawString(" Максимальное кол-во Волков: " + printGraphics.ostrov.getMaxWolf(), 470, 90);
        drp.drawString("---------------------------------------------------", 470, 110);

        //* Оси координат  *
        drp.drawLine(20 + (int)kx, 20 , 20+(int)kx, 300);
        drp.drawLine(20+ (int)kx, 300, 460+ (int)kx, 300);

        drp.drawString("0", 15+(int)kx, 315);
        drp.drawString("Кол-во дней", 470+(int)kx, 315);
        drp.drawString("Кол-во животных", 5 + (int)kx, 15);

        //* Рисуем графики и разметку *
        int kDay;

        if (xLength < 50){
            kDay = 5;
        } else if (xLength <= 100) {
            kDay = 10;
        }else if (xLength >= 1000){
            kDay = 200;
        } else {
            kDay = 100; // 100< xLength < 1000
        }

        for (Integer i = 1; i <x.length ; i++) {

            gh.setColor(new Color(255, 0, 0));
            drp.drawLine(20+(int)(x[i-1]*kx), 300 - (int)(y[i-1]*k), 20 + (int)(x[i]*kx), 300 - (int)(y[i]*k));
            gh.setColor(new Color(0, 0, 255));
            drp.drawLine(20+(int)(x[i-1]*kx), 300 - (int)(z[i-1]*k), 20 + (int)(x[i]*kx), 300 - (int)(z[i]*k));
            drp.setColor(new Color(0, 0, 0));
            if (i% kDay ==0) {
                drp.setFont(new Font("TimesRoman", Font.PLAIN, 9));
                drp.drawLine(20+(int)(x[i]*kx), 305 , 20+(int)(x[i]*kx), 295);
                drp.drawString( i.toString() , 15+(int)(x[i]*kx), 315 );
            }

        }


        int yOs = (printGraphics.ostrov.getMaxWolf() >printGraphics.ostrov.getMaxRabbit()) ? printGraphics.ostrov.getMaxWolf() : printGraphics.ostrov.getMaxRabbit();
        int kOs;

        if (yOs <50 ){
            kOs = 5;
        } else if (yOs <=100) {
            kOs =10 ;
        }else if (yOs >= 1000){
            kOs = 200;
        } else {
            kOs = 100;
        }

        for (Integer i = 1; i <=yOs ; i++) {
            if (i % kOs == 0) {
                drp.drawLine(15+ (int)kx, 300 - (int)(i*k), 25+ (int)kx, 300 - (int)(i*k));
                drp.drawString( i.toString() , 5+(int)(kx), 302-(int)(i*k) );
            }

        }

    }
}

public class PrintGraphics extends JFrame{
    public  int x[];
    public  int y[];
    public  int z[];
    public int maxWolf, maxRabbit;

    public Island ostrov;

    public PrintGraphics (Island ostrov, String title) {
        super(title);
        this.ostrov = ostrov;
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComp (this), BorderLayout.CENTER);
        jcp.setBackground(Color.white);
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //------------------------------------------------------------------------------------
        HashMap<Integer, Map<String, Integer> > statisticAnaimalEveryDay = ostrov.getStatistics();

        int sizeArray = ostrov.getDay();
        int[] arrDay = new int[sizeArray];
        int[] arrWolf = new int[sizeArray];
        int[] arrRabbit = new int[sizeArray];
        int i = 0;

        for (Map.Entry<Integer, Map<String, Integer>> entry: statisticAnaimalEveryDay.entrySet()){
            arrDay[i] = entry.getKey();
            for (Map.Entry<String, Integer> map2: entry.getValue().entrySet()){
                if (map2.getKey().equalsIgnoreCase("wolf")) { arrWolf[i] =  map2.getValue(); }
                if (map2.getKey().equalsIgnoreCase("rabbit")) { arrRabbit[i] = map2.getValue(); }
            }
            i++;
        }

        x = arrDay;
        y = arrWolf;
        z = arrRabbit;
        maxWolf = ostrov.getMaxWolf();
        maxRabbit = ostrov.getMaxRabbit();

    }




}