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
        frame.setSize(800, 1000);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Stats = new JTextPane();
        Stats.setSize(400,1000);
        panel.add(Stats);

        Stats1 = new JTextPane();
        Stats1.setSize(400,1000);
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
        CreatureMetabolism Metabolism=CurrentCreature.GetMetabolism();

        String cp=AddSection("Creature Info",0);
        cp+=AddField("ID",1, CurrentCreature.GetUUID().toString());
        cp+=AddField("Alive",1,Vitals.IsAlive());
        cp+=AddField("Current Action",1,CurrentCreature.GetCurrentAction().toString());
        cp+=AddField("Hungry",1,Vitals.IsHungry());
        cp+=AddField("Pregnant",1,false);
        cp+=AddField("Speed",1,Genes.GetSpeed(),Physics.GetCurrentSpeed());
        cp+=AddField("MaxTurnAngle",1,Genes.GetMaxTurnAngle(),Physics.GetCurrentTurnAngle());
        cp+=AddSection("Location Info",1);
        cp+=AddField("Type",2,CurrentCreature.GetTargetObject().ObjectTypeInRange().toString());
        cp+=AddField("X",2,CurrentCreature.GetTargetObject().X(),Vitals.GetX());
        cp+=AddField("Y",2,CurrentCreature.GetTargetObject().Y(),Vitals.GetY());
        cp+=AddField("Distance to Target",2,CurrentCreature.GetDistanceToTarget(),CurrentCreature.GetPreviousDistanceToTarget());

        String bp=AddSection("Body Info",0);
        bp+=AddField("Length",1,Genes.GetBodyLength(),Body.GetBodyLength());
        bp+=AddField("Width",1,Genes.GetBodyWidth(),Body.GetCurrentBodyWidth());
        bp+=AddField("Height",1,Genes.GetBodyHeight(),Body.GetCurrentBodyHeight());
        bp+=AddField("Tapper",1,Genes.GetBodyTaper());
        bp+=AddField("Color",1,Genes.GetBodyColor(),Body.GetCurrentHeadColor());
        bp+=AddSection("Mouth Info",1);
        bp+=AddField("Present",2,Genes.GetMouthPresent(),Body.GetMouthPresent());
        bp+=AddField("Size",2,Genes.GetMouthSize(),Body.GetCurrentMouthSize());
        bp+=AddField("Bite Strength",2,Genes.GetBiteStrength(),Body.GetCurrentBiteStrength());
        bp+=AddField("Color",2,Genes.GetMouthColor(),Body.GetCurrentMouthColor());
        bp+=AddSection("Eye Info",1);
        bp+=AddField("Present",2,Genes.GetEyesPresent(),Body.GetEyesPresent());
        bp+=AddField("Size",2,Genes.GetMouthSize(),Body.GetCurrentMouthSize());
        bp+=AddField("Color",2,Genes.GetEyeColor(),Body.GetCurrentEyesColor());
        bp+=AddSection("Flippers Info",1);
        bp+=AddField("Present",2,Genes.GetFlipperPresent(),Body.GetFlipperPresent());
        bp+=AddField("Width",2,Genes.GetFlipperWidth(),Body.GetCurrentFlipperWidth());
        bp+=AddField("Height",2,Genes.GetFlipperHeight(),Body.GetCurrentFlipperHeight());
        bp+=AddField("Color",2,Genes.GetFlipperColor(),Body.GetCurrentFlipperColor());
        bp+=AddSection("Tail Info",1);
        bp+=AddField("Present",2,Genes.GetTailPresent(),Body.GetTailPresent());
        bp+=AddField("Width",2,Genes.GetTailWidthPercentage(),Body.GetCurrentTailWidth());
        bp+=AddField("Height",2,Genes.GetTailHeightPercentage(),Body.GetCurrentTailHeight());
        bp+=AddField("Color",2,Genes.GetFlipperColor(),Body.GetCurrentTailColor());
        bp+=AddField("Modifier",2,Physics.GetTailSpeedMod(),Physics.GetCurrentTailSpeedMod());

        String ap=AddSection("Age Info",0);
        ap+=AddField("Age",1, Vitals.GetLifeSpan(), Vitals.GetAge());
        ap+=AddField("Maturity",1,Vitals.GetMaturity());
        ap+=AddField("Maturity",1,Genes.GetMatureAgePercentage(),Vitals.GetMaturityAge());
        ap+=AddField("Senior",1,Genes.GetSeniorAgePercentage(),Vitals.GetSeniorAge());

        String mp=AddSection("Metabolism Info",0);
        mp+=AddSection("Digestion Info",1);
        mp+=AddField("StomachSize",2,Genes.GetStomachSize(),Vitals.GetCurrentStomachSize());
        mp+=AddSection("Stomach Content",2);
        mp+=AddField("Total",3,Vitals.GetTotalStomachContent());
        mp+=AddField("Plant",3,Vitals.GetPlantStomachContent());
        mp+=AddField("Meat",3,Vitals.GetMeatStomachContent());
        mp+=AddField("DigestionRate",2,Genes.GetDigestionRate());
        mp+=AddField("Plant To Energy Conversion Rate",2,Genes.GetPlantToEnergyConversionRate());
        mp+=AddField("Meat To Energy Conversion Rate",2,Genes.GetMeatToEnergyConversionRate());
        mp+=AddSection("Health Info",1);
        mp+=AddField("Health",2,Genes.GetMaxHealth(),Vitals.GetHealth());
        mp+=AddSection("Energy Info",1);
        mp+=AddField("Max Energy Storage",2,Genes.GetMaxStoredEnergy(),Vitals.GetCurrentMaxEnergyStorage());
        mp+=AddField("Energy Stored",2,Vitals.GetEnergyLevel());

        String vp=AddSection("Vision Info",0);
        vp+=AddField("Angle",1,Genes.GetVisionAngle());
        vp+=AddField("Distance",1,Genes.GetVisionDistance());
        vp+=AddField("Clarity",1,Genes.GetVisionClarity());
        vp+=AddField("Scan Freq",1,Genes.GetVisionScanFreq());

        String rp=AddSection("Reproduction Info",0);
        rp+=AddField("Birth Recovery Time",1,Genes.GetBirthRecoveryTime(),Vitals.GetBirthRecoveryTime());
        rp+=AddField("Energy Birth Gestation Energy Cost",1,Genes.GetBirthGestationEnergyCost());
        rp+=AddField("Gestation Period",1,Genes.GetGestationPeriod(),Vitals.GetGestationPeriodCountDown());
        rp+=AddField("Birth Energy Cost",1,Genes.GetBirthEnergyCost());

        Stats.setText(cp+bp+ap);
        Stats1.setText(mp+rp+vp);
    }
}
