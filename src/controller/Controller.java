package controller;

import model.Model;

import javax.swing.*;

public class Controller {

    private Model model = new Model();

    public void handleClick(String string) {
        String result = model.parseString(string);
    }
}
