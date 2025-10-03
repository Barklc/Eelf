package main.Creature;

public class CreatureStomachContent {

    public float MeatContent;
    public float PlantContent;

    public CreatureStomachContent(){

    }

    public float AddMeatContent(float stomachSize,float value){
        if (GetTotalContent()+value>stomachSize){
            float totalAdded=value-(stomachSize-(GetTotalContent()+value));
            MeatContent+=totalAdded;
            return totalAdded;
        }
        MeatContent+=value;
        return value;
    }
    public float AddPlantContent(float stomachSize,float value){
        if (GetTotalContent()+value>stomachSize){
            float totalAdded=value-(stomachSize-(GetTotalContent()+value));
            PlantContent+=totalAdded;
            return totalAdded;
        }
        PlantContent+=value;
        return value;
    }
    public float RemoveMeatContent(float value){
        if (GetTotalContent()-value<0){
            float totalRemoved=value+(GetPlantContent()-value);
            MeatContent-=totalRemoved;
            return totalRemoved;
        }
        MeatContent-=value;
        return value;
    }
    public float RemovePlantContent(float value){
        if (GetPlantContent()-value<0){
            float totalRemoved=value+(GetPlantContent()-value);
            PlantContent-=totalRemoved;
            return totalRemoved;
        }
        PlantContent-=value;
        return value;
    }
    public float GetPlantContent(){
        return PlantContent;
    }

    public float GetMeatContent(){
        return MeatContent;
    }

    public float GetTotalContent(){
        return PlantContent+MeatContent;
    }


}
