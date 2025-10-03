package main.Creature;
import main.Constants;
import main.Creature.BodySegments.BodySegment;

import processing.core.*;
import java.util.ArrayList;

import static main.Main.*;

public class CreatureVision{
    private Creature CurrentCreature;
    private CreatureGeneValues CGV;
    private float hx,hy;
    private float angle,hangle;
    private float distance;
    private float clarity;
    private ArrayList<SightLine> sightlines;
    CreatureGeneValues cgv;

    public CreatureVision(Creature currentCreature) {
        CurrentCreature=currentCreature;
        CGV=CurrentCreature.GetGenes();
    }

    public void InitializeVision(float a, float d, float newClarity){
        angle=(float)Math.toRadians(a);
        distance=d;
        clarity=newClarity;
        sightlines=new ArrayList<SightLine>();
    }

    public void SetVisionDistance(float value){
        distance=value;
    }
    public void UpdateLocation(BodySegment head){
        hx = head.GetSegmentX();
        hy = head.GetSegmentY();
        hangle = head.GetSegmentAngle();
    }

    public void UpdateSightLines(float d){
        sightlines=new ArrayList<SightLine>();
        distance=d;

        float temp=(float)Math.toDegrees(angle);
        //get outside sight lines
        float x1,y1,x2,y2;
        x1 = (float) (hx + (d)*Math.cos(hangle-(angle/2)*Math.PI));
        y1 = (float) (hy + (d)*Math.sin(hangle-(angle/2)*Math.PI));
        sightlines.add(new SightLine(hx,hy,x1,y1));

        x2 = (float) (hx + (d)*Math.cos(hangle+(angle/2)*Math.PI));
        y2 = (float) (hy + (d)*Math.sin(hangle+(angle/2)*Math.PI));
        sightlines.add(new SightLine(hx,hy,x2,y2));

        float steps=clarity;
        float sightLineCount=(angle*100)/((clarity)*100);
        //print("Angle=" + angle*100 + "  Clarity=" + clarity + " Step=" + steps + "\r\n");
        //print("sightLineCount= " + sightLineCount + "\r\n");
        float startingAngle= (float) (hangle-(angle/2)*Math.PI);
        for(int i=0;i<sightLineCount;i++){
            x2 = (float) (hx + (d)*Math.cos(startingAngle+((steps*i))*Math.PI));
            y2 = (float) (hy + (d)*Math.sin(startingAngle+((steps*i))*Math.PI));
            sightlines.add(new SightLine(hx,hy,x2,y2));
        }

    }

    public void Display(PApplet w,float scale){

        if (Constants.ShowVisionSightLinesFlag){
            if (!sightlines.isEmpty()){
                w.stroke(100);
                SightLine sightline=sightlines.get(0);
                w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());

                sightline=sightlines.get(1);
                w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());

                w.stroke(100);
                w.noFill();
                w.arc(hx,hy,distance*2,distance*2, (float) (hangle-(angle/2)*Math.PI), (float) (hangle+(angle/2)*Math.PI));

                for(int i=2;i<sightlines.size();i++){
                    sightline=sightlines.get(i);
                    w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());
                }
            }
        }
    }

    public ArrayList<ObjectInRange> FindObjects(ArrayList<ObjectInRange> ObjectsInRange){
        ArrayList<ObjectInRange> oir=new ArrayList<ObjectInRange>();
        for(int i=0;i<ObjectsInRange.size();i++){
            ObjectInRange object=ObjectsInRange.get(i);
            float radius=0;
            float distance=-1;
            switch (object.objectType){
                case Plant:
                    radius=gWorld.gNourishment.get(object.IdOfObject()).GetNourishmentSize()/2;
                    distance=object.distance=itemInSightLine(object.X(),object.Y(),radius);
                    object.distance=distance;
                    break;
                case Meat:
                    radius=gWorld.gNourishment.get(object.IdOfObject()).GetNourishmentSize()/2;
                    distance=object.distance=itemInSightLine(object.X(),object.Y(),radius);
                    object.distance=distance;
                    break;
                case Creature:
                    radius=cgv.GetBodyWidth();
                    distance=object.distance=itemInSightLine(object.X(),object.Y(),radius);
                    object.distance=distance;
                    break;
                case PlantScent:
                case MeatScent:
                case CreatureScent:
                    distance=-1;
                    break;
            }
            if (distance!=-1){
                oir.add(object);
            }
        }

        return oir;
    }

    private ArrayList<ObjectInRange> FilterByOjectType(ArrayList<ObjectInRange> objectsInRange,ObjectInRangeType objectType){
        ArrayList<ObjectInRange> newList=new ArrayList<>();
        for(ObjectInRange o : objectsInRange){
            if (o.objectType==objectType){
                newList.add(o);
            }
        }
        return newList;
    }
    public ObjectInRange GetNearestObjectSpecified(ArrayList<ObjectInRange> visualObjectsInRange,ObjectInRangeType objectType){
        ObjectInRange Closest;

        if (visualObjectsInRange==null || visualObjectsInRange.isEmpty()){
            return null;
        }
        ArrayList<ObjectInRange> objectsInRange=FilterByOjectType(visualObjectsInRange,objectType);
        if (objectsInRange.isEmpty()){
            return null;
        }
        Closest=objectsInRange.get(0);
        for(int i=1;i<objectsInRange.size();i++){
            if (objectsInRange.get(i).Distance()<Closest.Distance()){
                Closest=objectsInRange.get(i);
            }
        }
        return Closest;
    }

    public float itemInSightLine(float x,float y,float r){
        float distance=-1;

        for(int i=0;i<sightlines.size();i++){
            distance=-1;
            //determine if x,y with radius r crosses sight line.
            SightLine sightline=sightlines.get(i);
            //https://stackoverflow.com/questions/67116296/is-this-code-for-determining-if-a-circle-and-line-segment-intersects-correct
            if (gUtils.lineSegmentIntersectsCircleOptimized(sightline.GetEyeX(),sightline.GetEyeY(),sightline.GetDistanceX(),sightline.GetDistanceY(),x,y,r)){
                distance=gUtils.DistanceBetweenPoints(sightline.GetEyeX(),sightline.GetEyeY(),x,y);
                break;
            }
        }
        return distance;
    }

}

