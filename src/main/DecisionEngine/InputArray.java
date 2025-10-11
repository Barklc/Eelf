package main.DecisionEngine;

import java.util.ArrayList;

public class InputArray {
    ArrayList<Object> input;

    public void InputArray(){
        input =new ArrayList<>();
        for(int i=0;i<30;i++){
            input.add(new Object());
        }
    }

    public void SetInputArray(int ID, Object value){
        if (input==null){
            ClearInputs();
        }
        input.set(ID,value);
    }

    public Object GetInputArrayValue(int ID){
        return input.get(ID);
    }

    public void ClearInputs(){
        input =new ArrayList<>();
        for(int i=0;i<30;i++){
            input.add(new Object());
        }
    }
}
