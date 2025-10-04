package main.Creature;

import main.GameParameters;

import java.util.ArrayList;

import static main.Main.*;

public class CreatureOlfactory{
    private Creature CurrentCreature;
    private CreatureGeneValues CGV;
    private CreatureVitals Vitals;
    float Sensitivity;

    public CreatureOlfactory(Creature currentCreature){
        CurrentCreature=currentCreature;
        CGV=currentCreature.GetGenes();
        Sensitivity=CGV.GetReceptorSensitivity();
        Vitals=currentCreature.GetVitals();
    }

    public float ScentDetected(float x, float y, float scentx, float scenty, float strength){
        float returnValue = 0;

        float distance=gUtils.DistanceBetweenPoints(x,y,scentx,scenty);

        //println("CreatureOlfactory.ScentDetected - strength: " + strength);
        //println("CreatureOlfactory.ScentDetected - distance: " + distance);
        //println("CreatureOlfactory.ScentDetected - maxScentDistance: " + gMaxScentDistance);
        //println("CreatureOlfactory.ScentDetected - Sensitivity: " + strength*Sensitivity);
        if (distance<= GameParameters.MaxScentDistance){
            float scentStrengthPerPointOfDistance=strength/GameParameters.MaxScentDistance;
            float scentStrengthAtDistance=strength-(scentStrengthPerPointOfDistance*distance);
            //println("CreatureOlfactory.ScentDetected - scentStrengthPerPointOfDistance: " + scentStrengthPerPointOfDistance);
            //println("CreatureOlfactory.ScentDetected - scentStrengthAtDistance: " + scentStrengthAtDistance);
            if (scentStrengthAtDistance>=strength*Sensitivity){
                returnValue=scentStrengthAtDistance;
            }
        }
        return returnValue;
    }

    public ArrayList<ObjectInRange> FindScents(ArrayList<ObjectInRange> objectInRanges){
        ArrayList<ObjectInRange> scentsInRange = new ArrayList<ObjectInRange>();
        for (ObjectInRange o : objectInRanges) {
            //println("World.Display - o.objectType: " + o.objectType);
            if (o.objectType == ObjectInRangeType.PlantScent || o.objectType == ObjectInRangeType.MeatScent) {
                float scentDetected = CurrentCreature.GetOlfactory().ScentDetected(CurrentCreature.GetVitals().GetX(), CurrentCreature.GetVitals().GetY(), o.X(), o.Y(), o.GetScentStrength());
                o.SetScentStrength(scentDetected);
                scentsInRange.add(o);
            }
        }
        return scentsInRange;
    }

    public ObjectInRange GetStrongestScent(ArrayList<ObjectInRange> scentObjectInRange){
        ObjectInRange Strongest;

        if (scentObjectInRange.isEmpty()){
            return null;
        }
        Strongest= scentObjectInRange.get(0);
        if (scentObjectInRange.size()>1){
            Strongest= scentObjectInRange.get(0);
            for(int i = 1; i< scentObjectInRange.size(); i++){
                if (scentObjectInRange.get(i).GetScentStrength()<Strongest.GetScentStrength()){
                    Strongest= scentObjectInRange.get(i);
                }
            }
        }
        return Strongest;

    }
    private ArrayList<ObjectInRange> FilterByObjectType(ArrayList<ObjectInRange> objectsInRange,ObjectInRangeType objectType){
        ArrayList<ObjectInRange> newList=new ArrayList<>();
        for(ObjectInRange o : objectsInRange){
            if (o.objectType==objectType){
                newList.add(o);
            }
        }
        return newList;
    }
    public ObjectInRange GetNearestScentSpecified(ArrayList<ObjectInRange> scentObjectsInRange,ObjectInRangeType objectType){
        ObjectInRange Closest;

        if (scentObjectsInRange==null || scentObjectsInRange.isEmpty()){
            return null;
        }
        ArrayList<ObjectInRange> objectsInRange=FilterByObjectType(scentObjectsInRange,objectType);
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
}