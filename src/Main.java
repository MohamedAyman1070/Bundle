import dataBase.databaseHandler;

public class Main {
    public static void main(String[] args) throws Exception {
       databaseHandler database = databaseHandler.getInstance();
       loadWindow w = new loadWindow();
       window program = new window();
       program.setVisible(false);
       if(w.isFilled()&&database.isConnected()){
           w.dispose();
           program.setVisible(true);
       }
    }
}


