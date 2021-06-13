import java.util.Scanner; // package for user input and reading values
import java.util.concurrent.TimeUnit; // package for console time delay
import java.lang.InterruptedException; // package for error handler for TimeUnit
import java.util.ArrayList; // package for a more flexible array class
import java.util.InputMismatchException; // package for handling user input error

/**
 * Billy's Den Customer: Customer tells the cashier what he/she wants to order
 * for "To-Go."
 * 
 * Testing method invocations (line 45, 46). Testing methods (203-228, line
 * 230-255)
 *
 * @author Kyle Huang
 * @version 10.6
 * @since 5/30/2021
 */
public class BillysDenCustomer {
    private Scanner input = new Scanner(System.in); // user input object
    private ArrayList<String> orderedItems; // ordered items goes in here
    private ArrayList<Integer> quantityOfOrderedItems; // quantity goes in here

    /**
     * Constructor: auto reset order.
     */
    BillysDenCustomer() {
        this.tare();
    }

    /**
     * Deconstructor: resets the order; clears data.
     */
    public void tare() {
        orderedItems = null;
        quantityOfOrderedItems = null;
    }

    /**
     * Customer chooses what to order on the menu
     */
    public void chooseOrder() {
        this.orderedItems = new ArrayList<String>();
        this.quantityOfOrderedItems = new ArrayList<Integer>();
        this.inputOrder();
        // testStringDoubleArrayLists(orderedItems, quantityOfOrderedItems);
        // testCopiedArrays();
    }

    /**
     * Customer answers the Cashier
     */
    public void answerCashier() {
        // prints the quantity first and the food item
        for (int i = 0; i < orderedItems.size(); i++) {
            this.wait(750); // wait 0.75 second
            System.out.println(quantityOfOrderedItems.get(i) + " " + orderedItems.get(i));
        }
        System.out.print("\n");
        this.outputOrder(); // prints the food item and the quantity
    }

    /**
     * For customer to say something to the Cashier
     * 
     * @param text message to the customer
     */
    public void say(String text) {
        this.wait(900); // wait 0.9 second
        System.out.println("Customer: " + text);
    }

    /**
     * asks user to write down what item the user wants and how many of each of
     * them.
     */
    public void inputOrder() {
        boolean doAgain = true;

        System.out.println("What are you going to order?");
        // loops for every item requested
        while (doAgain) {
            try {
                System.out.print("Item [type in the food name]: ");
                String foodItem = input.nextLine(); // item input
                orderedItems.add(foodItem); // adds to orderedItems array
                System.out.print("Quantity [type in amount]: ");
                String tempQuanitity = input.nextLine(); // quantity input String
                System.out.print("\n");
                // parses tempQuantity as a positive integer into quantity
                int quantity = Math.abs(Integer.parseInt(tempQuanitity));
                quantityOfOrderedItems.add(quantity); // adds to quantityOfOrderedItems array
                System.out.print("add more? [y/n]: "); // add more?
                String answer = input.nextLine(); // y/n
                // if answer is "n," close while-loop
                if (answer.equalsIgnoreCase("n")) {
                    doAgain = false; // close main while-loop
                    // if answer is not "y," run block below
                } else if (!answer.equalsIgnoreCase("y")) {
                    // while answer is not "y," asks for user input again
                    while (!answer.equalsIgnoreCase("y")) {
                        System.out.print("Invalid answer, type in y/n: ");
                        answer = input.nextLine();
                        // if answer is "n," close while-loop
                        if (answer.equalsIgnoreCase("n")) {
                            doAgain = false; // close main while-loop
                            break; // break this inner while-loop
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("User Input Error: inputOrder()");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: inputOrder() - type in an integer for quantity");
                e.printStackTrace();
            }
        }
        System.out.print("\n");
    }

    /**
     * Prints out the items the user picked and the quantity of them.
     */
    public void outputOrder() {
        System.out.println("Your Order: ");
        // try and catch error handlings of arrays
        try {
            // print the item from orderedItems array and quantity of each
            // of its values from quantityOfOrderedItems array
            for (int i = 0; i < orderedItems.size(); i++) {
                System.out.println(orderedItems.get(i) + " (" + quantityOfOrderedItems.get(i) + ")");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: outputOrder()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: outputOrder()");
            e.printStackTrace();
        }
    }

    /**
     * Creates copy of itemsOrdered array
     * 
     * @return copy of an array
     */
    public String[] getItemsOrdered() {
        String[] copyOfOrderedItems = new String[orderedItems.size()];
        // try and catch error handlings of arrays
        try {
            // copy each values from orderedItems array values into
            // copyOfOrderedItems array
            for (int i = 0; i < copyOfOrderedItems.length; i++) {
                copyOfOrderedItems[i] = orderedItems.get(i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: getItemsOrdered()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: getItemsOrdered()");
            e.printStackTrace();
        }
        return copyOfOrderedItems;
    }

    /**
     * Creates copy of quantityOfOrderedItems array
     * 
     * @return copy of an array
     */
    public int[] getQuantityOfItemsOrdered() {
        int[] copyOfQuantityOfOrderedItems = new int[quantityOfOrderedItems.size()];
        // try and catch error handlings of arrays
        try {
            // copy each items from quantityOfOrderedItems array into
            // copyOfQuantityOfOrderedItems array
            for (int i = 0; i < copyOfQuantityOfOrderedItems.length; i++) {
                copyOfQuantityOfOrderedItems[i] = quantityOfOrderedItems.get(i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: getQuantityOfItemsOrdered()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: getQuantityOfItemsOrdered()");
            e.printStackTrace();
        }
        return copyOfQuantityOfOrderedItems;
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
            System.out.println("Error of wait method");
            e.printStackTrace();
        }
    }

    /**
     * Prints out two arrays, one being a String and the second an Integer array
     * 
     * @param arr1 String array
     * @param arr2 double array
     */
    public void testStringDoubleArrayLists(ArrayList<String> arr1, ArrayList<Integer> arr2) {
        System.out.println("\n-------Testing ArrayLists-------");
        System.out.println("List of what you've ordered: ");
        // try and catch error handlings of arrays
        try {
            // print out the food item from arr1 and the quantity of each
            // from arr2
            for (int i = 0; i < arr1.size(); i++) {
                System.out.println(arr1.get(i) + " : " + arr2.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: testStringDoubleArrayLists()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: testStringDoubleArrayLists()");
            e.printStackTrace();
        }
        System.out.println("-------Testing ArrayLists-------");
        System.out.print("\n");
    }

    /**
     * Verifies each of the copy arrays are functional by printing out the items
     * ordered and the quantity of each of them.
     */
    public void testCopiedArrays() {
        System.out.println("------Testing Copied Arrays------");
        String[] tempOrderedItems = getItemsOrdered();
        int[] tempQuantityOfOrderedItems = getQuantityOfItemsOrdered();
        // try and catch error handlings of arrays
        try {
            // print out each food from tempOrderedItems array and the
            // quantity from tempQuantityOfOrderedItems array
            for (int i = 0; i < tempOrderedItems.length; i++) {
                System.out.print(tempOrderedItems[i] + " : ");
                System.out.println(tempQuantityOfOrderedItems[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: testCopiedArrays()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: testCopiedArrays()");
            e.printStackTrace();
        }
        System.out.println("------Testing Copied Arrays------\n");
    }
}
