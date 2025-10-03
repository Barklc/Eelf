package main.Genetics;

import java.util.ArrayList;

public class Genome{
    private final ArrayList<Chromosome> chromosomes;
    private final int maxChromosomes;

    private final GeneDefinitions genesDef;


    public Genome(int MaxChromosomes, GeneDefinitions GenesDef){
        chromosomes= new ArrayList<>();
        chromosomes.add(new Chromosome(ChromosomeLength.Body)); //Body
        chromosomes.add(new Chromosome(ChromosomeLength.Vision)); //Vision
        chromosomes.add(new Chromosome(ChromosomeLength.Aging)); //Aging
        chromosomes.add(new Chromosome(ChromosomeLength.Mouth)); //Mouth
        chromosomes.add(new Chromosome(ChromosomeLength.Digestion)); //Digestion
        chromosomes.add(new Chromosome(ChromosomeLength.Metabolism)); //Metabolism
        chromosomes.add(new Chromosome(ChromosomeLength.Reproduction)); //Reproduction
        chromosomes.add(new Chromosome(ChromosomeLength.Physics)); //Physics
        chromosomes.add(new Chromosome(ChromosomeLength.Olfactory)); //Olfactory
        chromosomes.add(new Chromosome(ChromosomeLength.ScentEmission)); //ScentEmission
        maxChromosomes=MaxChromosomes;

        genesDef=GenesDef;
    }

    public Chromosome GetChromosome(int ChromosomeID){
        return chromosomes.get(ChromosomeID);
    }

    public void SetChromosome(int ChromosomeID, Chromosome Chromosome){
        chromosomes.set(ChromosomeID,Chromosome);
    }

    public float GetGene(int GeneId){
        GeneBase gene=genesDef.GetGene(GeneId);
        int cn=gene.GetChromosome();
        Chromosome c=chromosomes.get(cn);
        int gl=gene.GetGeneLocationOnChromosome();
        return c.GetGene(gl);
    }

    public void SetGeneInChromosome(int GeneId, float value){
        System.out.print("Genome.SetGeneInChromosome: " + GeneId);
        GeneBase gene=genesDef.GetGene(GeneId);

        int chrome=gene.GetChromosome();
        System.out.print(" - " + chrome);
        Chromosome c=chromosomes.get(chrome);
        int location=gene.GetGeneLocationOnChromosome();
        System.out.println(":" + location +"="+value);
        c.SetGene(location,value);


        chromosomes.set(chrome,c);
    }

    public GeneBase GetGeneDef(int GeneId){
        return genesDef.GetGene(GeneId);
    }

    //public ArrayList<Float> CrossOver(ArrayList<Float> parentDNA1,ArrayList<Float> parentDNA2){
    //  int numberOfCrossovers=0;
    //  ArrayList<Float> newDNA=new ArrayList<Float>();
    //  ArrayList<Integer> changes=new ArrayList<Integer>();
    //  for(int i=0;i<parentDNA1.size();i++){
    //    changes.add(0);
    //    if (GetGene(i).Active()){
    //       float change=random(0,1);
    //       if (change>.50 && numberOfCrossovers<=parentDNA1.size()/2){
    //         changes.set(i,1);
    //         numberOfCrossovers++;
    //       }
    //     }
    //  }

    //   for(int i=0;i<parentDNA1.size();i++){
    //    //By default the child will get parent1 gene unless the change value is greater the .50 then parent2 gene is passed on
    //    newDNA.set(i,parentDNA1.get(i));

    //    //Check if change should be made and if not more then half the DNA is changed
    //    if (changes.get(i)==1){
    //      newDNA.set(i,parentDNA2.get(i));
    //    }
    //   }

    //   return newDNA;
    //}

    public void ExportGenome(){
        for(int i=0;i<maxChromosomes-1;i++){
            //print(i);
            Chromosome Chromosome=chromosomes.get(i);
            for(int j=0;j<Chromosome.GetChromosomeSize();j++){
                float geneValue=chromosomes.get(i).GetGene(j);
                //print("(" + i + "," + j +"}" + geneValue);
            }
            //println();
        }
    }

}
