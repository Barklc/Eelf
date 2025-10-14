package main.Creature;
import main.FlagsOverride;
import main.GameParameters;
import main.Nourishments.DietaryPreference;

import java.util.UUID;

public class CreatureVitals {
    private final CreatureGeneValues CGV;
    private float X;
    private float Y;
    private float Angle;
    private float Health;
    private int Age;
    private int MaturityAge;
    private int SeniorAge;
    private float LifeSpan;
    private float MaturityRate;
    private boolean Alive;
    private boolean Pregnant;
    private boolean ReadyToMate;
    private CreatureStomachContent StomachContent;
    private float EnergyLevel;
    private int BirthRecoveryTime;
    private int GestationPeriodCountDown;
    private int BirthRecoveryCountDown;
    private float UnbornHealthDamage;
    private UUID ParentMaleID;
    private UUID ParentFemaleID;
    private float StomachSize;
    private float Maturity;

    public CreatureVitals(Creature currentCreature){
        CGV= currentCreature.GetGenes();
    }

    public void InitializeCreatureVitals(float x, float y, float angle, float health,int age, UUID parentMale, UUID parentFemale){
        X=x;
        Y=y;
        Angle=angle;
        Health=health;
        Age=age;

        LifeSpan= CGV.GetLifeSpan();
        MaturityAge= (int) (LifeSpan*CGV.GetMatureAgePercentage());
        SeniorAge=(int) (LifeSpan*CGV.GetSeniorAgePercentage());
        MaturityRate=( 1.0f /MaturityAge);
        if (Age<MaturityAge){
            Maturity=MaturityRate*Age;
        } else {
            Maturity=0.01f;
        }
        Alive=true;
        Pregnant=false;

        StomachContent=new CreatureStomachContent();
        EnergyLevel=0.0f;
        BirthRecoveryTime=0;
        GestationPeriodCountDown=0;
        BirthRecoveryCountDown=0;

        ParentMaleID=parentMale;
        ParentFemaleID=parentFemale;

        StomachSize=CGV.GetStomachSize();

    }

    public float GetX(){return X;}
    public void SetX(float value){
        X=value;
    }
    public float GetY(){return Y;}
    public void SetY(float value){
        Y=value;
    }
    public float GetAngle(){return Angle;}
    public void SetAngle(float value) {
        Angle=value;
    }
    public float GetHealth(){return Health;}
    public void DecreaseHealth(float value) {
        if (Health-value>0.0f){
            Health-=value;
        } else {
            Health=0.0f;
        }
        EvaluateHealth();
    }
    public void IncreaseHealth(float value){
        if (Health+value>CGV.GetMaxHealth()){
            Health=CGV.GetMaxHealth();
        } else {
            Health+=value;
        }
    }
    public void EvaluateHealth(){
        if (Health<0f){
            Alive=false;
        }
    }
    public int GetAge(){return Age;}
    public void SetAge(int value) {
        if (value>CGV.GetLifeSpan()) {
            Alive = false;
        } else {
            Age = value;
        }
    }
    public float GetLifeSpan(){return LifeSpan;}
    public int GetMaturityAge(){return MaturityAge;}
    public int GetSeniorAge(){return SeniorAge;}

    public boolean IsAlive() {return Alive;}
    public void Died(){Alive=false;}
    public boolean IsHungry(){
        if (FlagsOverride.HungryOverride){
            return true;
        }
        return StomachContent.GetTotalContent()<GetCurrentStomachSize()-1;
    }
    public DietaryPreference GetDietaryPreference(){
        float meatDominance=CGV.GetMeatToEnergyConversionRate();
        float plantDominance=CGV.GetPlantToEnergyConversionRate();

        if (plantDominance>GameParameters.OmnivorePlantThreshold && meatDominance>GameParameters.OmnivoreMeatThreshold){
            return DietaryPreference.Omnivore;
        }
        if (meatDominance>plantDominance){
            return DietaryPreference.Carnivore;
        }
        if (plantDominance>meatDominance){
            return DietaryPreference.Herbivore;
        }


        return DietaryPreference.Herbivore;
    }
    public boolean IsPregnant(){return Pregnant;}
    public void IsPregnant(boolean value){Pregnant=value;}
    public boolean IsReadyToMate(){return GetMaturity() == 1.0f && GetBirthRecoveryCountDown() == 0;}
    public float GetMeatStomachContent(){return StomachContent.GetMeatContent();}
    public float AddMeatStomachContent(float value){return StomachContent.AddMeatContent(GetCurrentStomachSize(),value);}
    public float GetPlantStomachContent(){return StomachContent.GetPlantContent();}
    public float AddPlantStomachContent(float value){return StomachContent.AddPlantContent(GetCurrentStomachSize(),value);}
    public float GetEnergyLevel(){return EnergyLevel;}
    public void SetEnergyLevel(float value){
        if (value>GetCurrentMaxEnergyStorage()){
            EnergyLevel=GetCurrentMaxEnergyStorage();
        } else EnergyLevel = Math.max(value, 0.0f);
    }
    public int GetBirthRecoveryTime(){return BirthRecoveryTime;}
    public void SetBirthRecoveryTime(int value){BirthRecoveryTime=value;}
    public int GetBirthRecoveryCountDown(){return BirthRecoveryCountDown;}
    public void SetBirthRecoveryCountDown(int value){BirthRecoveryCountDown=value;}
    public void DecreaseBirthRecoveryCountDown(){
        if (BirthRecoveryCountDown<=1){
            BirthRecoveryCountDown=0;
        }else {
            BirthRecoveryCountDown--;
        }
    }
    public int GetGestationPeriodCountDown(){return GestationPeriodCountDown;}
    public void IncreaseGestationPeriodCountDown(){GestationPeriodCountDown++;}
    public void DecreaseGestationPeriodCountDown(){GestationPeriodCountDown--;}
    public float GetUnbornHealthDamage(){return UnbornHealthDamage;}
    public void IncreaseUnbornHealthDamage(){UnbornHealthDamage+= GameParameters.UnbornHealthDamagePerIncrease;}
    public void SetGestationPeriodCountDown(int value){GestationPeriodCountDown=value;}
    public UUID GetParentMaleID(){return ParentMaleID;}
    public void SetParentMaleID(UUID value){ParentMaleID=value;}
    public UUID GetParentFemaleID(){return ParentFemaleID;}
    public void SetParentFemaleID(UUID value){ParentFemaleID=value;}

    public float GetMaturity(){return Maturity;}
    public void SetMaturity(float value){
        Maturity=value;
    }
    public void IncreaseMaturity(){
        if (Maturity<1.0f){
            Maturity+=MaturityRate;
        } else {
            Maturity=1.0f;
        }
    }
    public float GetCurrentMaxEnergyStorage(){return CGV.GetMaxStoredEnergy() * GetMaturity();}
    public float GetTotalStomachContent(){return StomachContent.GetTotalContent();}
    public float GetCurrentStomachSize(){return StomachSize * GetMaturity();}
    public float RemoveMeatStomachContent(float meat){
        return StomachContent.RemoveMeatContent(meat);
    }
    public float RemovePlantStomachContent(float plant){
        return StomachContent.RemovePlantContent(plant);
    }
    public float GetCurrentVisionDistance(){return CGV.GetVisionDistance()* GetMaturity();}
    public float GetVisionDominancePercentage(){return CGV.GetVisionDominancePercentage();}
    public float GetScentDominancePercentage(){return CGV.GetScentDominancePercentage();}
}
