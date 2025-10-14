package main.Creature;
import main.Genetics.GeneMinMax;
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
    //private final float flipperColorDeviation;
    private final int flipperColorBlue;
    private final int flipperColorGreen;
    private final int flipperColorRed;
    private final int tailColorBlue;
    private final int tailColorGreen;
    private final int tailColorRed;
    private final float receptorSensitivity;
    private final float maxHealth;
    private final float healthIncreasePercentage;
    public final float massPercentage;
    private final float visionDominancePercentage;
    private final float scentDominancePercentage;

    public CreatureGeneValues(Genome genome){

        dna=genome;
        bodyLength = (int) Math.floor(GeneMinMax.BodyLengthMin + (0.01f * (GeneMinMax.BodyLengthMax-GeneMinMax.BodyLengthMin)));
        //bodyLength = (int) Math.floor(GeneMinMax.BodyLengthMin + (dna.GetGene(GeneID.BodyLength) * (GeneMinMax.BodyLengthMax-GeneMinMax.BodyLengthMin)));
        bodyHeight = GeneMinMax.BodyHeightMin + (dna.GetGeneValue(GeneID.BodyHeight) * (GeneMinMax.BodyHeightMax-GeneMinMax.BodyHeightMin));
        biteStrength= GeneMinMax.BiteStrengthMin + (dna.GetGeneValue(GeneID.BiteStrength) * (GeneMinMax.BiteStrengthMax - GeneMinMax.BiteStrengthMin));
        birthRecoveryTime=GeneMinMax.BirthRecoveryMin + (dna.GetGeneValue(GeneID.BirthRecoveryTime) * (GeneMinMax.BirthRecoveryMax-GeneMinMax.BirthRecoveryMin));
        birthEnergyCost=GeneMinMax.BirthEnergyCostMin + (dna.GetGeneValue(GeneID.BirthEnergyCost) * (GeneMinMax.BirthEnergyCostMax-GeneMinMax.BirthEnergyCostMin));
        birthGestationEnergyCost=GeneMinMax.BirthGestationEnergyCostMin + (dna.GetGeneValue(GeneID.BirthGestationEnergyCost) * (GeneMinMax.BirthGestationEnergyCostMax-GeneMinMax.BirthGestationEnergyCostMin));
        bodyWidth=GeneMinMax.BodyWidthMin + (dna.GetGeneValue(GeneID.BodyWidth) * (GeneMinMax.BodyWidthMax-GeneMinMax.BodyWidthMin));
        bodyTaper=GeneMinMax.BodyTapperMin + (dna.GetGeneValue(GeneID.BodyTaper) * (GeneMinMax.BodyTapperMax-GeneMinMax.BodyTapperMin));
        flipperPresent=dna.GetGeneValue(GeneID.FlipperPresent);
        flipperHeight=GeneMinMax.FlipperHeightMin + (dna.GetGeneValue(GeneID.FlipperHeight)* (GeneMinMax.FlipperHeightMax-GeneMinMax.FlipperHeightMin));
        flipperWidth=GeneMinMax.FlipperWidthMin + (dna.GetGeneValue(GeneID.FlipperWidth)* (GeneMinMax.FlipperWidthMax-GeneMinMax.FlipperWidthMin));
        tailPresent=dna.GetGeneValue(GeneID.TailPresent);
        tailHeightPercentage=GeneMinMax.TailHeightMinPercentage + (dna.GetGeneValue(GeneID.TailHeightPercentage) * (GeneMinMax.TailHeightMaxPercentage-GeneMinMax.TailHeightMinPercentage));
        tailWidthPercentage=GeneMinMax.TailWidthMinPercentage + (dna.GetGeneValue(GeneID.TailWidthPercentage)* (GeneMinMax.TailWidthMaxPercentage-GeneMinMax.TailWidthMinPercentage));
        bodyColorRed=(int) Math.floor(dna.GetGeneValue(GeneID.BodyColorRed));
        bodyColorGreen=(int) Math.floor(dna.GetGeneValue(GeneID.BodyColorGreen));
        bodyColorBlue=(int) Math.floor(dna.GetGeneValue(GeneID.BodyColorBlue));
        //flipperColorDeviation=-0.5f; //dna.GetGene(GeneID.FlipperColorDeviation);
        flipperColorRed=(int) Math.floor(dna.GetGeneValue(GeneID.FlipperColorRed));
        flipperColorGreen=(int) Math.floor(dna.GetGeneValue(GeneID.FlipperColorGreen));
        flipperColorBlue=(int) Math.floor(dna.GetGeneValue(GeneID.FlipperColorBlue));
        tailColorRed=(int) Math.floor(dna.GetGeneValue(GeneID.TailColorRed));
        tailColorGreen=(int) Math.floor(dna.GetGeneValue(GeneID.TailColorGreen));
        tailColorBlue=(int) Math.floor(dna.GetGeneValue(GeneID.TailColorBlue));
        digestionRate=dna.GetGeneValue(GeneID.DigestionRate);
        eyeColorRed=(int) Math.floor(dna.GetGeneValue(GeneID.EyeColorRed));
        eyeColorGreen=(int) Math.floor(dna.GetGeneValue(GeneID.EyeColorGreen));
        eyeColorBlue=(int) Math.floor(dna.GetGeneValue(GeneID.EyeColorBlue));
        eyeSize=GeneMinMax.EyeSizeMin + (dna.GetGeneValue(GeneID.EyeSize) * (GeneMinMax.EyeSizeMax-GeneMinMax.EyeSizeMin));
        eyesPresent=dna.GetGeneValue(GeneID.EyesPresent);
        plantToEnergyConversionRate=dna.GetGeneValue(GeneID.PlantToEnergyConversionRate);
        meatToEnergyConversionRate=dna.GetGeneValue(GeneID.MeatToEnergyConversionRate);
        gestationPeriod=GeneMinMax.GestationPeriodMin + (dna.GetGeneValue(GeneID.GestationPeriod) * (GeneMinMax.GestationPeriodMax-GeneMinMax.GestationPeriodMin));
        headShape=dna.GetGeneValue(GeneID.HeadShape);
        lifeSpan = GeneMinMax.LifeSpanMax + (dna.GetGeneValue(GeneID.LifeSpan) * (GeneMinMax.LifeSpanMax-GeneMinMax.LifeSpanMin));
        matureAgePercentage=GeneMinMax.MatureAgePercentageMin + (dna.GetGeneValue(GeneID.MatureAgePercentage) * (GeneMinMax.MatureAgePercentageMax-GeneMinMax.MatureAgePercentageMin));
        maxTurnAngle = dna.GetGeneValue(GeneID.MaxTurnAngle);
        mouthColorRed=(int) Math.floor(dna.GetGeneValue(GeneID.MouthColorRed));
        mouthColorGreen=(int) Math.floor(dna.GetGeneValue(GeneID.MouthColorGreen));
        mouthColorBlue= (int) Math.floor(dna.GetGeneValue(GeneID.MouthColorBlue));
        mouthSize = bodyHeight*(dna.GetGeneValue(GeneID.MouthSize));
        mouthPresent =dna.GetGeneValue(GeneID.MouthPresent);
        seniorAgePercentage=GeneMinMax.SeniorAgePercentageMin + (dna.GetGeneValue(GeneID.SeniorAgePercentage) * (GeneMinMax.SeniorAgePercentageMax-GeneMinMax.SeniorAgePercentageMin));
        skinToughness=GeneMinMax.SkinToughnessMin + (dna.GetGeneValue(GeneID.SkinToughness) * (GeneMinMax.SkinToughnessMax-GeneMinMax.SkinToughnessMin));
        speed = GeneMinMax.MovementSpeedMin + (dna.GetGeneValue(GeneID.MovementSpeed) * (GeneMinMax.MovementSpeedMax-GeneMinMax.MovementSpeedMin));
        stomachSize=GeneMinMax.StomachSizeMin + (dna.GetGeneValue(GeneID.StomachSize) * (GeneMinMax.StomachSizeMax-GeneMinMax.StomachSizeMin));
        maxStoredEnergy=GeneMinMax.EnergyStorageMin + (dna.GetGeneValue(GeneID.MaxStoredEnergy) *(GeneMinMax.EnergyStorageMax-GeneMinMax.EnergyStorageMin));
        visionAngle=GeneMinMax.VisionAngleMin + (dna.GetGeneValue(GeneID.VisionAngle) * (GeneMinMax.VisionAngleMax-GeneMinMax.VisionAngleMin));
        visionClarity=GeneMinMax.VisionClarityMin + (dna.GetGeneValue(GeneID.VisionClarity) * (GeneMinMax.VisionClarityMax-GeneMinMax.VisionClarityMin));
        visionDistance=GeneMinMax.VisionDistanceMin + (dna.GetGeneValue(GeneID.VisionDistance) * (GeneMinMax.VisionDistanceMax-GeneMinMax.VisionDistanceMin));
        visionScanFreq = GeneMinMax.VisionScanFreqMin + (dna.GetGeneValue(GeneID.VisionScanFreq) * (GeneMinMax.VisionScanFreqMax-GeneMinMax.VisionScanFreqMin));
        bodyDistanceBetweenSegments=bodyHeight* (GeneMinMax.BodyDistanceBetweenSegmentsMin + (dna.GetGeneValue(GeneID.BodyDistanceBetweenSegments) * (GeneMinMax.BodyDistanceBetweenSegmentsMax-GeneMinMax.BodyDistanceBetweenSegmentsMin)));
        receptorSensitivity=dna.GetGeneValue(GeneID.ReceptorsSensitivity);
        maxHealth=GeneMinMax.HealthMin+(dna.GetGeneValue(GeneID.MaxHealth)*(GeneMinMax.HealthMax-GeneMinMax.HealthMin));
        healthIncreasePercentage=dna.GetGeneValue(GeneID.IncreaseHealthPercentage);
        massPercentage=dna.GetGeneValue(GeneID.MassPercentage);
        visionDominancePercentage=GeneMinMax.VisionDominanceMin + (dna.GetGeneValue(GeneID.VisionDominancePercentage) * (GeneMinMax.VisionDominanceMax-GeneMinMax.VisionDominanceMin));
        scentDominancePercentage=GeneMinMax.ScentDominanceMin + (dna.GetGeneValue(GeneID.ScentDominancePercentage) * (GeneMinMax.ScentDominanceMax-GeneMinMax.ScentDominanceMin));

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

    //public float GetFlipperColorDeviation(){return flipperColorDeviation;}
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

    public float GetMassPercentage(){return massPercentage;}

    public float GetVisionDominancePercentage(){return visionDominancePercentage;}

    public float GetScentDominancePercentage(){return scentDominancePercentage;}
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
