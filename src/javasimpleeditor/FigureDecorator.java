/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Graphics;
import javax.swing.JTextField;

/**
 *
 * @author yusufcole
 */
 public abstract class FigureDecorator implements BaseFigure{

    /**
     *Figures decorator
     */
    protected BaseFigure decoratedFigure;
    protected String Type;
    protected JTextField t;
    public FigureDecorator(BaseFigure fig) {
        this.decoratedFigure = fig;
        Type = "ornament";
    }
    public FigureDecorator(BaseFigure fig, JTextField t){
        this.decoratedFigure = fig;
        this.t = t;
    }
    private BaseFigure CheckSetfigure(){
        GroupFigure f;
        if(decoratedFigure.getType().equals("group")){
            f = (GroupFigure)decoratedFigure;
            return f;  
        }else{       
        return decoratedFigure;
        }
    }
    @Override
    public String getType(){
        return Type;
    }
    @Override
    public void draw(Graphics g) {
        CheckSetfigure();
        decoratedFigure.draw(g);
    }   
}
