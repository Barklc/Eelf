package main.Nourishments;
import main.FlagsOverride;
import main.GameParameters;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;


public class PlantNourishment extends Nourishment{

    public PlantNourishment(){
        super();
    }

    @Override
    public NourishmentTypes NourishmentType(){
        return NourishmentTypes.Plant;
    }

    @Override
    public void DisplayNourishment(PApplet w, float scale){
        w.fill(GetNourishmentColor().hashCode());
        w.noStroke();
        //Push current display matrix to stack
        w.pushMatrix();
        //Make new one with X,Y the new 0,0
        w.translate(GetNourishmentX(),GetNourishmentY());
        //circle wants diameter so double radius
        w.circle(0,0,GetNourishmentSize());
        w.noFill();
        if (FlagsOverride.ShowScentRangeFlag){
            switch(NourishmentType()){
                case Plant:
                    w.stroke(new Color(0,255,0).hashCode());
                    w.circle(0,0, GameParameters.MaxScentDistance *2);
                    break;
                case Meat:
                    w.stroke(new Color(255,0,0).hashCode());
                    w.circle(0,0,GameParameters.MaxScentDistance*2);
                    break;
            }
        }
        //w.scale(scale);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShape(float w, float h, Color c){
        return new ArrayList<>();
    }
}
