package main;

import main.Creature.Creature;
import main.Genetics.GeneID;
import main.Genetics.Genome;

import java.util.ArrayList;
import java.util.UUID;

import static main.Main.gGenesDef;

public class Population {
    private final ArrayList<Creature> population;
    int maxPop=0;

    public Population() {
        population=new ArrayList<Creature>();
    }

    public void CreatePopulation(int MaxPop,int Width,int Height){
        maxPop=MaxPop;
        for(int i=0;i<maxPop;i++){
            Genome g=BuildRandomGenome();
            g.ExportGenome();
            Creature creature = new Creature((float) Width /2, (float) Height /2,g,UUID.randomUUID());
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
        newGenome.SetGeneInChromosome(GeneID.TailHeight, gGenesDef.GetGene(GeneID.TailHeight).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.TailWidth, gGenesDef.GetGene(GeneID.TailWidth).RandomValue());
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

        newGenome.SetGeneInChromosome(GeneID.VisionAngle, gGenesDef.GetGene(GeneID.VisionAngle).RandomValue(true));
        newGenome.SetGeneInChromosome(GeneID.VisionClarity, gGenesDef.GetGene(GeneID.VisionClarity).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.VisionDistance, gGenesDef.GetGene(GeneID.VisionDistance).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.VisionScanFreq, gGenesDef.GetGene(GeneID.VisionScanFreq).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.EyeColorRed, gGenesDef.GetGene(GeneID.EyeColorRed).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeColorGreen, gGenesDef.GetGene(GeneID.EyeColorGreen).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeColorBlue, gGenesDef.GetGene(GeneID.EyeColorBlue).RandomValue(false, 1));
        newGenome.SetGeneInChromosome(GeneID.EyeSize, gGenesDef.GetGene(GeneID.EyeSize).RandomValue(false,1));
        newGenome.SetGeneInChromosome(GeneID.EyesPresent, gGenesDef.GetGene(GeneID.EyesPresent).RandomValue(false, 1));

        newGenome.SetGeneInChromosome(GeneID.MovementSpeed, gGenesDef.GetGene(GeneID.MovementSpeed).RandomValue());
        newGenome.SetGeneInChromosome(GeneID.MaxTurnAngle, gGenesDef.GetGene(GeneID.MaxTurnAngle).RandomValue());

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

        return newGenome;
    }
}
