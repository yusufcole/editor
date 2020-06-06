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
class CommandDrawRect implements IMyCommand{
    private final MyShapesFactory ishapes;
    LinkedList<MyShapesFactory> olmy = new LinkedList<>();
    BaseFigure fig;
    private int y,x,w,h;
    private final int Oldx;
    private final int Oldy;
    private final int Oldw;
    private final int Oldh;
    public CommandDrawRect(MyShapesFactory shape){
        this.ishapes = shape;
        this.Oldx = x;
        this.Oldy = y;
        this.Oldw = w;
        this.Oldh = h;
    }
    @Override
    public void execute() {
        ishapes.rect(fig,x,y,w,h);
    }

    @Override
    public void undo() {
        ishapes.undo();
    }
    
}
