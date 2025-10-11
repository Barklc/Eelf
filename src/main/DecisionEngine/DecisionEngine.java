package main.DecisionEngine;

import main.Actions;
import main.Creature.*;
import main.Nourishments.Nourishment;

import java.util.ArrayList;

public class DecisionEngine {
    private final Creature CurrentCreature;
    private final CreaturePhysics Physics;
    private final CreatureBody Body;
    private final CreatureVitals Vitals;
    //private final CreatureGeneValues Genes;
    public float InputCreatureX;
    public float InputCreatureY;
    public ObjectInRange InputCreatureTarget;
    public float InputCreatureAngleToTarget;
    public float InputCreatureDistanceToTarget;
    public float InputCreaturePreviousDistanceToTarget;
    public boolean InputVisionDominance;
    public boolean InputScentDominance;
    public ArrayList<Nourishment> InputNourishments;
    public boolean InputIsHungry;
    public boolean InputIsPregnant;
    public boolean InputMouthAtPoint;
    public float InputCreatureCurrentSpeed;
    public ObjectInRange InputNearestPlantInRange;
    public ObjectInRange InputNearestMeatInRange;
    public ObjectInRange InputNearestCreatureInRange;
    public ObjectInRange InputNearestPlantScentInRange;
    public ObjectInRange InputNearestMeatScentInRange;
    public ObjectInRange InputNearestCreatureScentInRange;
    public ArrayList<ObjectInRange> InputAllObjectsInRange;
    public ArrayList<ObjectInRange> InputScentObjectsInRange;
    public ArrayList<ObjectInRange> InputSeenObjectsInRange;
    public float InputAliveTickCount;
    public int InputAge;
    public int InputMaturityAge;
    public int InputSeniorAge;
    public float InputLifeSpan;
    public float InputHealth;
    public float InputCurrentTurnRate;

    private final InputArray inputArray;

    public DecisionEngine(Creature currentCreature) {
        CurrentCreature=currentCreature;
        Physics=CurrentCreature.GetPhysics();
        Body=CurrentCreature.GetBody();
        Vitals=CurrentCreature.GetVitals();
        inputArray=new InputArray();
    }

    public void SetInputArray(int ID,Object value){
        inputArray.SetInputArray(ID,value);
    }

    public void SetInputs(){

        InputCreatureX=(float)inputArray.GetInputArrayValue(InputID.InputCreatureX);
        InputCreatureY=(float)inputArray.GetInputArrayValue(InputID.InputCreatureY);
        InputCreatureTarget=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputCreatureTarget);
        InputCreatureAngleToTarget=(float)inputArray.GetInputArrayValue(InputID.InputCreatureAngleToTarget);
        InputCreatureDistanceToTarget=(float)inputArray.GetInputArrayValue(InputID.InputCreatureDistanceToTarget);
        InputCreaturePreviousDistanceToTarget=(float)inputArray.GetInputArrayValue(InputID.InputCreaturePreviousDistanceToTarget);
        InputVisionDominance=(boolean)inputArray.GetInputArrayValue(InputID.InputVisionDominance);
        InputScentDominance=(boolean)inputArray.GetInputArrayValue(InputID.InputScentDominance);
        InputNourishments=(ArrayList<Nourishment>) inputArray.GetInputArrayValue(InputID.InputNourishments);
        InputIsHungry=(boolean)inputArray.GetInputArrayValue(InputID.InputIsHungry);
        InputIsPregnant=(boolean)inputArray.GetInputArrayValue(InputID.InputIsPregnant);
        InputMouthAtPoint=(boolean)inputArray.GetInputArrayValue(InputID.InputMouthAtPoint);
        InputCreatureCurrentSpeed=(float)inputArray.GetInputArrayValue(InputID.InputCreatureCurrentSpeed);
        InputAllObjectsInRange=(ArrayList<ObjectInRange>)inputArray.GetInputArrayValue(InputID.InputAllObjectsInRange);
        InputScentObjectsInRange=(ArrayList<ObjectInRange>)inputArray.GetInputArrayValue(InputID.InputScentObjectsInRange);
        InputSeenObjectsInRange=(ArrayList<ObjectInRange>)inputArray.GetInputArrayValue(InputID.InputSeenObjectsInRange);
        InputNearestPlantInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestPlantInRange);
        InputNearestMeatInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestMeatInRange);
        InputNearestCreatureInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestCreatureInRange);
        InputNearestPlantScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestPlantScentInRange);
        InputNearestMeatScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestMeatScentInRange);
        InputNearestCreatureScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestCreatureScentInRange);
        InputAliveTickCount=(int)inputArray.GetInputArrayValue(InputID.InputAliveTickCount);
        InputAge=(int)inputArray.GetInputArrayValue(InputID.InputAge);
        InputMaturityAge=(int)inputArray.GetInputArrayValue(InputID.InputMaturityAge);
        InputSeniorAge=(int)inputArray.GetInputArrayValue(InputID.InputSeniorAge);
        InputLifeSpan=(float)inputArray.GetInputArrayValue(InputID.InputLifeSpan);
        InputHealth=(float)inputArray.GetInputArrayValue(InputID.InputHealth);
        InputCurrentTurnRate=(float)inputArray.GetInputArrayValue(InputID.InputCurrentTurnRate);
    }
    private void ClearInputs(){
        inputArray.ClearInputs();
    }

    public Actions Decision(Actions previous){
        if (inputArray==null){
            return Actions.Move;
        }

        SetInputs();
        boolean VisionPresent=Body.GetEyesPresent() && InputVisionDominance;
        boolean ScentPresent=InputScentDominance;

        System.out.println("Decision.Decision:" + previous);
        if (InputCreatureTarget==null){
            System.out.println("New Destination - Target Object Null");
            return Actions.NewDestination;
        }

        if (previous==Actions.Eat && InputNourishments.get(InputCreatureTarget.IdOfObject()).GetNourishmentMass()<1){
            return Actions.Move;
        }

        if (InputIsHungry && previous==Actions.Eat){
            return Actions.Eat;
        }

        if (Vitals.IsHungry() &&
                previous!=Actions.MoveToPlant && previous!=Actions.MoveToMeat &&

                InputNourishments.get(InputCreatureTarget.IdOfObject()).GetNourishmentMass()>1) {
            if (VisionPresent) {
                if (InputNearestMeatInRange != null) {
                    return Actions.TargetMeat;
                }
                if (InputNearestPlantInRange != null) {
                    return Actions.TargetPlant;
                }
            }
            if (ScentPresent) {
                if (InputNearestMeatScentInRange != null) {
                    return Actions.TargetMeatScent;
                }
                if (InputNearestPlantScentInRange != null) {
                    return Actions.TargetPlantScent;
                }
            }
        }

        if (previous==Actions.MoveToPlant && InputMouthAtPoint){
            return Actions.Eat;
        }

        if ((previous==Actions.Move) && InputMouthAtPoint){
            System.out.println("New Destination - MouthAtPoint");
            return Actions.NewDestination;
        }

        if (previous==Actions.Move && InputCreatureAngleToTarget>359.0f) {
            System.out.println("New Destination - Angle " + CurrentCreature.GetAngleToTarget(CurrentCreature.GetTargetObject().X(),CurrentCreature.GetTargetObject().Y()) + " ");
            return Actions.NewDestination;
        }

        //System.out.println((CurrentCreature.GetAngleToTarget(CurrentCreature.GetTargetObject().X(),CurrentCreature.GetTargetObject().Y())>Physics.GetCurrentTurnAngle()) + "=" +
        //        CurrentCreature.GetAngleToTarget(CurrentCreature.GetTargetObject().X(),CurrentCreature.GetTargetObject().Y()) + " " + Physics.GetCurrentTurnAngle());
        if (previous==Actions.Move &&
           (InputCreaturePreviousDistanceToTarget>InputCreatureDistanceToTarget-InputCreatureCurrentSpeed &&
                InputCreaturePreviousDistanceToTarget<InputCreatureDistanceToTarget) &&
                InputCreatureAngleToTarget>InputCurrentTurnRate){
                System.out.println("New Destination - Distance Equal " + InputCreaturePreviousDistanceToTarget + " " + InputCreatureDistanceToTarget + " ");
                return Actions.NewDestination;
        }

        ClearInputs();
        return Actions.Move;
    }
}
