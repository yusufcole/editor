/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author yusufcole
 */
public interface BaseFigure {
//int left =0,top =0 , width =0, height =0, ID=0;
//   String Type = " ";
    //Color color;
    abstract void draw(Graphics g);
    public void accept(IVisitor v);
    public  boolean containst(int x, int y);
    public String getType();
    public int getFigureID();
    public void setID(int id);
    public int getLeft();
    public void setLeft(int left);
    public int getTop();
    public void setTop(int top);
    public int getWidth();
    public void setWith(int width);
    public int getHeight();
    public void setHeigth(int height);
}
