package controller;

import model.Model;
import view.View;


public class Controller {

    private Model model = new Model();
    private View view = new View();

    public void handleClick(String string) {
        String result = model.parseString(string);
        String fmtText = view.format();
    }
}
