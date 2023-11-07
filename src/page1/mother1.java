package page1;

import dataBase.databaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SectionList extends JPanel implements ActionListener  {
    databaseHandler database ;
    int numberOFSection= 0;
    Integer count = 1 ;
    SectionList(){
        database=databaseHandler.getInstance();
        //setSize (700 ,600);
        setBackground(new Color(27,27,27));//161, 255, 0
        //setLocation(80,20);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        //setLayout(new GridLayout(0,1,0,5));

        add(new Section("+"));
        try{
            numberOFSection = database.numOFSection();
        }catch(Exception e){
            e.printStackTrace();
        }
        Timer t = new Timer(1 , this);
        //t.start();
        readSections();
    }
    private void readSections(){
        try{
            Thread thread = new Thread(){
                @Override
                public void run(){
                    while (true){
                       while (count <= numberOFSection){
                           try{
                               add(new Section("  "+database.getNameOFSection(count.toString())));
                               count++ ;
                           }catch(Exception e){
                               e.printStackTrace();
                           }
                       }
                    }
                }
            };

            thread.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}

public class mother1 extends JPanel implements ActionListener {
    SectionList sectionList ;
    JLabel title ;
    JScrollPane scrollPane ;
    private static mother1 m = null;

    private static final Object lock = new Object();
    public mother1 (){
    setSize(850,640);
    setBackground(new Color(27,27,27));
    setLocation(15,10);
    //setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
    setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
    title = new JLabel("Bundle");
    title.setFont(new Font(null , Font.PLAIN ,100));
    title.setForeground(Color.orange);
    //sections
    {
        sectionList = new SectionList();
        scrollPane = new JScrollPane(sectionList);
        //scrollPane.setSize (700 ,600);
        scrollPane.setPreferredSize(new Dimension(700 ,600));
        scrollPane.setLocation(80,20);
        add(Box.createHorizontalStrut(300) ,BoxLayout.X_AXIS);
        add(title);
        add(scrollPane);
    }
}
public static mother1 getInstance(){
        if(m ==  null){
            synchronized (lock){
                if(m==null)
                {
                    m= new mother1();
                }
            }
        }
        return m ;
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
