package main;

import java.util.Random;

public class Utilities{

    public Utilities(){}

    public boolean lineSegmentIntersectsCircleOptimized(float x1, float y1, float x2, float y2, float cx, float cy, float r) {
        float x_linear = x2 - x1;
        float x_constant = x1 - cx;
        float y_linear = y2 - y1;
        float y_constant = y1 - cy;
        float a = x_linear * x_linear + y_linear * y_linear;
        float half_b = x_linear * x_constant + y_linear * y_constant;
        float c = x_constant * x_constant + y_constant * y_constant - r * r;
        return (
                half_b * half_b >= a * c &&
                        (-half_b <= a || c + half_b + half_b + a <= 0) &&
                        (half_b <= 0 || c <= 0) );
    }

    public boolean Overlaps(float x1,float y1,float r1,float x2,float y2,float r2){
        //Use distance formula to check if a circle overlaps with another circle.
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + (Math.pow(y2- y1, 2)));
        return distance <= (r1 + r2) && distance >= Math.abs(r1 - r2);
    }

    public double circleIntersectionArea(double r1, double x1, double y1, double r2, double x2, double y2) {
        double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (d >= r1 + r2) return 0.0; // No intersection
        if (d <= Math.abs(r1 - r2))
            return Math.PI * Math.pow(Math.min(r1, r2), 2); // One circle is within another
        double theta1 = 2 * Math.acos((Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(d, 2)) / (2 * r1 * d));
        double theta2 = 2 * Math.acos((Math.pow(r2, 2) - Math.pow(r1, 2) + Math.pow(d, 2)) / (2 * r2 * d));
        return 0.5f * (Math.pow(r1, 2f) * (theta1 - Math.sin(theta1)) + Math.pow(r2, 2) * (theta2 - Math.sin(theta2)));
    }

    public float AreaOfCircle(float r){
        // calculating the area of the circle
        return (float) (Math.PI*r*r);
    }

    public float DistanceBetweenPoints(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public Coords calculatePointOnEllipse( float x,float y, float angle,float semiMajorAxis, float semiMinorAxis) {
        Coords coords=new Coords(0,0);
        // Convert angle from degrees to radians
        //float angleInRadians = radians(angle);

        // Calculate x and y coordinates using parametric equations
        coords.x= (float) (x + semiMajorAxis * Math.cos(angle));
        coords.y = (float) (y + semiMinorAxis * Math.sin(angle));

        return coords;
    }

    public Coords calculatePointOnCircle(float x,float y, float radius, float angle){
        Coords coords=new Coords(0,0);

        coords.x= (float) (x+radius*Math.cos(angle));
        coords.y= (float) (y+radius*Math.sin(angle));

        return coords;
    }

    public float GetRandomNumber(float min, float max) {
        Random random=new Random();
        return min + random.nextFloat(max-min);
    }

}
