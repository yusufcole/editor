/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.util.LinkedList;

/**
 *
 * @author yusufcole
 */
public class CommandDelete implements IMyCommand{
    private MyShapesFactory ishapes;
 
    public CommandDelete(MyShapesFactory shape){
        this.ishapes = shape;
    }
    @Override
    public void execute() {
        ishapes.removeShape();
    }
    @Override
    public void undo() {
        ishapes.undoDeleteFigures();
    }
}
