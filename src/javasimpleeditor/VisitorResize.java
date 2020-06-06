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
class VisitorResize implements IVisitor{
    int w; int h;
    public VisitorResize(int w, int h){
        this.w = w;
        this.h = h;
    }
    @Override
    public void visite(ElliepseFigure o) {
        o.setWith(w);
        o.setHeigth(h);
    }

    @Override
    public void visite(RectangleFigure r) {
        r.setWith(w);
        r.setHeigth(h);
    }

    @Override
    public void visite(GroupFigure g) {
        g.setHeigth(w);
        g.setHeigth(h);
    }

}
