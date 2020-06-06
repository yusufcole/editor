/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
//import java.awt.Menu;
//import java.awt.MenuBar;
//import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author yusufcole
 */
public final class JavaSimpleEditor extends JFrame implements ActionListener{
    public final int Frame_With = 800;
    public final int Frame_Heigth = 800;
    private final MyShapesFactory myshape = MyShapesFactory.getInstance();
    private IMyCommand drawrec;
    private IMyCommand deletshape;
    private IMyCommand drawelieps;
    private IMyCommand AddTogroup;
    private final CommandHandler commandhandler = new CommandHandler();
    public JavaSimpleEditor(){
        super();
        createGui();           
    }
    private void createGui(){
        myshape.openSavefile();
        Container con = getContentPane();
        JMenuBar mb=new JMenuBar();
        mb.setBorderPainted(rootPaneCheckingEnabled);
        JMenu menu=new JMenu("Addornament");
        menu.setBackground(Color.red); 
        JMenuItem i1= new JMenuItem("addLeft");
        i1.setBackground(Color.red);
        i1.setOpaque(rootPaneCheckingEnabled);
        JMenuItem i2=new JMenuItem("addTop");
        i2.setBackground(Color.red);
        JMenuItem i3=new JMenuItem("addRight");
        i3.setBackground(Color.red);
        JMenuItem i4=new JMenuItem("addBottom");
        i4.setBackground(Color.red);
        JMenuItem i5=new JMenuItem("Item 5");
        JMenuItem i6=new JMenuItem("Item 6");
        JPanel btnPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton Ellieps = new JButton("Ellieps");
        Ellieps.addActionListener(this);
        Ellieps.setBackground(Color.red);
        Ellieps.setOpaque(rootPaneCheckingEnabled);
        Ellieps.setBorderPainted(rootPaneCheckingEnabled);
        JButton Rect = new JButton("Rectangel");
        Rect.addActionListener(this);
        Rect.setBackground(Color.red);
        Rect.setOpaque(rootPaneCheckingEnabled);
        Rect.setBorderPainted(rootPaneCheckingEnabled);
        JButton Delete = new JButton("Delete");
        Delete.addActionListener(this);
        Delete.setBackground(Color.red);
        Delete.setOpaque(rootPaneCheckingEnabled);
        Delete.setBorderPainted(rootPaneCheckingEnabled);
        JButton Move = new JButton("Move");
        Move.addActionListener(this);
        Move.setBackground(Color.red);
        Move.setOpaque(rootPaneCheckingEnabled);
        Move.setBorderPainted(rootPaneCheckingEnabled);
        JButton Undo = new JButton("Undo");
        Undo.addActionListener(this);
        Undo.setBackground(Color.red);
        Undo.setOpaque(rootPaneCheckingEnabled);
        Undo.setBorderPainted(rootPaneCheckingEnabled);
        JButton Redo = new JButton("Redo");
        Redo.addActionListener(this);
        Redo.setBackground(Color.red);
        Redo.setOpaque(rootPaneCheckingEnabled);
        Redo.setBorderPainted(rootPaneCheckingEnabled);
        JButton Resize = new JButton("Resize");
        Resize.addActionListener(this);
        Resize.setBackground(Color.red);
        Resize.setOpaque(rootPaneCheckingEnabled);
        Resize.setBorderPainted(rootPaneCheckingEnabled);
        JButton Group = new JButton("AddToGroup");
        Group.addActionListener(this);
        Group.setBackground(Color.red);
        Group.setOpaque(rootPaneCheckingEnabled);
        Group.setBorderPainted(rootPaneCheckingEnabled);
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        menu.add(i1);  
        menu.add(i2);  
        menu.add(i3);
        menu.add(i4);
        mb.add(menu); 
        con.add("Center",myshape);
        btnPanel.add(Ellieps);
        btnPanel.add(Rect);
        btnPanel.add(Group);
        btnPanel.add(Delete);
        btnPanel.add(Move);
        btnPanel.add(Undo);
        btnPanel.add(Redo);
        btnPanel.add(Resize);
        btnPanel.add(mb);

        con.add(btnPanel, BorderLayout.NORTH);
        setPreferredSize(new Dimension(Frame_With, Frame_Heigth));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Java SimpleEditor");
        setVisible(true);  
    }
    // Allows 30 pixels of extra space around the edges of the applet
    // that will appear in the background color.
     @Override
    public Insets getInsets() {
        return new Insets(30,30,30,30);
    } 
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
            switch (action) {
                case "Ellieps":
                    drawelieps = new CommandDrawElliep(myshape);
                    commandhandler.addComand(drawelieps);
                    myshape.repaint();
                    break;
                case "Rectangel":
                    drawrec = new CommandDrawRect(myshape);
                    commandhandler.addComand(drawrec);
                    myshape.repaint();
                    break;
                 case "AddToGroup":
                    AddTogroup = new CommandaddToGroup(myshape);
                    commandhandler.addComand(AddTogroup);
                    myshape.repaint();
                    break;
                case "Delete":
                    deletshape = new CommandDelete(myshape);
                    commandhandler.addComand(deletshape);
                    myshape.repaint();
                    break;
                case "Move":
                   myshape.shapemove();
                   myshape.repaint();
                    break;
                case "Resize":
                    myshape.resizeFigure();
                    myshape.repaint();
                    break;    
                case "Undo":
                    commandhandler.undo();
                    //myshape.undo();
                    myshape.repaint();
                    break;
                case "Redo":
                    commandhandler.redo();
                    myshape.repaint();
                    break;
                case "addLeft":
                    myshape.addDecorator("addLeft");
                    myshape.repaint();
                    break;
                case "addTop":
                    myshape.addDecorator("addTop");
                    myshape.repaint();
                    break;
                case "addRight":
                    myshape.addDecorator("addRight");
                    myshape.repaint();
                    break;
                case "addBottom":
                    myshape.addDecorator("addBottom");
                    myshape.repaint();
                    break;
                default:
                    break;
            }
    } 

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args)throws IOException {
        SwingUtilities.invokeLater(() -> {
            JavaSimpleEditor javaSimpleEditor = new JavaSimpleEditor();
        });
    }
    
}
