package main.Genetics;

import java.util.ArrayList;
import java.util.Objects;

public class GeneDefinitions {
    private final ArrayList<GeneBase> genes;

    public GeneDefinitions(){
        int maxGenes = 100;
        genes= new ArrayList<>(maxGenes);
        for(int i = 0; i< maxGenes; i++){
            genes.add(new GeneBase(false,-1,-1,-1,-1,true,"",-1,-1,"",i));
        }

        //Physical attributes
        //new GeneBase(Active,Min,Max,MutationFactor,StartingValue,RandomStartingValue,GeneDescription,GeneID,LocationOnChromosome,GeneIDName,Chromosome)
        int locationOnChromosome = 0;
        System.out.println("Length");
        //Body Genes
        GeneBase geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  0.0f, true, "Determines the number of body segments.", GeneID.BodyLength, locationOnChromosome++,"GeneID.BodyLength", ChromosomeID.Body);
        genes.set(GeneID.BodyLength,geneBase);

        geneBase=new GeneBase(true,  1.00f,   1.00f,  0.0f,  0.0f, true,   "Determines the shape if the head, as to how long it is.", GeneID.HeadShape, locationOnChromosome++,"GeneID.HeadShape", ChromosomeID.Body);
        genes.set(GeneID.HeadShape,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.05f,  0.0f, true,   "Determines the size of the head segment.", GeneID.BodyHeight, locationOnChromosome++,"GeneID.BodyHeight", ChromosomeID.Body);
        genes.set(GeneID.BodyHeight,geneBase);

        geneBase=new GeneBase(true,  0.0f,   1.00f,  0.05f,  0.0f, true,   "Determines the color of the creature.", GeneID.BodyTaper, locationOnChromosome++,"GeneID.BodyTaper", ChromosomeID.Body);
        genes.set(GeneID.BodyTaper,geneBase);

        geneBase=new GeneBase(true,  0.00f,   1.00f,  0.01f,  0.0f, true,   "Determines the width of the creature.", GeneID.BodyWidth, locationOnChromosome++,"GeneID.BodyWidth", ChromosomeID.Body);
        genes.set(GeneID.BodyWidth,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.5f,  0.0f, true,   "Determines the toughness of the skin toughness", GeneID.SkinToughness, locationOnChromosome++,"GeneID.SkinToughness", ChromosomeID.Body);
        genes.set(GeneID.SkinToughness,geneBase);

        //Flipper Genes
        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  0.0f, true,   "Determines the if flippers are present and if they should be considered in turn rate calculations.", GeneID.FlipperPresent, locationOnChromosome++,"GeneID.FlipperPresent", ChromosomeID.Body);
        genes.set(GeneID.FlipperPresent,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  3.0f, false,   "Determines the height of the flipper", GeneID.FlipperHeight, locationOnChromosome++,"GeneID.FlipperHeight", ChromosomeID.Body);
        genes.set(GeneID.FlipperHeight,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  1.0f, false,   "Determines the width of the skin flipper", GeneID.FlipperWidth, locationOnChromosome++,"GeneID.FlipperWidth", ChromosomeID.Body);
        genes.set(GeneID.FlipperWidth,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the red color of the creature.", GeneID.FlipperColorRed, locationOnChromosome++,"GeneID.FlipperColorRed", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorRed,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the green color of the creature.", GeneID.FlipperColorGreen, locationOnChromosome++,"GeneID.FlipperColorGreen", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorGreen,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the blue color of the creature.", GeneID.FlipperColorBlue, locationOnChromosome++,"GeneID.FlipperColorBlue", ChromosomeID.Body);
        genes.set(GeneID.FlipperColorBlue,geneBase);

        //Tail genes
        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  0.0f, true,   "Determines the if tail should be present and if they should be considered in speed rate calculation.", GeneID.TailPresent, locationOnChromosome++,"GeneID.TailPresent", ChromosomeID.Body);
        genes.set(GeneID.TailPresent,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  0.0f, true,   "Determines the height of the flipper.", GeneID.TailHeight, locationOnChromosome++,"GeneID.TailHeight", ChromosomeID.Body);
        genes.set(GeneID.TailHeight,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f,  0.01f,  0.0f, true,   "Determines the width of the skin flipper", GeneID.TailWidth, locationOnChromosome++,"GeneID.TailWidth", ChromosomeID.Body);
        genes.set(GeneID.TailWidth,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the red color of the creature.", GeneID.TailColorRed, locationOnChromosome++,"GeneID.TailColorRed", ChromosomeID.Body);
        genes.set(GeneID.TailColorRed,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the green color of the creature.", GeneID.TailColorGreen, locationOnChromosome++,"GeneID.TailColorGreen", ChromosomeID.Body);
        genes.set(GeneID.TailColorGreen,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the blue color of the creature.", GeneID.TailColorBlue, locationOnChromosome++,"GeneID.TailColorBlue", ChromosomeID.Body);
        genes.set(GeneID.TailColorBlue,geneBase);

        //Body Color
        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the red color of the creature.", GeneID.BodyColorRed, locationOnChromosome++,"GeneID.BodyColorRed", ChromosomeID.Body);
        genes.set(GeneID.BodyColorRed,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the green color of the creature.", GeneID.BodyColorGreen, locationOnChromosome++,"GeneID.BodyColorGreen", ChromosomeID.Body);
        genes.set(GeneID.BodyColorGreen,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the blue color of the creature.", GeneID.BodyColorBlue, locationOnChromosome++,"GeneID.BodyColorBlue", ChromosomeID.Body);
        genes.set(GeneID.BodyColorBlue,geneBase);

        geneBase=new GeneBase(true,   1.0f, 1.0f, 0.01f,  0.0f, true,   "Determines the percentage of the distance between body segments.", GeneID.BodyDistanceBetweenSegments, locationOnChromosome,"GeneID.BodyDistanceBetweenSegments", ChromosomeID.Body);
        genes.set(GeneID.BodyDistanceBetweenSegments,geneBase);

        //Vision Genes
        locationOnChromosome =0;
        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.025f,  0.0f, true, "Determines the angle of vision.", GeneID.VisionAngle, locationOnChromosome++,"GeneID.VisionAngle", ChromosomeID.Vision);
        genes.set(GeneID.VisionAngle,geneBase);

        geneBase=new GeneBase(true,  0.03f,   0.03f,  0.0f,  0.0f, true,   "Determine the clarity of the vision.", GeneID.VisionClarity, locationOnChromosome++,"GeneID.VisionClarity", ChromosomeID.Vision);
        genes.set(GeneID.VisionClarity,geneBase);

        geneBase=new GeneBase(true,0.00f, 1.00f, 0.05f,  0.0f, true,   "Determines the distance the creature can see.", GeneID.VisionDistance, locationOnChromosome++,"GeneID.VisionDistance", ChromosomeID.Vision);
        genes.set(GeneID.VisionDistance,geneBase);

        geneBase=new GeneBase(true,  0.00f,   1.00f, 0.025f,  0.0f, true,  "Determines how often to evaluate vision.", GeneID.VisionScanFreq, locationOnChromosome++,"GeneID.VisionScanFreq", ChromosomeID.Vision);
        genes.set(GeneID.VisionScanFreq,geneBase);

        //Eye Genes
        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the red color of the creature.", GeneID.EyeColorRed, locationOnChromosome++,"GeneID.EyeColorRed", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorRed,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the green color of the creature.", GeneID.EyeColorGreen, locationOnChromosome++,"GeneID.EyeColorGreen", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorGreen,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.0f, 10.0f,  0.0f, true,   "Determines the blue color of the creature.", GeneID.EyeColorBlue, locationOnChromosome++,"GeneID.EyeColorBlue", ChromosomeID.Vision);
        genes.set(GeneID.EyeColorBlue,geneBase);

        geneBase=new GeneBase(true,   0.0f, 1.0f, 0.02f,  0.0f, false,   "Determines the size of the creatures eyes.  They start at 0 and only come into effect if EyesPresent is true.", GeneID.EyeSize, locationOnChromosome++,"GeneID.EyeSize", ChromosomeID.Vision);
        genes.set(GeneID.EyeSize,geneBase);

        geneBase=new GeneBase(true,   0.0f, 1.0f, 0.01f,  0.0f, true,   "Determines the if creature develops eyes.", GeneID.EyesPresent, locationOnChromosome,"GeneID.GeneID.EyesPresent", ChromosomeID.Vision);
        genes.set(GeneID.EyesPresent,geneBase);

        //Physics
        locationOnChromosome =0;
        geneBase=new GeneBase(true,  0.00f,   2.00f,  0.2f,  0.0f, true, "Determines the speed the creature moves at.", GeneID.MovementSpeed, locationOnChromosome++,"GeneID.MovementSpeed", ChromosomeID.Physics);
        genes.set(GeneID.MovementSpeed,geneBase);

        geneBase=new GeneBase(true,  0.01f,   0.05f, 0.005f,  0.0f, true,    "Determines how tight an angle a creature can turn", GeneID.MaxTurnAngle, locationOnChromosome,"GeneID.MaxTurnAngle", ChromosomeID.Physics);
        genes.set(GeneID.MaxTurnAngle,geneBase);

        //Mouth
        locationOnChromosome =0;
        geneBase=new GeneBase(true,  0.50f,   1.00f, 0.1f,  0.0f, true,    "Determines the size of the mouth.", GeneID.MouthSize, locationOnChromosome++,"GeneID.MouthSize", ChromosomeID.Mouth);
        genes.set(GeneID.MouthSize,geneBase);

        geneBase=new GeneBase(true,  0.00f,  1.00f, 0.1f,  0.0f, true,    "Determines the strength of the bite.", GeneID.BiteStrength, locationOnChromosome++,"GeneID.BiteStrength", ChromosomeID.Mouth);
        genes.set(GeneID.BiteStrength,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the red color of the creature.", GeneID.MouthColorRed, locationOnChromosome++,"GeneID.MouthColorRed", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorRed,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the green color of the creature.", GeneID.MouthColorGreen, locationOnChromosome++,"GeneID.MouthColorGreen", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorGreen,geneBase);

        geneBase=new GeneBase(true,   0.0f, 255.00f, 10.0f,  0.0f, true,   "Determines the blue color of the creature.", GeneID.MouthColorBlue, locationOnChromosome++,"GeneID.MouthColorBlue", ChromosomeID.Mouth);
        genes.set(GeneID.MouthColorBlue,geneBase);

        geneBase=new GeneBase(true,   0.0f, 1.0f, 0.01f,  0.0f, true,   "Determines the if creature develops eyes.", GeneID.MouthPresent, locationOnChromosome,"GeneID.GeneID.MouthPresent", ChromosomeID.Mouth);
        genes.set(GeneID.MouthPresent,geneBase);

        //Life/Age
        locationOnChromosome =0;
        geneBase=new GeneBase(true, 0.0f, 1.0f, 0.1f,  0.0f, true,    "Determines the how long the creature lives.", GeneID.LifeSpan, locationOnChromosome++,"GeneID.LifeSpan", ChromosomeID.Aging);
        genes.set(GeneID.LifeSpan,geneBase); //In game days, 60 ticks

        geneBase=new GeneBase(true,  0.2f,   0.4f, 0.01f,  0.0f, true,    "Determines when the creature matures.", GeneID.MatureAgePercentage, locationOnChromosome++,"GeneID.MatureAgePercentage", ChromosomeID.Aging);
        genes.set(GeneID.MatureAgePercentage,geneBase); //Percentage of life expediency

        geneBase=new GeneBase(true,  0.7f,   0.9f, 0.01f,  0.0f, true,    "Determines when the creature is old.", GeneID.SeniorAgePercentage, locationOnChromosome,"GeneID.SeniorAgePercentage", ChromosomeID.Aging);
        genes.set(GeneID.SeniorAgePercentage,geneBase); //Percentage of life expediency

        //Metabolism
        locationOnChromosome =0;
        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.01f,  0.0f, true, "Determines how much food can be held in stomach.", GeneID.StomachSize, locationOnChromosome++,"GeneID.StomachSize", ChromosomeID.Metabolism);
        genes.set(GeneID.StomachSize,geneBase);

        geneBase=new GeneBase(true,  0.1f,   1.0f,  0.1f,  0.0f, true,   "Determine the rate of how fast food is converted to energy.", GeneID.DigestionRate, locationOnChromosome++,"GeneID.DigestionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.DigestionRate,geneBase);

        geneBase=new GeneBase(true,0.1f, 1.0f, 0.1f,  0.0f, true,   "Determines how what percentage of digested plant is converted to energy.", GeneID.PlantToEnergyConversionRate, locationOnChromosome++,"GeneID.PlantToEnergyConversionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.PlantToEnergyConversionRate,geneBase);

        geneBase=new GeneBase(true,0.1f, 1.0f, 0.1f,  0.0f, true,   "Determines how what percentage of digested meat is converted to energy.", GeneID.MeatToEnergyConversionRate, locationOnChromosome++,"GeneID.MeatToEnergyConversionRate", ChromosomeID.Metabolism);
        genes.set(GeneID.MeatToEnergyConversionRate,geneBase);

        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.1f,  0.0f, true, "Determines how much excess energy can be stored.", GeneID.MaxStoredEnergy, locationOnChromosome++,"GeneID.MaxStoredEnergy", ChromosomeID.Metabolism);
        genes.set(GeneID.MaxStoredEnergy,geneBase);

        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.01f,  0.0f, true, "Determines the maximum health for the creature.", GeneID.MaxHealth, locationOnChromosome++,"GeneID.MaxHealth", ChromosomeID.Metabolism);
        genes.set(GeneID.MaxHealth,geneBase);

        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.5f,  0.0f, true, "Determines the percentage of health increase when eaten.", GeneID.IncreaseHealthPercentage, locationOnChromosome,"GeneID.IncreaseHealthPercentage", ChromosomeID.Metabolism);
        genes.set(GeneID.IncreaseHealthPercentage,geneBase);

        //Reproduction
        locationOnChromosome =0;
        geneBase=new GeneBase(true,  0.0f,  1.0f,  0.1f,  0.0f, true, "Modifies how much energy is needed each cycle during gestation.", GeneID.BirthGestationEnergyCost, locationOnChromosome++,"GeneID.BirthEnergyNeed", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthGestationEnergyCost,geneBase);

        geneBase=new GeneBase(true,  0.0f,   1.0f,  0.1f,  0.0f, true,   "Modifies the amount of time between reproduction cycles.", GeneID.BirthRecoveryTime, locationOnChromosome++,"GeneID.BirthCoolDown", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthRecoveryTime,geneBase);

        geneBase=new GeneBase(true,0.0f, 1.0f, 0.1f,  0.0f, true,   "Modifies the cost of giving birth.", GeneID.BirthEnergyCost, locationOnChromosome++,"GeneID.BirthEnergyCost", ChromosomeID.Reproduction);
        genes.set(GeneID.BirthEnergyCost,geneBase);

        geneBase=new GeneBase(true,1.0f,9.0f, 0.25f,  0.0f, true, "Gestation period of offspring", GeneID.GestationPeriod, locationOnChromosome,"GeneID.GestationPeriod", ChromosomeID.Reproduction);
        genes.set(GeneID.GestationPeriod,geneBase);

        //Olfactory
        locationOnChromosome =0;
        geneBase=new GeneBase(true,0.1f, 1.0f, 0.1f,  0.0f, true, "The sensitivity of the olfactory receptors.  How strong the smell to be to be detected.", GeneID.ReceptorsSensitivity, locationOnChromosome,"GeneID.ReceptorsSensitivity", ChromosomeID.Olfactory);
        genes.set(GeneID.ReceptorsSensitivity,geneBase);
        //ReadyToMateScent=91;
        //FleeOrRepulseScent=92;
        //FoodScent=93;

        //ScentEmission
        //ScentEmitterRed=100;
        //ScentEmitterGreen=101;
        //ScentEmitterBlue=102;
        //ScentEmissionRateRed=103;
        //ScentEmissionRateGreen=104;
        //ScentEmissionRateBlue=105;
        //ScentRedStrength=106;
        //ScentGreenStrength=107;
        //ScentBlueStrength=108;
        //ScentRedDropOff=109;
        //ScentGreenDropOff=110;
        //ScentBlueDropOff=111;
    }

    public GeneBase GetGene(int index){
        //println("GeneDef.GetGene - Index: " + index);
        return genes.get(index);
    }

    public float GeneCount(){
        return genes.size();
    }

    public String Version(){
        return "1.0";
    }

    public String GenesLibrary(){
        StringBuilder output;
        output = new StringBuilder("Active, Minimum, Maximum, MutationFactor, StartingValue, RandomStartingValue, Description, GeneID, GeneLocation, GeneName, Chromosome\r\n");
        for(int i=0;i<GeneCount();i++){
            GeneBase g=GetGene(i);
            if(!Objects.equals(g.Description(), "")){
                output.append(g.Active()).append(",").append(g.Minimum()).append(",").append(g.Maximum()).append(",").append(g.GetMutationRate()).append(",").append(g.StartingValue()).append(",").append(g.RandomStartingValue()).append(",").append(g.Description()).append(",").append(g.GeneID()).append(",").append(g.GetGeneLocationOnChromosome()).append(",").append(g.GeneName()).append(",").append(g.GetChromosome()).append("\r\n");
            }
        }
        return output.toString();
    }

}

