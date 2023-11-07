package page2;

import dataBase.databaseHandler;
import page3.buttons;
import validationWorkSpace.validation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class mother2 extends JPanel implements ActionListener {
    validation v = validation.getInstance();
//    SpringLayout layout = new SpringLayout();
    FlowLayout layout = new FlowLayout(FlowLayout.CENTER , 30 ,150);
    //GroupLayout layout = new GroupLayout(this);
    JLabel lnameOFSection , lbundle;
    JTextField tnameOFSection , tbundle;
    static String name ;
    buttons ok  , back;
    databaseHandler database ;
    public static boolean goToMother3 , goToMother1 ;
    private static final Object lock = new Object();
    private static  mother2  m = null;
    public mother2(){
        database = databaseHandler.getInstance();
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
        lnameOFSection = new JLabel("اسم القسم :");
        lbundle = new JLabel("المبلغ :");
        {
            lnameOFSection.setFont(new Font(null ,Font.PLAIN, 30));
            lbundle .setFont (new Font(null, Font.PLAIN,30));
            lbundle.setForeground(Color.white);
            lnameOFSection.setForeground(Color.white);
           // lnameOFSection.setSize (50,50);
           // lbundle.setSize(150,50);
            lnameOFSection.setPreferredSize(new Dimension(150,50));
            lbundle.setPreferredSize(new Dimension(150,50));
        }
        tnameOFSection = new JTextField();
        tbundle = new JTextField();
        {
            tnameOFSection.setPreferredSize(new Dimension(600 ,60));
            tnameOFSection.setBackground(Color.lightGray);
            tnameOFSection.setFont(new Font(null ,Font.PLAIN , 25));
            tbundle.setPreferredSize(new Dimension(600 , 60));
            tbundle.setFont(new Font(null , Font.PLAIN , 25));
            tbundle.setBackground(Color.lightGray);
        }
        ok = new buttons("تأكيد");
        {
            ok.setPreferredSize(new Dimension(200,80));
            ok.setFont(new Font(null, Font.PLAIN , 25));
            ok.addActionListener(this);
        }
        back = new buttons("السابق");
        {
            back.setPreferredSize(new Dimension(200 , 80));
            back.setFont(new Font(null , Font.PLAIN , 25));
            back.addActionListener(this);
        }

//        layout.setHorizontalGroup(
//                layout.createSequentialGroup()
//                        .addComponent(lnameOFSection)
//                        .addComponent(tnameOFSection)
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                .addComponent(lbundle)
//                                .addComponent(tbundle)
//                        )
//        );
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                        .addComponent(lbundle)
//                        .addComponent(tbundle)
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                        .addComponent(lnameOFSection)
//                                        .addComponent(tnameOFSection)
//                                )
//
//        );


        //design
//        {
//            layout.putConstraint(SpringLayout.EAST,lnameOFSection , 20, SpringLayout.EAST,this);
//            layout.putConstraint(SpringLayout.NORTH,lnameOFSection , 150, SpringLayout.NORTH,this);
//
//            layout.putConstraint(SpringLayout.EAST,tnameOFSection , 15, SpringLayout.EAST,lnameOFSection);
//            layout.putConstraint(SpringLayout.NORTH,tnameOFSection , 70, SpringLayout.NORTH,lnameOFSection);
//
//            layout.putConstraint(SpringLayout.EAST,lbundle , 0, SpringLayout.EAST,lnameOFSection);
//            layout.putConstraint(SpringLayout.NORTH,lbundle , 70, SpringLayout.NORTH,tnameOFSection);
//
//            layout.putConstraint(SpringLayout.EAST,tbundle , 15, SpringLayout.EAST,lnameOFSection);
//            layout.putConstraint(SpringLayout.NORTH,tbundle , 70, SpringLayout.NORTH,lbundle);
//
//            layout.putConstraint(SpringLayout.EAST,ok , -18, SpringLayout.EAST,this);
//            layout.putConstraint(SpringLayout.SOUTH, ok , -15, SpringLayout.SOUTH,this);
//        }
        setSize(850,640);
        setLayout(layout);
       // setBackground(Color.black);
        setLocation(15,10);
        setBackground(new Color(27,27,27));
        add(tnameOFSection);
        add(lnameOFSection);
        add(tbundle);
        add(lbundle);
        add(back);
        add(Box.createHorizontalStrut(200));
        add(ok);


    }
    public static  mother2 getInstance(){
        if (m == null){
            synchronized (lock){
                if(m==null){
                    m=new mother2();
                }
            }
        }
        return m ;
    }
    public String getSectionNameFromMother2(){
        return name;
    }


    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if(e.getSource()==ok){
            System.out.println(tnameOFSection.getText());
            if(!v.isBlank(tnameOFSection.getText()) && !v.isBlank(tbundle.getText())
            &&v.isName(tnameOFSection.getText()) && v.isNumber(tbundle.getText())
            ){
                name = tnameOFSection.getText();
                try {
                    database.newSection(tnameOFSection.getText());
                    database.putCredit(database.getSectionID(tnameOFSection.getText()),  new Float(tbundle.getText()));
                    goToMother3 = true ;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            if(v.isBlank(tnameOFSection.getText())){
                JOptionPane.showMessageDialog(null ,null , "تحذير : الاسم فارغ",JOptionPane.ERROR_MESSAGE);
            }
            else if(!v.isName(tnameOFSection.getText())){
                JOptionPane.showMessageDialog(null ,null , "من فضلك ادخل اسم صحيح",JOptionPane.ERROR_MESSAGE);
            }
            if(v.isBlank(tbundle.getText())){
                JOptionPane.showMessageDialog(null ,null , "تحذير : الرقم فارغ",JOptionPane.ERROR_MESSAGE);
            }
            else if(!v.isNumber(tbundle.getText())){
                JOptionPane.showMessageDialog(null ,null , "من فضلك ادخل رقم صحيح",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == back){
            goToMother1 =true;
            tbundle.setText("");tnameOFSection.setText("");
        }
    }
}
