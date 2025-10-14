package main.Creature;

import main.Actions;
import main.Creature.BodySegments.*;
import main.DecisionEngine.DecisionEngine;
import main.DecisionEngine.InputID;
import main.FlagsOverride;
import main.GameParameters;
import main.Genetics.Genome;
import main.Nourishments.Nourishment;
import processing.core.PApplet;

import java.awt.*;
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
    private final main.DecisionEngine.DecisionEngine DecisionEngine;
    private final CreatureMetabolism Metabolism;
    private float Speed;
    private float BodyMass;
    private float TurnAngle;
    private final UUID guid;
    private Actions CreatureAction;
    private float DistanceToTarget;
    private float PreviousDistanceToTarget;
    private ObjectInRange TargetObject;
    private ArrayList<ObjectInRange> ObjectsInRange;
    private ArrayList<ObjectInRange> ScentObjectsInRange;
    private ArrayList<ObjectInRange> SeenObjectsInRange;
    private int AliveTickCount;
    private ObjectInRange NearestPlant;
    private ObjectInRange NearestMeat;
    private ObjectInRange NearestCreature;
    private ObjectInRange NearestPlantScent;
    private ObjectInRange NearestMeatScent;
    private ObjectInRange NearestCreatureScent;

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
        DecisionEngine=new DecisionEngine(this);

        guid=uuid;
        Physics.SetBaseSpeed(Genes.GetSpeed());
        Speed = Physics.DetermineSpeed(BodyMass);
        Physics.SetBaseTurnRate(.025f);
        TurnAngle = Physics.DetermineTurnRate(Genes.GetFlipperWidth(),Genes.GetBodyWidth());
        Vision.InitializeVision(Genes.GetVisionAngle(), Vitals.GetCurrentVisionDistance(), Genes.GetVisionClarity());
        Body.CreateBody(Vitals.GetX(),Vitals.GetY());
        BodyMass=Body.CalculateBodyMass();
        CreatureAction=Actions.NewDestination;
        DistanceToTarget=0;
        PreviousDistanceToTarget=0;
        TargetObject=new ObjectInRange(0,0,0,ObjectInRangeType.Location,0,0);
        ObjectsInRange=new ArrayList<>();
        ScentObjectsInRange=new ArrayList<>();
        SeenObjectsInRange=new ArrayList<>();
        AliveTickCount=0;
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

    public DecisionEngine GetDecisionEngine(){return DecisionEngine;}
    public CreatureVision GetCreatureVision(){return Vision;}

    public ObjectInRange GetTargetObject(){return TargetObject;}

    public void UpdateCreatureLocation(){
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
        }
    }

    public void MoveTo(float targetX,float targetY){
        PreviousDistanceToTarget=DistanceToTarget;
        float a= (float) Math.atan2(targetY - Vitals.GetY(), targetX - Vitals.GetX());
        float delta = a-Vitals.GetAngle();
        while (delta < -Math.PI){delta+= (float) (2*Math.PI);}
        while (delta > Math.PI){delta-= (float) (2*Math.PI);}
        Vitals.SetAngle(Vitals.GetAngle() + (TurnAngle * delta));
        Vitals.SetX((float) (Vitals.GetX() + Speed*Math.cos(Vitals.GetAngle())));
        Vitals.SetY((float) (Vitals.GetY() + Speed*Math.sin(Vitals.GetAngle())));

        DistanceToTarget= gUtils.DistanceBetweenPoints(Vitals.GetX(),Vitals.GetY(),TargetObject.X(),TargetObject.Y());

        //creatureProperties.ReduceEnergyLevel((currentSpeed*gSpeedEnergyMod)/60);
    }
    public float GetAngleToTarget(float targetX,float targetY){
        float delta= (float) Math.atan2(targetY - Vitals.GetY(), targetX - Vitals.GetX());
        //while (delta < -Math.PI){delta+= (float) (2*Math.PI);}
        //while (delta > Math.PI){delta-= (float) (2*Math.PI);}
        System.out.println("GetAngleToTarget.Angle: " + Math.toDegrees(delta) );
        System.out.println("GetAngleToTarget.Physic: " + Physics.GetCurrentTurnAngle());
        return (float)Math.toDegrees(delta);
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
    public void SetCurrentAction(Actions action){
        System.out.println("Creature.SetCurrentAction: " + action);
        CreatureAction=action;
    }
    public ArrayList<ObjectInRange> GetObjectsInRange(UUID CurrentUUID){return ObjectsInRange;}
    public ArrayList<ObjectInRange> GetObjectsScentInRange(ArrayList<ObjectInRange> objectsInRangeList) {return ScentObjectsInRange;}
    public ArrayList<ObjectInRange> GetObjectsSeenInRange(ArrayList<ObjectInRange> objectsInRangeList) {return SeenObjectsInRange;}
    public int GetAliveTickCount(){return AliveTickCount;}
    public void SetAliveTickCount(int value){AliveTickCount=value;}
    public void IncreaseAliveTickCount(){AliveTickCount++;}

    public void SetInputs(){
        DecisionEngine.SetInputArray(InputID.InputCreatureX,Vitals.GetX());
        DecisionEngine.SetInputArray(InputID.InputCreatureY,Vitals.GetY());
        DecisionEngine.SetInputArray(InputID.InputCreatureAngleToTarget,GetAngleToTarget(TargetObject.X(),TargetObject.Y()));
        DecisionEngine.SetInputArray(InputID.InputCreatureTarget,GetTargetObject());
        DecisionEngine.SetInputArray(InputID.InputCreatureDistanceToTarget,DistanceToTarget);
        DecisionEngine.SetInputArray(InputID.InputCreaturePreviousDistanceToTarget,PreviousDistanceToTarget);
        DecisionEngine.SetInputArray(InputID.InputVisionDominance,Body.GetEyesPresent() && Vitals.GetVisionDominancePercentage()>Vitals.GetScentDominancePercentage());
        DecisionEngine.SetInputArray(InputID.InputScentDominance,Vitals.GetScentDominancePercentage()> Vitals.GetVisionDominancePercentage());
        DecisionEngine.SetInputArray(InputID.InputNourishments,gWorld.gNourishment);
        DecisionEngine.SetInputArray(InputID.InputIsHungry,Vitals.IsHungry());
        DecisionEngine.SetInputArray(InputID.InputIsPregnant,Vitals.IsPregnant());
        DecisionEngine.SetInputArray(InputID.InputMouthAtPoint,Physics.MouthAtPoint(GetTargetObject().X(),GetTargetObject().Y(),GetTargetObject().GetRadius()/2));
        DecisionEngine.SetInputArray(InputID.InputCreatureCurrentSpeed,Physics.GetCurrentSpeed());

        ObjectsInRange = gWorld.ObjectsInRange(Vitals.GetX(),Vitals.GetY(),GameParameters.MaxObjectInRangeRadius,guid);
        DecisionEngine.SetInputArray(InputID.InputAllObjectsInRange,ObjectsInRange);
        ScentObjectsInRange=Olfactory.FindScents(ObjectsInRange);
        DecisionEngine.SetInputArray(InputID.InputScentObjectsInRange,ScentObjectsInRange);
        SeenObjectsInRange=Vision.FindObjects(ObjectsInRange);
        DecisionEngine.SetInputArray(InputID.InputSeenObjectsInRange,SeenObjectsInRange);


        NearestPlant=Vision.GetNearestObjectSpecified(SeenObjectsInRange,ObjectInRangeType.Plant);
        DecisionEngine.SetInputArray(InputID.InputNearestPlantInRange,NearestPlant);
        NearestMeat=Vision.GetNearestObjectSpecified(SeenObjectsInRange,ObjectInRangeType.Meat);
        DecisionEngine.SetInputArray(InputID.InputNearestMeatInRange,NearestMeat);
        NearestCreature=Vision.GetNearestObjectSpecified(SeenObjectsInRange,ObjectInRangeType.Creature);
        DecisionEngine.SetInputArray(InputID.InputNearestCreatureInRange,NearestCreature);
        if (NearestMeat!=null && NearestPlant!=null){
            if (NearestMeat.Distance()<NearestPlant.Distance()){
                DecisionEngine.SetInputArray(InputID.InputClosestSeenNourishmentInRange,NearestMeat);
            } else {
                DecisionEngine.SetInputArray(InputID.InputClosestSeenNourishmentInRange,NearestPlant);
            }
        }
        if (NearestPlant!=null && NearestMeat==null) { DecisionEngine.SetInputArray(InputID.InputClosestSeenNourishmentInRange,NearestPlant);}
        if (NearestMeat!=null && NearestPlant==null) { DecisionEngine.SetInputArray(InputID.InputClosestSeenNourishmentInRange,NearestMeat);}
        if (NearestMeat==null && NearestPlant==null){
            DecisionEngine.SetInputArray(InputID.InputClosestSeenNourishmentInRange,null);
        }

        NearestPlantScent=Olfactory.GetNearestScentSpecified(ScentObjectsInRange,ObjectInRangeType.PlantScent);
        DecisionEngine.SetInputArray(InputID.InputNearestPlantScentInRange,NearestPlantScent);
        NearestMeatScent=Olfactory.GetNearestScentSpecified(ScentObjectsInRange,ObjectInRangeType.MeatScent);
        DecisionEngine.SetInputArray(InputID.InputNearestMeatScentInRange,NearestMeatScent);
        NearestCreatureScent=Olfactory.GetNearestScentSpecified(ScentObjectsInRange,ObjectInRangeType.CreatureScent);
        DecisionEngine.SetInputArray(InputID.InputNearestCreatureScentInRange,NearestCreatureScent);
        if (NearestMeatScent!=null && NearestPlantScent!=null){
            if (NearestMeatScent.Distance()<NearestPlantScent.Distance()){
                DecisionEngine.SetInputArray(InputID.InputClosestSmeltNourishmentInRange,NearestMeatScent);
            } else {
                DecisionEngine.SetInputArray(InputID.InputClosestSmeltNourishmentInRange,NearestPlantScent);
            }
        }
        if (NearestPlantScent!=null && NearestMeatScent==null) { DecisionEngine.SetInputArray(InputID.InputClosestSmeltNourishmentInRange,NearestPlantScent);}
        if (NearestMeatScent!=null && NearestPlantScent==null) { DecisionEngine.SetInputArray(InputID.InputClosestSmeltNourishmentInRange,NearestMeatScent);}
        if (NearestMeatScent==null && NearestPlantScent==null){
            DecisionEngine.SetInputArray(InputID.InputClosestSmeltNourishmentInRange,null);
        }

        DecisionEngine.SetInputArray(InputID.InputAliveTickCount,GetAliveTickCount());
        DecisionEngine.SetInputArray(InputID.InputAge,Vitals.GetAge());
        DecisionEngine.SetInputArray(InputID.InputMaturityAge,Vitals.GetMaturityAge());
        DecisionEngine.SetInputArray(InputID.InputSeniorAge,Vitals.GetSeniorAge());
        DecisionEngine.SetInputArray(InputID.InputLifeSpan,Vitals.GetLifeSpan());
        DecisionEngine.SetInputArray(InputID.InputHealth,Vitals.GetHealth());
        DecisionEngine.SetInputArray(InputID.InputCurrentTurnRate,Physics.GetCurrentTurnAngle());

        DecisionEngine.SetInputArray(InputID.InputDietaryPreference, Vitals.GetDietaryPreference());
    }
    public void CreatureAction(){
        IncreaseAliveTickCount();
        UpdateCreatureLocation();

        Vision.SetVisionDistance(Vitals.GetCurrentVisionDistance());
        Vision.UpdateLocation(Body.GetHeadSegment());
        Vision.UpdateSightLines();

        SetInputs();

        Actions PreviousAction = GetCurrentAction();

        SetCurrentAction(DecisionEngine.Decision(GetCurrentAction()));

        switch (CreatureAction){
            case NewDestination:
                TargetObject=NewDestination();
                DistanceToTarget= gUtils.DistanceBetweenPoints(Vitals.GetX(),Vitals.GetY(),TargetObject.X(),TargetObject.Y());
                SetCurrentAction(Actions.Move);
                break;
            case Move, MoveToPlant,MoveToMeat:
                Physics.PauseSpeed(false);
                MoveTo(TargetObject.X(),TargetObject.Y());
                break;
            case TargetPlant:
                TargetObject=NearestPlant;
                SetCurrentAction(Actions.MoveToPlant);
                break;
            case TargetMeat:
                TargetObject=NearestMeat;
                CreatureAction=Actions.MoveToMeat;
                break;
            case Eat:
                Physics.PauseSpeed(true);
                if (gWorld.gTicks%GameParameters.TicksBetweenBites==0){
                    Nourishment nourishment=gWorld.gNourishment.get(TargetObject.IdOfObject());
                    float amountBit=Metabolism.Bite(nourishment);
                    nourishment.SetNourishmentMass(nourishment.GetNourishmentMass()-amountBit);
                    gWorld.gNourishment.set(TargetObject.IdOfObject(),nourishment);
                }
                break;
            case SearchForMate:
                CreatureAction=Actions.MateFound;
        }

        //TODO: Determine movement distance and pass it to energy cycle
        Metabolism.SetEnergyUsedBasePerTick(0.0f);
        Metabolism.SetEnergyUsedInGestationPerTick(0.0f);
        Metabolism.SetEnergyUsedInBirthPerTick(0.0f);
        Metabolism.SetEnergyUsedForMovementPerTick(0.0f);
        Metabolism.SetEnergyUsedDuringBirthRecoveryTimePerTick(0.0f);
        Metabolism.EnergyCyclePerTick();

        //TODO: Check health of unborn and determine its state (alive/dead)
        //TODO: If unborn is lost clear pregnant flag and start birthRecoveryTime
        Body.GetHeadSegment().SetSegmentX(Vitals.GetX());
        Body.GetHeadSegment().SetSegmentY(Vitals.GetY());
        Body.GetHeadSegment().SetSegmentAngle(Vitals.GetAngle());
         if (gWorld.gTicks==1){
            Vitals.IncreaseMaturity();
            Body.UpdateBody();
        }
        //Update body mass, speed and turn rate based on it growing.  Otherwise, use the last
        if (Vitals.GetMaturity()<1.0f){
            BodyMass=Body.GetCurrentBodyMass();
            Speed = Physics.DetermineSpeed(Body.GetCurrentBodyMass());
            TurnAngle = Physics.DetermineTurnRate(Body.GetCurrentFlipperWidth(),Body.GetBodySegment(GameParameters.FlippersSegmentConnected).GetSegmentWidth());
            //System.out.println("TurnAngle=" + TurnAngle);
        }

    }

    public void Display(PApplet w,float scale){
       w.stroke(0);

       if (FlagsOverride.ShowObjectsInRangeFlag) {
           w.fill(new Color(128, 128, 128).hashCode());
           w.circle(Body.GetHeadSegment().GetSegmentX(), Body.GetHeadSegment().GetSegmentY(), GameParameters.MaxObjectInRangeRadius * 2);
       }
        BodySegment b=Body.GetMouthSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Mouth){
            b.DisplaySegment(w,scale);
        }
        b=Body.GetFlippersSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Flippers){
            b.DisplaySegment(w,scale);
        }
        for(int i=0;i<Body.GetBodyLength()-1;i++){
            Body.GetBodySegment(i).DisplaySegment(w, scale);
            System.out.println(i + ":" + Body.GetBodySegment(i).GetSegmentAngle());
        }
        b=Body.GetEyesSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Eyes){
            b.DisplaySegment(w,scale);
        }
        b=Body.GetTailSegment();
        if (b !=null && b.BodySegmentType()==SegmentID.Tail){
            b.DisplaySegment(w,scale);
        }

        Vision.Display(w, scale);
        if(TargetObject!=null) {
            w.circle(TargetObject.X(), TargetObject.Y(), 5);
        }
    }
}


//978-601-1791