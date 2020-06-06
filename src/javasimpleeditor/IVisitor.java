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
public interface IVisitor {
    public void visite(ElliepseFigure o);
    public void visite(RectangleFigure r);
    public void visite(GroupFigure g);
}
