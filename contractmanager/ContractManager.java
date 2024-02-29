/*
This program is for storing information 
of customers and their contracts
for a telephone contract manager
 */
package contractmanager;

import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JOptionPane;

//@author Tom D

public class ContractManager 
{

    public static void main(String[] args) throws FileNotFoundException 
    {
        Contract customerContract = new Contract();
        int option = 5;
        while (option != 0)
        {
            option = getMenu();
            if (option == 1)
            {
                addNewContract();
            }
            else if (option == 2)
            {
                getSummary();
            }
            else if (option == 3)
            {
                getSelectedSummary();
            }
            else if (option == 4)
            {
                getSearch();
            }
        }
        System.out.println("Program is closing...");
        System.exit(0);
        
        
    } //end of main
    
    
    //displays menu and returns option
    public static int getMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(
                "1. Enter new Contract\n" + 
                "2. Display Summary of Contracts\n" + 
                "3. Display Summary of Contracts for Selected Month\n" + 
                "4. Find and display Contract\n" + 
                "0. Exit");
        int option = keyboard.nextInt();
        while (option > 4 || option < 0)
        {
            System.out.print("Please enter a number between 0-4: ");
            option = keyboard.nextInt();
        }
        return option;
    }
    
    //adds new contract
    public static void addNewContract()
    {
        displayWarning();

        String cName = getName();

        String cPackage = getPackage();

        String cBundle = getBundle();

        String cBusiness = getBusiness();

        String cRef = getRef(cBusiness);

        String cPeriod = getContractLength(cBusiness);

        String cIntCalls = getIntCalls();

        double cMonthlyCharge = getTotal(cPackage, cBundle, cRef, cBusiness, cPeriod, cIntCalls);

        int cDiscount = getDiscount(cBusiness, cPeriod, cIntCalls);

        displayResult(cName, cRef, cBundle, cPeriod, cIntCalls, cPackage, cBusiness, cMonthlyCharge, cDiscount);

        String cDate = getDate();

        saveToFile(cDate, cPackage, cBundle, cPeriod, cIntCalls, cRef, cMonthlyCharge, cName);
        
    }
    
    //shows summary of contracts
    public static void getSummary() throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);        
        System.out.println("Select a file\n contracts \n archive");
        String input = keyboard.nextLine();
        double count = 0,countBuffer=0,countLine=0;
        String lineNumber = "";
        String filePath = input + ".txt";
        BufferedReader br;
        String inputSearch;
        inputSearch = "Unlimited";
        @SuppressWarnings("UnusedAssignment")
        String line = "";

        br = new BufferedReader(new FileReader(filePath));
        try 
        {
            try
            {
                while((line = br.readLine()) != null)
                {
                    countLine++;

                    String[] words = line.split(" ");

                    for (String word : words) 
                    {
                        if (word.equals(inputSearch)) 
                        {
                            count++;
                            countBuffer++;
                        }
                    }

                    if(countBuffer > 0)
                    {
                        countBuffer = 0;
                        lineNumber += countLine + ",";
                    }

                }   
            } 
            catch (IOException ex)
            {
                Logger.getLogger(ContractManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            br.close();

        }          
        catch (IOException ex)
        {
            Logger.getLogger(ContractManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Total Number of contracts: "+ lineNumber);
        System.out.println("Number of Unlimited Data Bundles: " + count);
        System.out.print(" ");
    }
            
    
    //Searches for user selected month and displays information
    public static void getSelectedSummary()
    {
        System.out.println("Not Avaliable...");
    }
    
    
    //Searches for contracts that match the user input
    public static void getSearch()
    {
        /*
        This could have been done, but required a rewrite of the code
        to change strings to ints for the package and bundle etc.
        */
        System.out.println("Not Avaliable...");
    }
    

    
    //get the customers name   
    public static String getName()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter your name (e.g. J Mason): ");
        String cName = keyboard.nextLine();
        while (cName.length() > 25 && 0 >= cName.length())
            {
                System.out.print("Too many characters, please re-enter a name 1-25 characters: ");
                cName = keyboard.nextLine();
            }
        Contract.customerName = cName;
        return cName;
    }
    
    
    //get the minutes package
    public static String getPackage()
    {
        Scanner keyboard = new Scanner(System.in);
        String cPackage = null;
        System.out.println("Please enter the number of the package you would like (e.g. 1/2/3)\n" + "1. Small  300 mins\n" + "2. Medium 600 mins\n" + "3. Large 1200 mins");
        int oPackage = keyboard.nextInt();
        while (oPackage > 3 || oPackage < 1)
        {
            System.out.print("Please enter a number between 1-3: ");
            oPackage = keyboard.nextInt();
        }
        switch (oPackage)
        {
            case 1:
                cPackage = "Small (300)";
                break;
            case 2:
                cPackage = "Medium (600)";
                break;
            case 3:
                cPackage = "Large (1200)";
                break;
            default:
                break;
        }
        Contract.customerPackage = cPackage;
        return cPackage;
    }
    
    
    //get the data bundle choice from user
    public static String getBundle()
    {
        Scanner keyboard = new Scanner(System.in);
        String cBundle = null;
        System.out.println("Please enter the data bundle you would like (e.g. 1/2/3/4)\n" + "1. Low (1GB)\n" + "2. Medium(4GB)\n" + "3. High(8GB)\n" + "4. Unlimited");
        int oBundle = keyboard.nextInt();
        while (oBundle > 4 || oBundle < 1)
        {
            System.out.print("Please enter a number between 1-4: ");
            oBundle = keyboard.nextInt();
        }
        switch (oBundle)
        {
            case 1:
                cBundle = "Low (1GB)";
                break;
            case 2:
                cBundle = "Medium (4GB)";
                break;
            case 3:
                cBundle = "High (8GB)";
                break;
            case 4:
                cBundle = "Unlimited";
                break;
            default:
                break;
        }
        Contract.customerBundle= cBundle;
        return cBundle;
    }
    
    
    
    //adds customer to business or non-business
    public static String getBusiness()
    {
        Scanner keyboard = new Scanner(System.in);        
        System.out.print("Would you like a business account? (Y/N): ");
        String cBusiness = keyboard.next();
        while (!(cBusiness.toLowerCase().equals("y") || cBusiness.toLowerCase().equals("yes")) && !(cBusiness.toLowerCase().equals("n") || cBusiness.toLowerCase().equals("no")))
        {
            System.out.print("Please use Y or N: ");
            cBusiness = keyboard.next();
        }
        if (cBusiness.toLowerCase().equals("y") || cBusiness.toLowerCase().equals("yes"))
        {
            cBusiness = "Business";
        }
        else if (cBusiness.toLowerCase().equals("n") || cBusiness.toLowerCase().equals("no"))
        {
            cBusiness = "Non-Business";
        }
        Contract.customerBusiness = cBusiness;
        return cBusiness;
        
    } 
    
    
    
    //get the reference number
    public static String getRef(String cBusiness)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter a reference number (Two Letters followed by three numbers e.g. JB123): ");
        String cRef = keyboard.next();
        String check = "^[a-zA-Z]{2}[0-9]{3}$";
        
        boolean verifyRef = cRef.matches(check);
                
        while (cRef.length() != 5 && verifyRef != true )
        {
            System.out.print("Incorrect, please re-enter a reference number (e.g. JB123): ");
            cRef = keyboard.next();
        }
        
        cRef = cRef.toUpperCase();
        
        if (cBusiness.equals("Business"))
        {
            cRef = cRef + "B";
        }
        else if (cBusiness.equals("Non-Business"))
        {
            cRef = cRef + "N";
        }
        Contract.customerRef = cRef;
        return cRef;
    }
    
    

    
    
    //gets the contract length
    public static String getContractLength(String cBusiness)
    {
        Scanner keyboard = new Scanner(System.in);        
        String cPeriod = null;
        if (cBusiness.equals("Business"))
        {
            System.out.println("Please enter the contract period you would like\n" + "1. 12 Months\n" + "2. 18 Months\n" + "3. 24 Months");
            int oPeriod = keyboard.nextInt();
            while (oPeriod > 3 || oPeriod < 1)
            {
            System.out.print("Please enter a number between 1-3: ");
            oPeriod = keyboard.nextInt();
            }
            switch (oPeriod)
            {
                case 1:
                    cPeriod = "12 Months";
                    break;
                case 2:
                    cPeriod = "18 Months";
                    break;
                case 3:
                    cPeriod = "24 Months";
                    break;
                default:
                    break;
            }
        }
        else if (cBusiness.equals("Non-Business"))
        {
            System.out.println("Please enter the contract period you would like\n" + "1. 1 Month\n" + "2. 12 Months\n" + "3. 18 Months\n" + "4. 24 Months");
            int oPeriod = keyboard.nextInt();
            while (oPeriod > 4 || oPeriod < 1)
            {
                System.out.print("Please enter a number between 1-4: ");
                oPeriod = keyboard.nextInt();
            }
            switch (oPeriod)
            {
                case 1:
                    cPeriod = "1 Month";
                    break;
                case 2:
                    cPeriod = "12 Months";
                    break;
                case 3:
                    cPeriod = "18 Months";
                    break;
                case 4:
                    cPeriod = "24 Months";
                    break;
                default:
                    break;
            }
        }
        Contract.customerPeriod = cPeriod;
        return cPeriod;
    }
    
    
    //asks if they want international calls
    public static String getIntCalls()
    {
        Scanner keyboard = new Scanner(System.in);        
        System.out.print("Would you like to add international calls to your plan? (Y/N): ");
        String cIntCalls = keyboard.next();
        while (!(cIntCalls.toLowerCase().equals("y") || cIntCalls.toLowerCase().equals("yes")) && !(cIntCalls.toLowerCase().equals("n") || cIntCalls.toLowerCase().equals("no")))
        {
            System.out.print("Please use Y or N: ");
            cIntCalls = keyboard.next();
        }
        if (cIntCalls.toLowerCase().equals("y") || cIntCalls.toLowerCase().equals("yes"))
        {
            cIntCalls = "Yes";
        }
        else if (cIntCalls.toLowerCase().equals("n") || cIntCalls.toLowerCase().equals("no"))
        {
            cIntCalls = "No";
        }
        Contract.customerIntCalls = cIntCalls;
        return cIntCalls;
    }
    
    
    
    public static int getTotal(String cPackage, String cBundle, String cRef, String cBusiness, String cPeriod, String cIntCalls)
    {
        int cMonthlyCharge = 0;
        if (cPackage.equals("Small (300)") && cBundle.equals("Low (1GB)"))
        {
            cMonthlyCharge = 500;     
        }
        else if (cPackage.equals("Small (300)") && cBundle.equals("Medium (4GB)"))
        {
            cMonthlyCharge = 700;
        }
        else if (cPackage.equals("Small (300)") && cBundle.equals("High (8GB)"))
        {
            cMonthlyCharge = 900;
        }
        
        
        else if (cPackage.equals("Medium (600)") && cBundle.equals("Low (1GB)"))
        {
            cMonthlyCharge = 650;
        }
        else if (cPackage.equals("Medium (600)") && cBundle.equals("Medium (4GB)"))
        {
            cMonthlyCharge = 850;
        }
        else if (cPackage.equals("Medium (600)") && cBundle.equals("High (8GB)"))
        {
            cMonthlyCharge = 1050;
        }
        
        
        else if (cPackage.equals("Large (1200)") && cBundle.equals("Low (1GB)"))
        {
            cMonthlyCharge = 850;
        }
        else if (cPackage.equals("Large (1200)") && cBundle.equals("Medium (4GB)"))
        {
            cMonthlyCharge = 1050;
        }
        else if (cPackage.equals("Large (1200)") && cBundle.equals("High (8GB)"))
        {
            cMonthlyCharge = 1250;
        }
        else if (cPackage.equals("Large (1200)") && cBundle.equals("Unlimited"))
        {
            cMonthlyCharge = 2000;
        }
        
        if (cBusiness.equals("Business"))
        {
            cMonthlyCharge = cMonthlyCharge - (cMonthlyCharge/100*10);
        }
        
        if (cBusiness.equals("Non-Business") && cPeriod.equals("12 Months") || cPeriod.equals("18 Months"))
        {
            cMonthlyCharge = cMonthlyCharge - (cMonthlyCharge/100*5);
        }
        else if (cBusiness.equals("Non-Business") && cPeriod.equals("24 Months"))
        {
            cMonthlyCharge = cMonthlyCharge - (cMonthlyCharge/100*10);
        }
        
        if (cIntCalls.equals("Yes"))
        {
            cMonthlyCharge = cMonthlyCharge + (cMonthlyCharge/100*15);
        }
        Contract.customerMonthlyPay = cMonthlyCharge;
        return cMonthlyCharge;
    }
    
    
    public static int getDiscount(String cBusiness, String cPeriod, String cIntCalls)
    {
        int cDiscount = 0;
        if (cBusiness.equals("Business"))
        {
            cDiscount = 10;
        }
        
        if (cBusiness.equals("Non-Business") && cPeriod.equals("12 Months") || cPeriod.equals("18 Months"))
        {
            cDiscount = 5;
        }
        else if (cBusiness.equals("Non-Business") && cPeriod.equals("24 Months"))
        {
            cDiscount = 10;
        }
        
        if (cIntCalls.equals("Yes"))
        {
            cDiscount = 15;
        }
        return cDiscount;
    }
    
    
    
    //displays the contract details
    public static void displayResult(String cName, String cRef, String cBundle, String cPeriod, String cIntCalls, String cPackage, String cBusiness, double cMonthlyCharge, int cDiscount)
    {
        String sDate = getDate();
        System.out.printf("+--------------------------------------------+\n");
        System.out.printf("|                                            |\n");
        System.out.printf("| Customer: %-25s        |\n", cName);
        System.out.printf("|                                            |\n");
        System.out.printf("|      Ref: %-6s        Date: %-11s  |\n", cRef, sDate);
        System.out.printf("|  Package: %-12s  Data: %-12s |\n", cPackage, cBundle);
        System.out.printf("|   Period: %-9s     Type: %-12s |\n", cPeriod, cBusiness);
        System.out.printf("|                                            |\n");
        System.out.printf("| Discount: %-2d     Intl. Calls: %-3s          |\n", cDiscount, cIntCalls);
        System.out.printf("|                                            |\n");
        if (cDiscount > 0)
        {
            System.out.printf("|      Discounted Monthly Charge: £%-5.2f     |\n", cMonthlyCharge/100);
        }
        else
        {
            System.out.printf("|           Monthly Charge: £%-5.2f           |\n", cMonthlyCharge/100);
        }
        System.out.printf("|                                            |\n");
        System.out.printf("+--------------------------------------------+\n");
        
    }
    
    
    public static String saveToFile(String cDate, String cPackage, String cBundle, String cPeriod, String cIntCalls, String cRef, double cMonthlyCharge, String cName)       
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("contracts.txt"), true));
            pw.print(cDate);
            pw.print("    ");
            pw.print(cPackage);
            pw.print("    ");
            pw.print(cBundle);
            pw.print("    ");
            pw.print(cPeriod);
            pw.print("    ");
            pw.print(cIntCalls);
            pw.print("    ");
            pw.print(cRef);
            pw.print("    ");
            pw.print(cMonthlyCharge);
            pw.print("    ");
            pw.print(cName);                
            pw.println();

            pw.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: closing program");
            System.exit(0);
        }
        return null;
    }
    
    
    
    //gets the current date
    public static String getDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(cal.getTime());
    }
    
    
    //displays a warning to the user
    static void displayWarning()
    {
        System.out.println("********************************************");
        System.out.println("* All information supplied is Confidential *");
        System.out.println("********************************************");
    }
    
   
}