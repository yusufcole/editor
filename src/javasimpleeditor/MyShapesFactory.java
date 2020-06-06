/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author yusufcole
 */
public class MyShapesFactory extends JPanel implements MouseListener,MouseMotionListener{
    private int startX, startY, currentX = 0, currentY = 0, nextX, nextY;
    private final int Width = 50;
    private final int Height = 50;
    private boolean deleated = false;
    private final LinkedList<BaseFigure> DeletedFigures = new LinkedList<>();
    private LinkedList<BaseFigure> ListOfFigures = new LinkedList<>();
    private final  LinkedList<LinkedList<BaseFigure>> StackOfFigures = new LinkedList<>();
    private int FigureID = 0;
    private Cursor cursor = Cursor.getDefaultCursor();
    private GroupFigure group;
    private VisitorMove moveFigure;
    private VisitorResize resizeFigure;
    private VisitorFile_io fileio;
    private BaseFigure selectedShape = null;
    private JTextField textField;
    private Graphics dec;
    private static final MyShapesFactory Instance = new MyShapesFactory();
    private MyShapesFactory(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public static MyShapesFactory getInstance(){
        return Instance;
    }
    @Override
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, super.getWidth(), super.getHeight());
        Graphics2D g2D = (Graphics2D) g;
        dec = g;
                //draw figures that are in the list
        ListOfFigures.forEach((BaseFigure shapeInArray) -> {
            if(selectedShape!=null && shapeInArray == selectedShape){
                selectedShape = shapeInArray;
                FigureBorderHelper(g2D,selectedShape);
                selectedShape.draw(g);
                if((shapeInArray == selectedShape && shapeInArray.getType().equals("group"))
                        && !shapeInArray.getType().equals("ornament")){
                    GroupFigure groupFigureInList = (GroupFigure)shapeInArray;
                    selectedShape = groupFigureInList;
                    selectedShape.draw(g);
                    FigureBorderHelper(g2D, groupFigureInList);
                    //groupFigureInList.draw(g);
                    groupFigureInList.getGroup().forEach((BaseFigure Figure) -> {
                        FigureBorderHelper(g2D, Figure);
                        Figure.draw(g);
                    });
                }else if(shapeInArray.getType().equals("ornament")){
                    shapeInArray.draw(dec);
                    shapeInArray.toString();
                }
            }else{
                shapeInArray.draw(g);
            }
        });
       
    }
    //add figure to group or group/groups to group
    public void groupFigures(){
        GroupFigure fig;
        boolean g =false;
        try {
            for(BaseFigure singleFigure : ListOfFigures){
                if("group".equals(singleFigure.getType())){
                    fig = (GroupFigure)singleFigure;
                    g = true;
                    if(g && selectedShape != null){
                        if(fig.containst(nextX, nextY)){
                          if(selectedShape.getType().equals("group")){
                              group = (GroupFigure)selectedShape;
                              //ListOfFigures.remove(group);
                              fig.addToGroup(group);
                          }else{
                             // ListOfFigures.remove(selectedShape);
                              fig.addToGroup(selectedShape);
                              //System.out.println("Figures in my group" + fig.getGroup());
                          }

                        }
                    }
                }else{
                        g = !g;
                }   
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("groupFigures = something is wrong here");
        }
        repaint();
    }
    //add decorator to selected shape
    void addDecorator(String choice){
        BaseFigure figdec;
        String inputs;
        if(selectedShape != null){                
            switch (choice) {
                case "addLeft":
                    textField = new JTextField();
                    add(textField);
                    figdec = new LeftDecorator(selectedShape);
                    figdec.draw(dec);
                    ListOfFigures.add(figdec);
                    System.out.println("addDecorator()"+ figdec.getType()+"\t"+ figdec.toString());
                    break;
                case "addTop":
                    textField = new JTextField();
                    add(textField);
                    //inputs = textField.getText();
                    figdec = new TopDecorator(selectedShape);
                    figdec.draw(dec);
                    System.out.println("addDecorator()"+ figdec.getType()+"\t"+ figdec.toString());
                    ListOfFigures.add(figdec);
                    break;
                case "addRight":
                    textField = new JTextField();
                    figdec = new RightDecoretor(selectedShape);
                    figdec.draw(dec);
                    System.out.println("addDecorator()" + figdec.getType()+"\t"+ figdec.toString());
                    ListOfFigures.add(figdec);
                    break;
                case "addBottom":
                    textField = new JTextField();
                    figdec = new BottomDecorator(selectedShape);
                    figdec.draw(dec);
                    ListOfFigures.add(figdec);
                    System.out.println(".addDecorator()" + figdec.getType()+"\t"+ figdec.toString());
                    break;
                default:
                    break;
            }
        }
            repaint();
    }
    //create group figure
    void createGroup(){
        BaseFigure fig;
        int x = currentX;
        int y = currentY;
        group = new GroupFigure(x, y, Width, Height, FigureID);
        group.setID(FigureID);
        FigureID ++;
        fig = group;
        FigureID++;
        ListOfFigures.add(fig);
        System.out.println("group Created" + group + " " + FigureID);
    }
    //create rechtangel
    void rect(BaseFigure figure,int x, int y, int w, int h){
        x = currentX;
        y = currentY;
        w = Width;
        h = Height;
        figure = new RectangleFigure(x, y, w, h, FigureID);
        figure.setID(FigureID);
        startX = x;
        startY = y;
        ListOfFigures.add(figure);
        FigureID ++;
    }
    //create ellipse
    void ellips(BaseFigure figure,int x, int y, int w, int h){
        x = currentX;
        y = currentY;
        w = Width;
        h = Height;
        figure = new ElliepseFigure(x, y, w, h, FigureID);
        startX = x;
        startY = y;
        figure.setID(FigureID);
        ListOfFigures.add(figure);
        FigureID ++;
    }
    //add figure helper to figures
    private void FigureBorderHelper(Graphics2D gs, BaseFigure s){
        int x = s.getLeft(),
            y = s.getTop(),
            w = s.getWidth(),
            h = s.getHeight();
        
        if(h < 50 ||w < 50){
            s.setHeigth(50);
            s.setWith(50);
        }
        gs.setColor(Color.black);
        gs.fill(new Rectangle(x - 2,y - 2, 4, 4));
        gs.fill(new Rectangle(x + w - 2,y - 2, 4, 4));
        gs.fill(new Rectangle(x - 2,y + h - 2, 4, 4));
        gs.fill(new Rectangle(x + w - 2,y + h - 2, 4, 4));
    }
    /*
    press mouse to select figure
    create groups with doubleckick
    */
    @Override
    public void mousePressed(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        selectedShape = null;
        for (int i = 0; i < ListOfFigures.size(); i++) {
            if(ListOfFigures.get(i).containst(currentX, currentY)){
                selectedShape = ListOfFigures.get(i);
                if (selectedShape!= null){
                    cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                }
               // System.out.println("@MyShapes are = " + selectedShape.getFigureID());
            }
        }
        if(selectedShape == null && e.getClickCount() == 2){
            createGroup();
        }
        repaint();
    }
    /*
    mouse release 
    */
    @Override
    public void mouseReleased(MouseEvent e) {
        startX = e.getPoint().x;
        startY = e.getPoint().y;
        try {
        if(selectedShape == null){
            if (cursor != null){
                setCursor(cursor);
            }
        }
            saveShapes();
        } catch (IOException ex) {
            Logger.getLogger(MyShapesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        nextX = e.getPoint().x;
        nextY = e.getPoint().y;
        startX = nextX;
        startY = nextY;
    }
    public void resizeFigure(){
        selectedShape = null;
        int w = (nextX) + Width;
        int h = (nextY) + Height;
        int x = currentX;
        int y = currentY;
            for (int i = 0; i <= ListOfFigures.size() - 1; i++) {
                if(ListOfFigures.get(i).containst(x,y)){
                    resizeFigure = new VisitorResize(w,h);
                    ListOfFigures.get(i).accept(resizeFigure);
                    System.out.println("@resizeFigure = " + ListOfFigures.get(i));
                }
            }
    } 
    /*
    move selected figure
    */
    public void shapemove(){
        if(selectedShape != null){
            for (int i = 0; i< ListOfFigures.size(); i++) {
                if(ListOfFigures.get(i).containst(currentX, currentY)){
                currentX = nextX;
                currentY = nextY;
                moveFigure = new VisitorMove(currentX,currentY);
                ListOfFigures.get(i).accept(moveFigure);
                System.out.println("@Shapemoved are = " + selectedShape);
                }
            }
        }
    }
    /*
    remove or delete selected figure
    keep it just in case of undo redo
    */
    public void removeShape(){
        BaseFigure fig;
        GroupFigure groupfig;
        try {
            if(selectedShape != null){
            fig = selectedShape;
                if (selectedShape.getType().equals("group")) {
                    groupfig =(GroupFigure)fig;
                    DeletedFigures.addLast(groupfig);
                    ListOfFigures.remove(groupfig);
                } else {
                    DeletedFigures.addLast(selectedShape);
                    ListOfFigures.remove(fig);
                }
            }
            deleated = !deleated;
        } catch (Exception e) {
            e.getMessage();
            System.out.println("@MyShapes.removeShape()");
        }
    }
//    /*
//    clear all shapes
//    */
//    public void clearShapeFactory(){
//        try {
//            ListOfFigures.clear();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
    /*
    undo actions
    */
    public void undo(){
        try {
            ListOfFigures.pop();
            StackOfFigures.pop();
            LinkedList<BaseFigure> figureList;
            figureList = StackOfFigures.getLast();
            ListOfFigures = figureList; 
        } catch (Exception e) {
            e.getMessage();
            System.out.println("nothing More to undo() !!!");
        }
        repaint();
    } 
    /*
    undo deleted shape
    */
    public void undoDeleteFigures(){
        try {
            if(DeletedFigures.size() != -1){
            BaseFigure newshape = DeletedFigures.removeLast();
            newshape.getType();
            ListOfFigures.addLast(newshape);
        }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("@MyShapes.delatedundo()" + ListOfFigures);
        }
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }       
    @Override
    public void mouseClicked(MouseEvent e) { }
    /**
     * Retrief shapes from files
     * in text(String)
     * as Object(IShape)
     */
    public void openSavefile(){
        String f = "editorout.ser"; 
        String file = "editorout.txt";
        try {
            FileInputStream intext = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(intext);
            BufferedReader br = new BufferedReader(isr);
            String lines;
            while((lines = br.readLine()) != null){
                System.out.println("openSavefileText = " + lines.trim());
            }
        }   catch (IOException ex) {
                Logger.getLogger(MyShapesFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        try (ObjectInputStream in =
            new ObjectInputStream(
            new FileInputStream(f))){
                LinkedList<BaseFigure> sh = (LinkedList<BaseFigure>)in.readObject();
                in.close();    
            if(ListOfFigures.isEmpty()){
                ListOfFigures = sh;
                System.out.println("save figures are" + ListOfFigures);
            }
        }   catch (ClassNotFoundException|IOException ex){
                ex.getMessage();
            }    
    }
    
    /**
     * Saveshapes to file...
     * write text(String to file)
     * Write Object(Shapes to file)
     * @throws IOException
     */
    public void saveShapes() throws IOException{            
        String f = "editorout.ser";
        String fileText = "editorout.txt";
        FileWriter  writer = new FileWriter(fileText);
        try(PrintWriter printWriter = new PrintWriter(writer)) {
            ListOfFigures.forEach((BaseFigure Figure) -> {
                fileio = new VisitorFile_io(Figure.getType(), Figure.getLeft(), Figure.getTop(), Figure.getWidth(), 
                        Figure.getHeight(), Figure.getFigureID());
                if("ornament".equalsIgnoreCase(Figure.getType())){
                    printWriter.println(Figure.toString());
                }else if("group".equals(Figure.getType())){
                    GroupFigure fig;
                    fig =(GroupFigure)Figure;
                    //fig.accept(fileio);
                    printWriter.println(fig.getType() +"\t" + fig.getGroup().size());
                    fig.getGroup().forEach((BaseFigure subshapes) -> {
                        if(!"group".equals(subshapes.getType())){
                            //subshapes.accept(fileio);
                            printWriter.println("\t" + subshapes.getType() +" "+ subshapes.getLeft() + " " + subshapes.getTop() +
                            " "+ subshapes.getWidth() + " " + subshapes.getHeight());
                        }
                        else if("group".equals(subshapes.getType())){
                            GroupFigure insubshapes;
                            insubshapes = (GroupFigure)subshapes;
                            //insubshapes.accept(fileio);
                            printWriter.println("\t"+ insubshapes.getType() + "\t" + insubshapes.getGroup().size());
                            insubshapes.getGroup().forEach((BaseFigure inshapefigsub)->{
                                //inshapefigsub.accept(fileio);
                                if("group".equals(inshapefigsub.getType())){
                                    printWriter.println("\t"+ insubshapes.getType() + "  " + insubshapes.getGroup().size());
                                }else{
                                    printWriter.println("\t\t"+ inshapefigsub.getType() + " " + inshapefigsub.getLeft() + " "
                                            + inshapefigsub.getTop() + " " + inshapefigsub.getWidth() + " " + inshapefigsub.getHeight());
                                }
                            });
                        }
                    });
                }else{
                    //Figure.accept(fileio);
                printWriter.println(Figure.getType() +" "+ Figure.getLeft() + " " + Figure.getTop() +
                        " "+ Figure.getWidth() + " " + Figure.getHeight() + "\t");
            }     
            });
        }
        ObjectOutputStream obj;
        obj = new ObjectOutputStream(new FileOutputStream(f));
        obj.writeObject(ListOfFigures);
    }
}
