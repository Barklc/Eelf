package main.Creature;
import main.FlagsOverride;
import main.Creature.BodySegments.BodySegment;
import main.GameParameters;
import main.GeneMinMax;
import main.Genetics.GeneID;

import static main.Main.gUtils;

public class CreaturePhysics{
    private final CreatureBody Body;
    private final CreatureGeneValues Genes;
    private float BaseSpeed;
    private final float MaxTurnAngle;
    private float TurnAngle;
    private final float TailWidthPercentage;
    private float TailSpeedMod;
    private final float MassMax;
    private float MassSpeedMod;
    private final float FlipperWidthMax;
    private float FlipperTurnAngleMod;
    private final float SegmentWidthMax;
    private float CurrentSpeed;
    private float CurrentTurnAngle;
    private boolean PauseSpeed;

    public CreaturePhysics(Creature currentCreature){
        Genes = currentCreature.GetGenes();
        Body = currentCreature.GetBody();
        MassMax=195;
        MaxTurnAngle= Genes.GetBaseDNA().GetGeneDef(GeneID.MaxTurnAngle).Maximum();
        TailWidthPercentage=Genes.GetTailWidthPercentage();
        FlipperWidthMax= Genes.GetBaseDNA().GetGeneDef(GeneID.FlipperWidth).Maximum(); //GetGeneValueMax(GeneID.FlipperWidth);
        SegmentWidthMax= Genes.GetBaseDNA().GetGeneDef(GeneID.BodyWidth).Maximum();
        PauseSpeed=false;
    }

    public void SetBaseSpeed(float s){
        BaseSpeed=s;
    }

    public float GetBaseSpeed(){
        return BaseSpeed;
    }

    public void SetBaseTurnRate(float tr){
        TurnAngle=tr;
    }

    public float GetBaseTurnRate(){
        return TurnAngle;
    }

    public float DetermineSpeed(float Mass){
        if (PauseSpeed){
            return 0;
        }
        if(TailWidthPercentage>= GameParameters.TailThresholdForSpeedMod){
            TailSpeedMod=GeneMinMax.TailSpeedModMinPercentage + (Body.GetCurrentTailWidthPercentage() * (GeneMinMax.TailSpeedModMaxPercentage-GeneMinMax.TailSpeedModMinPercentage));
            System.out.println("GetCurrentTailWidthPercentage=" + Body.GetCurrentTailWidthPercentage());
            System.out.println("TailSpeedMod=" + TailSpeedMod);
        }
        if(true){
            MassSpeedMod=1f-(Mass/MassMax);
        }
        CurrentSpeed= BaseSpeed + TailSpeedMod;// + MassSpeedMod;
        return CurrentSpeed;
    }

    public float DetermineTurnRate(float FlipperWidth,float SegmentWidth){
        if (FlipperWidth>=FlipperWidthMax/2){
            FlipperTurnAngleMod=.01f*((FlipperWidth/2f)/(FlipperWidthMax/2f)-1f);
        }
        float segmentWidthTurnAngleMod = .01f * (1f - (SegmentWidth / SegmentWidthMax));

        float actualTurnAngle = TurnAngle + FlipperTurnAngleMod + segmentWidthTurnAngleMod;

        if (actualTurnAngle >MaxTurnAngle){
            actualTurnAngle =MaxTurnAngle;
        }
        //println(FlipperWidth + "=" + FlipperWidthMax/2 + " == " + (FlipperWidth/2)/(FlipperWidthMax/2));
        //println("CreaturePhysics.DetermineTurnRate - FlipperTurnAngleMod: " + FlipperTurnAngleMod);
        //println("CreaturePhysics.DetermineTurnRate - SegmentWidthTurnAngleMod: " + SegmentWidthTurnAngleMod);
        //println("CreaturePhysics.DetermineTurnRate - ActualTurnAngle: " + ActualTurnAngle);
        if (FlagsOverride.TurnRateOverride){actualTurnAngle=0.025f;}
        CurrentTurnAngle=actualTurnAngle;
        return actualTurnAngle;
    }

    public float GetCurrentSpeed(){
        if (PauseSpeed) {
            return 0;
        }
        return CurrentSpeed;
    }

    public float GetCurrentTurnAngle(){
        return CurrentTurnAngle;
    }

    public boolean MouthAtPoint(float nx,float ny,float offset){
        boolean result=false;
        BodySegment mouth=Body.GetMouthSegment();
        if (gUtils.Overlaps(mouth.GetSegmentX(),mouth.GetSegmentY(),Body.GetCurrentMouthSize(),nx,ny,offset)){
            result=true;
        }
        return result;
    }

    public void PauseSpeed(boolean value){
        PauseSpeed=value;
    }

    public float GetTailSpeedMod(){
        return  GeneMinMax.TailSpeedModMinPercentage + (Genes.GetTailWidthPercentage() * (GeneMinMax.TailSpeedModMaxPercentage-GeneMinMax.TailSpeedModMinPercentage));
    }

    public float GetCurrentTailSpeedMod(){
        return TailSpeedMod;
    }
}
