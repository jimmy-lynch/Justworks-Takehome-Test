package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        //Setting up initial scanners, collecting information to read the csv file
        Scanner in = new Scanner(System.in);
        Boolean worked = false;
        String delimiter = ",";
        String dataline;
        System.out.println("Hello! Please input your FILENAME.csv of the data you would like to process. The file should be placed in the resources directory.");

        //read the csv file, add all transactions.
        while (!worked) { // loop in case of error, to re-attempt the file input
            String filename = "resources/" + in.nextLine();
            try { //basic exception handling
                Scanner data = new Scanner(new File(filename), "utf-8");
                worked = true;
                while (data.hasNext()) {
                    dataline = data.next();
                    String[] input = dataline.split(delimiter); //0 is cID, 1 is date, 2 is amount.
                    if (input.length == 0) {
                        continue; //handling an empty line.
                    }
                    Customer temp = Customer.getCustomer(input[0]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
                    LocalDate date = LocalDate.parse(input[1], formatter);

                    temp.addTransaction(date, Integer.parseInt(input[2]));
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("Error with filename, try again.");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //Iterate through customers, calculate balances for each month
        Set<String> keys = Customer.getDirectory().keySet();
        HashMap<String,Customer> directory = Customer.getDirectory();
        List<String[]> output = new ArrayList<String[]>();
        for (String k : keys) {
            Customer currCustomer = directory.get(k);
            currCustomer.processTransactions(); //process the transactions

            for (MonthlyBalances m : currCustomer.getBalancesByMonth()) {
                String[] outputLine = new String[5];
                outputLine[0] = k; //customerID
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");
                outputLine[1] = m.getYearMonth().format(format).toString(); //date in MM/YYYY
                outputLine[2] = String.valueOf(m.getMinBalance()); //minimum balance
                outputLine[3] = String.valueOf(m.getMaxBalance()); //maximum balance
                outputLine[4] = String.valueOf(m.getEndingBalance()); //ending balance

                output.add(outputLine);
            }
        }

        //write to output.csv
        try {
            File csvFile = new File("output.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            for (String[] line : output) {
                String s = line[0] + "," + line[1] + "," + line[2] + "," + line[3] + "," + line[4] + "\n";
                fileWriter.write(s);
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Transaction processing is done, see the new output.csv file for the results!");
    }
}
