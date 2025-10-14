package main;

import main.Creature.*;

import javax.swing.*;
import java.awt.*;

public class CreatureStatsWindow {
    static JTextPane Stats;
    static JTextPane Stats1;
    public CreatureStatsWindow(){
        //Creating the Frame
        JFrame frame = new JFrame("Creature Stats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Stats = new JTextPane();
        Stats.setSize(300,1000);
        panel.add(Stats);

        Stats1 = new JTextPane();
        Stats1.setSize(300,1000);
        panel.add(Stats1);

        //Adding Components to the frame.
        frame.getContentPane().add(panel);


        frame.setVisible(true);

    }
    String FloatToString(float value){
        return String.format("%.2f",value);
    }
    private String AddField(String Name, int offset, float GeneValue, float VitalsValue){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": (" + FloatToString(GeneValue) + ") " + FloatToString(VitalsValue) + "\r\n";
    }
    private String AddField(String Name, int offset, float GeneValue, boolean VitalsValue){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": (" + FloatToString(GeneValue)+ ") " + VitalsValue + "\r\n";
    }

    private String AddField(String Name, int offset, Color GeneValue, Color VitalsValue){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": (" + GeneValue.getRed() + "," + GeneValue.getGreen() + "," + GeneValue.getBlue() + ") " + VitalsValue.getRed() + "," + VitalsValue.getGreen() + "," + VitalsValue.getBlue() + "\r\n";
    }
    private String AddField(String Name, int offset, String Value){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": " + Value + "\r\n";
    }
    private String AddField(String Name, int offset, boolean Value){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": " + Value + "\r\n";
    }
    private String AddField(String Name, int offset, float Value){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": " + FloatToString(Value) + "\r\n";
    }
    private String AddSection(String Name, int offset){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + "\r\n";
    }
    public void Update(Creature CurrentCreature) {
        CreatureVitals Vitals=CurrentCreature.GetVitals();
        CreatureBody Body=CurrentCreature.GetBody();
        CreaturePhysics Physics=CurrentCreature.GetPhysics();
        CreatureGeneValues Genes=CurrentCreature.GetGenes();

        String GeneralStats=AddSection("Creature Info",0);
        GeneralStats+=AddField("ID",1, CurrentCreature.GetUUID().toString());
        GeneralStats+=AddField("Alive",1,Vitals.IsAlive());
        GeneralStats+=AddField("Current Action",1,CurrentCreature.GetCurrentAction().toString());
        GeneralStats+=AddField("Hungry",1,Vitals.IsHungry());
        GeneralStats+=AddField("Pregnant",1,false);
        GeneralStats+=AddField("Speed",1,Genes.GetSpeed(),Physics.GetCurrentSpeed());
        GeneralStats+=AddField("Tail Speed Modifier",2,Physics.GetTailSpeedMod(),Physics.GetCurrentTailSpeedMod());
        GeneralStats+=AddField("Mass Speed Modifier", 2,Physics.GetMassSpeedMod(),Physics.GetCurrentMassSpeedMod());
        GeneralStats+=AddField("MaxTurnAngle",1,Genes.GetMaxTurnAngle(),Physics.GetCurrentTurnAngle());
        GeneralStats+=AddSection("Location Info",1);
        GeneralStats+=AddField("Type",2,CurrentCreature.GetTargetObject().ObjectTypeInRange().toString());
        GeneralStats+=AddField("X",2,CurrentCreature.GetTargetObject().X(),Vitals.GetX());
        GeneralStats+=AddField("Y",2,CurrentCreature.GetTargetObject().Y(),Vitals.GetY());
        GeneralStats+=AddField("Radius",2,CurrentCreature.GetTargetObject().GetRadius());
        GeneralStats+=AddField("Distance to Target",2,CurrentCreature.GetDistanceToTarget(),CurrentCreature.GetPreviousDistanceToTarget());

        String BodyStats=AddSection("Body Info",0);
        BodyStats+=AddField("Length",1,Genes.GetBodyLength(),Body.GetBodyLength());
        BodyStats+=AddField("Mass",1,Body.GetBodyMass(),Body.GetCurrentBodyMass());
        BodyStats+=AddField("Width",1,Genes.GetBodyWidth(),Body.GetCurrentBodyWidth());
        BodyStats+=AddField("Height",1,Genes.GetBodyHeight(),Body.GetCurrentBodyHeight());
        BodyStats+=AddField("Distance Between Segments",1,Genes.GetBodyDistanceBetweenSegments(),Body.GetCurrentBodyDistanceBetweenSegments());
        BodyStats+=AddField("Tapper",1,Genes.GetBodyTaper());
        BodyStats+=AddField("Color",1,Genes.GetBodyColor(),Body.GetCurrentHeadColor());

        BodyStats+=AddSection("Mouth Info",1);
        BodyStats+=AddField("Present",2,Genes.GetMouthPresent(),Body.GetMouthPresent());
        if (Body.GetMouthPresent()) {
            BodyStats += AddField("Connect Segment", 2, Body.GetMouthSegment().GetSegmentConnectedTo());
        }
        BodyStats+=AddField("Size",2,Genes.GetMouthSize(),Body.GetCurrentMouthSize());
        BodyStats+=AddField("Bite Strength",2,Genes.GetBiteStrength(),Body.GetCurrentBiteStrength());
        BodyStats+=AddField("Color",2,Genes.GetMouthColor(),Body.GetCurrentMouthColor());
        BodyStats+=AddSection("Eye Info",1);
        BodyStats+=AddField("Present",2,Genes.GetEyesPresent(),Body.GetEyesPresent());
        if (Body.GetEyesPresent()) {
            BodyStats += AddField("Connect Segment", 2, Body.GetEyesSegment().GetSegmentConnectedTo());
        }
        BodyStats+=AddField("Size",2,Genes.GetMouthSize(),Body.GetCurrentMouthSize());
        BodyStats+=AddField("Color",2,Genes.GetEyeColor(),Body.GetCurrentEyesColor());
        BodyStats+=AddSection("Flippers Info",1);
        BodyStats+=AddField("Present",2,Genes.GetFlipperPresent(),Body.GetFlipperPresent());
        if (Body.GetFlipperPresent()) {
            BodyStats += AddField("Connect Segment", 2, Body.GetFlippersSegment().GetSegmentConnectedTo());
        }
        BodyStats+=AddField("Width",2,Genes.GetFlipperWidth(),Body.GetCurrentFlipperWidth());
        BodyStats+=AddField("Height",2,Genes.GetFlipperHeight(),Body.GetCurrentFlipperHeight());
        BodyStats+=AddField("Color",2,Genes.GetFlipperColor(),Body.GetCurrentFlipperColor());
        BodyStats+=AddSection("Tail Info",1);
        BodyStats+=AddField("Present",2,Genes.GetTailPresent(),Body.GetTailPresent());
        if (Body.GetTailPresent()){
            BodyStats += AddField("Connect Segment", 2, Body.GetTailSegment().GetSegmentConnectedTo());
        }
        BodyStats+=AddField("Width",2,Genes.GetTailWidthPercentage(),Body.GetCurrentTailWidth());
        BodyStats+=AddField("Height",2,Genes.GetTailHeightPercentage(),Body.GetCurrentTailHeight());
        BodyStats+=AddField("Color",2,Genes.GetFlipperColor(),Body.GetCurrentTailColor());

        String AgeStats=AddSection("Age Info",0);
        AgeStats+=AddField("Age",1, Vitals.GetLifeSpan(), Vitals.GetAge());
        AgeStats+=AddField("Maturity",1,Vitals.GetMaturity());
        AgeStats+=AddField("Maturity",1,Genes.GetMatureAgePercentage(),Vitals.GetMaturityAge());
        AgeStats+=AddField("Senior",1,Genes.GetSeniorAgePercentage(),Vitals.GetSeniorAge());

        String MetabolismStats=AddSection("Metabolism Info",0);
        MetabolismStats+=AddSection("Digestion Info",1);
        MetabolismStats+=AddField("StomachSize",2,Genes.GetStomachSize(),Vitals.GetCurrentStomachSize());
        MetabolismStats+=AddSection("Stomach Content",2);
        MetabolismStats+=AddField("Total",3,Vitals.GetTotalStomachContent());
        MetabolismStats+=AddField("Plant",3,Vitals.GetPlantStomachContent());
        MetabolismStats+=AddField("Meat",3,Vitals.GetMeatStomachContent());
        MetabolismStats+=AddField("DigestionRate",2,Genes.GetDigestionRate());
        MetabolismStats+=AddField("Plant To Energy Conversion Rate",2,Genes.GetPlantToEnergyConversionRate());
        MetabolismStats+=AddField("Meat To Energy Conversion Rate",2,Genes.GetMeatToEnergyConversionRate());
        MetabolismStats+=AddField("Dietary Preference",2,Vitals.GetDietaryPreference().toString());
        MetabolismStats+=AddSection("Health Info",1);
        MetabolismStats+=AddField("Health",2,Genes.GetMaxHealth(),Vitals.GetHealth());
        MetabolismStats+=AddSection("Energy Info",1);
        MetabolismStats+=AddField("Max Energy Storage",2,Genes.GetMaxStoredEnergy(),Vitals.GetCurrentMaxEnergyStorage());
        MetabolismStats+=AddField("Energy Stored",2,Vitals.GetEnergyLevel());

        String VisionStats=AddSection("Vision Info",0);
        VisionStats+=AddField("Angle",1,(float)Math.toDegrees(Genes.GetVisionAngle()));
        VisionStats+=AddField("Distance",1,Genes.GetVisionDistance());
        VisionStats+=AddField("Clarity",1,Genes.GetVisionClarity());
        VisionStats+=AddField("Scan Freq",1,Genes.GetVisionScanFreq());
        VisionStats+=AddField("VisionDominance",1,Genes.GetVisionDominancePercentage());

        String ReproductionStats=AddSection("Reproduction Info",0);
        ReproductionStats+=AddField("Birth Recovery Time",1,Genes.GetBirthRecoveryTime(),Vitals.GetBirthRecoveryTime());
        ReproductionStats+=AddField("Energy Birth Gestation Energy Cost",1,Genes.GetBirthGestationEnergyCost());
        ReproductionStats+=AddField("Gestation Period",1,Genes.GetGestationPeriod(),Vitals.GetGestationPeriodCountDown());
        ReproductionStats+=AddField("Birth Energy Cost",1,Genes.GetBirthEnergyCost());

        String OlfactoryStats=AddSection("Olfactory",0);
        OlfactoryStats+=AddField("ScentDominance",1,Genes.GetScentDominancePercentage());

        Stats.setText(GeneralStats + BodyStats + AgeStats);
        Stats1.setText(MetabolismStats + ReproductionStats + VisionStats + OlfactoryStats);
    }
}
