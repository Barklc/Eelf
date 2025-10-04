package main;
import processing.core.PApplet;
import static main.Main.*;

public class Visualization extends PApplet {


    public void settings() {
        size(1400, 1000);
    }

    public void setup() {

        gWorld.CreatePopulation(GameParameters.MaxPop);
        gWorld.CreateNourishment(GameParameters.MaxPlants, GameParameters.MaxMeat);

    }

    public void draw() {
        background(128, 128, 128);

        gWorld.Display(this);

    }

}
