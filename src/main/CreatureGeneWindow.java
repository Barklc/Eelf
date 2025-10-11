package main;

import main.Creature.*;
import main.Genetics.GeneBase;
import main.Genetics.GeneID;
import main.Genetics.Genome;

import javax.swing.*;

public class CreatureGeneWindow {
    static JTextPane Stats;

    public CreatureGeneWindow(){
        //Creating the Frame
        JFrame frame = new JFrame("Creature Genes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);

        Stats = new JTextPane();
        Stats.setSize(400,1000);

        //Creating the panel at bottom and adding components
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Stats);
//        Stats1 = new JTextPane();
//        Stats1.setSize(400,1000);
//        panel.add(Stats1);

        JScrollPane pane= new JScrollPane(panel);
        //Adding Components to the frame.
        frame.getContentPane().add(pane);


        frame.setVisible(true);

    }
    String FloatToString(float value){
        return String.format("%.2f",value);
    }
    private String AddField(int offset, GeneBase gene, float geneValue){
        String tab = "    ";
        return tab + gene.GeneName() +
                //"      Description: " + gene.Description() + "\r\n" +
                tab.repeat(offset) + "Value: " + FloatToString(geneValue) +
                "     (" + FloatToString(gene.Minimum()) +
                " - " + FloatToString(gene.Maximum()) +
                ")     Mut:" + FloatToString(gene.GetMutationRate()) +
                //tab.repeat(offset) + "Starting: " + FloatToString(gene.StartingValue()) +
                //"     Allow Random : " + gene.RandomStartingValue() + "\r\n" +
                 "    Chr: " + gene.GetChromosome() +
                "     Gene Loc: " + gene.GetGeneLocationOnChromosome() + "\r\n";
    }
    private String AddSection(String Name, int offset){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + "\r\n";
    }
    public void Update(Creature CurrentCreature) {
        CreatureGeneValues Genes=CurrentCreature.GetGenes();
        Genome genome=Genes.GetBaseDNA();

        String BodyGenes=AddSection("Body",0);
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyLength),genome.GetGene(GeneID.BodyLength));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.HeadShape),genome.GetGene(GeneID.HeadShape));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyHeight),genome.GetGene(GeneID.BodyHeight));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyTaper),genome.GetGene(GeneID.BodyTaper));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyWidth),genome.GetGene(GeneID.BodyWidth));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.SkinToughness),genome.GetGene(GeneID.SkinToughness));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperPresent),genome.GetGene(GeneID.FlipperPresent));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperHeight),genome.GetGene(GeneID.FlipperHeight));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperWidth),genome.GetGene(GeneID.FlipperWidth));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailPresent),genome.GetGene(GeneID.TailPresent));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailHeightPercentage),genome.GetGene(GeneID.TailHeightPercentage));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailWidthPercentage),genome.GetGene(GeneID.TailWidthPercentage));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorRed),genome.GetGene(GeneID.TailColorRed));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorGreen),genome.GetGene(GeneID.TailColorGreen));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorBlue),genome.GetGene(GeneID.TailColorBlue));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorRed),genome.GetGene(GeneID.BodyColorRed));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorGreen),genome.GetGene(GeneID.BodyColorGreen));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorBlue),genome.GetGene(GeneID.BodyColorBlue));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyDistanceBetweenSegments),genome.GetGene(GeneID.BodyDistanceBetweenSegments));
        BodyGenes+="\r\n";

        String VisionGenes=AddSection("Vision",0);
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionAngle),genome.GetGene(GeneID.VisionAngle));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionClarity),genome.GetGene(GeneID.VisionClarity));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionDistance),genome.GetGene(GeneID.VisionDistance));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionScanFreq),genome.GetGene(GeneID.VisionScanFreq));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorRed),genome.GetGene(GeneID.EyeColorRed));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorGreen),genome.GetGene(GeneID.EyeColorGreen));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorBlue),genome.GetGene(GeneID.EyeColorBlue));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeSize),genome.GetGene(GeneID.EyeSize));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyesPresent),genome.GetGene(GeneID.EyesPresent));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionDominancePercentage),genome.GetGene(GeneID.VisionDominancePercentage));
        VisionGenes+="\r\n";

        String AgingGenes=AddSection("Aging",0);
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.LifeSpan),genome.GetGene(GeneID.LifeSpan));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.MatureAgePercentage),genome.GetGene(GeneID.MatureAgePercentage));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.SeniorAgePercentage),genome.GetGene(GeneID.SeniorAgePercentage));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxHealth),genome.GetGene(GeneID.MaxHealth));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.IncreaseHealthPercentage),genome.GetGene(GeneID.IncreaseHealthPercentage));
        AgingGenes+="\r\n";

        String MetabolismGenes=AddSection("Metabolism",0);
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.StomachSize),genome.GetGene(GeneID.StomachSize));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.DigestionRate),genome.GetGene(GeneID.DigestionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.PlantToEnergyConversionRate),genome.GetGene(GeneID.PlantToEnergyConversionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.MeatToEnergyConversionRate),genome.GetGene(GeneID.MeatToEnergyConversionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxStoredEnergy),genome.GetGene(GeneID.MaxStoredEnergy));
        MetabolismGenes+="\r\n";

        String MouthGenes=AddSection("Mouth",0);
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthSize),genome.GetGene(GeneID.MouthSize));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorRed),genome.GetGene(GeneID.MouthColorRed));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorGreen),genome.GetGene(GeneID.MouthColorGreen));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorBlue),genome.GetGene(GeneID.MouthColorBlue));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.BiteStrength),genome.GetGene(GeneID.BiteStrength));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthPresent),genome.GetGene(GeneID.MouthPresent));
        MouthGenes+="\r\n";

        String GenderGenes=AddSection("Gender",0);
        GenderGenes+="\r\n";
        //gep+=AddField(1,genome.GetGeneDef(GeneID),genome.GetGene(GeneID.));

        String ReproductionGenes=AddSection("Reproduction",0);
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthGestationEnergyCost),genome.GetGene(GeneID.BirthGestationEnergyCost));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthRecoveryTime),genome.GetGene(GeneID.BirthRecoveryTime));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthEnergyCost),genome.GetGene(GeneID.BirthEnergyCost));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.GestationPeriod),genome.GetGene(GeneID.GestationPeriod));
        ReproductionGenes+="\r\n";

        String PhysicsGenes=AddSection("Physics",0);
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MassPercentage),genome.GetGene(GeneID.MassPercentage));
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxTurnAngle),genome.GetGene(GeneID.MaxTurnAngle));
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MovementSpeed),genome.GetGene(GeneID.MovementSpeed));
        PhysicsGenes+="\r\n";

        String OlfactoryGenes=AddSection("Olfactory",0);
        OlfactoryGenes+=AddField(1,genome.GetGeneDef(GeneID.ReceptorsSensitivity),genome.GetGene(GeneID.ReceptorsSensitivity));
        OlfactoryGenes+=AddField(1,genome.GetGeneDef(GeneID.ScentDominancePercentage),genome.GetGene(GeneID.ScentDominancePercentage));
        OlfactoryGenes+="\r\n";

        Stats.setText(BodyGenes + VisionGenes + AgingGenes + MetabolismGenes + MouthGenes + GenderGenes + ReproductionGenes + PhysicsGenes + OlfactoryGenes);
        //Stats1.setText();
    }
}
