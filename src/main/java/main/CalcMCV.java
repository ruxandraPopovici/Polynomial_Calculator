package main;

import data_models.CalcModel;
import user_interface.CalcController;
import user_interface.CalcView;

public class CalcMCV {
    public static void main (String [] args){
        CalcModel model = new CalcModel();
        CalcView view = new CalcView(model);
        CalcController controller = new CalcController(view, model);
        view.setVisible(true);
    }
}


