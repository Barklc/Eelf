package main.Creature;
import main.Coords;
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
    private boolean CreatureDisplayWindowFlag=false;

    public CreatureVision(Creature currentCreature) {
        cgv=currentCreature.GetGenes();
    }

    public void InitializeVision(float a, float d, float newClarity){
        angle=a;
        distance=d;
        clarity=newClarity;
        sightlines=new ArrayList<>();
    }

    public void SetVisionDistance(float value){
        distance=value;
    }
    public void SetVisionDirectionAngle(float value){hangle=value;}
    public void UpdateLocation(BodySegment head){
        hx = head.GetSegmentX();
        hy = head.GetSegmentY();
        hangle = head.GetSegmentAngle();
    }

    public void UpdateSightLines(){
        sightlines=new ArrayList<>();
        //System.out.println(Math.toDegrees(hangle));
        //System.out.println(Math.toDegrees(hangle-(angle/2)*Math.PI));
        //System.out.println(Math.toDegrees(hangle+(angle/2)*Math.PI));
        //System.out.println(Math.toDegrees(angle));

        //get outside sight lines
        float x1,y1,x2,y2;
        x1 = (float) (hx + (distance)*Math.cos(hangle-(angle/2)));
        y1 = (float) (hy + (distance)*Math.sin(hangle-(angle/2)));
        sightlines.add(new SightLine(hx,hy,x1,y1));

        x2 = (float) (hx + (distance)*Math.cos(hangle+((angle/2))));
        y2 = (float) (hy + (distance)*Math.sin(hangle+((angle/2))));
        sightlines.add(new SightLine(hx,hy,x2,y2));

        float steps=clarity;
        float sightLineCount=(angle)/((clarity));
        //print("Angle=" + angle*100 + "  Clarity=" + clarity + " Step=" + steps + "\r\n");
        //print("sightLineCount= " + sightLineCount + "\r\n");
        float startingAngle= (hangle-(angle/2));
        for(int i=0;i<sightLineCount;i++){
            x2 = (float) (hx + (distance)*Math.cos(startingAngle+((steps*i))));
            y2 = (float) (hy + (distance)*Math.sin(startingAngle+((steps*i))));
            sightlines.add(new SightLine(hx,hy,x2,y2));
        }

    }

    public void SetCreatureDisplayWindowFlag(boolean value){
        CreatureDisplayWindowFlag=value;
    }
    public void Display(PApplet w,float scale){

        if (!CreatureDisplayWindowFlag && FlagsOverride.ShowVisionSightLinesFlag){
            if (!sightlines.isEmpty()){
                w.stroke(100);
                SightLine sightline=sightlines.get(0);
                w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());

                sightline=sightlines.get(1);
                w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());

                w.stroke(100);
                w.noFill();
                w.arc(hx,hy,distance*2,distance*2, (hangle-(angle/2)), (hangle+(angle/2)));

                for(int i=2;i<sightlines.size();i++){
                    sightline=sightlines.get(i);
                    w.line(sightline.GetEyeX(), sightline.GetEyeY(), sightline.GetDistanceX(), sightline.GetDistanceY());
                }
                w.stroke(2);
                Coords coords = gUtils.calculatePointOnCircle(hx,hy,distance,hangle);
                w.line(hx,hy,coords.X(),coords.Y());
            }
        }
        CreatureDisplayWindowFlag=false;
    }

    public ArrayList<ObjectInRange> FindObjects(ArrayList<ObjectInRange> ObjectsInRange){
        ArrayList<ObjectInRange> oir=new ArrayList<>();
        float distanceToObject=-1;
        for (ObjectInRange object : ObjectsInRange) {
            float radius;
            switch (object.objectType) {
                case Plant, Meat:
                    radius = gWorld.gNourishment.get(object.IdOfObject()).GetNourishmentSize() / 2;
                    object.distance = itemInSightLine(object.X(), object.Y(), radius);
                    object.distance = itemInSightLine(object.X(), object.Y(), radius);
                    distanceToObject=object.distance;
                    break;
                case Creature:
                    radius = cgv.GetBodyWidth();
                    object.distance = itemInSightLine(object.X(), object.Y(), radius);
                    distanceToObject=object.distance;
                    break;
                case PlantScent:
                case MeatScent:
                case CreatureScent:
                    distanceToObject = -1;
                    break;
            }
            if (distanceToObject != -1.0f) {
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

