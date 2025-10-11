package main.Nourishments;
import main.GameParameters;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class Nourishment{
    private float NourishmentX;
    private float NourishmentY;
    private float NourishmentMass;
    private float NourishmentMassMax;
    private Color NourishmentColor;
    private float NourishmentScent;
    private float NourishmentScentStrength;

    public Nourishment(){
        //TODO: Establish max energy/biomass in world.  Plants, meat, and creatures take from it.  Energy used and meat rotted returns it.
    }

    public abstract NourishmentTypes NourishmentType();


    public Float GetNourishmentX(){
        return NourishmentX;
    }

    public void SetNourishmentX(float x){
        NourishmentX=x;
    }

    public Float GetNourishmentY(){
        return NourishmentY;
    }

    public void SetNourishmentY(float y){
        NourishmentY=y;
    }

    public Float GetNourishmentSize(){
        return NourishmentMass*GameParameters.NourishmentMassToSizeAdjustment;
    }


    public Float GetNourishmentMass(){
        return NourishmentMass;
    }

    public void SetNourishmentMass(float m){
        if (m<0) {NourishmentMass=0.5f;} else {NourishmentMass=m;}
    };

    public Color GetNourishmentColor(){
        return NourishmentColor;
    }

    public void SetNourishmentColor(Color c){
        NourishmentColor=c;
    }


    public Float GetNourishmentScent(){
        return NourishmentScent;
    }

    public void SetNourishmentScent(float s){
        NourishmentScent=s;
    }

    public Float GetNourishmentScentStrength(){
        return NourishmentScentStrength;
    }

    public void SetNourishmentScentStrength(float s){
        NourishmentScentStrength=s;
    }

    public void GrowRotNourishment(){
        if (NourishmentType()==NourishmentTypes.Plant){
            float growthAmount = (GetNourishmentMass() * GameParameters.GrowthPercentagePerTick);
            float currentMass = GetNourishmentMass();
            SetNourishmentMass(Math.min(currentMass + growthAmount, NourishmentMassMax));
        } else {
            float rotAmount = (GetNourishmentMass() * GameParameters.RotPercentagePerTick);
            float currentMass = GetNourishmentMass();
            if (currentMass -  rotAmount<0){
                SetNourishmentMass(0);
            } else {
                SetNourishmentMass(currentMass - rotAmount);
            }
        }
    }
    public void InitializeNourishment(float x,float y, float mass,Color c,float scent,float strength){
        NourishmentX=x;
        NourishmentY=y;
        NourishmentMass=mass;
        NourishmentMassMax=mass;
        NourishmentColor=c;
        NourishmentScent=scent;
        NourishmentScentStrength=strength;
    }

    public abstract void DisplayNourishment(PApplet w, float scale);

    protected abstract ArrayList<PShape> CreateShape(float w, float h, Color c);

}
