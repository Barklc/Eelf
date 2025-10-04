package main.Creature.BodySegments;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

import static processing.core.PConstants.CLOSE;
import static processing.core.PConstants.CORNER;

public class Flippers extends BodySegment {
    private float FlipperWidth;
    private float FlipperHeight;

    public Flippers(){
        super();
    }

    public void SetFlipperWidth(float w){
        FlipperWidth=w;
    }

    public void SetFlipperHeight(float h){
        FlipperHeight=h;
    }


    @Override
    public SegmentID BodySegmentType(){
        return SegmentID.Flippers;
    }

    @Override
      public void InitializeSegment(float x,float y,float w, float h, float a, float d,Color c){
      SetSegmentX(x);
      SetSegmentY(y);
      SetSegmentHeight(h);
      SetSegmentWidth(w);
      SetSegmentAngle(a);
      SetSegmentColor(c);
      SetSegmentDistance(d);
    }

    @Override
    public void UpdateSegment(BodySegment prev){
        SetSegmentX(prev.GetSegmentX());
        SetSegmentY(prev.GetSegmentY());
        SetSegmentAngle(prev.GetSegmentAngle());
        SetSegmentWidth(prev.GetSegmentWidth());
        SetSegmentHeight(prev.GetSegmentHeight());

    }

    @Override
    public void DisplaySegment(PApplet w, float scale){

        float fx= (float) (GetSegmentX() + (GetSegmentWidth())*Math.cos(GetSegmentAngle()+(0.5f*Math.PI)));
        float fy= (float) (GetSegmentY() + (GetSegmentHeight())*Math.sin(GetSegmentAngle()+(0.5f*Math.PI)));

        PShape bFlipper=CreateBottomFlipper(w,FlipperWidth,FlipperHeight,GetSegmentColor());
        bFlipper.rotate(GetSegmentAngle());
        w.stroke(0);
        w.shapeMode(CORNER);
        w.shape(bFlipper,fx,fy);

        float gx= (float) (GetSegmentX() + (GetSegmentWidth())*Math.cos(GetSegmentAngle()+(-0.5f*Math.PI)));
        float gy= (float) (GetSegmentY() + (GetSegmentHeight())*Math.sin(GetSegmentAngle()+(-0.5f*Math.PI)));

        PShape tFlipper=CreateBottomFlipper(w,FlipperWidth,FlipperHeight,GetSegmentColor());
        tFlipper.scale(1,-1);
        tFlipper.rotate(-GetSegmentAngle());
        w.stroke(0);
        w.shapeMode(CORNER);
        w.shape(tFlipper,gx,gy);
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<>();
    }

    private PShape CreateBottomFlipper(PApplet w, float fheight, float fwidth,Color fcolor){
        float topStart=0;
        float topEnd=90;
        PShape flipper;

        flipper=w.createShape();
        flipper.beginShape();
        flipper.stroke(0);
        flipper.fill(fcolor.hashCode());
        float angle=0.0f;
        float nx=0.0f;
        float ny=0.0f;

        //Draw the outside of the mouth
        for(float i=topStart;i<topEnd;i++)
        {
            angle = (float) Math.toRadians( i );
            nx = (float) ((fwidth) * Math.cos( angle ));
            ny = (float) ((fheight) * Math.sin( angle ));

            flipper.vertex( nx , ny);
        }
        for(float i=1;i<fwidth;i++){
            flipper.vertex(i,0);
        }
        flipper.noStroke();
        flipper.endShape(CLOSE);

        return flipper;
    }

}
