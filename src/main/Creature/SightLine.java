package main.Creature;

public class SightLine{
    private float eyex,eyey;
    private float distancex, distancey;

    public SightLine(float ex,float ey, float dx, float dy){
        eyex=ex;
        eyey=ey;
        distancex=dx;
        distancey=dy;
    }

    public float GetEyeX(){
        return eyex;
    }

    public float GetEyeY(){
        return eyey;
    }

    public float GetDistanceX(){
        return distancex;
    }

    public float GetDistanceY(){
        return distancey;
    }
}
