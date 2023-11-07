package page1;

import dataBase.databaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Section extends JLabel implements MouseListener {
    databaseHandler database;
    public static boolean goToMother2;
    public static boolean goToMother3 ;
    private static final Object lock = new Object();
    private static Section section = null;
    private static String name ;

    public Section(String name) {
        database =databaseHandler.getInstance();
        setText(name);
        setVerticalAlignment(CENTER);setHorizontalAlignment(CENTER);
        setPreferredSize(new Dimension(150, 150));
        setFont(new Font(null, Font.PLAIN, 50));
        setOpaque(true);
        setBackground(new Color(220, 96, 0,200));//36, 59, 232,200
        setForeground(Color.black);
        addMouseListener(this);

    }

    public static Section getInstance() {
        if (section == null) {
            synchronized (lock) {
                if (section == null) {
                    section = new Section("");
                }
            }
        }
        return section;
    }
    public String getNameFromMother1(){
        return name.trim();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if(e.getSource()==this){
               if(this.getText().equals("+")){
                   goToMother2 =true;
               }
               else{
                   goToMother3=true;
                   name = this.getText();
               }
            }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
