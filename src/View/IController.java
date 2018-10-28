package View;

import Controller.Controller;

public abstract class IController {
    protected static Controller controller;

    public static void setController(Controller itzik){
        controller = itzik;
    }
}
