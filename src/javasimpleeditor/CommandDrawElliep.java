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
public class CommandDrawElliep implements IMyCommand{
    private final MyShapesFactory ishapes;
    //collectie lijst van mijn hele stack
     LinkedList<BaseFigure> ListOfFig;
    private LinkedList<MyShapesFactory> olshape;
     BaseFigure fig;
    private int y,x,w,h;
    private final int Oldx,Oldy,Oldw,Oldh;
    public CommandDrawElliep(MyShapesFactory shape){
        this.ishapes = shape;
        this.Oldx = x;
        this.Oldy = y;
        this.Oldw = w;
        this.Oldh = h;
    }
    @Override
    public void execute() {
        ishapes.ellips(fig,x,y,w,h);
    }

    @Override
    public void undo() {
        ishapes.undo();
    }
}
