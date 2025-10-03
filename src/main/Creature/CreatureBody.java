package main.Creature;

import main.GameParameters;
import main.FlagsOverride;
import main.Creature.BodySegments.*;
import java.awt.*;
import java.util.ArrayList;

public class CreatureBody{

    private Creature CurrentCreature;
    private CreatureGeneValues CGV;
    private CreatureVitals Vitals;
    private ArrayList<BodySegment> Body;
    private int HeadSegmentID;
    private int MouthSegmentID;
    private int EyesSegmentID;
    private int FlippersSegmentID;
    private int TailSegmentID;
    private float Mass;

    //CGV parameter variables
    private final float HeadShape;
    private final boolean MouthPresent;
    private boolean FlipperPresent;
    private boolean TailPresent;

    private boolean EyesPresent;

    //create cgv parameter
    public CreatureBody(Creature currentCreature){
        CurrentCreature=currentCreature;
        CGV=CurrentCreature.GetGenes();
        Vitals=CurrentCreature.GetVitals();

        //class variables
        Body=new ArrayList<BodySegment>();

        HeadSegmentID=0;
        MouthSegmentID=-1;
        EyesSegmentID=-1;
        FlippersSegmentID=-1;
        TailSegmentID=-1;


        //get gene values
        HeadShape=CGV.GetHeadShape();

        MouthPresent=(CGV.GetBiteStrength()>=GameParameters.MouthPresentThreshold);
        if (FlagsOverride.MouthPresentOverride){
            MouthPresent=true;
        }
        FlipperPresent=(CGV.GetFlipperPresent()>=GameParameters.FlipperPresentThreshold);
        if (FlagsOverride.FlipperPresentOverride){
            FlipperPresent=true;
        }
        TailPresent=(CGV.GetTailPresent()>=GameParameters.TailPresentThreshold);
        if (FlagsOverride.TailPresentOverride){
            TailPresent=true;
        }
        EyesPresent=(CGV.GetEyesPresent()>GameParameters.EyesPresentThreshold);
        if (FlagsOverride.EyesPresentOverride){
            EyesPresent=true;
        }

    }

    public void CreateBody(float x,float y){
        float WidthOfCurrentSegment=0;
        float HeightOfCurrentSegment=0;


        Head h=new Head();
        //InitializeSegment(float x,float y,float w, float h, float a, float d,color c)
        h.InitializeSegment(x,y,GetCurrentBodyWidth(),GetCurrentBodyHeight(),0,GetCurrentBodyDistanceBetweenSegments(),GetCurrentHeadColor());
        h.SetHeadShape(HeadShape);
        HeadSegmentID=0;
        Body.add(h);


        for(int i=1;i<GetBodyLength()-1;i++){
            HeightOfCurrentSegment=HeightOfCurrentSegment-i*(HeightOfCurrentSegment/(GetBodyLength()-1));
            Segment s=new Segment();
            s.InitializeSegment(x-i*HeightOfCurrentSegment,y,GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),i,GetBodyLength()),GetCurrentBodyHeight()-DetermineTaper(GetCurrentBodyWidth(),i,GetBodyLength()),0,GetCurrentBodyDistanceBetweenSegments(),GetCurrentSegmentsColor(i));
            s.SetSegmentConnectedTo(i-1);
            Body.add(s);
        }

        if (MouthPresent){
            Mouth m=new Mouth();
            m.SetMouthSize(GetCurrentMouthSize());
            m.SetBiteStrength(GetCurrentBiteStrength());
            m.SetSegmentConnectedTo(GameParameters.MouthSegmentConnected);
            m.SetMouthColor(GetCurrentMouthColor());
            Body.add(m);
            MouthSegmentID=Body.size()-1;
        }
        if (EyesPresent){
            Eyes e=new Eyes();
            e.InitializeSegment(x,y,GetCurrentBodyWidth(),GetCurrentBodyHeight(),0,GetCurrentBodyDistanceBetweenSegments(),GetCurrentHeadColor());
            e.SetEyeSize(GetCurrentEyeSize());
            e.SetSegmentConnectedTo(GameParameters.EyesSegmentConnected);
            e.SetEyeColor(GetCurrentEyesColor());
            Body.add(e);
            EyesSegmentID=Body.size()-1;
        }
        if (FlipperPresent){
            Flippers f=new Flippers();
            f.InitializeSegment(x,y,GetCurrentBodyWidth(),GetCurrentBodyHeight(),0,GetCurrentBodyDistanceBetweenSegments(), GetCurrentFlipperColor());
            f.SetFlipperWidth(GetCurrentFlipperWidth());
            f.SetFlipperHeight(GetCurrentFlipperHeight());
            f.SetSegmentConnectedTo(GameParameters.FlippersSegmentConnected);
            Body.add(f);
            FlippersSegmentID=Body.size()-1;
        }
        if(TailPresent){
            Tail t=new Tail();
            t.InitializeSegment(x-(GetBodyLength()-GameParameters.TailSegmentOffset)*HeightOfCurrentSegment,
                    y,
                    GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),GetBodyLength()-GameParameters.TailSegmentOffset,GetBodyLength()),
                    GetCurrentBodyHeight()-DetermineTaper(GetCurrentBodyWidth(),GetBodyLength()-GameParameters.TailSegmentOffset,GetBodyLength()),
                    0,GetCurrentBodyDistanceBetweenSegments(),
                    GetCurrentTailColor());
            t.SetTailWidth(GetCurrentTailWidth());
            t.SetTailHeight(GetCurrentTailHeight());
            t.SetSegmentConnectedTo(GetBodyLength()- GameParameters.TailSegmentOffset);
            Body.add(t);
            TailSegmentID=Body.size()-1;
        }

        //println("CreatureBody.CreateBody - CalculateBodyMass: " + CalculateBodyMass());
    }

    public void UpdateBody(){

        float WidthOfCurrentSegment=0;
        float HeightOfCurrentSegment=0;

        BodySegment bodySegment= GetBodySegment(0);
        BodySegment previousSegment=bodySegment;

        Head head=(Head) bodySegment;
        head.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
        head.SetSegmentWidth(GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),0,GetBodyLength()));
        head.SetSegmentHeight(GetCurrentBodyHeight());
        previousSegment=bodySegment;
        Body.set(0,head);

        for(int i=1;i<GetBodyLength();i++) {
            bodySegment = GetBodySegment(i);
            bodySegment.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
            bodySegment.SetSegmentWidth(GetCurrentBodyWidth() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(GetCurrentBodyHeight() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(GetCurrentBodyHeight());
            previousSegment = bodySegment;
            Body.set(i, bodySegment);
        }

        int count=GetBodyLength();
        bodySegment=GetBodySegment(count);
        if(bodySegment.BodySegmentType()==SegmentID.Mouth) {
            Mouth mouth=(Mouth) bodySegment;
            mouth.SetMouthSize(GetCurrentMouthSize());
            mouth.SetBiteStrength(GetCurrentBiteStrength());
            mouth.UpdateSegment(GetBodySegment(mouth.GetSegmentConnectedTo()));
            Body.set(count++,mouth);
            if (GetTotalBodySegmentLength()<count){bodySegment=GetBodySegment(count);}
        }
        if (bodySegment.BodySegmentType()==SegmentID.Eyes){
            Eyes eyes=(Eyes) bodySegment;
            eyes.SetEyeSize(GetCurrentEyeSize());
            eyes.SetEyeColor(CGV.GetEyeColor());
            eyes.UpdateSegment(GetBodySegment(eyes.GetSegmentConnectedTo()));
            Body.set(count++,eyes);
            if (GetTotalBodySegmentLength()<count){bodySegment=GetBodySegment(count);}
        }
        if (bodySegment.BodySegmentType() == SegmentID.Flippers) {
            Flippers flippers=(Flippers) bodySegment;
            flippers.SetFlipperHeight(GetCurrentFlipperHeight());
            flippers.SetFlipperWidth(GetCurrentFlipperWidth());
            flippers.UpdateSegment(GetBodySegment(flippers.GetSegmentConnectedTo()));
            Body.set(count,flippers);
            if (GetTotalBodySegmentLength()<count){bodySegment=GetBodySegment(count);}
        }
        if (bodySegment.BodySegmentType()==SegmentID.Tail){
            Tail tail=(Tail) bodySegment;
            tail.SetTailHeight(GetCurrentTailHeight());
            tail.SetTailWidth(GetCurrentTailWidth());
            bodySegment.UpdateSegment(GetBodySegment(tail.GetSegmentConnectedTo()));
            Body.set(count,tail);
        }

        //    float finalRadius=0;
        //    float r1=cgv.GetBodyHeight()*gr;
        //    float w1=cgv.GetBodyWidth()*gr;
        //    for (int i=0; i< cgv.GetBodyLen(); i++){
        //      float r=r1 - i*(r1/(cgv.GetBodyLen()-1));
        //      float w=w1 - i*(w1/(cgv.GetBodyLen()-1)); //+((w1/(cgv.GetBodyLen()-1)*(cgv.GetBodyTaper())));
        //      finalRadius=r;
        //      //Segment(x, y, angle, distance, radius and hue
        //      Segment s=body.get(i);
        //      s.segmentWidth=w;
        //      if (Pregnent() && i==2){
        //        finalRadius=r+(cgv.GetGestationPeriod()-gestationCountdown);
        //      }
        //      if (!Pregnent() && birthCooldown>0 && (i==2)){
        //          float nr=r + (s.segmentHeight-r)/2;
        //          if (nr<r){
        //            finalRadius=r;
        //          } else{
        //            finalRadius=nr;
        //          }
        //      }
        //      if (!Pregnent() && birthCooldown==0){
        //        finalRadius=r;
        //      }
        //
        //      s.segmentHeight=finalRadius;
        //
        //      s.segmentDistance=r;
        //      s.segmentColor=DetermineBodyColor(i);
        //      if (i==cgv.GetBodyLen()-1 && (GetTailPresent() || gTailPresentFlag)){
        //        s.segmentHeight=GetTailHeight();
        //        s.segmentWidth=GetTailWidth();
        //        s.segmentDistance=GetTailWidth();
        //        s.segmentColor=DetermineBodyColor(i);
        //      }
        //      body.set(i,s);
        //    }
    }

    public BodySegment GetHeadSegment(){
        return Body.get(HeadSegmentID);
    }

    public void SetHeadSegment(BodySegment segment){
        Body.set(HeadSegmentID,segment);
    }
    public BodySegment GetMouthSegment(){
        if(MouthSegmentID==-1)
            return null;
        else
            return Body.get(MouthSegmentID);
    }

    public void SetMouthSegment(BodySegment segment){
        Body.set(MouthSegmentID,segment);
    }
    public BodySegment GetEyesSegment(){
        if(EyesSegmentID==-1)
            return null;
        else
            return Body.get(EyesSegmentID);
    }

    public void SetEyesSegment(BodySegment segment){
        Body.set(EyesSegmentID,segment);
    }
    public BodySegment GetFlippersSegment(){
        if(FlippersSegmentID==-1)
            return null;
        else
            return Body.get(FlippersSegmentID);
    }

    public void SetFlippersSegment(BodySegment segment){
        Body.set(FlippersSegmentID,segment);
    }
    public BodySegment GetTailSegment(){
        if(TailSegmentID==-1)
            return null;
        else
            return Body.get(TailSegmentID);
    }

    public void SetTailSegment(BodySegment segment){
        Body.set(TailSegmentID,segment);
    }
    public BodySegment GetBodySegment(int value){
        return Body.get(value);
    }

    public void SetBodySegment(int value,BodySegment segment){
        Body.set(value,segment);
    }

    public float GetBodyMass(){
        return Mass;
    }

    public float CalculateBodyMass(){
        Mass=0;
        for (BodySegment bs : Body) {
            SegmentID si = bs.BodySegmentType();
            if (si == SegmentID.Head || si == SegmentID.Segment) {
                Mass += bs.GetSegmentWidth() + bs.GetSegmentHeight();
            }
        }
        return Mass;
    }

    float DetermineTaper(float width, float count,float length){
        return count*(width/length);
    }
    public float GetCurrentBodyDistanceBetweenSegments(){return CGV.GetBodyDistanceBetweenSegments() * Vitals.GetMaturity();}

    public int GetBodyLength(){
        return (int) Math.floor(CGV.GetBodyLength());
    }
    public int GetTotalBodySegmentLength(){
        return Body.size();
    }
    public float GetCurrentBodyHeight(){return CGV.GetBodyHeight() * Vitals.GetMaturity();}
    //public void SetCurrentBodyHeight(float value){CurrentBodyHeight=value;}
    public float GetCurrentBodyWidth(){return CGV.GetBodyWidth() * Vitals.GetMaturity();}
    //public void SetCurrentBodyWidth(float value){CurrentBodyWidth=value;}
    public float GetCurrentFlipperHeight(){return CGV.GetFlipperHeight() * Vitals.GetMaturity();}
    //public void SetCurrentFlipperHeight(float value){CurrentFlipperHeight=value;}
    public float GetCurrentFlipperWidth(){return CGV.GetFlipperWidth() * Vitals.GetMaturity();}
    //public void SetCurrentFlipperWidth(float value){CurrentFlipperWidth=value;}
    public float GetCurrentTailHeight(){return CGV.GetTailHeight() * Vitals.GetMaturity();}
    //public void SetCurrentTailHeight(float value){CurrentTailHeight=value;}
    public float GetCurrentTailWidth(){return CGV.GetTailWidth() * Vitals.GetMaturity();}
    //public void SetCurrentTailWidth(float value){CurrentTailWidth=value;}
    public float GetCurrentMouthSize(){return CGV.GetMouthSize() * Vitals.GetMaturity();}
    public float GetCurrentEyeSize(){return CGV.GetEyeSize() * Vitals.GetMaturity();}

    public Color GetCurrentHeadColor(){return CGV.GetBodyColor();}
    public Color GetCurrentSegmentsColor(int index){return CGV.GetBodyColor();}
    public Color GetCurrentMouthColor(){return CGV.GetMouthColor();}
    public Color GetCurrentEyesColor(){return CGV.GetEyeColor();}
    public Color GetCurrentFlipperColor(){return CGV.GetFlipperColor();}
    public Color GetCurrentTailColor(){return CGV.GetTailColor();}
    public boolean GetMouthPresent(){return MouthPresent;}
    public boolean GetEyesPresent(){return EyesPresent;}
    public boolean GetFlipperPresent(){return FlipperPresent;}
    public boolean GetTailPresent(){return TailPresent;}
    public float GetCurrentBiteStrength(){return CGV.GetBiteStrength() * Vitals.GetMaturity();}
}