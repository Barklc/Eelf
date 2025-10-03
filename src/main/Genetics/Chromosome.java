package main.Genetics;

import java.util.ArrayList;

public class Chromosome{
    private final ArrayList<Float> genes;


    public Chromosome(int MaxGenes){
        genes=new ArrayList<Float>();

        for(int i=0;i<MaxGenes;i++){
            genes.add(-1.0f);
        }
        //println("Chromosome.Constructor - Gene Count: " + genes.size());
    }

    public float GetGene(int GeneLocation){
        return genes.get(GeneLocation);
    }

    public void SetGene(int location,float value){

        //println("Chromosome.SetGene: " + location +"=" + value);
        genes.set(location,value);
    }

    public int GetChromosomeSize(){
        return genes.size();
    }

}
