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
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyLength),genome.GetGeneValue(GeneID.BodyLength));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.HeadShape),genome.GetGeneValue(GeneID.HeadShape));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyHeight),genome.GetGeneValue(GeneID.BodyHeight));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyTaper),genome.GetGeneValue(GeneID.BodyTaper));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyWidth),genome.GetGeneValue(GeneID.BodyWidth));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.SkinToughness),genome.GetGeneValue(GeneID.SkinToughness));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperPresent),genome.GetGeneValue(GeneID.FlipperPresent));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperHeight),genome.GetGeneValue(GeneID.FlipperHeight));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.FlipperWidth),genome.GetGeneValue(GeneID.FlipperWidth));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailPresent),genome.GetGeneValue(GeneID.TailPresent));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailHeightPercentage),genome.GetGeneValue(GeneID.TailHeightPercentage));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailWidthPercentage),genome.GetGeneValue(GeneID.TailWidthPercentage));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorRed),genome.GetGeneValue(GeneID.TailColorRed));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorGreen),genome.GetGeneValue(GeneID.TailColorGreen));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.TailColorBlue),genome.GetGeneValue(GeneID.TailColorBlue));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorRed),genome.GetGeneValue(GeneID.BodyColorRed));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorGreen),genome.GetGeneValue(GeneID.BodyColorGreen));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyColorBlue),genome.GetGeneValue(GeneID.BodyColorBlue));
        BodyGenes+=AddField(1,genome.GetGeneDef(GeneID.BodyDistanceBetweenSegments),genome.GetGeneValue(GeneID.BodyDistanceBetweenSegments));
        BodyGenes+="\r\n";

        String VisionGenes=AddSection("Vision",0);
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionAngle),genome.GetGeneValue(GeneID.VisionAngle));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionClarity),genome.GetGeneValue(GeneID.VisionClarity));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionDistance),genome.GetGeneValue(GeneID.VisionDistance));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionScanFreq),genome.GetGeneValue(GeneID.VisionScanFreq));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorRed),genome.GetGeneValue(GeneID.EyeColorRed));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorGreen),genome.GetGeneValue(GeneID.EyeColorGreen));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeColorBlue),genome.GetGeneValue(GeneID.EyeColorBlue));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyeSize),genome.GetGeneValue(GeneID.EyeSize));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.EyesPresent),genome.GetGeneValue(GeneID.EyesPresent));
        VisionGenes+=AddField(1,genome.GetGeneDef(GeneID.VisionDominancePercentage),genome.GetGeneValue(GeneID.VisionDominancePercentage));
        VisionGenes+="\r\n";

        String AgingGenes=AddSection("Aging",0);
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.LifeSpan),genome.GetGeneValue(GeneID.LifeSpan));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.MatureAgePercentage),genome.GetGeneValue(GeneID.MatureAgePercentage));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.SeniorAgePercentage),genome.GetGeneValue(GeneID.SeniorAgePercentage));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxHealth),genome.GetGeneValue(GeneID.MaxHealth));
        AgingGenes+=AddField(1,genome.GetGeneDef(GeneID.IncreaseHealthPercentage),genome.GetGeneValue(GeneID.IncreaseHealthPercentage));
        AgingGenes+="\r\n";

        String MetabolismGenes=AddSection("Metabolism",0);
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.StomachSize),genome.GetGeneValue(GeneID.StomachSize));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.DigestionRate),genome.GetGeneValue(GeneID.DigestionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.PlantToEnergyConversionRate),genome.GetGeneValue(GeneID.PlantToEnergyConversionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.MeatToEnergyConversionRate),genome.GetGeneValue(GeneID.MeatToEnergyConversionRate));
        MetabolismGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxStoredEnergy),genome.GetGeneValue(GeneID.MaxStoredEnergy));
        MetabolismGenes+="\r\n";

        String MouthGenes=AddSection("Mouth",0);
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthSize),genome.GetGeneValue(GeneID.MouthSize));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorRed),genome.GetGeneValue(GeneID.MouthColorRed));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorGreen),genome.GetGeneValue(GeneID.MouthColorGreen));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthColorBlue),genome.GetGeneValue(GeneID.MouthColorBlue));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.BiteStrength),genome.GetGeneValue(GeneID.BiteStrength));
        MouthGenes+=AddField(1,genome.GetGeneDef(GeneID.MouthPresent),genome.GetGeneValue(GeneID.MouthPresent));
        MouthGenes+="\r\n";

        String GenderGenes=AddSection("Gender",0);
        GenderGenes+="\r\n";
        //gep+=AddField(1,genome.GetGeneDef(GeneID),genome.GetGene(GeneID.));

        String ReproductionGenes=AddSection("Reproduction",0);
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthGestationEnergyCost),genome.GetGeneValue(GeneID.BirthGestationEnergyCost));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthRecoveryTime),genome.GetGeneValue(GeneID.BirthRecoveryTime));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.BirthEnergyCost),genome.GetGeneValue(GeneID.BirthEnergyCost));
        ReproductionGenes+=AddField(1,genome.GetGeneDef(GeneID.GestationPeriod),genome.GetGeneValue(GeneID.GestationPeriod));
        ReproductionGenes+="\r\n";

        String PhysicsGenes=AddSection("Physics",0);
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MassPercentage),genome.GetGeneValue(GeneID.MassPercentage));
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MaxTurnAngle),genome.GetGeneValue(GeneID.MaxTurnAngle));
        PhysicsGenes+=AddField(1,genome.GetGeneDef(GeneID.MovementSpeed),genome.GetGeneValue(GeneID.MovementSpeed));
        PhysicsGenes+="\r\n";

        String OlfactoryGenes=AddSection("Olfactory",0);
        OlfactoryGenes+=AddField(1,genome.GetGeneDef(GeneID.ReceptorsSensitivity),genome.GetGeneValue(GeneID.ReceptorsSensitivity));
        OlfactoryGenes+=AddField(1,genome.GetGeneDef(GeneID.ScentDominancePercentage),genome.GetGeneValue(GeneID.ScentDominancePercentage));
        OlfactoryGenes+="\r\n";

        Stats.setText(BodyGenes + VisionGenes + AgingGenes + MetabolismGenes + MouthGenes + GenderGenes + ReproductionGenes + PhysicsGenes + OlfactoryGenes);
        //Stats1.setText();
    }
}
