import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class User_Account {


    private int Account_Number;
    private String UserName;
    private String password;
    private float account_balance;
    private static Connection con;




    public  void CreateAccount() throws SQLException {



        Scanner sc=new Scanner(System.in);

        System.out.println("Enter your Account Number(100-1000):");

        Account_Number=sc.nextInt();

        System.out.println("Enter a username:");
        UserName=sc.next();
        System.out.println("Enter your password:");
        password=sc.next();
        System.out.println("Enter your account balance:");

        account_balance= sc.nextFloat();



        Statement st = con.createStatement();

        String sqlinsert="INSERT INTO user_accounts VALUES('"+Account_Number+"','"+UserName+"','"+password+"','"+account_balance+"')";

        st.executeUpdate(sqlinsert);

        System.out.println("Account Successfully Created");




    }


    public static Connection Connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String pwd = "sheru123#";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con = DriverManager.getConnection(url, user, pwd);

        return con;

    }

    public static void main(String args[]) throws SQLException {



    User_Account.Connection();

        User_Account u=new User_Account();

        u.CreateAccount();












    }


}
