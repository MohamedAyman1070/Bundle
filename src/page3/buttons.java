package page3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class buttons extends JButton implements MouseListener {
    public  buttons(String name) {
        setText(name);
        setPreferredSize(new Dimension(200 , 50));
        setFocusable(false);
        setForeground(Color.white);
        setFont(new Font(null , Font.PLAIN , 25));
        setBackground(new Color(220, 96, 0));
        setIgnoreRepaint(true);
        setBorderPainted(false);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==this){
            setBackground(new Color(175, 76, 4));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==this){
            setBackground(new Color(220, 96, 0));
        }
    }
}
