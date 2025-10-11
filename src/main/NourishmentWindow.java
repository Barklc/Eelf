package main;

import main.Nourishments.Nourishment;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NourishmentWindow {
    static JTextPane Stats;
    public NourishmentWindow(){
        //Creating the Frame
        JFrame frame = new JFrame("Creature Nourishment Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 1000);

        Stats = new JTextPane();
        Stats.setSize(200,1000);

        //Creating the panel at bottom and adding components
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Stats);

        JScrollPane pane= new JScrollPane(panel);
        //Adding Components to the frame.
        frame.getContentPane().add(pane);

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

    private String AddField(String Name, int offset, Color GeneValue){
        String tab = "    ";
        return tab.repeat(Math.max(0, offset)) +
                Name + ": (" + GeneValue.getRed() + "," + GeneValue.getGreen() + "," + GeneValue.getBlue() + ") " + "\r\n";
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
    public void Update(ArrayList<Nourishment> nourishments) {
        StringBuilder NourishmentStats= new StringBuilder(AddSection("Nourishment Info", 0));
        for(int i=0;i<nourishments.size(); i++) {
            Nourishment currentNourishment = nourishments.get(i);
            NourishmentStats.append(AddField("ID", 1, (float) i));
            NourishmentStats.append(AddField("Type", 2, currentNourishment.NourishmentType().toString()));
            NourishmentStats.append(AddField("Mass", 2, currentNourishment.GetNourishmentMass()));
            NourishmentStats.append(AddField("Size", 2, currentNourishment.GetNourishmentSize()));
            NourishmentStats.append(AddField("Scent Strength", 2, currentNourishment.GetNourishmentScentStrength()));
            NourishmentStats.append(AddField("Scent", 2, currentNourishment.GetNourishmentScent()));
            NourishmentStats.append(AddField("Color", 2, currentNourishment.GetNourishmentColor()));
        }

        Stats.setText(NourishmentStats.toString());

    }
}
