import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

public class ATM {

    static String password;


    public static void main(String[] args) throws SQLException {


     ATM a=new ATM();
     a.userInterface();







    }


    public void userInterface() throws SQLException {
        int choice;
        System.out.println("Enter 1 for withdrawing money");
        System.out.println("Enter 2 for depositing money");
        System.out.println("Enter 3 for checking balance");

        String username;










        for(int i=0;i<3;i++) {


            System.out.println("Enter your choice");

            Scanner sc=new Scanner(System.in);

            choice=sc.nextInt();

            System.out.println("Enter your username");
            username=sc.next();

            System.out.println("Enter your password");
            password=sc.next();




            Integer a= Login(username,password);
            switch (choice) {




                case 1:
                    System.out.println("Enter the amount to be withdrawn:");
                    float amount;
                    amount = sc.nextFloat();

                    if(a!=0)
                    {
                        Withdraw_Money(a, amount);
                    }

                    else {
                        System.out.println("Invalid Credentials");
                    }


                    break;


                case 2:

                    System.out.println("Enter the amount to be deposited:");
                    float amt;
                    amt = sc.nextFloat();

                    if(a!=0)
                    {
                        DepositMoney(a, amt);
                    }

                    else {
                        System.out.println("Invalid Credentials");
                    }

                    break;


                case 3:



                    if(a!=0)
                    {
                       Check_Balance(a);
                    }

                    else {
                        System.out.println("Invalid Credentials");
                    }

                    break;



                default:
                    System.out.println("Invalid Choice");




            }


            System.out.println("Do you want to continue (1-yes 0-No)");

            int s=sc.nextInt();

            if(s==0)
            {
                break;
            }

        }

    }


    public static int Login(String UserName, String Password) throws SQLException {
        Connection con = User_Account.Connection();




        // Prepare the SQL query with placeholders for username and password
        String sqlQuery = "SELECT * from user_accounts where username='"+UserName+"' AND password='"+Password+"'";

        // Create a PreparedStatement to execute the SELECT query
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);


        // Execute the SELECT query



        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            // Count the number of rows in the ResultSet




            while (resultSet.next()) {



                int a=resultSet.getInt(1);

                return a;














            }






        }


    catch (SQLException e) {
        e.printStackTrace();
    }



        return 0;




    }


    public static void Withdraw_Money(int Account_Number, float withdraw_money) throws SQLException {


        Connection con = User_Account.Connection();


        String selectQuery = "SELECT balance,username FROM user_accounts WHERE account_number = '" + Account_Number + "'";


        // Create a PreparedStatement and set the parameter value for the WHERE clause
        try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {


            // Execute the SELECT query
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve the balance from the result set
                    float balance = resultSet.getFloat("balance");

                    System.out.println("Current Balance for Account Number  " + Account_Number + ": " + balance);



                    // Update balance using UPDATE query
                    float newBalance = balance - withdraw_money; // Example: Adding 1000 to the current balance
                    String updateQuery = "UPDATE user_accounts SET balance = ? WHERE account_number = ?";

                    if (withdraw_money <= balance) {
                        // Create a PreparedStatement and set the parameter values for the UPDATE query
                        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                            updateStatement.setFloat(1, newBalance);
                            updateStatement.setInt(2, Account_Number);

                            // Execute the UPDATE query
                            int rowsUpdated = updateStatement.executeUpdate();
                            if (rowsUpdated == 1) {
                                System.out.println("Balance Successfully Updated");
                                System.out.println("Updated Balance is:" + newBalance);
                            }
                        }


                    } else {
                        System.out.println("Insufficient Balance");
                    }

                } else {
                    System.out.println("User not found.");
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public static void DepositMoney(int Account_Number,float money) throws SQLException {


        Connection con = User_Account.Connection();


        String selectQuery = "SELECT balance,username FROM user_accounts WHERE account_number = '" + Account_Number + "'";


        // Create a PreparedStatement and set the parameter value for the WHERE clause
        try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {


            // Execute the SELECT query
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve the balance from the result set
                    float balance = resultSet.getFloat("balance");

                    System.out.println("Current Balance for Account Number  " + Account_Number + ": " + balance);



                    // Update balance using UPDATE query
                    float newBalance = balance + money; //
                    String updateQuery = "UPDATE user_accounts SET balance = ? WHERE account_number = ?";


                        // Create a PreparedStatement and set the parameter values for the UPDATE query
                        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                            updateStatement.setFloat(1, newBalance);
                            updateStatement.setInt(2, Account_Number);

                            // Execute the UPDATE query
                            int rowsUpdated = updateStatement.executeUpdate();
                            if (rowsUpdated == 1) {
                                System.out.println("Balance Successfully Updated");
                                System.out.println("Updated Balance is:" + newBalance);
                            }
                        }


                    }



                 else {
                    System.out.println("User not found.");
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }






    }


    public static void Check_Balance(int Account_Number) throws SQLException {
        Connection con = User_Account.Connection();


        String selectQuery = "SELECT balance FROM user_accounts WHERE account_number = '" + Account_Number + "'";


        // Create a PreparedStatement and set the parameter value for the WHERE clause
        try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {


            // Execute the SELECT query
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve the balance from the result set
                    float balance = resultSet.getFloat("balance");

                    System.out.println("Current Balance for Account Number  " + Account_Number + ": " + balance);






                }



                else {
                    System.out.println("User not found.");
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
