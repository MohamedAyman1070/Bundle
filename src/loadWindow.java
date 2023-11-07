import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class loadWindow extends JFrame {
    JProgressBar loadingBar ;
    JLabel label1,label2 ;
    loadWindow(){
        label1 = new JLabel("CREATED BY:");
        label1.setFont(new Font(null ,Font.BOLD , 50));
        label1.setBounds(60,50,350,150);
        label1.setForeground(new Color(220, 96, 0));

        label2 = new JLabel("MOHAMED AYMAN");
        label2.setFont(new Font(null ,Font.BOLD , 50));
        label2.setBounds(60,100,600,150);
        label2.setForeground(new Color(220, 96, 0));


        loadingBar = new JProgressBar();
        loadingBar.setValue(0);
        loadingBar.setBorder(BorderFactory.createSoftBevelBorder(1));
        loadingBar.setBounds(80 ,300 , 400,20);
        loadingBar.setFocusable(false);
        loadingBar.setBackground(new Color(0, 0, 27));
        loadingBar.setForeground(new Color(220, 96, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocation(500 , 200);
        setSize(600,450);
        getContentPane().setBackground(new Color(27,27,27));
        setShape(new RoundRectangle2D.Double(0,0,580,430,50,50));
        setLayout(null);
        add(loadingBar);
        add(label1);
        add(label2);
        setVisible(true);
        fillBar();
    }
    public void fillBar(){
        int count = 0 ;
        while(count++<=100){
            try{loadingBar.setValue(count);
                Thread.sleep(40);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isFilled(){
        return loadingBar.getValue() == 100;
    }
}
