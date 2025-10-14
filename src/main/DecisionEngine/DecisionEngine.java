package main.DecisionEngine;

import main.Actions;
import main.Creature.*;
import main.Nourishments.DietaryPreference;
import main.Nourishments.Nourishment;
import main.Nourishments.NourishmentTypes;

import java.util.ArrayList;

public class DecisionEngine {
    private final Creature CurrentCreature;
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
    public ObjectInRange InputClosestSeenNourishmentInRange;
    public ObjectInRange InputNearestPlantScentInRange;
    public ObjectInRange InputNearestMeatScentInRange;
    public ObjectInRange InputNearestCreatureScentInRange;
    public ObjectInRange InputClosestScentNourishmentInRange;
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
    public DietaryPreference InputDietaryPreference;

    private final InputArray inputArray;

    public DecisionEngine(Creature currentCreature) {
        CurrentCreature=currentCreature;
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
        InputClosestSeenNourishmentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputClosestSeenNourishmentInRange);
        InputNearestPlantScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestPlantScentInRange);
        InputNearestMeatScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestMeatScentInRange);
        InputNearestCreatureScentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputNearestCreatureScentInRange);
        InputClosestScentNourishmentInRange=(ObjectInRange)inputArray.GetInputArrayValue(InputID.InputClosestSmeltNourishmentInRange);
        InputAliveTickCount=(int)inputArray.GetInputArrayValue(InputID.InputAliveTickCount);
        InputAge=(int)inputArray.GetInputArrayValue(InputID.InputAge);
        InputMaturityAge=(int)inputArray.GetInputArrayValue(InputID.InputMaturityAge);
        InputSeniorAge=(int)inputArray.GetInputArrayValue(InputID.InputSeniorAge);
        InputLifeSpan=(float)inputArray.GetInputArrayValue(InputID.InputLifeSpan);
        InputHealth=(float)inputArray.GetInputArrayValue(InputID.InputHealth);
        InputCurrentTurnRate=(float)inputArray.GetInputArrayValue(InputID.InputCurrentTurnRate);
        InputDietaryPreference=(DietaryPreference)inputArray.GetInputArrayValue(InputID.InputDietaryPreference);
    }
    private void ClearInputs(){
        inputArray.ClearInputs();
    }

    private  Actions CheckInputArrayIsNotNull(Actions previous){
        if (inputArray==null){
            return Actions.Move;
        }
        return previous;
    }

    private Actions IfHungryDecision(Actions previous){
        //If creature is hungry
        //  if previous action was eat
        //      if nourishment being eaten is below minimum mass
        //          Have the creature move
        //      if nourishment being eaten is above minimum mass
        //          Have the creature eat
        //  if previous action was MoveToPlant and creature is herbivore or omnivore and nourishment type is plant
        //     if InputMouthAtTarget
        //          Have the creature eat
        //  if previous action was MoveToMeat and creature is carnivore or omnivore and nourishment type is meat
        //     if InputMouthAtTarget
        //          Have the creature eat
        //  if previous action was NOT MoveToPlant or MoveToMeat
        //      if targeted nourishment mass is above minimum
        //          if Vision is dominant
        //              if DietaryPreference is Herbivore or Omnivore
        //                  Have creature TargetPlant
        //              if DietaryPreference is Carnivore or Omnivore
        //                  Have creature TargetMeat
        //          if Scent is dominant
        //              if DietaryPreference is Herbivore or Omnivore
        //                  Have creature TargetPlantScent
        //              if DietaryPreference is Carnivore or Omnivore
        //                  Have creature TargetMeatScent
        if (previous==Actions.Eat) {
            if (InputNourishments.get(InputCreatureTarget.IdOfObject()).GetNourishmentMass() < 1) {
                return Actions.NewDestination;
            }
            if (InputNourishments.get(InputCreatureTarget.IdOfObject()).GetNourishmentMass() > 1) {
                return Actions.Eat;
            }
        }
        if (previous==Actions.MoveToPlant &&
                (InputDietaryPreference==DietaryPreference.Herbivore || InputDietaryPreference==DietaryPreference.Omnivore) &&
                InputNourishments.get(InputCreatureTarget.IdOfObject()).NourishmentType()==NourishmentTypes.Plant &&
                InputMouthAtPoint){
            return Actions.Eat;
        }
        if (previous==Actions.MoveToMeat &&
                (InputDietaryPreference==DietaryPreference.Carnivore || InputDietaryPreference==DietaryPreference.Omnivore) &&
                InputNourishments.get(InputCreatureTarget.IdOfObject()).NourishmentType()==NourishmentTypes.Meat &&
                InputMouthAtPoint){
            return Actions.Eat;
        }
        if (previous==Actions.Move){
            if (InputVisionDominance){
                if (InputNearestPlantInRange!=null && (InputDietaryPreference==DietaryPreference.Herbivore || InputDietaryPreference==DietaryPreference.Omnivore)){
                    return Actions.TargetPlant;
                }
                if (InputNearestMeatInRange!=null && (InputDietaryPreference==DietaryPreference.Carnivore || InputDietaryPreference==DietaryPreference.Omnivore)){
                    return Actions.TargetMeat;
                }
            }
            if (InputScentDominance){
                if (InputDietaryPreference==DietaryPreference.Herbivore || InputDietaryPreference==DietaryPreference.Omnivore){
                    return Actions.TargetPlantScent;
                }
                if (InputDietaryPreference==DietaryPreference.Carnivore|| InputDietaryPreference==DietaryPreference.Omnivore){
                    return Actions.TargetMeatScent;
                }
            }

        }
        return Actions.None;
    }

    private Actions MoveDecision(Actions previous){
        // If previous action was Move
        //      if InputMouthAtPoint
        //          Have creature determine new destination
        //      NOTE: Is this needed???
        //      if InputCreatureAngleToTarget>359.0f
        //          Have creature determine new destination
        //      NOTE: Is this working????
        //      if InputCreaturePreviousDistanceToTarget>InputCreatureDistanceToTarget-InputCreatureCurrentSpeed &&
        //                InputCreaturePreviousDistanceToTarget<InputCreatureDistanceToTarget) &&
        //                InputCreatureAngleToTarget>InputCurrentTurnRate
        //          Have creature determine new destination
        if (previous==Actions.Move){
            if (InputMouthAtPoint){
                System.out.println("New Destination - MouthAtPoint");
                return Actions.NewDestination;
            }
            if (InputCreatureAngleToTarget>359.0f) {
                System.out.println("New Destination - Angle " + CurrentCreature.GetAngleToTarget(CurrentCreature.GetTargetObject().X(),CurrentCreature.GetTargetObject().Y()) + " ");
                return Actions.NewDestination;
            }
            if ((InputCreaturePreviousDistanceToTarget>InputCreatureDistanceToTarget-InputCreatureCurrentSpeed &&
                    InputCreaturePreviousDistanceToTarget<InputCreatureDistanceToTarget) &&
                    InputCreatureAngleToTarget>InputCurrentTurnRate){
                System.out.println("New Destination - Distance Equal " + InputCreaturePreviousDistanceToTarget + " " + InputCreatureDistanceToTarget + " ");
                return Actions.NewDestination;
            }
        }
        return Actions.None;
    }

    public Actions Decision(Actions previous){
        if (inputArray==null){
            return Actions.Move;
        }

        SetInputs();
        System.out.println("Decision.Decision:" + previous);
        boolean VisionPresent=Body.GetEyesPresent() && InputVisionDominance;
        boolean ScentPresent=InputScentDominance;

        //Check to see if previous was set to NewDestination but was not fulfilled.  If so, resend it.
        if (previous==Actions.NewDestination){
            return Actions.NewDestination;
        }

        //Check to see that creature has a target to move toward. If not, request a new one.
        if (InputCreatureTarget==null){
            System.out.println("New Destination - Target Object Null");
            return Actions.NewDestination;
        }

        //Check to see if creature is hungry and determine what action it should take.
        if (Vitals.IsHungry()){
            Actions returnedAction=IfHungryDecision(previous);
            if (returnedAction!=Actions.None){return returnedAction;}
        }

        //Check to see what move action should be taken
        Actions returnAction=MoveDecision(previous);
        if (returnAction!=Actions.None){return returnAction;}

        // if creature is ReadyToMate
        //      NOTE: For now make this a single parent birth
        //      if previous action was not SearchForMate or MateFound
        //          Have creature search for mate
        //      if previous action was SearchForMate and a Mate is Found
        //          Have creature conceived

        // If creature is Pregnant

        ClearInputs();
        return Actions.Move;
    }
}
