package main.Nourishments;
import main.Nourishments.Nourishment;
import main.Nourishments.NourishmentTypes;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

import static main.Main.gMaxScentDistance;
import static main.Main.gShowScentRangeFlag;

public class MeatNourishment extends Nourishment {

    public MeatNourishment(){
        super();
    }

    @Override
    public NourishmentTypes NourishmentType(){
        return NourishmentTypes.Meat;
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
        if (gShowScentRangeFlag){
            switch(NourishmentType()){
                case Plant:
                    w.stroke(new Color(0,255,0).hashCode());
                    w.circle(0,0,gMaxScentDistance);
                    break;
                case Meat:
                    w.stroke(new Color(255,0,0).hashCode());
                    w.circle(0,0,gMaxScentDistance);
                    break;
            }
        }
        //w.scale(scale);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShape(float w, float h, Color c){
        return new ArrayList<PShape>();
    }
}
