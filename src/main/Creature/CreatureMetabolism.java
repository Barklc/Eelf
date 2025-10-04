package main.Creature;

import main.GameParameters;
import main.Nourishments.Nourishment;
import main.Nourishments.NourishmentTypes;
import main.Nourishments.PlantNourishment;

public class CreatureMetabolism {
    private final CreatureGeneValues CGV;
    private final CreatureVitals Vitals;
    private float EnergyUsedBase;
    private float EnergyUsedInGestation;
    private float EnergyUsedInBirth;
    private float EnergyUsedForMovement;
    private float EnergyUsedDuringBirthRecoveryTime;
    public CreatureMetabolism(Creature currentCreature){
        CGV= currentCreature.GetGenes();
        Vitals= currentCreature.GetVitals();

        //BP = 100 * maturity * pow(Size Ratio (gene), 2) - Max energy that can be stored.

        //Body-Energy Ratio = Base Body-Energy Ratio (setting)
        //
        //+ View Angle (gene) / View Angle Body Cost (setting)
        //
        //+ View Radius (gene) / View Radius Body Cost (setting)
        //
        //+ Strength (gene) / Strength Body Cost (setting)
        //
        //+ Immune System Strength (gene) / Immune Body Cost (setting)

        //Max Energy = Total Body Points (BP) * Max Energy to Body Ratio (setting)
    }

    //Digest food in stomach and convert to energy.
    private float DigestionCycle(){
        float energyCreated=0;
        float MeatDigestionAmount;
        float PlantDigestionAmount;

        if (Vitals.GetTotalStomachContent()>0){
            float digestionAmount= GameParameters.BaseDigestionAmount*CGV.GetDigestionRate();
            if (Vitals.GetMeatStomachContent()>0){
                MeatDigestionAmount=Vitals.RemoveMeatStomachContent(digestionAmount);
                digestionAmount=digestionAmount-MeatDigestionAmount;
                energyCreated=MeatDigestionAmount*CGV.GetMeatToEnergyConversionRate();
            }
            if (Vitals.GetPlantStomachContent()>0) {
                PlantDigestionAmount = Vitals.RemovePlantStomachContent(digestionAmount);
                energyCreated = PlantDigestionAmount * CGV.GetPlantToEnergyConversionRate();
            }
        }
        Vitals.IncreaseHealth(CGV.GetIncreaseHealthPercentage()*energyCreated);
        return energyCreated;
    }

    public void EnergyUsedReset(){
        EnergyUsedBase=0.0f;
        EnergyUsedInGestation=0.0f;
        EnergyUsedInBirth=0.0f;
        EnergyUsedForMovement=0.0f;
        EnergyUsedDuringBirthRecoveryTime=0.0f;
    }
    public void SetEnergyUsedBase(float value){
        //TODO: Determine how much energy is needed to run basic body needs
        EnergyUsedBase=value;
    }

    public void SetEnergyUsedInGestation(float value){
        //TODO: Determine how much energy is needed to each gestation cycle
        EnergyUsedInGestation=value;
    }

    public void SetEnergyUsedInBirth(float value){
        //TODO: Determine how much energy is needed to give birth
        EnergyUsedInBirth=value;
    }

    public void SetEnergyUsedForMovement(float value){
        //TODO: Determine how much energy is used to move the distance moved
        EnergyUsedForMovement=value;
        //Metabolism Cost (E/S) = Default Metabolism Cost (setting) *  Speed (gene) *  Size (2D)
    }

    public void SetEnergyUsedDuringBirthRecoveryTime(float value){
        //TODO: Determine how much energy is used to for each birth cool down cycle
        EnergyUsedDuringBirthRecoveryTime=value;
    }

    //The energy cycle converts stomach content to energy, takes the value provided by the SetEnergyUsed methods and
    //determines if the needed energy using processes get what is needed.  Any remaining energy is stored.
    public void EnergyCycle(){
        float energyCreated=DigestionCycle();
        float totalEnergy=Vitals.GetEnergyLevel()+energyCreated;
        //TODO: Put energy usage in descending order of importance. (Think about adding gene control for the priority)

        //TODO: Determine the general energy usage to stay alive and healthy
        if (totalEnergy<EnergyUsedBase){
            //TODO: Health is impacted
            Vitals.DecreaseHealth(EnergyUsedBase-totalEnergy);
            //TODO: Set EnergyLevel to 0
            Vitals.SetEnergyLevel(0);
        } else  {
            totalEnergy-=EnergyUsedBase;
        }

        //TODO: Determine the energy used during the Gestation Period when pregnant
        if (totalEnergy<EnergyUsedInGestation){
            //TODO: Gestation is impacted
            Vitals.IncreaseGestationPeriodCountDown();
            //TODO: Unborn health is impacted
            Vitals.IncreaseUnbornHealthDamage();

        } else {
            totalEnergy-=EnergyUsedInGestation;
        }

        //TODO: Determine the energy usage when giving birth
        if (totalEnergy<EnergyUsedInBirth){
            //TODO: Health is impacted
            Vitals.DecreaseHealth(EnergyUsedInBirth-totalEnergy);
        } else {
            totalEnergy-=EnergyUsedInBirth;
        }

        //TODO: Determine the energy usage from moving taking into effect mass
        if (totalEnergy<EnergyUsedForMovement){
            //TODO: Health is impacted
            Vitals.DecreaseHealth(EnergyUsedForMovement-totalEnergy);
        } else {
            totalEnergy-=EnergyUsedForMovement;
        }

        //TODO: Determine the energy usage during birthCoolDown
        if (totalEnergy<EnergyUsedDuringBirthRecoveryTime){
            //TODO: Health is impacted
            Vitals.DecreaseHealth(EnergyUsedDuringBirthRecoveryTime-totalEnergy);
            //TODO: if in cool down it is delayed
        } else {
            totalEnergy-=EnergyUsedDuringBirthRecoveryTime;
        }
        //Check the remaining totalEnergy value is not more than can be storied.
        if (totalEnergy>Vitals.GetCurrentMaxEnergyStorage()){
            totalEnergy=Vitals.GetCurrentMaxEnergyStorage();
        }

        //Check to see if totalEnergy is negative, if so set to zero.
        if(totalEnergy<0){totalEnergy=0.0f;}

        //Update EnergyLevel with any energy left
        Vitals.SetEnergyLevel(totalEnergy);

        //Reset the energy used variables as a new cycle is starting
        EnergyUsedReset();

    }

    //Call when creature takes a bite of plants or meat
    public float Bite(Nourishment nourishment){
        float biteAmount=CGV.GetMouthSize();
        float amountAdded=0;

        if (nourishment.NourishmentType()== NourishmentTypes.Plant){
            PlantNourishment plant = new PlantNourishment();
            float mass=plant.GetNourishmentMass();
            if (mass>1) {
                if (biteAmount > mass) {
                    biteAmount = mass;
                    plant.SetNourishmentMass(0);
                } else {
                    plant.SetNourishmentMass(mass - biteAmount);
                }
                amountAdded=Vitals.AddPlantStomachContent(biteAmount);
            }
        }
        if (nourishment.NourishmentType()== NourishmentTypes.Meat){
            PlantNourishment meat = new PlantNourishment();
            float mass=meat.GetNourishmentMass();
            if (mass>1) {
                if (biteAmount > mass) {
                    biteAmount = mass;
                    meat.SetNourishmentMass(0);
                } else {
                    meat.SetNourishmentMass(mass - biteAmount);
                }
                amountAdded=Vitals.AddMeatStomachContent(biteAmount);
            }
        }
        return amountAdded;
    }

    public void Aging(){
        Vitals.SetAngle(Vitals.GetAge()+1);
        if (Vitals.GetMaturity()<1){
            Vitals.IncreaseMaturity();
        }
    }
}