package main.Creature.BodySegments;
import main.Coords;
import main.GameParameters;
import main.Creature.GeometryUtils;
import processing.core.PApplet;
import processing.core.PShape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
        // Use getRGB() instead of hashCode()
        w.fill(GetMouthColor() != null ? GetMouthColor().getRGB() : Color.BLACK.getRGB());
        w.translate(coords.X(),coords.Y());
        w.stroke(GetMouthColor() != null ? GetMouthColor().getRGB() : Color.BLACK.getRGB());
        w.rotate(GetSegmentAngle());
        w.shapeMode(CENTER);
        w.shape(mouth,mouth.width/2,mouth.height/2);
        w.popMatrix();
    }

    @Override
    public ArrayList<PShape> CreateShapes(float w, float h, Color c){
        return new ArrayList<>();
    }

    PShape CreateMouth(PApplet w,float mouthSize, float biteStrength){
        PShape mouth;
        float modifier=0.1f;
        if (biteStrength>GameParameters.BiteStrengthVisualThreshold){
            modifier=.1f*biteStrength-GameParameters.BiteStrengthVisualThreshold;
        }

        float mouthRight=20;
        float mouthLeft=340;

        mouth=w.createShape();
        mouth.beginShape();
        // use getRGB() to provide Processing the ARGB int
        mouth.fill(GetMouthColor() != null ? GetMouthColor().getRGB() : Color.BLACK.getRGB());
        // Exterior part of shape, clockwise winding
        // Generate outer arc points using GeometryUtils
        List<Point2D.Float> outer = GeometryUtils.generateArcPoints(mouthSize, mouthRight, mouthLeft, 1.0f);
        for (Point2D.Float p : outer) {
            mouth.vertex(p.x, p.y);
        }

        // Compute inner radius using GeometryUtils (clamped)
        float mouthInnerRadius = GeometryUtils.computeInnerRadius(mouthSize, biteStrength, GameParameters.BiteStrengthVisualThreshold);
        float cx = mouthSize - mouthInnerRadius; // offset for inner arc center
        float cy = 0;

        // Generate inner arc points and iterate in reverse to match original winding
        List<Point2D.Float> inner = GeometryUtils.generateArcPoints(mouthInnerRadius, mouthRight, mouthLeft, 1.0f);
        for (int idx = inner.size() - 1; idx >= 0; idx--) {
            Point2D.Float p = inner.get(idx);
            mouth.vertex(cx + p.x, cy + p.y);
        }

        mouth.disableStyle();
        mouth.endShape(CLOSE);

        return mouth;
    }
}