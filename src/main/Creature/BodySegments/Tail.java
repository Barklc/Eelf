package main.Creature.BodySegments;
import main.Coords;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

import static main.Main.gUtils;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CLOSE;

public class Tail extends BodySegment {
    private float TailWidth;
    private float TailHeight;

    public Tail(){
    }

    public void SetTailWidth(float w){
        TailWidth=w;
    }

    public void SetTailHeight(float h){
        TailHeight=h;
    }

    // @Override
    //  public void InitializeSegment(float x,float y,float w, float h, float a, float d,color c){
    //  SetSegmentX(x);
    //  SetSegmentY(y);
    //  SetSegmentHeight(h);
    //  SetSegmentWidth(w);
    //  SetSegmentAngle(a);
    //  SetSegmentColor(c);
    //  SetSegmentDistance(d);
    //}

    @Override
    public SegmentID BodySegmentType(){
        return SegmentID.Tail;
    }

    @Override
    public void UpdateSegment(BodySegment prev){
        float a= (float) Math.atan2(prev.GetSegmentY()-GetSegmentY(),prev.GetSegmentX()-GetSegmentX());
        SetSegmentAngle(a);
        //take the x and y squared, find the sqr root is the distance between
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

        float nx= (float) (GetSegmentX() + (GetSegmentWidth()+TailWidth)*Math.cos(GetSegmentAngle()));
        float ny= (float) (GetSegmentY() + (GetSegmentHeight()+TailWidth)*Math.sin(GetSegmentAngle()));
        PShape tail=CreateTail(w, TailHeight, TailWidth, GetSegmentColor());
        //w.circle(nx,ny,5);
        w.pushMatrix();
        w.translate(nx,ny);
        w.rotate(GetSegmentAngle());
        w.shapeMode(CENTER);
        w.shape(tail,0,0);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<>();
    }

    private PShape CreateTail(PApplet w,float theight, float twidth, Color tcolor){
        float topStart=0;
        float topEnd=90;
        float bottomEnd=90;
        float bottomStart=0;
        PShape tail;

        tail=w.createShape();
        tail.beginShape();
        tail.stroke(tcolor.hashCode());
        tail.fill(tcolor.hashCode());

        float angle;
        float nx;
        float ny;

        //Draw the outside of the mouth
        for(float i=topStart;i<topEnd;i++)
        {
            angle = (float) Math.toRadians( i );
            nx = (float) ((twidth) * Math.cos( angle ));
            ny = (float) (theight/2-((theight/2) * Math.sin( angle )));

            tail.vertex( nx , ny);
        }
        for(float i=1;i<theight;i++){
            tail.vertex(1,i);
        }
        for(float i=bottomStart;i<bottomEnd;i++)
        {
            angle = (float) Math.toRadians( i );
            nx = (float) ((twidth) * Math.cos( angle ));
            ny = (float) (theight/2 + ((theight/2-1) * Math.sin( angle )));

            tail.vertex( nx , ny);
        }
        tail.noStroke();
        tail.endShape(CLOSE);


        return tail;
    }
}
