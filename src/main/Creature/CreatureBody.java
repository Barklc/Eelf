package main.Creature;

import main.GameParameters;
import main.FlagsOverride;
import main.Creature.BodySegments.*;
import java.awt.*;
import java.util.ArrayList;

public class CreatureBody{

    private final CreatureGeneValues CGV;
    private final CreatureVitals Vitals;
    private final ArrayList<BodySegment> Body;
    private int HeadSegmentID;
    private int MouthSegmentID;
    private int EyesSegmentID;
    private int FlippersSegmentID;
    private int TailSegmentID;
    private float Mass;

    //CGV parameter variables
    private final float HeadShape;
    private boolean MouthPresent;
    private boolean FlipperPresent;
    private boolean TailPresent;

    private boolean EyesPresent;

    //create cgv parameter
    public CreatureBody(Creature currentCreature){
        CGV= currentCreature.GetGenes();
        Vitals= currentCreature.GetVitals();

        //class variables
        Body=new ArrayList<>();

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
        TailPresent =(CGV.GetTailPresent()>=GameParameters.TailPresentThreshold);
        if (FlagsOverride.TailPresentOverride){
            TailPresent =true;
        }
        EyesPresent=(CGV.GetEyesPresent()>GameParameters.EyesPresentThreshold);
        if (FlagsOverride.EyesPresentOverride){
            EyesPresent=true;
        }

    }

    public void CreateBody(float x,float y){
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
            s.InitializeSegment(x-i*HeightOfCurrentSegment,y,GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),i,GetBodyLength()),GetCurrentBodyHeight()-DetermineTaper(GetCurrentBodyWidth(),i,GetBodyLength()),0,GetCurrentBodyDistanceBetweenSegments(),GetCurrentSegmentsColor());
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
            f.SetFlipperHeight(GetCurrentFlipperHeight());
            f.SetFlipperWidth(GetCurrentFlipperWidth());
            int connectorOffset=0;
            if (GetCurrentFlipperHeight()>GetCurrentBodyDistanceBetweenSegments()) {
                connectorOffset= (int) Math.floor(GetCurrentFlipperHeight()/GetCurrentBodyDistanceBetweenSegments());
            }
            f.SetSegmentConnectedTo(GameParameters.FlippersSegmentConnected+connectorOffset);
            Body.add(f);
            FlippersSegmentID=Body.size()-1;
        }
        if(TailPresent){
            Tail t=new Tail();
            t.InitializeSegment(x-(GetBodyLength()-GameParameters.TailSegmentOffsetFromEnd),
                    y,
                    GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),GetBodyLength()-2-GameParameters.TailSegmentOffsetFromEnd,GetBodyLength()),
                    GetCurrentBodyHeight()-DetermineTaper(GetCurrentBodyWidth(),GetBodyLength()-2-GameParameters.TailSegmentOffsetFromEnd,GetBodyLength()),
                    0,GetCurrentBodyDistanceBetweenSegments(),
                    GetCurrentTailColor());
            t.SetTailWidth(GetCurrentTailWidth());
            t.SetTailHeight(GetCurrentTailHeight());
            t.SetSegmentConnectedTo(GetBodyLength()- 2- GameParameters.TailSegmentOffsetFromEnd);
            Body.add(t);
            TailSegmentID=Body.size()-1;
        }

        //println("CreatureBody.CreateBody - CalculateBodyMass: " + CalculateBodyMass());
    }

    public void UpdateBody(){

        BodySegment bodySegment= GetBodySegment(0);

        Head head=(Head) bodySegment;
        head.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
        head.SetSegmentWidth(GetCurrentBodyWidth()-DetermineTaper(GetCurrentBodyWidth(),0,GetBodyLength()));
        head.SetSegmentHeight(GetCurrentBodyHeight());
        Body.set(0,head);

        for(int i=1;i<GetBodyLength()-1;i++) {
            bodySegment = GetBodySegment(i);
            bodySegment.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
            bodySegment.SetSegmentWidth(GetCurrentBodyWidth() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(GetCurrentBodyHeight() - DetermineTaper(GetCurrentBodyWidth(), i, GetBodyLength()));
            bodySegment.SetSegmentHeight(GetCurrentBodyHeight());
            Body.set(i, bodySegment);
        }

        if(GetMouthPresent() && GetBodySegment(MouthSegmentID).BodySegmentType()==SegmentID.Mouth) {
            Mouth mouth=(Mouth) GetBodySegment(MouthSegmentID);
            mouth.SetMouthSize(GetCurrentMouthSize());
            mouth.SetBiteStrength(GetCurrentBiteStrength());
            mouth.UpdateSegment(GetBodySegment(mouth.GetSegmentConnectedTo()));
            Body.set(MouthSegmentID,mouth);
        }
        if (GetEyesPresent() && GetBodySegment(EyesSegmentID).BodySegmentType()==SegmentID.Eyes){
            Eyes eyes=(Eyes) GetBodySegment(EyesSegmentID);
            eyes.SetEyeSize(GetCurrentEyeSize());
            eyes.SetEyeColor(CGV.GetEyeColor());
            eyes.UpdateSegment(GetBodySegment(eyes.GetSegmentConnectedTo()));
            Body.set(EyesSegmentID,eyes);
        }
        if (GetFlipperPresent() && GetBodySegment(FlippersSegmentID).BodySegmentType() == SegmentID.Flippers) {
            Flippers flippers=(Flippers) GetBodySegment(FlippersSegmentID);
            flippers.SetFlipperHeight(GetCurrentFlipperHeight());
            flippers.SetFlipperWidth(GetCurrentFlipperWidth());
            flippers.UpdateSegment(GetBodySegment(flippers.GetSegmentConnectedTo()));
            Body.set(FlippersSegmentID,flippers);
        }
        if (GetTailPresent() && GetBodySegment(TailSegmentID).BodySegmentType()==SegmentID.Tail){
            Tail tail=(Tail) GetBodySegment(TailSegmentID);
            tail.SetSegmentDistance(GetCurrentBodyDistanceBetweenSegments());
            tail.SetSegmentAngle(GetBodySegment(tail.GetSegmentConnectedTo()).GetSegmentAngle());
            tail.SetTailHeight(GetCurrentTailHeight());//   GetCurrentTailHeight());
            tail.SetTailWidth(GetCurrentTailWidth()); // GetCurrentTailWidth());
            bodySegment.UpdateSegment(GetBodySegment(tail.GetSegmentConnectedTo()));
            Body.set(TailSegmentID,tail);
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

    public float GetCurrentBodyMass(){return Mass * Vitals.GetMaturity();}

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
    public float GetCurrentTailHeight(){return (CGV.GetTailHeightPercentage()*CGV.GetBodyHeight()) * Vitals.GetMaturity();}
    //public void SetCurrentTailHeight(float value){CurrentTailHeight=value;}
    public float GetCurrentTailWidth(){return (CGV.GetTailWidthPercentage()*CGV.GetBodyWidth()) * Vitals.GetMaturity();}
    //public void SetCurrentTailWidth(float value){CurrentTailWidth=value;}
    public float GetCurrentMouthSize(){return CGV.GetMouthSize() * Vitals.GetMaturity();}
    public float GetCurrentEyeSize(){return CGV.GetEyeSize() * Vitals.GetMaturity();}

    public Color GetCurrentHeadColor(){return CGV.GetBodyColor();}
    public Color GetCurrentSegmentsColor(){return CGV.GetBodyColor();}
    public Color GetCurrentMouthColor(){return CGV.GetMouthColor();}
    public Color GetCurrentEyesColor(){return CGV.GetEyeColor();}
    public Color GetCurrentFlipperColor(){
        return CGV.GetFlipperColor();
        //return gUtils.ChangeColorByPercentage(new Color(CGV.GetBodyColorRGB().get(0),CGV.GetBodyColorRGB().get(1),CGV.GetBodyColorRGB().get(2)),CGV.GetFlipperColorDeviation());
    }
    public Color GetCurrentTailColor(){return CGV.GetTailColor();}
    public boolean GetMouthPresent(){return MouthPresent;}
    public boolean GetEyesPresent(){return EyesPresent;}
    public boolean GetFlipperPresent(){return FlipperPresent;}
    public boolean GetTailPresent(){return TailPresent;}
    public float GetCurrentBiteStrength(){return CGV.GetBiteStrength() * Vitals.GetMaturity();}
    public float GetCurrentTailWidthPercentage(){return CGV.GetTailWidthPercentage() * Vitals.GetMaturity();}
}