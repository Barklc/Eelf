package main.Creature.BodySegments;
import main.Coords;
import processing.core.PApplet;
import processing.core.PShape;
import processing.opengl.PShapeOpenGL;

import java.awt.*;
import java.util.ArrayList;

import static main.Main.gUtils;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CLOSE;

public class Mouth extends BodySegment {
    private float MouthSize;
    private float BiteStrength;
    private Color MouthColor;
    //private float HeadShape;

    public Mouth(){
        super();
    }

    public float GetMouthSize(){
        return MouthSize;
    }

    public void SetMouthSize(float s){
        MouthSize=s;
    }

    public float GetBiteStrength(){
        return BiteStrength;
    }

    public void SetBiteStrength(float s){
        BiteStrength=s;
    }

    public Color GetMouthColor(){return MouthColor;}
    public void SetMouthColor(Color c){MouthColor=c;}
    @Override
    public SegmentID BodySegmentType(){
        return SegmentID.Mouth;
    }

    @Override
    public void UpdateSegment(BodySegment segment){
        SetSegmentX(segment.GetSegmentX());
        SetSegmentY(segment.GetSegmentY());
        SetSegmentAngle(segment.GetSegmentAngle());
        //HeadShape =((Head)segment).GetHeadShape();
        SetSegmentDistance((segment.GetSegmentDistance()));
        SetSegmentWidth(segment.GetSegmentWidth());
        SetSegmentHeight(segment.GetSegmentHeight());

    }

    @Override
    public void DisplaySegment(PApplet w, float scale){
        Coords coords=gUtils.calculatePointOnEllipse(GetSegmentX(),GetSegmentY(), GetSegmentAngle(),GetSegmentHeight(), GetSegmentHeight());
        PShape mouth=CreateMouth(w,MouthSize,BiteStrength);
        w.pushMatrix();
        w.fill(GetMouthColor().hashCode());
        w.translate(coords.X(),coords.Y());
        w.stroke(GetMouthColor().hashCode());
        w.rotate(GetSegmentAngle());
        w.shapeMode(CENTER);
        w.shape(mouth,mouth.width/2,mouth.height/2);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<PShape>();
    }

    PShape CreateMouth(PApplet w,float mouthSize, float biteStrength){
        PShape mouth;
        float modifier=.1f*biteStrength;
        float mouthRight=20;
        float mouthLeft=340;

        mouth=w.createShape();
        mouth.beginShape();
        mouth.fill(MouthColor.hashCode());
        // Exterior part of shape, clockwise winding
        float angle = 0.0f;
        float nx = 0.0f;
        float ny = 0.0f;

        //Draw the outside of the mouth
        for(float i=mouthRight;i<mouthLeft;i++)
        {
            angle = (float) Math.toRadians( i );
            nx = (float) ((mouthSize) * Math.cos( angle ));
            ny = (float) ((mouthSize) * Math.sin( angle ));

            mouth.vertex( nx , ny);
        }

        float mouthInnerRadius=mouthSize*(1-modifier);
        float cx=mouthSize-mouthInnerRadius;//cos(angle)+mouthInnerRadius;
        float cy=0;

        for (float i = mouthLeft; i>mouthRight; i-- )
        {
            angle = (float) Math.toRadians( i );
            nx = (float) (cx + mouthInnerRadius * Math.cos(angle));
            ny = (float) (cy + mouthInnerRadius * Math.sin(angle));

            mouth.vertex( nx, ny);

        }

        mouth.disableStyle();
        mouth.endShape(CLOSE);

        return mouth;
    }
}
