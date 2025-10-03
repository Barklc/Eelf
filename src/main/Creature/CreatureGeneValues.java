package main.Creature;
import main.Constants;
import main.Genetics.Genome;
import main.Genetics.GeneID;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;

public class CreatureGeneValues{

    //variables set initial by genes
    private final float biteStrength;
    private final float birthRecoveryTime;
    private final float birthGestationEnergyCost;
    private final float birthEnergyCost;
    private final int bodyColorBlue;
    private final int bodyColorGreen;
    private final int bodyColorRed;
    private final int bodyLength;
    private float bodyWidth;
    private final float bodyTaper;
    private final float bodyDistanceBetweenSegments;
    private final float digestionRate;
    private final Genome dna;
    private final int eyeColorBlue;
    private final int eyeColorGreen;
    private final int eyeColorRed;
    private final float eyeSize;
    private final float eyesPresent;
    private final float plantToEnergyConversionRate;
    private final float meatToEnergyConversionRate;
    private final float gestationPeriod;
    private final float bodyHeight;
    private final float headShape;
    private final float lifeSpan;
    private final float matureAgePercentage;
    private final float maxTurnAngle;
    private final int mouthColorBlue;
    private final int mouthColorGreen;
    private final int mouthColorRed;
    private final float mouthSize;
    private final float mouthPresent;
    private final float seniorAgePercentage;
    private final float skinToughness;
    private final float speed;
    private final float stomachSize;
    private final float maxStoredEnergy;
    private final float visionAngle;
    private final float visionClarity;
    private final float visionDistance;
    private final float visionScanFreq;
    private final float flipperHeight;
    private final float flipperWidth;
    private final float flipperPresent;
    private final float tailHeight;
    private final float tailWidth;
    private final float tailPresent;
    private final int flipperColorBlue;
    private final int flipperColorGreen;
    private final int flipperColorRed;
    private final int tailColorBlue;
    private final int tailColorGreen;
    private final int tailColorRed;
    private final float receptorSensitivity;
    private final float maxHealth;
    private final float healthIncreasePercentage;

    public CreatureGeneValues(Genome genome){

        dna=genome;
        bodyLength = (int) Math.floor(Constants.BodyLengthMin + (dna.GetGene(GeneID.BodyLength) * (Constants.BodyLengthMax-Constants.BodyLengthMin)));
        bodyHeight = Constants.BodyHeightMin + (dna.GetGene(GeneID.BodyHeight) * (Constants.BodyHeightMax-Constants.BodyHeightMin));
        biteStrength= Constants.BiteStrengthMin + (dna.GetGene(GeneID.BiteStrength) * (Constants.BiteStrengthMax - Constants.BiteStrengthMin));
        birthRecoveryTime=Constants.BirthRecoveryMin + (dna.GetGene(GeneID.BirthRecoveryTime) * (Constants.BirthRecoveryMax-Constants.BirthRecoveryMin));
        birthEnergyCost=Constants.BirthEnergyCostMin + (dna.GetGene(GeneID.BirthEnergyCost) * (Constants.BirthEnergyCostMax-Constants.BirthEnergyCostMin));
        birthGestationEnergyCost=Constants.BirthGestationEnergyCostMin + (dna.GetGene(GeneID.BirthGestationEnergyCost) * (Constants.BirthGestationEnergyCostMax-Constants.BirthGestationEnergyCostMin));
        bodyWidth=Constants.BodyWidthMin + (dna.GetGene(GeneID.BodyWidth) * (Constants.BodyWidthMax-Constants.BodyWidthMin));
        bodyTaper=Constants.BodyTapperMin + (dna.GetGene(GeneID.BodyTaper) * (Constants.BodyTapperMax-Constants.BodyTapperMin));
        flipperPresent=dna.GetGene(GeneID.FlipperPresent);
        flipperHeight=Constants.FlipperHeightMin + (dna.GetGene(GeneID.FlipperHeight)* (Constants.FlipperHeightMax-Constants.FlipperHeightMin));
        flipperWidth=Constants.FlipperWidthMin + (dna.GetGene(GeneID.FlipperWidth)* (Constants.FlipperWidthMax-Constants.FlipperWidthMin));
        tailPresent=dna.GetGene(GeneID.TailPresent);
        tailHeight=Constants.TailHeightMin + (dna.GetGene(GeneID.TailHeight)* (Constants.TailHeightMax-Constants.TailHeightMin));
        tailWidth=Constants.TailWidthMin + (dna.GetGene(GeneID.TailWidth)* (Constants.TailWidthMax-Constants.TailWidthMin));
        bodyColorRed=(int) Math.floor(dna.GetGene(GeneID.BodyColorRed));
        bodyColorGreen=(int) Math.floor(dna.GetGene(GeneID.BodyColorGreen));
        bodyColorBlue=(int) Math.floor(dna.GetGene(GeneID.BodyColorBlue));
        flipperColorRed=(int) Math.floor(dna.GetGene(GeneID.FlipperColorRed));
        flipperColorGreen=(int) Math.floor(dna.GetGene(GeneID.FlipperColorGreen));
        flipperColorBlue=(int) Math.floor(dna.GetGene(GeneID.FlipperColorBlue));
        tailColorRed=(int) Math.floor(dna.GetGene(GeneID.TailColorRed));
        tailColorGreen=(int) Math.floor(dna.GetGene(GeneID.TailColorGreen));
        tailColorBlue=(int) Math.floor(dna.GetGene(GeneID.TailColorBlue));
        digestionRate=dna.GetGene(GeneID.DigestionRate);
        eyeColorRed=(int) Math.floor(dna.GetGene(GeneID.EyeColorRed));
        eyeColorGreen=(int) Math.floor(dna.GetGene(GeneID.EyeColorGreen));
        eyeColorBlue=(int) Math.floor(dna.GetGene(GeneID.EyeColorBlue));
        eyeSize=Constants.EyeSizeMin + (dna.GetGene(GeneID.EyeSize) * (Constants.EyeSizeMax-Constants.EyeSizeMin));
        eyesPresent=dna.GetGene(GeneID.EyesPresent);
        plantToEnergyConversionRate=dna.GetGene(GeneID.PlantToEnergyConversionRate);
        meatToEnergyConversionRate=dna.GetGene(GeneID.MeatToEnergyConversionRate);
        gestationPeriod=Constants.GestationPeriodMin + (dna.GetGene(GeneID.GestationPeriod) * (Constants.GestationPeriodMax-Constants.GestationPeriodMin));
        headShape=dna.GetGene(GeneID.HeadShape);
        lifeSpan = Constants.LifeSpanMax + (dna.GetGene(GeneID.LifeSpan) * (Constants.LifeSpanMax-Constants.LifeSpanMin));
        matureAgePercentage=dna.GetGene(GeneID.MatureAgePercentage);
        maxTurnAngle = dna.GetGene(GeneID.MaxTurnAngle);
        mouthColorRed=(int) Math.floor(dna.GetGene(GeneID.MouthColorRed));
        mouthColorGreen=(int) Math.floor(dna.GetGene(GeneID.MouthColorGreen));
        mouthColorBlue= (int) Math.floor(dna.GetGene(GeneID.MouthColorBlue));
        mouthSize = bodyHeight*(dna.GetGene(GeneID.MouthSize));
        mouthPresent =dna.GetGene(GeneID.MouthPresent);
        seniorAgePercentage=dna.GetGene(GeneID.SeniorAgePercentage);
        skinToughness=Constants.SkinToughnessMin + (dna.GetGene(GeneID.SkinToughness) * (Constants.SkinToughnessMax-Constants.SkinToughnessMin));
        speed = dna.GetGene(GeneID.MovementSpeed);
        stomachSize=Constants.StomachSizeMin + (dna.GetGene(GeneID.StomachSize) * (Constants.StomachSizeMax-Constants.StomachSizeMin));
        maxStoredEnergy=Constants.EnergyStorageMin + (dna.GetGene(GeneID.MaxStoredEnergy) *(Constants.EnergyStorageMax-Constants.EnergyStorageMin));
        float temp=dna.GetGene(GeneID.VisionAngle);
        visionAngle=Constants.VisionAngleMin + (dna.GetGene(GeneID.VisionAngle) * (Constants.VisionAngleMax-Constants.VisionAngleMin));
        visionClarity=dna.GetGene(GeneID.VisionClarity);
        visionDistance=Constants.VisionDistanceMin + (dna.GetGene(GeneID.VisionDistance) * (Constants.VisionDistanceMax-Constants.VisionDistanceMin));
        visionScanFreq = Constants.VisionScanFreqMin + (dna.GetGene(GeneID.VisionScanFreq) * (Constants.VisionScanFreqMax-Constants.VisionScanFreqMin));
        bodyDistanceBetweenSegments=bodyHeight*dna.GetGene(GeneID.BodyDistanceBetweenSegments);
        receptorSensitivity=dna.GetGene(GeneID.ReceptorsSensitivity);
        maxHealth=Constants.HealthMin+(dna.GetGene(GeneID.MaxHealth)*(Constants.HealthMax-Constants.HealthMin));
        healthIncreasePercentage=dna.GetGene(GeneID.IncreaseHealthPercentage);
    }

    public Genome GetBaseDNA(){
        return dna;
    }

    public float GetBodyLength(){
        return bodyLength;
    }

    public float GetBodyHeight(){
        return bodyHeight;
    }

    public float GetHeadShape(){
        return headShape;
    }

    public float GetBodyWidth(){
        if (bodyWidth<0) bodyWidth=0;
        return bodyHeight+bodyWidth;
    }

    public float GetBodyTaper(){
        return bodyTaper;
    }

    public float GetBodyDistanceBetweenSegments(){
        return bodyDistanceBetweenSegments;
    }

    public float GetFlipperPresent(){
        return flipperPresent;
    }

    public float GetFlipperHeight(){
        return flipperHeight;
    }

    public float GetFlipperWidth(){
        return flipperWidth;
    }

    public Color GetFlipperColor(){
        return new Color(flipperColorRed,flipperColorGreen,flipperColorBlue);
    }

    public ArrayList<Integer> GetFlipperColorRGB(){
        ArrayList<Integer> flipperColor=new ArrayList<Integer>();
        flipperColor.add(flipperColorRed);
        flipperColor.add(flipperColorGreen);
        flipperColor.add(flipperColorBlue);
        return flipperColor;
    }

    public float GetTailPresent(){return tailPresent;
    }

    public float GetTailHeight(){
        return tailHeight;
    }

    public float GetTailWidth(){
        return tailWidth;
    }

    public Color GetTailColor(){
        return new Color(tailColorRed,tailColorGreen,tailColorBlue);
    }

    public ArrayList<Integer> GetTailColorRGB(){
        ArrayList<Integer> tailColor=new ArrayList<Integer>();
        tailColor.add(tailColorRed);
        tailColor.add(tailColorGreen);
        tailColor.add(tailColorBlue);
        return tailColor;
    }

    public float GetVisionAngle(){
        return visionAngle;
    }

    public float GetVisionClarity(){
        return visionClarity;
    }

    public float GetVisionDistance(){
        return visionDistance;
    }

    public Color GetEyeColor(){
        return new Color(eyeColorRed,eyeColorGreen,eyeColorBlue);
    }

    public ArrayList<Integer> GetEyeColorRGB(){
        ArrayList<Integer> eyeColor=new ArrayList<Integer>();
        eyeColor.add(eyeColorRed);
        eyeColor.add(eyeColorGreen);
        eyeColor.add(eyeColorBlue);
        return eyeColor;
    }

    public float GetEyeSize(){
        return eyeSize;
    }

    public float GetEyesPresent(){
        return eyesPresent;
    }

    public float GetSpeed(){
        return speed;
    }

    public Color GetBodyColor(){
        return new Color(bodyColorRed,bodyColorGreen,bodyColorBlue);
    }

    public ArrayList<Integer> GetBodyColorRGB(){
        ArrayList<Integer> bodyColor=new ArrayList<Integer>();
        bodyColor.add(bodyColorRed);
        bodyColor.add(bodyColorGreen);
        bodyColor.add(bodyColorBlue);
        return bodyColor;
    }

    public float GetVisionScanFreq(){
        return visionScanFreq;
    }

    public float GetMouthSize(){
        return mouthSize;
    }

    public Color GetMouthColor(){
        return new Color(mouthColorRed,mouthColorGreen,mouthColorBlue);
    }

    public ArrayList<Integer> GetMouthColorRGB(){
        ArrayList<Integer> mouthColor=new ArrayList<Integer>();
        mouthColor.add(mouthColorRed);
        mouthColor.add(mouthColorGreen);
        mouthColor.add(mouthColorBlue);
        return mouthColor;
    }

    public float GetBiteStrength(){
        return biteStrength;
    }

    public float GetMouthPresent(){
        return mouthPresent;
    }

    public float GetMaxTurnAngle(){
        return maxTurnAngle;
    }

    public float GetLifeSpan(){
        return lifeSpan;
    }

    public float GetSkinToughness(){
        return skinToughness;
    }

    public float GetSeniorAgePercentage(){
        return seniorAgePercentage;
    }

    public float GetMatureAgePercentage(){
        return matureAgePercentage;
    }

    public float GetStomachSize(){
        return stomachSize;
    }

    public float GetMaxStoredEnergy(){
        return maxStoredEnergy;
    }

    public float GetDigestionRate(){
        return digestionRate;
    }

    public float GetPlantToEnergyConversionRate(){
        return plantToEnergyConversionRate;
    }
    public float GetMeatToEnergyConversionRate(){return meatToEnergyConversionRate;
    }

    public float GetBirthGestationEnergyCost(){
        return birthGestationEnergyCost;
    }

    public float GetBirthRecoveryTime(){return birthRecoveryTime;}

    public float GetBirthEnergyCost(){
        return birthEnergyCost;
    }

    public float GetGestationPeriod(){
        return gestationPeriod;
    }

    public float GetReceptorSensitivity(){
        return receptorSensitivity;
    }

    public float GetMaxHealth() {return maxHealth;}

    public float GetIncreaseHealthPercentage() {return healthIncreasePercentage;}

    //public String ExportDNA(){
    //  String newdna="";
    //  for(int i=0;i<dna.size()-1;i++){
    //      BigDecimal bd=new BigDecimal(dna.get(i));
    //      if (i==0){
    //        newdna+=String.valueOf(bd.setScale(2,RoundingMode.HALF_UP));
    //      } else {
    //        newdna+= "," + String.valueOf(bd.setScale(2,RoundingMode.HALF_UP));
    //      }
    //  }
    //  return newdna;
    //}
}
