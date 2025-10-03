package main.Creature.BodySegments;
import main.Coords;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;
import static main.Main.gUtils;

public class Segment extends BodySegment {

    public Segment(){
        super();
    }

    @Override
    public SegmentID BodySegmentType(){
        return SegmentID.Segment;
    }

    @Override
    public void UpdateSegment(BodySegment prev){

        float a= (float) Math.atan2(prev.GetSegmentY()-GetSegmentY(),prev.GetSegmentX()-GetSegmentX());
        SetSegmentAngle(a);
        //take the x and y squared, find the sqr root is the disance between
        float d = (float) Math.sqrt(Math.pow(prev.GetSegmentX()-GetSegmentX(),2) + Math.pow(prev.GetSegmentY()-GetSegmentY(),2));
        if (d >GetSegmentDistance()){
            float delta = d-GetSegmentDistance();
            //cos determines new x location based on angle.
            //sin determines new y location based on angle.
            Coords coords=gUtils.calculatePointOnCircle(GetSegmentX(),GetSegmentY(),delta,GetSegmentAngle());
            SetSegmentX(coords.X());
            SetSegmentY(coords.Y());
        }
    }

    @Override
    public void DisplaySegment(PApplet w, float scale){
        w.fill(GetSegmentColor().hashCode());
        w.noStroke();
        //Push current display matrix to stack
        w.pushMatrix();
        //Make new one with X,Y the new 0,0
        w.translate(GetSegmentX(),GetSegmentY());
        //rotate it so it is angled
        w.rotate(GetSegmentAngle());
        //circle wants diameter so double radius
        w.ellipse(0,0,2*GetSegmentHeight(),2*GetSegmentWidth());
        //w.scale(scale);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<PShape>();
    }
}
