package main.Creature;
import main.FlagsOverride;
import main.Creature.BodySegments.BodySegment;

import processing.core.*;
import java.util.ArrayList;

import static main.Main.*;

public class CreatureVision{
    private float hx,hy;
    private float angle,hangle;
    private float distance;
    private float clarity;
    private ArrayList<SightLine> sightlines;
    CreatureGeneValues cgv;

    public CreatureVision(Creature currentCreature) {
        cgv=currentCreature.GetGenes();
    }

    public void InitializeVision(float a, float d, float newClarity){
        angle=(float)Math.toRadians(a);
        distance=d;
        clarity=newClarity;
        sightlines=new ArrayList<>();
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
        sightlines=new ArrayList<>();
        distance=d;

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

        if (FlagsOverride.ShowVisionSightLinesFlag){
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
        ArrayList<ObjectInRange> oir=new ArrayList<>();
        for (ObjectInRange object : ObjectsInRange) {
            float radius;
            switch (object.objectType) {
                case Plant, Meat:
                    radius = gWorld.gNourishment.get(object.IdOfObject()).GetNourishmentSize() / 2;
                    object.distance = itemInSightLine(object.X(), object.Y(), radius);
                    break;
                case Creature:
                    radius = cgv.GetBodyWidth();
                    object.distance = itemInSightLine(object.X(), object.Y(), radius);
                    break;
                case PlantScent:
                case MeatScent:
                case CreatureScent:
                    distance = -1;
                    break;
            }
            if (distance != -1) {
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

        for (SightLine sightLine : sightlines) {
            distance = -1;
            //determine if x,y with radius r crosses sight line.
            //https://stackoverflow.com/questions/67116296/is-this-code-for-determining-if-a-circle-and-line-segment-intersects-correct
            if (gUtils.lineSegmentIntersectsCircleOptimized(sightLine.GetEyeX(), sightLine.GetEyeY(), sightLine.GetDistanceX(), sightLine.GetDistanceY(), x, y, r)) {
                distance = gUtils.DistanceBetweenPoints(sightLine.GetEyeX(), sightLine.GetEyeY(), x, y);
                break;
            }
        }
        return distance;
    }

}

