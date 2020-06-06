/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author yusufcole
 */
public class RightDecoretor extends FigureDecorator implements Serializable{

    public RightDecoretor(BaseFigure fig) {
        super(fig);
        this.Type ="ornament";
    }
    @Override
    public void draw(Graphics g) {
        g.drawRect(decoratedFigure.getLeft()+ decoratedFigure.getWidth()-2,
                decoratedFigure.getTop()+(decoratedFigure.getHeight()-1)/2, 10, 10);
        decoratedFigure.draw(g);
        setAtRight(decoratedFigure);
    }
    private String setAtRight(BaseFigure fig){
        return super.Type+ " "+fig.getClass().getSimpleName()+"\t"+"Right";
    }

    @Override
    public void accept(IVisitor v) {
        decoratedFigure.accept(v);
    }

    @Override
    public boolean containst(int x, int y) {
       return decoratedFigure.containst(x, y);
    }

    @Override
    public String getType() {
        return Type;
    }

    @Override
    public int getFigureID() {
        return decoratedFigure.getFigureID();
    }

    @Override
    public void setID(int id) {
        id = decoratedFigure.getFigureID();
    }

    @Override
    public int getLeft() {
        return decoratedFigure.getLeft();
    }

    @Override
    public void setLeft(int left) {
        left = decoratedFigure.getLeft();
    }

    @Override
    public int getTop() {
        return decoratedFigure.getTop();
    }

    @Override
    public void setTop(int top) {
        top = decoratedFigure.getTop();
    }

    @Override
    public int getWidth() {
        return decoratedFigure.getWidth();
    }

    @Override
    public void setWith(int width) {
        width = 10;
    }

    @Override
    public int getHeight() {
        return decoratedFigure.getHeight();
    }

    @Override
    public void setHeigth(int height) {
        height = 10;
    }
        @Override
    public String toString(){
        return setAtRight(decoratedFigure);
    }
}
