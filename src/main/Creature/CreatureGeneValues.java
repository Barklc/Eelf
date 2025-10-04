package main.Creature;
import main.GeneMinMax;
import main.Genetics.Genome;
import main.Genetics.GeneID;

import java.awt.*;
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
    private final float tailHeightPercentage;
    private final float tailWidthPercentage;
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
        bodyLength = (int) Math.floor(GeneMinMax.BodyLengthMin + (dna.GetGene(GeneID.BodyLength) * (GeneMinMax.BodyLengthMax-GeneMinMax.BodyLengthMin)));
        bodyHeight = GeneMinMax.BodyHeightMin + (dna.GetGene(GeneID.BodyHeight) * (GeneMinMax.BodyHeightMax-GeneMinMax.BodyHeightMin));
        biteStrength= GeneMinMax.BiteStrengthMin + (dna.GetGene(GeneID.BiteStrength) * (GeneMinMax.BiteStrengthMax - GeneMinMax.BiteStrengthMin));
        birthRecoveryTime=GeneMinMax.BirthRecoveryMin + (dna.GetGene(GeneID.BirthRecoveryTime) * (GeneMinMax.BirthRecoveryMax-GeneMinMax.BirthRecoveryMin));
        birthEnergyCost=GeneMinMax.BirthEnergyCostMin + (dna.GetGene(GeneID.BirthEnergyCost) * (GeneMinMax.BirthEnergyCostMax-GeneMinMax.BirthEnergyCostMin));
        birthGestationEnergyCost=GeneMinMax.BirthGestationEnergyCostMin + (dna.GetGene(GeneID.BirthGestationEnergyCost) * (GeneMinMax.BirthGestationEnergyCostMax-GeneMinMax.BirthGestationEnergyCostMin));
        bodyWidth=GeneMinMax.BodyWidthMin + (dna.GetGene(GeneID.BodyWidth) * (GeneMinMax.BodyWidthMax-GeneMinMax.BodyWidthMin));
        bodyTaper=GeneMinMax.BodyTapperMin + (dna.GetGene(GeneID.BodyTaper) * (GeneMinMax.BodyTapperMax-GeneMinMax.BodyTapperMin));
        flipperPresent=dna.GetGene(GeneID.FlipperPresent);
        flipperHeight=GeneMinMax.FlipperHeightMin + (dna.GetGene(GeneID.FlipperHeight)* (GeneMinMax.FlipperHeightMax-GeneMinMax.FlipperHeightMin));
        flipperWidth=GeneMinMax.FlipperWidthMin + (dna.GetGene(GeneID.FlipperWidth)* (GeneMinMax.FlipperWidthMax-GeneMinMax.FlipperWidthMin));
        tailPresent=dna.GetGene(GeneID.TailPresent);
        tailHeightPercentage=GeneMinMax.TailHeightMinPercentage + (dna.GetGene(GeneID.TailHeightPercentage) * (GeneMinMax.TailHeightMaxPercentage-GeneMinMax.TailHeightMinPercentage));
        tailWidthPercentage=GeneMinMax.TailWidthMinPercentage + (dna.GetGene(GeneID.TailWidthPercentage)* (GeneMinMax.TailWidthMaxPercentage-GeneMinMax.TailWidthMinPercentage));
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
        eyeSize=GeneMinMax.EyeSizeMin + (dna.GetGene(GeneID.EyeSize) * (GeneMinMax.EyeSizeMax-GeneMinMax.EyeSizeMin));
        eyesPresent=dna.GetGene(GeneID.EyesPresent);
        plantToEnergyConversionRate=dna.GetGene(GeneID.PlantToEnergyConversionRate);
        meatToEnergyConversionRate=dna.GetGene(GeneID.MeatToEnergyConversionRate);
        gestationPeriod=GeneMinMax.GestationPeriodMin + (dna.GetGene(GeneID.GestationPeriod) * (GeneMinMax.GestationPeriodMax-GeneMinMax.GestationPeriodMin));
        headShape=dna.GetGene(GeneID.HeadShape);
        lifeSpan = GeneMinMax.LifeSpanMax + (dna.GetGene(GeneID.LifeSpan) * (GeneMinMax.LifeSpanMax-GeneMinMax.LifeSpanMin));
        matureAgePercentage=dna.GetGene(GeneID.MatureAgePercentage);
        maxTurnAngle = dna.GetGene(GeneID.MaxTurnAngle);
        mouthColorRed=(int) Math.floor(dna.GetGene(GeneID.MouthColorRed));
        mouthColorGreen=(int) Math.floor(dna.GetGene(GeneID.MouthColorGreen));
        mouthColorBlue= (int) Math.floor(dna.GetGene(GeneID.MouthColorBlue));
        mouthSize = bodyHeight*(dna.GetGene(GeneID.MouthSize));
        mouthPresent =dna.GetGene(GeneID.MouthPresent);
        seniorAgePercentage=dna.GetGene(GeneID.SeniorAgePercentage);
        skinToughness=GeneMinMax.SkinToughnessMin + (dna.GetGene(GeneID.SkinToughness) * (GeneMinMax.SkinToughnessMax-GeneMinMax.SkinToughnessMin));
        speed = dna.GetGene(GeneID.MovementSpeed);
        stomachSize=GeneMinMax.StomachSizeMin + (dna.GetGene(GeneID.StomachSize) * (GeneMinMax.StomachSizeMax-GeneMinMax.StomachSizeMin));
        maxStoredEnergy=GeneMinMax.EnergyStorageMin + (dna.GetGene(GeneID.MaxStoredEnergy) *(GeneMinMax.EnergyStorageMax-GeneMinMax.EnergyStorageMin));
        visionAngle=GeneMinMax.VisionAngleMin + (dna.GetGene(GeneID.VisionAngle) * (GeneMinMax.VisionAngleMax-GeneMinMax.VisionAngleMin));
        visionClarity=dna.GetGene(GeneID.VisionClarity);
        visionDistance=GeneMinMax.VisionDistanceMin + (dna.GetGene(GeneID.VisionDistance) * (GeneMinMax.VisionDistanceMax-GeneMinMax.VisionDistanceMin));
        visionScanFreq = GeneMinMax.VisionScanFreqMin + (dna.GetGene(GeneID.VisionScanFreq) * (GeneMinMax.VisionScanFreqMax-GeneMinMax.VisionScanFreqMin));
        bodyDistanceBetweenSegments=bodyHeight*dna.GetGene(GeneID.BodyDistanceBetweenSegments);
        receptorSensitivity=dna.GetGene(GeneID.ReceptorsSensitivity);
        maxHealth=GeneMinMax.HealthMin+(dna.GetGene(GeneID.MaxHealth)*(GeneMinMax.HealthMax-GeneMinMax.HealthMin));
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
        ArrayList<Integer> flipperColor=new ArrayList<>();
        flipperColor.add(flipperColorRed);
        flipperColor.add(flipperColorGreen);
        flipperColor.add(flipperColorBlue);
        return flipperColor;
    }

    public float GetTailPresent(){return tailPresent;
    }

    public float GetTailHeightPercentage(){
        return tailHeightPercentage;
    }

    public float GetTailWidthPercentage(){return tailWidthPercentage;}

    public Color GetTailColor(){
        return new Color(tailColorRed,tailColorGreen,tailColorBlue);
    }

    public ArrayList<Integer> GetTailColorRGB(){
        ArrayList<Integer> tailColor=new ArrayList<>();
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
        ArrayList<Integer> eyeColor=new ArrayList<>();
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
        ArrayList<Integer> bodyColor=new ArrayList<>();
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
        ArrayList<Integer> mouthColor=new ArrayList<>();
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

    public float GetSkinToughness(){return skinToughness;}

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
