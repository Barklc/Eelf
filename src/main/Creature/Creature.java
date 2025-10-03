package main.Creature;

import main.Actions;
import main.Creature.BodySegments.*;
import main.GameParameters;
import main.Genetics.Genome;
import main.Nourishments.Nourishment;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.UUID;

import static main.Main.*;

public class Creature{
    private final CreatureBody Body;
    private final CreaturePhysics Physics;
    private final CreatureGeneValues Genes;
    private final CreatureOlfactory Olfactory;
    private final CreatureVision Vision;
    private final CreatureVitals Vitals;
    private final CreatureDecisionEngine DecisionEngine;
    private final CreatureMetabolism Metabolism;
    private ArrayList<ObjectInRange> ObjectsInRange;
    private float Speed;
    private float BodyMass;
    private float TurnAngle;
    private final UUID guid;
    private Actions CreatureAction;
    private float DistanceToTarget;
    private float PreviousDistanceToTarget;
    private ObjectInRange TargetObject;


    public Creature(float startX, float startY, Genome genome, UUID uuid){

        Genes = new CreatureGeneValues(genome);
        Vitals=new CreatureVitals(this);
        Vitals.InitializeCreatureVitals(startX,startY,0,0,5,uuid,UUID.randomUUID());
        Body=new CreatureBody(this);
        Physics=new CreaturePhysics(this);
        Olfactory=new CreatureOlfactory(this);
        ObjectsInRange=new ArrayList<>();
        Vision=new CreatureVision(this);
        Metabolism=new CreatureMetabolism(this);
        DecisionEngine=new CreatureDecisionEngine(this);

        guid=uuid;
        Physics.SetBaseSpeed(Genes.GetSpeed());
        Speed = Physics.DetermineSpeed(Genes.GetTailHeight(),BodyMass);
        Physics.SetBaseTurnRate(.025f);
        TurnAngle = Physics.DetermineTurnRate(Genes.GetFlipperWidth(),Genes.GetBodyWidth());
        Vision.InitializeVision(Genes.GetVisionAngle(), Vitals.GetCurrentVisionDistance(), Genes.GetVisionClarity());
        Body.CreateBody(Vitals.GetX(),Vitals.GetY());
        BodyMass=Body.CalculateBodyMass();
        CreatureAction=Actions.NewDestination;
        DistanceToTarget=0;
        PreviousDistanceToTarget=0;
        TargetObject=new ObjectInRange(0,0,0,ObjectInRangeType.Location,0,0);
    }

    public UUID GetUUID() {
        return guid;
    }

    public CreatureBody GetBody(){
        return Body;
    }

    public CreatureVitals GetVitals(){return Vitals;}

    public CreaturePhysics GetPhysics(){return Physics;}

    public CreatureOlfactory GetOlfactory(){return Olfactory;}

    public CreatureGeneValues GetGenes(){return Genes;}

    public CreatureMetabolism GetMetabolism(){return Metabolism;}

    public CreatureDecisionEngine GetDecisionEngine(){return DecisionEngine;}

    public ObjectInRange GetTargetObject(){return TargetObject;}

    public ArrayList<ObjectInRange> GetObjectsInRange(UUID CurrentUUID){
        ObjectsInRange=gWorld.ObjectsInRange(Vitals.GetX(),Vitals.GetY(),gMaxObjectInRangeRadius,CurrentUUID);
        return ObjectsInRange;
    }

    public void UpdateCreatureLocation(){
        //Update speed value based on when growing.  Otherwise, use the last
        BodyMass=Body.GetBodyMass();
        Speed = Physics.DetermineSpeed(Body.GetCurrentTailHeight(),Body.GetBodyMass());
        TurnAngle = Physics.DetermineTurnRate(Body.GetCurrentFlipperWidth(),Body.GetBodySegment(GameParameters.FlippersSegmentConnected).GetSegmentWidth());
        System.out.println("TurnAngle=" + TurnAngle);

        //Loop through each body segment and update location, color and angle
        for(int i=0;i<Body.GetTotalBodySegmentLength();i++){
            BodySegment b=Body.GetBodySegment(i);
            switch(b.BodySegmentType()){
                case Mouth:
                    Mouth m=(Mouth)b;
                    m.SetMouthSize(Body.GetCurrentMouthSize());
                    m.SetBiteStrength(Body.GetCurrentBiteStrength());
                    m.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Flippers:
                    Flippers f=(Flippers)b;
                    f.SetFlipperWidth(Body.GetCurrentFlipperWidth());
                    f.SetFlipperHeight(Body.GetCurrentFlipperHeight());
                    f.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Tail:
                    Tail t=(Tail)b;
                    t.SetTailHeight(Body.GetCurrentTailHeight());
                    t.SetTailWidth(Body.GetCurrentTailWidth());
                    t.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
                case Head, Eyes, Segment:
                    b.UpdateSegment(Body.GetBodySegment(b.GetSegmentConnectedTo()));
                    break;
            }

            Vision.SetVisionDistance(Vitals.GetCurrentVisionDistance());
            Vision.UpdateLocation(Body.GetHeadSegment());
            Vision.UpdateSightLines(Vitals.GetCurrentVisionDistance());

        }
    }

    public void MoveTo(float targetX,float targetY){
        PreviousDistanceToTarget=DistanceToTarget;
        float a= (float) Math.atan2(targetY -Vitals.GetY(), targetX -Vitals.GetX());
        float delta = a-Vitals.GetAngle();
        while (delta < -Math.PI){delta+= (float) (2*Math.PI);}
        while (delta > Math.PI){delta-= (float) (2*Math.PI);}
        Vitals.SetAngle(Vitals.GetAngle()+(TurnAngle* delta));
        Vitals.SetX((float) (Vitals.GetX()+Speed*Math.cos(Vitals.GetAngle())));
        Vitals.SetY((float) (Vitals.GetY()+Speed*Math.sin(Vitals.GetAngle())));

        DistanceToTarget= gUtils.DistanceBetweenPoints(Vitals.GetX(),Vitals.GetY(),TargetObject.X(),TargetObject.Y());

        //creatureProperties.ReduceEnergyLevel((currentSpeed*gSpeedEnergyMod)/60);
    }
    public float GetDistanceToTarget(){
        return DistanceToTarget;
    }
    public float GetPreviousDistanceToTarget(){
        return PreviousDistanceToTarget;
    }
    private ObjectInRange NewDestination(){
        PreviousDistanceToTarget=0;
        return new ObjectInRange(gUtils.GetRandomNumber(10,1190),gUtils.GetRandomNumber(10,990),0,ObjectInRangeType.Location,0,0);
    }

    public Actions GetCurrentAction(){
        return CreatureAction;
    }
    public void CreatureAction(float ticks){
        UpdateCreatureLocation();


        ArrayList<ObjectInRange> objectsInRange = GetObjectsInRange(guid);
        ArrayList<ObjectInRange> ScentObjectInRange=Olfactory.FindScents(objectsInRange);
        ArrayList<ObjectInRange> visualObjectsInRange=Vision.FindObjects(objectsInRange);
        Actions PreviousAction = CreatureAction;
        //TODO: Reengineer this to be an array with specific objects at specified locations in array to allow for easy additions to senses.
        ObjectInRange PlantScent=Olfactory.GetNearestScentSpecified(ScentObjectInRange,ObjectInRangeType.PlantScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(PlantScent,ObjectInRangeType.PlantScent);
        ObjectInRange MeatScent=Olfactory.GetNearestScentSpecified(ScentObjectInRange,ObjectInRangeType.MeatScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(MeatScent,ObjectInRangeType.MeatScent);
        ObjectInRange CreatureScent=Olfactory.GetNearestScentSpecified(ScentObjectInRange,ObjectInRangeType.CreatureScent);
        DecisionEngine.SetObjectInRangeBySpecifiedType(CreatureScent,ObjectInRangeType.CreatureScent);
        ObjectInRange Plant=Vision.GetNearestObjectSpecified(visualObjectsInRange,ObjectInRangeType.Plant);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Plant,ObjectInRangeType.Plant);
        ObjectInRange Meat=Vision.GetNearestObjectSpecified(visualObjectsInRange,ObjectInRangeType.Meat);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Meat,ObjectInRangeType.Meat);
        ObjectInRange Creature=Vision.GetNearestObjectSpecified(visualObjectsInRange,ObjectInRangeType.Creature);
        DecisionEngine.SetObjectInRangeBySpecifiedType(Creature,ObjectInRangeType.Creature);

        CreatureAction=DecisionEngine.Decision(CreatureAction);
        System.out.println("CreatureAction=" + CreatureAction);
        switch (CreatureAction){
            case NewDestination:
                TargetObject=NewDestination();
                DistanceToTarget= gUtils.DistanceBetweenPoints(Vitals.GetX(),Vitals.GetY(),TargetObject.X(),TargetObject.Y());
                CreatureAction=Actions.Move;
                break;
            case Move:
                MoveTo(TargetObject.X(),TargetObject.Y());
                break;
            case TargetPlant:
                Nourishment plant=gWorld.gNourishment.get(Plant.IdOfObject());
                TargetObject=Plant;
                CreatureAction=Actions.MoveToPlant;
                break;
            case MoveToPlant:
                MoveTo(TargetObject.X(),TargetObject.Y());
                break;
            case Eat:
                Physics.PauseSpeed(true);
                Nourishment nourishment=plant=gWorld.gNourishment.get(Plant.IdOfObject());
                float amountBit=Metabolism.Bite(nourishment);
                nourishment.SetNourishmentMass(nourishment.GetNourishmentMass()-amountBit);
                gWorld.gNourishment.set(Plant.IdOfObject(),nourishment);
                break;
        }
        System.out.println("Vital X=" + Vitals.GetX());
        System.out.println("Vital Y=" + Vitals.GetY());
        System.out.println("Target X=" + TargetObject.X());
        System.out.println("Target Y=" + TargetObject.Y());
        System.out.println("Angle=" + Vitals.GetAngle());
        System.out.println("DistanceToTarget=" + DistanceToTarget);
        System.out.println("PreviousDistanceToTarget=" + PreviousDistanceToTarget);
        //println("Creature.CreatureAction - soir.size(): " + soir.size());
        //println("Creature.CreatureAction - voir.size(): " + voir.size());

        //TODO: Determine movement distance and pass it to energy cycle
        Metabolism.SetEnergyUsedBase(0.0f);
        Metabolism.SetEnergyUsedInGestation(0.0f);
        Metabolism.SetEnergyUsedInBirth(0.0f);
        Metabolism.SetEnergyUsedForMovement(0.0f);
        Metabolism.SetEnergyUsedDuringBirthRecoveryTime(0.0f);
        Metabolism.EnergyCycle();
        //TODO: Check health of unborn and determine its state (alive/dead)
        //TODO: If unborn is lost clear pregnant flag and start birthRecoveryTime
        Body.GetHeadSegment().SetSegmentX(Vitals.GetX());
        Body.GetHeadSegment().SetSegmentY(Vitals.GetY());
        Body.GetHeadSegment().SetSegmentAngle(Vitals.GetAngle());

        if (ticks==1){
            Vitals.IncreaseMaturity();
            Body.UpdateBody();
        }



    }

    public void Display(PApplet w,float scale){
        BodySegment head=Body.GetHeadSegment();
        w.stroke(0);
       // w.fill(new Color(128,128,128).hashCode());
       // w.circle(head.GetSegmentX(),head.GetSegmentY(),gMaxObjectInRangeRadius*2);
       // w.stroke(new Color(0,0,255).hashCode());
       // w.circle(head.GetSegmentX(),head.GetSegmentY(),gMaxScentDistance*2);
        BodySegment b=Body.GetMouthSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Mouth){
            b.DisplaySegment(w,scale);
        }
        b=Body.GetFlippersSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Flippers){
            b.DisplaySegment(w,scale);
        }
        for(int i=0;i<Body.GetTotalBodySegmentLength();i++){
            b=Body.GetBodySegment(i);
            if (b.BodySegmentType()!=SegmentID.Mouth && b.BodySegmentType()!=SegmentID.Flippers) {
                b.DisplaySegment(w, scale);
            }
        }

        Vision.Display(w, scale);
        w.circle(TargetObject.X(),TargetObject.Y(),5);
    }
}
