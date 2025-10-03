package main.Creature.BodySegments;
import processing.core.*;
import java.awt.*;
import java.util.ArrayList;

public class Eyes extends BodySegment {
    private float EyeSize;
    private Color EyeColor;
//  private float HeadShape;

    public Eyes(){
        super();
    }

    public float GetEyeSize(){
        return EyeSize;
    }

    public void SetEyeSize(float s){
        EyeSize=s;
    }

    public Color GetEyeColor(){
        return EyeColor;
    }

    public void SetEyeColor(Color c){
        EyeColor=c;
    }

    //@Override
    //public void InitializeSegment(float x,float y,float w, float h, float a, float d,color c){
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
        return SegmentID.Eyes;
    }

    @Override
    public void UpdateSegment(BodySegment segment){
        SetSegmentX(segment.GetSegmentX());
        SetSegmentY(segment.GetSegmentY());
        SetSegmentAngle(segment.GetSegmentAngle());
        SetSegmentWidth(segment.GetSegmentWidth());
        SetSegmentHeight(segment.GetSegmentHeight());
        //HeadShape =((Head)segment).GetHeadShape();
    }

    @Override
    public void DisplaySegment(PApplet w, float scale){
        w.pushMatrix();
        w.translate(GetSegmentX(),GetSegmentY());
        w.rotate(GetSegmentAngle());
        w.fill(EyeColor.hashCode());
        w.circle((float) (GetSegmentWidth()*.2), (float) (GetSegmentWidth()*.3),EyeSize);
        w.circle((float) (GetSegmentWidth()*.2), (float) (-GetSegmentWidth()*.3),EyeSize);

        w.fill(0, 0, 0);
        w.circle((float) (GetSegmentWidth()*.2), (float) (GetSegmentWidth()*.3),1);
        w.circle((float) (GetSegmentWidth()*.2), (float) (-GetSegmentWidth()*.3),1);
        w.popMatrix();

    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<PShape>();
    }

}