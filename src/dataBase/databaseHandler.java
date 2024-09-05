package dataBase;

import page1.Section;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class databaseHandler {
    private Connection con ;
    private Statement statement;
    private ResultSet res ;
    private static final Object lock = new Object();
    private static databaseHandler data = null ;
    public databaseHandler(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/creditsDatabase","root","password");
            statement = con.createStatement();
            System.out.println("it works");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean isConnected(){
        return this.con != null;
    }
    public static  databaseHandler getInstance(){
        if (data == null){
            synchronized (lock){
                if (data == null ){
                    data = new databaseHandler();
                }
            }
        }
        return data;
    }
    public void newSection (String sectionName)throws Exception{
           con.prepareCall("call createSection(\""+sectionName+"\");").execute();
    }
    public void putCredit (int SectionID , float bundle)throws Exception{
        con.prepareCall("call putCredit(\""+SectionID +"\"," +"\""+bundle+"\");").execute();
    }
    public  void putRate(String CreditID , float bundle , String item)throws Exception {
        con.prepareCall("call putRate (\""+CreditID+"\""+ ", \""+bundle+"\""+ ", \""+item+"\""+");").execute();
    }
    public synchronized Map readRate(int CreditID)throws Exception{
        //con.prepareCall("call readRate(\""+CreditID+"\");").execute();
        Map<String , ArrayList> data = new HashMap<>();
        ArrayList date,bundle = new ArrayList<String>();ArrayList items = new ArrayList<String>() ;
        date= new ArrayList<String>();
        res = statement.executeQuery("select * from Rate where Credit_id ="+"\""+CreditID+"\""+";");
            while (res.next()){
                date.add(res.getString("dates"));
                bundle.add(res.getString("mountOFMoney"));
                items.add(res.getString("items"));
                System.out.println("hello there : "+res.getString("dates"));
            }
        data.put("date",date);
        data.put("bundle" ,bundle);
        data.put("items" , items);
        System.out.println(data);
        return data ;
    }
    public int numOFSection()throws Exception{
         res =statement.executeQuery("select count(id) from Section;");
         if (res.next()){
             return res.getInt(1);
         }
         return -1;
    }
    public  Integer getSectionID (String SectionName)throws Exception{
        res = statement.executeQuery("select id from Section where nameOFSection = \""+SectionName+"\" ;");
        if(res.next()){
            return new Integer(res.getString("id"));
        }
        return -1 ;
    }
    public String getNameOFSection(String ID)throws Exception{
        res = statement.executeQuery("select nameOFSection from Section where id = "+ID+";");
        if (res.next()){
            return res.getString("nameOFSection");
        }
        return "";
    }
    public String getCreditIDFromSectionID(String SectionID)throws Exception{
        res = statement.executeQuery("select id from Credit where Section_id = "+ SectionID+";");
        if (res.next()){
            return res.getString("id");
        }
        return "";
    }
    public int getBundle(String CreditID)throws Exception{
        res = statement.executeQuery("select bundle_of_money from Credit where id = "+CreditID+";");
        if (res.next()){
            //return res.getString("bundle_of_money"));
            return res.getInt(1);
        }
        return -1;
    }
    public int getCountRate(String CreditID)throws Exception{
            res = statement.executeQuery("select sum(mountOFMoney) from Rate where Credit_id = "+CreditID+";");
            if(res.next()){
                return res.getInt(1);
            }
            return -1;
    }






}
