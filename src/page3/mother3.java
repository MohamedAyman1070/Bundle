package page3;

import dataBase.databaseHandler;
import page1.Section;
import page2.mother2;
import validationWorkSpace.validation;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.*;


public class mother3 extends JPanel implements ActionListener {
    validation v = validation.getInstance();
    private boolean goBack ;
    buttons back , insert ;
    JLabel credit , reminder , lBundle , lItem ;
    JTable table  ;
    JScrollPane scrollPane ;
    JTextField bundle, item ;
    databaseHandler database;
    mother2 m2 ;
    DefaultTableModel model;
    Section section ;
    Timer timer;
    JProgressBar measureBar ;
    public static boolean goToMother1 ;
    private static final Object lock = new Object();
    private static  mother3  m = null ;
    public mother3(){
        m2= mother2.getInstance();
        section = Section.getInstance();
        database = databaseHandler.getInstance();
        setSize(850,640);
        setLocation(15,10);
        //SpringLayout layout = new SpringLayout();
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        {
            back = new buttons("السابق");
            back.addActionListener(this);

            insert = new buttons("إدخال");
            insert.addActionListener(this);
        }
        {
            credit = new JLabel("المبلغ :                                                                        ");
            credit.setPreferredSize(new Dimension(700 , 40));
            credit.setFont(new Font(null ,Font.PLAIN , 30));
            credit.setBackground(new Color(220,96,0));credit.setOpaque(true);

            reminder = new JLabel("المتبقي :                                                                        ");
            reminder.setPreferredSize(new Dimension(700 , 40));
            reminder.setFont(new Font(null, Font.PLAIN , 30));
            reminder.setBackground(new Color(220,96,0));reminder.setOpaque(true);

            lBundle = new JLabel("المبلغ:");
            lBundle.setFont(new Font(null, Font.PLAIN , 30));
            lBundle.setPreferredSize(new Dimension(700 , 40));
            lBundle.setForeground(Color.white);

            lItem = new JLabel("الصنف:");
            lItem.setPreferredSize(new Dimension(700 , 40));
            lItem.setFont(new Font(null, Font.PLAIN , 30));
            lItem.setForeground(Color.white);
        }
        {
            model = new DefaultTableModel();
            String [] column = {"المدفوع","الوقت/التاريخ"  , "الصنف"};
            model.setColumnIdentifiers(column);
            table = new JTable();
            table.setModel(model);
            table.setFont(new Font(null , Font.PLAIN , 30));
            table.getTableHeader().setFont(new Font(null , Font.PLAIN , 30));
            table.setRowHeight(50);
            table.setSelectionBackground(new Color(220,96,0));
            table.setShowVerticalLines(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(700,400));
        }
        {
            bundle = new JTextField();
            bundle.setFont(new Font(null , Font.PLAIN , 30));
            bundle.setPreferredSize(new Dimension(700 , 60));

            item = new JTextField();
            item.setFont(new Font(null , Font.PLAIN , 30));
            item.setPreferredSize(new Dimension(700 , 60));
        }
        {
            measureBar = new JProgressBar();
            measureBar.setValue(100);
            measureBar.setPreferredSize(new Dimension(700,30));
            measureBar.setBackground(new Color(0, 0, 27));
            measureBar.setForeground(Color.green);
            measureBar.setFocusable(false);
        }
        add(credit); add(reminder);
        add(scrollPane);
//        add(measureBar);
        add(lBundle);add(bundle);
        add(lItem);add(item);
        add(back);add(Box.createHorizontalStrut(100));
        add(insert);
        setBackground(new Color(27,27,27));
        timer = new Timer(1,this );
        timer.start();
//        barHandler();
    }
//    private synchronized void  barController(double paid , double bundle){
//        try{
//        Thread t = new Thread(){
//            @Override
//            public void run(){
//                double paidPercentage = (paid/bundle) * 100;
//                int  currentMeasure =measureBar.getValue();
//                System.out.println(measureBar.getValue());
//                while(measureBar.getValue() > 100-paidPercentage ){
//                    try{
//                        measureBar.setValue(currentMeasure--);
//                        Thread.sleep(30);
//                    }catch (Exception e){
//                        System.out.println(e.getMessage());
//                    }
//                    if(measureBar.getValue() <=100 && measureBar.getValue() >=60){
//                        measureBar.setForeground(Color.green);
//                    }
//                    if(measureBar.getValue() <=59 && measureBar.getValue() >=30){
//                        measureBar.setForeground(Color.yellow);
//                    }
//                    if(measureBar.getValue() <=29 && measureBar.getValue() >=0){
//                        measureBar.setForeground(Color.red);
//                    }
//                }
//            }
//        };
//        t.start();
//      }
//      catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    public static mother3 getInstance(){
        if(m == null){
            synchronized (lock){
                if(m==null){
                    m=new mother3();
                }
            }
        }
        return m ;
    }
    private void putDataToTable(String sectionName){
        try{
                        Iterator<ArrayList<String>> iter =  database.readRate(new Integer(database.getCreditIDFromSectionID(database.getSectionID(sectionName).toString()))).values().iterator();
                        ArrayList<ArrayList<String>> hell = new ArrayList<>();
                        String [] Data = new String[3];
                        while(iter.hasNext()) {
                            hell.add(iter.next());
                            System.out.println("hell : "+hell);
                        }
                        //String []Data = new String[2];
                        int j=0 ;
                        int index=0 ;
                        while(j!=hell.get(1).size()){
                            Data[index]=hell.get(1).get(j);index++; //date
                            Data[index]=hell.get(0).get(j);index++; //bundle
                            Data[index]=hell.get(2).get(j);index-=2;//items
                            model.addRow(Data);
                            j++;
                        }
                        System.out.println("end");
        }
        catch(Exception e){
                e.printStackTrace();
        }
    }

    private String getReminder (String sectionID)throws Exception{
        int rateCount =0;
        int  bundle =0;
            rateCount=database.getCountRate(database.getCreditIDFromSectionID(database.getSectionID(sectionID).toString()));
            bundle =database.getBundle(database.getCreditIDFromSectionID(database.getSectionID(sectionID).toString()));
        int reminder = bundle - rateCount ;
        return String.valueOf(reminder);
    }

    @Override
    public  void actionPerformed(ActionEvent e) {

        if(e.getSource()==back){
            goToMother1=true;
            model.getDataVector().removeAllElements();
            goBack = true;
            measureBar.setValue(100);
        }
       // System.out.println(m2.getSectionNameFromMother2());
        LocalDateTime currentDateTime = LocalDateTime.now();
        String []data = {bundle.getText() ,currentDateTime.toString() , item.getText() };
        if(e.getSource() == insert){
            try{
                if(m2.getSectionNameFromMother2()!=null){ System.out.println(database.getSectionID(m2.getSectionNameFromMother2()));
                    if(v.isNumber(bundle.getText()) && !v.isBlank(bundle.getText())
                       && v.isName(item.getText()) && !v.isBlank(item.getText())){
                        database.putRate(
                                database.getCreditIDFromSectionID(
                                        database.getSectionID(m2.getSectionNameFromMother2()).toString()
                                ),new Float(bundle.getText()) ,item.getText());
                    }
                }
                else if(section.getNameFromMother1()!=null){
                    if(v.isNumber(bundle.getText()) && !v.isBlank(bundle.getText())
                            && v.isName(item.getText()) && !v.isBlank(item.getText())){
                            database.putRate(
                                    database.getCreditIDFromSectionID(
                                            database.getSectionID(section.getNameFromMother1()).toString()
                                    ),new Float(bundle.getText()) , item.getText());
                    }
                }
                if(v.isNumber(bundle.getText()) && !v.isBlank(bundle.getText())
                        && v.isName(item.getText()) && !v.isBlank(item.getText())){
                    model.addRow(data);
                }
                if(v.isBlank(bundle.getText())){
                    JOptionPane.showMessageDialog(null,null,"تحذير:المبلغ فارغ",JOptionPane.ERROR_MESSAGE);
                }
                else if(!v.isNumber(bundle.getText())){
                    JOptionPane.showMessageDialog(null ,null ,"من فضلك ادخل مبلغ صحيح" , JOptionPane.ERROR_MESSAGE);
                }
                if(v.isBlank(item.getText())){
                    JOptionPane.showMessageDialog(null, null,"تحذير:الصنف فارغ"  , JOptionPane.ERROR_MESSAGE);
                }
                else if(!v.isName(item.getText())){
                    JOptionPane.showMessageDialog(null ,null ,"من فضلك ادخل اسم صنف صحيح" , JOptionPane.ERROR_MESSAGE);
                }
                bundle.setText("");
                item.setText("");

            }catch(Exception ex ){
                ex.printStackTrace();
            }
        }


        try{
            if (section.goToMother3){
                putDataToTable(section.getNameFromMother1());
            }
            if(m2.getSectionNameFromMother2()!=null){
                double bundle1 = database.getBundle(database.getCreditIDFromSectionID(database.getSectionID(m2.getSectionNameFromMother2()).toString()));
                String bundle =String.valueOf(bundle1);
                credit.setText("المبلغ:                                                "+bundle);
                reminder.setText("الباقي:                                                 "+getReminder(m2.getSectionNameFromMother2()));
            }
            else if(section.getNameFromMother1()!=null){
                double bundle1 = database.getBundle(database.getCreditIDFromSectionID(database.getSectionID(section.getNameFromMother1()).toString()));
                String bundle =String.valueOf(bundle1);
                credit.setText("المبلغ:                                                 "+bundle);
                reminder.setText("الباقي:                                                  "+getReminder(section.getNameFromMother1()));
            }
        }catch(Exception edx){
          //  edx.printStackTrace();
        }


    }
//    private void barHandler (){
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                    try{
//                        while(true){
//                            if(m2.getSectionNameFromMother2()!=null){
//                                double bundle1 = database.getBundle(database.getCreditIDFromSectionID(database.getSectionID(m2.getSectionNameFromMother2()).toString()));
//                                double paid =database.getCountRate(database.getCreditIDFromSectionID(database.getSectionID(m2.getSectionNameFromMother2()).toString()));
//                                barController(paid ,bundle1);
//                                return;
//                            }
//                            else if(section.getNameFromMother1()!=null){
//                                double bundle1 = database.getBundle(database.getCreditIDFromSectionID(database.getSectionID(section.getNameFromMother1()).toString()));
//                                double paid = database.getCountRate(database.getCreditIDFromSectionID(database.getSectionID(section.getNameFromMother1()).toString()));
//                                barController(paid ,bundle1);
//                                return ;
//                            }
//                        }
//                    }catch(Exception e){e.printStackTrace();}
//            }
//        };
//       if(section.goToMother3)t.start();
//       if(goBack)t.stop();
//    }

}
