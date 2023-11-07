import page1.mother1;
import page2.mother2;
import page3.mother3;
import page1.*;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window extends JFrame implements ActionListener {
    CardLayout layout ;
    Section  key1 ;
    mother2 key2 ;
    mother3 key3;
    Container main;
    window(){
        key1 = Section. getInstance();
        key2 = mother2.getInstance();
        key3 = mother3.getInstance();
        layout=new CardLayout();
        mother1 p1 = new mother1();
        mother2 p2 = new mother2();
        mother3 p3 = new mother3();
        main = new Container();
        main.setLayout(layout);
        main.add(p1 ,"mother1");
        main.add(p2 , "mother2");
        main.add(p3 , "mother3");
        layout.show(main , "mother1");
        setLocation(400 , 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,900);
        setMaximumSize(new Dimension(1000,1000));
        setLayout(new BorderLayout());
        setTitle("Bundle");
        add(main ,BorderLayout.CENTER);
       // setResizable(true);
        setVisible(true);
        Timer t= new Timer(1 , this);
        t.start();
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if(key1.goToMother2){
            layout.show(main, "mother2");
            key1.goToMother2 = false;
        }
        if(key2.goToMother3){
            layout.show(main , "mother3");
            key2.goToMother3=false;
        }
        if(key1.goToMother3){
            layout.show(main,"mother3");
            key1.goToMother3 = false;
        }
        if(key3.goToMother1){
            layout.show(main, "mother1");
            key3.goToMother1 = false;
        }
        if(key2.goToMother1){
            layout.show(main,"mother1");
            key2.goToMother1 = false;
        }




    }
}
