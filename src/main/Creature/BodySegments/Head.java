package main.Creature.BodySegments;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

public class Head extends BodySegment {
    private float HeadShape;

     public Head(){
        super();
    }

    public float GetHeadShape(){
        return HeadShape;
    }

    public void SetHeadShape(float s){
        HeadShape=s;
    }

    @Override
    public SegmentID BodySegmentType(){
        return SegmentID.Head;
    }

    @Override
    public void UpdateSegment(BodySegment segment){
        SetSegmentX(segment.GetSegmentX());
        SetSegmentY(segment.GetSegmentY());
        SetSegmentAngle(segment.GetSegmentAngle());
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
        //if(gShowID){
        //   w.textSize(14);
        //   w.fill(0);
        //   w.text(GetSegmentIndex() + "(" + generation + ")",0,GetSegmentHeight()+14);
        // }
        //starting x.y,length of head, width of head, staring point for arc, ending point for arc
        // arc(0,0,2*(GetSegmentWidth()+HeadShape),2*GetSegmentHeight(),-0.5*PI,+0.5*PI);
        //circle wants diameter so double radius
        w.ellipse(0,0,-2*GetSegmentHeight(),2*GetSegmentWidth());
        w.scale(scale);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<PShape>();
    }
}
