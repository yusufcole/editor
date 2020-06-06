/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

/**
 *
 * @author yusufcole
 */
public class CommandaddToGroup implements IMyCommand
{
    private final MyShapesFactory ishapes;
    
    public CommandaddToGroup(MyShapesFactory shapes){
        this.ishapes = shapes;
    }
    @Override
    public void execute() {
        ishapes.groupFigures();
    }
    @Override
    public void undo() {
       ishapes.undo();
    }

}
