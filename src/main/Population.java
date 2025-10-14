package main;

import main.Creature.Creature;
import main.Genetics.GeneBase;
import main.Genetics.GeneID;
import main.Genetics.Genome;

import java.util.ArrayList;
import java.util.UUID;

import static main.Main.gGenesDef;

public class Population {
    private final ArrayList<Creature> population;
    int maxPop=0;

    public Population() {
        population=new ArrayList<>();
    }

    public void CreatePopulation(int MaxPop,int Width,int Height){
        maxPop=MaxPop;
        Genome Base=BuildRandomGenome();
        for(int i=0;i<maxPop;i++){
            Genome newGenome=Mutate(Base);
            newGenome.ExportGenome();
            Creature creature = new Creature((float) Width /2, (float) Height /2,newGenome,UUID.randomUUID());
            creature.SetCurrentAction(Actions.NewDestination);
            AddPopulation(creature);
        }
    }

    public void AddPopulation(Creature creature){
        population.add(creature);
    }

    public Creature GetCreature(int index){
        return population.get(index);
    }

    public int GetMaxPop(){
        return maxPop;
    }

    public int GetCurrentPopulationSize(){
        //TODO: Comeback and add filters on this such as alive,pregnant, etc...
        return population.size();
    }

    public Creature CreateCreature(float startX, float startY, Genome genome) {
        return new Creature(startX, startY, genome,UUID.randomUUID());
    }

    public Genome BuildRandomGenome() {
        Genome newGenome=new Genome(8,gGenesDef);

        newGenome.SetGeneInChromosome(GeneID.BodyLength, gGenesDef.GetGene(GeneID.BodyLength).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.HeadShape, gGenesDef.GetGene(GeneID.HeadShape).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BodyHeight, gGenesDef.GetGene(GeneID.BodyHeight).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BodyTaper, gGenesDef.GetGene(GeneID.BodyTaper).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BodyWidth, gGenesDef.GetGene(GeneID.BodyWidth).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.FlipperPresent, gGenesDef.GetGene(GeneID.FlipperPresent).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.FlipperHeight, gGenesDef.GetGene(GeneID.FlipperHeight).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.FlipperWidth, gGenesDef.GetGene(GeneID.FlipperWidth).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.TailPresent, gGenesDef.GetGene(GeneID.TailPresent).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.TailHeightPercentage, gGenesDef.GetGene(GeneID.TailHeightPercentage).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.TailWidthPercentage, gGenesDef.GetGene(GeneID.TailWidthPercentage).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BodyColorRed, gGenesDef.GetGene(GeneID.BodyColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.BodyColorGreen, gGenesDef.GetGene(GeneID.BodyColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.BodyColorBlue, gGenesDef.GetGene(GeneID.BodyColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.SkinToughness, gGenesDef.GetGene(GeneID.SkinToughness).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorRed, gGenesDef.GetGene(GeneID.FlipperColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorGreen, gGenesDef.GetGene(GeneID.FlipperColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorBlue, gGenesDef.GetGene(GeneID.FlipperColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.TailColorRed, gGenesDef.GetGene(GeneID.TailColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.TailColorGreen, gGenesDef.GetGene(GeneID.TailColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.TailColorBlue, gGenesDef.GetGene(GeneID.TailColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.BodyDistanceBetweenSegments, gGenesDef.GetGene(GeneID.BodyDistanceBetweenSegments).RandomValue(false, 1));

        newGenome.SetGeneInChromosome(GeneID.StomachSize, gGenesDef.GetGene(GeneID.StomachSize).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.DigestionRate, gGenesDef.GetGene(GeneID.DigestionRate).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.PlantToEnergyConversionRate, gGenesDef.GetGene(GeneID.PlantToEnergyConversionRate).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MeatToEnergyConversionRate, gGenesDef.GetGene(GeneID.MeatToEnergyConversionRate).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MaxStoredEnergy, gGenesDef.GetGene(GeneID.MaxStoredEnergy).RandomValue());

        newGenome.SetGeneInChromosome(GeneID.BirthGestationEnergyCost, gGenesDef.GetGene(GeneID.BirthGestationEnergyCost).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BirthRecoveryTime, gGenesDef.GetGene(GeneID.BirthRecoveryTime).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BirthEnergyCost,gGenesDef.GetGene(GeneID.BirthEnergyCost).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.GestationPeriod, gGenesDef.GetGene(GeneID.GestationPeriod).RandomValue());

        newGenome.SetGeneInChromosome(GeneID.VisionAngle, 1); //gGenesDef.GetGene(GeneID.VisionAngle).RandomValue(true));
        newGenome.SetGeneInChromosome(GeneID.VisionClarity, gGenesDef.GetGene(GeneID.VisionClarity).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.VisionDistance, gGenesDef.GetGene(GeneID.VisionDistance).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.VisionScanFreq, gGenesDef.GetGene(GeneID.VisionScanFreq).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.EyeColorRed, gGenesDef.GetGene(GeneID.EyeColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeColorGreen, gGenesDef.GetGene(GeneID.EyeColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeColorBlue, gGenesDef.GetGene(GeneID.EyeColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeSize, gGenesDef.GetGene(GeneID.EyeSize).RandomValue(false,1));
        newGenome.SetGeneInChromosome(GeneID.EyesPresent, gGenesDef.GetGene(GeneID.EyesPresent).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.VisionDominancePercentage, gGenesDef.GetGene(GeneID.VisionDominancePercentage).RandomValue(false,1));

        newGenome.SetGeneInChromosome(GeneID.MovementSpeed, gGenesDef.GetGene(GeneID.MovementSpeed).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MaxTurnAngle, gGenesDef.GetGene(GeneID.MaxTurnAngle).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MassPercentage, gGenesDef.GetGene(GeneID.MassPercentage).RandomValue());

        newGenome.SetGeneInChromosome(GeneID.MouthSize, gGenesDef.GetGene(GeneID.MouthSize).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.BiteStrength, gGenesDef.GetGene(GeneID.BiteStrength).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MouthColorRed, gGenesDef.GetGene(GeneID.MouthColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.MouthColorGreen, gGenesDef.GetGene(GeneID.MouthColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.MouthColorBlue, gGenesDef.GetGene(GeneID.MouthColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.MouthPresent, gGenesDef.GetGene(GeneID.MouthPresent).RandomValue());

        newGenome.SetGeneInChromosome(GeneID.LifeSpan, gGenesDef.GetGene(GeneID.LifeSpan).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MatureAgePercentage, gGenesDef.GetGene(GeneID.MatureAgePercentage).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.SeniorAgePercentage, gGenesDef.GetGene(GeneID.SeniorAgePercentage).RandomValue());

        newGenome.SetGeneInChromosome(GeneID.ReceptorsSensitivity, gGenesDef.GetGene(GeneID.ReceptorsSensitivity).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.ScentDominancePercentage, gGenesDef.GetGene(GeneID.ScentDominancePercentage).RandomValue());

        return newGenome;
    }

    public Genome Mutate(Genome genome){
        Genome newGenome=genome;
        newGenome.SetGeneInChromosome(GeneID.BodyLength,MutateGene(newGenome.GetGeneDef(GeneID.BodyLength),newGenome.GetGeneValue(GeneID.BodyLength)));
        newGenome.SetGeneInChromosome(GeneID.HeadShape, MutateGene(newGenome.GetGeneDef(GeneID.HeadShape),newGenome.GetGeneValue(GeneID.HeadShape)));
        newGenome.SetGeneInChromosome(GeneID.BodyHeight, MutateGene(newGenome.GetGeneDef(GeneID.BodyHeight),newGenome.GetGeneValue(GeneID.BodyHeight)));
        newGenome.SetGeneInChromosome(GeneID.BodyTaper, MutateGene(newGenome.GetGeneDef(GeneID.BodyTaper),newGenome.GetGeneValue(GeneID.BodyTaper)));
        newGenome.SetGeneInChromosome(GeneID.BodyWidth, MutateGene(newGenome.GetGeneDef(GeneID.BodyWidth),newGenome.GetGeneValue(GeneID.BodyWidth)));
        newGenome.SetGeneInChromosome(GeneID.FlipperPresent, MutateGene(newGenome.GetGeneDef(GeneID.FlipperPresent),newGenome.GetGeneValue(GeneID.FlipperPresent)));
        newGenome.SetGeneInChromosome(GeneID.FlipperHeight, MutateGene(newGenome.GetGeneDef(GeneID.FlipperHeight),newGenome.GetGeneValue(GeneID.FlipperHeight)));
        newGenome.SetGeneInChromosome(GeneID.FlipperWidth, MutateGene(newGenome.GetGeneDef(GeneID.FlipperWidth),newGenome.GetGeneValue(GeneID.FlipperWidth)));
        newGenome.SetGeneInChromosome(GeneID.TailPresent, MutateGene(newGenome.GetGeneDef(GeneID.TailPresent),newGenome.GetGeneValue(GeneID.TailPresent)));
        newGenome.SetGeneInChromosome(GeneID.TailHeightPercentage, MutateGene(newGenome.GetGeneDef(GeneID.TailHeightPercentage),newGenome.GetGeneValue(GeneID.TailHeightPercentage)));
        newGenome.SetGeneInChromosome(GeneID.TailWidthPercentage, MutateGene(newGenome.GetGeneDef(GeneID.TailWidthPercentage),newGenome.GetGeneValue(GeneID.TailWidthPercentage)));
        newGenome.SetGeneInChromosome(GeneID.BodyColorRed, MutateGene(newGenome.GetGeneDef(GeneID.BodyColorRed),newGenome.GetGeneValue(GeneID.BodyColorRed)));
        newGenome.SetGeneInChromosome(GeneID.BodyColorGreen, MutateGene(newGenome.GetGeneDef(GeneID.BodyColorGreen),newGenome.GetGeneValue(GeneID.BodyColorGreen)));
        newGenome.SetGeneInChromosome(GeneID.BodyColorBlue, MutateGene(newGenome.GetGeneDef(GeneID.BodyColorBlue),newGenome.GetGeneValue(GeneID.BodyColorBlue)));
        newGenome.SetGeneInChromosome(GeneID.SkinToughness, MutateGene(newGenome.GetGeneDef(GeneID.SkinToughness),newGenome.GetGeneValue(GeneID.SkinToughness)));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorRed, MutateGene(newGenome.GetGeneDef(GeneID.FlipperColorRed),newGenome.GetGeneValue(GeneID.FlipperColorRed)));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorGreen, MutateGene(newGenome.GetGeneDef(GeneID.FlipperColorGreen),newGenome.GetGeneValue(GeneID.FlipperColorGreen)));
        newGenome.SetGeneInChromosome(GeneID.FlipperColorBlue, MutateGene(newGenome.GetGeneDef(GeneID.FlipperColorBlue),newGenome.GetGeneValue(GeneID.FlipperColorBlue)));
        newGenome.SetGeneInChromosome(GeneID.TailColorRed, MutateGene(newGenome.GetGeneDef(GeneID.TailColorRed),newGenome.GetGeneValue(GeneID.TailColorRed)));
        newGenome.SetGeneInChromosome(GeneID.TailColorGreen, MutateGene(newGenome.GetGeneDef(GeneID.TailColorGreen),newGenome.GetGeneValue(GeneID.TailColorGreen)));
        newGenome.SetGeneInChromosome(GeneID.TailColorBlue, MutateGene(newGenome.GetGeneDef(GeneID.TailColorBlue),newGenome.GetGeneValue(GeneID.TailColorBlue)));
        newGenome.SetGeneInChromosome(GeneID.BodyDistanceBetweenSegments, MutateGene(newGenome.GetGeneDef(GeneID.BodyDistanceBetweenSegments),newGenome.GetGeneValue(GeneID.BodyDistanceBetweenSegments)));

        newGenome.SetGeneInChromosome(GeneID.StomachSize, MutateGene(newGenome.GetGeneDef(GeneID.StomachSize),newGenome.GetGeneValue(GeneID.StomachSize)));
        newGenome.SetGeneInChromosome(GeneID.DigestionRate, MutateGene(newGenome.GetGeneDef(GeneID.DigestionRate),newGenome.GetGeneValue(GeneID.DigestionRate)));
        newGenome.SetGeneInChromosome(GeneID.PlantToEnergyConversionRate, MutateGene(newGenome.GetGeneDef(GeneID.PlantToEnergyConversionRate),newGenome.GetGeneValue(GeneID.PlantToEnergyConversionRate)));
        newGenome.SetGeneInChromosome(GeneID.MeatToEnergyConversionRate, MutateGene(newGenome.GetGeneDef(GeneID.MeatToEnergyConversionRate),newGenome.GetGeneValue(GeneID.MeatToEnergyConversionRate)));
        newGenome.SetGeneInChromosome(GeneID.MaxStoredEnergy, MutateGene(newGenome.GetGeneDef(GeneID.MaxStoredEnergy),newGenome.GetGeneValue(GeneID.MaxStoredEnergy)));

        newGenome.SetGeneInChromosome(GeneID.BirthGestationEnergyCost, MutateGene(newGenome.GetGeneDef(GeneID.BirthGestationEnergyCost),newGenome.GetGeneValue(GeneID.BirthGestationEnergyCost)));
        newGenome.SetGeneInChromosome(GeneID.BirthRecoveryTime, MutateGene(newGenome.GetGeneDef(GeneID.BirthRecoveryTime),newGenome.GetGeneValue(GeneID.BirthRecoveryTime)));
        newGenome.SetGeneInChromosome(GeneID.BirthEnergyCost, MutateGene(newGenome.GetGeneDef(GeneID.BirthEnergyCost),newGenome.GetGeneValue(GeneID.BirthEnergyCost)));
        newGenome.SetGeneInChromosome(GeneID.GestationPeriod, MutateGene(newGenome.GetGeneDef(GeneID.GestationPeriod),newGenome.GetGeneValue(GeneID.GestationPeriod)));

        newGenome.SetGeneInChromosome(GeneID.VisionAngle, MutateGene(newGenome.GetGeneDef(GeneID.VisionAngle),newGenome.GetGeneValue(GeneID.VisionAngle)));
        newGenome.SetGeneInChromosome(GeneID.VisionClarity, MutateGene(newGenome.GetGeneDef(GeneID.VisionClarity),newGenome.GetGeneValue(GeneID.VisionClarity)));
        newGenome.SetGeneInChromosome(GeneID.VisionDistance, MutateGene(newGenome.GetGeneDef(GeneID.VisionDistance),newGenome.GetGeneValue(GeneID.VisionDistance)));
        newGenome.SetGeneInChromosome(GeneID.VisionScanFreq, MutateGene(newGenome.GetGeneDef(GeneID.VisionScanFreq),newGenome.GetGeneValue(GeneID.VisionScanFreq)));
        newGenome.SetGeneInChromosome(GeneID.EyeColorRed, MutateGene(newGenome.GetGeneDef(GeneID.EyeColorRed),newGenome.GetGeneValue(GeneID.EyeColorRed)));
        newGenome.SetGeneInChromosome(GeneID.EyeColorGreen, MutateGene(newGenome.GetGeneDef(GeneID.EyeColorGreen),newGenome.GetGeneValue(GeneID.EyeColorGreen)));
        newGenome.SetGeneInChromosome(GeneID.EyeColorBlue, MutateGene(newGenome.GetGeneDef(GeneID.EyeColorBlue),newGenome.GetGeneValue(GeneID.EyeColorBlue)));
        newGenome.SetGeneInChromosome(GeneID.EyeSize, MutateGene(newGenome.GetGeneDef(GeneID.EyeSize),newGenome.GetGeneValue(GeneID.EyeSize)));
        newGenome.SetGeneInChromosome(GeneID.EyesPresent, MutateGene(newGenome.GetGeneDef(GeneID.EyesPresent),newGenome.GetGeneValue(GeneID.EyesPresent)));
        newGenome.SetGeneInChromosome(GeneID.VisionDominancePercentage, MutateGene(newGenome.GetGeneDef(GeneID.VisionDominancePercentage),newGenome.GetGeneValue(GeneID.VisionDominancePercentage)));

        newGenome.SetGeneInChromosome(GeneID.MovementSpeed, MutateGene(newGenome.GetGeneDef(GeneID.MovementSpeed),newGenome.GetGeneValue(GeneID.MovementSpeed)));
        newGenome.SetGeneInChromosome(GeneID.MaxTurnAngle, MutateGene(newGenome.GetGeneDef(GeneID.MaxTurnAngle),newGenome.GetGeneValue(GeneID.MaxTurnAngle)));
        newGenome.SetGeneInChromosome(GeneID.MassPercentage, MutateGene(newGenome.GetGeneDef(GeneID.MassPercentage),newGenome.GetGeneValue(GeneID.MassPercentage)));

        newGenome.SetGeneInChromosome(GeneID.MouthSize, MutateGene(newGenome.GetGeneDef(GeneID.MouthSize),newGenome.GetGeneValue(GeneID.MouthSize)));
        newGenome.SetGeneInChromosome(GeneID.BiteStrength, MutateGene(newGenome.GetGeneDef(GeneID.BiteStrength),newGenome.GetGeneValue(GeneID.BiteStrength)));
        newGenome.SetGeneInChromosome(GeneID.MouthColorRed, MutateGene(newGenome.GetGeneDef(GeneID.MouthColorRed),newGenome.GetGeneValue(GeneID.MouthColorRed)));
        newGenome.SetGeneInChromosome(GeneID.MouthColorGreen, MutateGene(newGenome.GetGeneDef(GeneID.MouthColorGreen),newGenome.GetGeneValue(GeneID.MouthColorGreen)));
        newGenome.SetGeneInChromosome(GeneID.MouthColorBlue, MutateGene(newGenome.GetGeneDef(GeneID.MouthColorBlue),newGenome.GetGeneValue(GeneID.MouthColorBlue)));
        newGenome.SetGeneInChromosome(GeneID.MouthPresent, MutateGene(newGenome.GetGeneDef(GeneID.MouthPresent),newGenome.GetGeneValue(GeneID.MouthPresent)));

        newGenome.SetGeneInChromosome(GeneID.LifeSpan, MutateGene(newGenome.GetGeneDef(GeneID.LifeSpan),newGenome.GetGeneValue(GeneID.LifeSpan)));
        newGenome.SetGeneInChromosome(GeneID.MatureAgePercentage, MutateGene(newGenome.GetGeneDef(GeneID.MatureAgePercentage),newGenome.GetGeneValue(GeneID.MatureAgePercentage)));
        newGenome.SetGeneInChromosome(GeneID.SeniorAgePercentage, MutateGene(newGenome.GetGeneDef(GeneID.SeniorAgePercentage),newGenome.GetGeneValue(GeneID.SeniorAgePercentage)));

        newGenome.SetGeneInChromosome(GeneID.ReceptorsSensitivity, MutateGene(newGenome.GetGeneDef(GeneID.ReceptorsSensitivity),newGenome.GetGeneValue(GeneID.ReceptorsSensitivity)));
        newGenome.SetGeneInChromosome(GeneID.ScentDominancePercentage, MutateGene(newGenome.GetGeneDef(GeneID.ScentDominancePercentage),newGenome.GetGeneValue(GeneID.ScentDominancePercentage)));
        return newGenome;
    }

    public float MutateGene(GeneBase base,float value){
        //base.SetMutationRate(float) - to change the chances of a mutation from occurring.  Default is .10f
        return base.Mutation(value);
    }
}
