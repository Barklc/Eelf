package main.Creature;

import main.Nourishments.Nourishment;

import java.util.concurrent.Callable;

import static main.Main.gWorld;

public class ObjectInRange{
    float x,y,distance;
    ObjectInRangeType objectType;
    int idOfObject;
    float scentStrength;

    public ObjectInRange(float X,float Y, float Distance,ObjectInRangeType ObjectInRangeType,int IdOfObject,float ScentStrength){
        x=X;
        y=Y;
        distance=Distance;
        objectType=ObjectInRangeType;
        idOfObject=IdOfObject;
        scentStrength=ScentStrength;
    }

    public float X(){
        return x;
    }

    public float Y(){
        return y;
    }

    public float Distance(){
        return distance;
    }

    public ObjectInRangeType ObjectTypeInRange(){
        return objectType;
    }

    public int IdOfObject(){
        return idOfObject;
    }

    public float GetScentStrength(){
        return scentStrength;
    }

    public void SetScentStrength(float s){
        scentStrength=s;
    }
    public float GetRadius() {
        if (objectType == ObjectInRangeType.Plant || objectType == ObjectInRangeType.Meat) {
            Nourishment nourishment = gWorld.gNourishment.get(idOfObject);
            return nourishment.GetNourishmentSize();
        }
        if (objectType==ObjectInRangeType.Creature){
            Creature currentCreature=gWorld.gPopulation.GetCreature(idOfObject);
            return currentCreature.GetBody().GetHeadSegment().GetSegmentHeight();
        }
        return 10;
    }
}

