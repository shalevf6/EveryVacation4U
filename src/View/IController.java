package View;

import Controller.Controller;

public abstract class IController {
    protected static Controller controller;

 public void setController(Controller itzik){
     controller = itzik;
 }
}
