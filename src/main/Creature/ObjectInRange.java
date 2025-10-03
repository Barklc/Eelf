package main.Creature;

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
}

