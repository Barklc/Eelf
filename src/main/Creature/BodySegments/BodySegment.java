package main.Creature.BodySegments;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class BodySegment{
    private float SegmentX;
    private float SegmentY;
    private float SegmentAngle;
    private float SegmentHeight;
    private float SegmentWidth;
    private Color SegmentColor;
    private float SegmentDistance;
    private int SegmentConnectedTo;

    public BodySegment(){

    }

    public abstract SegmentID BodySegmentType();


    public Float GetSegmentX(){
        return SegmentX;
    };

    public void SetSegmentX(float x){
        SegmentX=x;
    };

    public Float GetSegmentY(){
        return SegmentY;
    };

    public void SetSegmentY(float y){
        SegmentY=y;
    };

    public Float GetSegmentAngle(){
        return SegmentAngle;
    };

    public void SetSegmentAngle(float a){
        SegmentAngle=a;
    };

    public Float GetSegmentHeight(){
        return SegmentHeight;
    };

    public void SetSegmentHeight(float h){
        SegmentHeight=h;
    };

    public Float GetSegmentWidth(){
        return SegmentWidth;
    };

    public void SetSegmentWidth(float w){
        SegmentWidth=w;
    };

    public Color GetSegmentColor(){
        return SegmentColor;
    };

    public void SetSegmentColor(Color c){
        SegmentColor=c;
    };

    public Float GetSegmentDistance(){
        return SegmentDistance;
    };
    public void SetSegmentDistance(float d){
        SegmentDistance=d;
    };

    public int GetSegmentConnectedTo(){return SegmentConnectedTo;
    };
    public void SetSegmentConnectedTo(int d){
        SegmentConnectedTo=d;
    };

    public void InitializeSegment(float x,float y,float w, float h, float a, float d,Color c){
        SegmentX=x;
        SegmentY=y;
        SegmentAngle=a;
        SegmentHeight=h;
        SegmentWidth=w;
        SegmentColor=c;
        SegmentDistance=d;
    }

    public abstract void UpdateSegment(BodySegment prev);

    public abstract void DisplaySegment(PApplet w, float scale);

    abstract ArrayList<PShape> CreateShapes(float w,float h,Color c);

}