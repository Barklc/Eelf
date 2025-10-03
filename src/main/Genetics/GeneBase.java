package main.Genetics;

import static main.Main.gUtils;

public class GeneBase{
    private final int geneID;
    private final boolean active;
    private final float minimum;
    private final float maximum;
    private final String description;
    private final float mutationFactor;
    private final String geneName;
    private final int geneLocation;
    private final float startingValue;
    private final boolean randomStartingValue;
    private final int chromosome;
    private float MutationRate;
    private float randomBand=0f;

    public GeneBase(boolean Active,float MinValue, float MaxValue, float MutationFactor, float StartingValue, boolean RandomStartingValue, String GeneDescription, int id, int gl, String gn,int chr){
        active=Active;
        minimum = MinValue;
        maximum = MaxValue;
        mutationFactor=MutationFactor;
        startingValue=StartingValue;
        randomStartingValue=RandomStartingValue;
        description = GeneDescription;
        geneName=gn;
        geneID=id;
        geneLocation=gl;
        chromosome=chr;
        MutationRate=0.10f;
     }

    public int GeneID(){
        return geneID;
    }

    public boolean Active(){
        return active;
    }
    public float Minimum(){
        return minimum;
    }
    public float Maximum(){
        return maximum;
    }
    public String Description(){
        return description;
    }

    public float MutationFactor(){
        return mutationFactor;
    }

    public float StartingValue(){
        return startingValue;
    }

    public boolean RandomStartingValue(){
        return randomStartingValue;
    }

    public String GeneName(){
        return geneName;
    }

    public int GetChromosome(){
        return chromosome;
    }

    public int GetGeneLocationOnChromosome(){
        return geneLocation;
    }

    public float GetMutationRate(){
        return MutationRate;
    }

    public void SetMutationRate(float value){
        MutationRate=value;
    }

    public float Mutation(float value){
        if (gUtils.GetRandomNumber(.01f,1f)<=MutationRate){
            value=value+gUtils.GetRandomNumber(-mutationFactor,mutationFactor);
            if (value<minimum) value=minimum;
            if (value>maximum) value=maximum;
        }
        return value;
    }

    public float RandomValue(){
        return RandomValue(false,randomBand);
    }

    public float RandomValue(boolean EvenOnly){
        return RandomValue(EvenOnly,randomBand);
    }

    public float RandomValue(boolean EvenOnly,float range){
        float returnvalue = startingValue;
        if (randomStartingValue){
//            float totalRange=maximum-minimum;
//            float mid=(totalRange/2);
//            float min=mid-(mid*range/2);
//            float max=mid+(mid*range/2);
//            returnvalue = gUtils.GetRandomNumber(min+minimum, max+minimum);
            if (minimum==maximum)
                {returnvalue=minimum;
            } else {
                returnvalue = gUtils.GetRandomNumber(minimum, maximum);
            }

            //if (EvenOnly)
            //   if (returnvalue % 2 != 0.0)
           //         returnvalue++;

            if (returnvalue<minimum){returnvalue=minimum;}
            if (returnvalue>maximum){returnvalue=maximum;}
        }
        return returnvalue;
    }

    public void SetRandomBand(float value){
        randomBand=value;
    }


}
