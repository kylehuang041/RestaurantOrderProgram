import java.util.HashMap; // package for creating dictionary key-value pairs
import java.util.Scanner; // package for reading and getting user input
import java.util.InputMismatchException; // package for user input error
import java.util.concurrent.TimeUnit; // package for delaying console
import java.lang.InterruptedException; // package for handling console timing error
import java.io.PrintWriter; // package for creating and writing file
import java.io.IOException; // package for file error: no file and more
import java.io.FileOutputStream; // package for adding a constructor for file name
import java.time.LocalDate; // package for local date time
import java.text.DecimalFormat; // package for formatting decimals and percentages
import java.text.NumberFormat; // package for formatting numbers into currency

/**
 * Billy's Den Cashier: Cashier asks what the customer wants to order and then
 * calculates the total price.
 * 
 * Testing method invocations (line 81, line 84). Testing methods (lines 284-303,
 * 305-328)
 *
 * @author Kyle Huang
 * @version 20.2
 * @since 5/30/2021
 */
public class BillysDenCashier {
    // set copy of menu dictionary
    private HashMap<String, String> setMenuDict = new HashMap<String, String>();
    private Scanner input = new Scanner(System.in); // create scanner for user input
    private double[] cost; // array for holding prices
    private double totalPrice; // total price of items
    private double tax = 0.088; // tax rate
    private DecimalFormat decimal = new DecimalFormat("#0.0#%");
    private NumberFormat number = NumberFormat.getCurrencyInstance();
    String[] setItemsOrdered; // set copy of items array
    int[] setQuantityOfOrderedItems; // set copy of quantity array

    /**
     * Constructor: auto reset
     */
    BillysDenCashier () {
        this.tare();
    }

    /**
     * Deconstructor: clears order and total price
     */
    public void tare () {
        setMenuDict.clear();
        cost = null;
        totalPrice = 0;
        setItemsOrdered = null;
        setQuantityOfOrderedItems = null;
    }

    /**
     * Cashier asks Customer about what kind of food he/she wants
     */
    public void giveMenu() {
        BillysDenMenu menu = new BillysDenMenu(); // Billy's Den Menu Object
        // get a copy of the menu dictionary
        this.setMenuDict = menu.getMenuDict();
        this.wait(1300); // wait a 1.3 second
        menu.getMenu(); // get copy of the menu
    }

    /**
     * copy of the total price, formatted into currency
     * 
     * @return copy of total price formatted into currency String
     */
    public String getTotalPrice() {
        this.wait(200); // wait 0.2 second
        String temp = number.format(totalPrice);
        return temp;
    }

    /**
     * Where the accounting takes place like calculating the cost of the food items
     * of what the customer wants.
     */
    public void doAccounting() {
        // testStringDoubleArrayValues(setItemsOrdered, setQuantityOfOrderedItems);
        this.setCost();
        this.getCost();
        // testDoubleArray(cost);
        this.totalPrice = calcTotalPrice(cost, tax);
    }

    /**
     * Cashier asks the customer if he/she wants a receipt.
     */
    public void giveReceipt() {
        this.wait(1050); // wait 1.05 second
        // System.out.printf("%s%.2f%n", "Customer: Here is ", totalPrice);
        this.askForReceipt();
        this.wait(1000); // wait a second
        this.downloadReceipt();
    }

    /**
     * Cashier saying something to the Customer
     * 
     * @param text message to the Customer
     */
    public void say(String text) {
        this.wait(950); // wait .95 second
        System.out.print("Cashier: " + text);
    }

    /**
     * creates a PrintWriter obj to create and write onto a file The file will
     * contain a copy of the receipt
     */
    public void downloadReceipt() {
        PrintWriter writer = null;
        String answer = "";

        System.out.print("Do you want to download the receipt? [y/n]: ");
        // try and catch user input errors
        try {
            answer = input.next();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        /*
         * If the answer is "y" for "yes" then proceed to creating the PrintWriter obj
         * to create a file named "receipt.txt" and will write down what you've ordered
         * and how many of each item. Then will calculate the price and would give you
         * the total price including tax
         */
        if (answer.equalsIgnoreCase("y")) {
            // create PrintWriter to create and write file
            try {
                writer = new PrintWriter(new FileOutputStream("receipt.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            writer.println("---------Receipt---------");
            writer.println("Billy's Den");
            LocalDate time = LocalDate.now(); // create Local Date obj for date record
            writer.println("Date: " + time + "\n"); // prints date
            writer.println("Your Order: ");

            // prints out the dish ordered, the quantity, and price of that item
            for (int i = 0; i < setItemsOrdered.length; i++) {
                writer.print(setItemsOrdered[i] + " (" + setQuantityOfOrderedItems[i] + ")");
                writer.println(" = " + number.format(cost[i]));
            }

            writer.print("\n");
            writer.println("Tax = " + decimal.format(tax)); // tax info
            writer.printf("%-8.7s", "Total =");
            writer.println(number.format(totalPrice) + "\n"); // print total price
            writer.println("Thanks come again!");
            writer.print("---------Receipt---------");
            writer.close(); // close writer object
            System.out.println("Receipt has been downloaded into this file's directory.\n");
        }
    }

    /**
     * console prints out the receipt with what you've ordered and prints out the
     * total price including tax
     */
    public void printReceipt() {
        System.out.println("\n---------Receipt---------");
        System.out.println("Billy's Den");
        LocalDate time = LocalDate.now(); // create Local Date obj for date record
        System.out.println("Date: " + time + "\n"); // prints date

        try {
            // prints out the dish ordered, the quantity, and price of that item
            for (int i = 0; i < setItemsOrdered.length; i++) {
                System.out.print(setItemsOrdered[i] + " (" + setQuantityOfOrderedItems[i] + ")");
                System.out.println(" = " + number.format(cost[i]));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: printReceipt()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: printReceipt()");
            e.printStackTrace();
        }

        System.out.print("\n");
        System.out.println("Tax = " + decimal.format(tax)); // tax
        System.out.printf("%-8.7s", "Total =");
        System.out.println(number.format(totalPrice) + "\n"); // print total price
        System.out.println("Thanks come again!");
        System.out.println("---------Receipt---------");
    }

    /**
     * Asks user if he wants a receipt
     */
    public void askForReceipt() {
        String answer = "";

        // try and catch user input error
        try {
            answer = input.next(); // input user, y/n
        } catch (InputMismatchException e) {
            System.out.println("User Input Error: askForReceipt()");
        }

        if (answer.equalsIgnoreCase("y")) {
            this.printReceipt();
            System.out.print("\n");
        }
    }

    /**
     * Calculates total price including tax.
     * 
     * @param arr holds the prices of what items you've ordered
     * @param taxRate Seattle's restaurant sales tax
     * @return total total price including tax
     */
    public double calcTotalPrice(double[] arr, double taxRate) {
        double total = 0;
        for (double price : arr) {
            total += price;
        }
        total *= taxRate + 1;
        return total;
    }

    /**
     * Uses menu dictionary to get the values (prices) of the items that the user
     * picked and multiply the quantity of each items.
     */
    public void getCost() {
        cost = new double[setItemsOrdered.length];
        try {
            // inserts the price of the specific food item times the quantity into cost
            // array
            for (int i = 0; i < setItemsOrdered.length; i++) {
                cost[i] = Double.parseDouble(setMenuDict.get(setItemsOrdered[i])) * setQuantityOfOrderedItems[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: getCost()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: getCost() - type in correct food name");
            e.printStackTrace();
        }
        System.out.print("\n");
    }

    /**
     * create and fill array with default values for storing the items' prices.
     */
    public void setCost() {
        cost = new double[setItemsOrdered.length];
        try {
            // fills the array with place holders
            for (int i = 0; i < cost.length; i++) {
                cost[i] = 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: setCost()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: setCost()");
            e.printStackTrace();
        }
    }

    /**
     * terminal/console delay method
     * 
     * @param milliseconds number of milliseconds
     */
    public void wait(int milliseconds) {
        // try and catch console time error
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Error in wait method");
            e.printStackTrace();
        }
    }

    /**
     * Prints out each item of the a double array
     * 
     * @param arr double array
     */
    public void testDoubleArray(double[] arr) {
        System.out.println("-----Testing Double Array-----");
        try {
            for (double price : arr) {
                System.out.println(price);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: createPriceChargesArray()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: createPriceChargesArray()");
            e.printStackTrace();
        }
        System.out.println("-----Testing Double Array-----\n");
    }

    /**
     * Prints out String array and int array. Here we're are printing out the items
     * ordered and the quantity of each.
     * 
     * @param arr1                       String array
     * @param setQuantityOfOrderedItems2
     */
    public void testStringDoubleArrayValues(String[] arr1, int[] arr2) {
        System.out.println("------Testing Arrays------");
        // try and catch array out of bounds error
        try {
            // prints the string-double pairs
            for (int i = 0; i < arr1.length; i++) {
                System.out.println(arr1[i] + " : " + arr2[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: testStringDoubleArrayValues()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: testStringDoubleArrayValues()");
            e.printStackTrace();
        }
        System.out.println("------Testing Arrays------");
    }
}
