package main;

import main.Genetics.GeneDefinitions;
import processing.core.PApplet;


public class Main {

    public static Utilities gUtils=new Utilities();
    public static World gWorld=new World();
    public static GeneDefinitions gGenesDef=new GeneDefinitions();
    public static int gMaxPop=1;
    public static int gMaxPlants=3;
    public static int gMaxMeat=0;


    static DisplayCreatureWindow gDisplayCreatureWnd;

    public static void main(String[] args) {

        Display();

    }

    public static void Display() {
        String[] processingArgs = {"MyProcessingSketch"};
        Visualization mySketch = new Visualization();
        PApplet.runSketch(processingArgs, mySketch);

        //gDisplayCreatureWnd=new DisplayCreatureWindow();
        //gDisplayCreatureWnd.windowMove(500,400);
    }
}