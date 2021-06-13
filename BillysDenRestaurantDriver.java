import java.util.Scanner; // package reading values and user input

/**
 * RESTAURANT ORDER PROGRAM
 * 
 * Billy's Den Restaurant: You're the customer of this restaurant and you would
 * like to order some food for "To-Go."
 * 
 * Main class
 *
 * @author Kyle Huang
 * @version 2.8
 * @since 5/30/2021
 */
public class BillysDenRestaurantDriver {
    public static void main(String[] args) {
        BillysDenCashier cashier = new BillysDenCashier(); // create cashier object
        BillysDenCustomer customer = new BillysDenCustomer(); // create customer object
        cashier.say("Hi, welcome. Are you here to eat here or to order?\n");
        customer.say("Hi, I'm here to order food.");
        cashier.say("Okay here is our menu, tell me once you're ready, thanks.\n\n");

        // Restart Order
        boolean restart = true;
        while (restart) {
            // Customer ordering food at the restaurant for "To-Go"
            cashier.giveMenu(); // give menu to customer
            customer.chooseOrder(); // choose your food items
            customer.say("Ready.");
            customer.say("I would like ...");
            customer.answerCashier(); // tell what you've ordered
            // import food name
            cashier.setItemsOrdered = customer.getItemsOrdered();
            // import food price
            cashier.setQuantityOfOrderedItems = customer.getQuantityOfItemsOrdered();
            cashier.doAccounting(); // price calculations
            // Total price for order
            cashier.say("That would be " + cashier.getTotalPrice() + "\n");
            customer.say("Here is " + cashier.getTotalPrice());
            cashier.say("Would you like your receipt? [y/n]: ");
            cashier.giveReceipt(); // receipt and download of it

            // Restart
            System.out.print("Create new order? [y/n]: ");
            cashier.tare(); // or cashier = new BillysDenCashier(): to clear data 
            customer.tare(); // or customer = new BillysDenCustomer(): to clear data 
            Scanner input = new Scanner(System.in); // create user input object
            String response = input.nextLine(); // order again or not: user input
            // If response is "y," create new order or loop the main while-loop again
            if (response.equalsIgnoreCase("y")) {
                customer.say("I would like to order again");
                cashier.say("Okay\n\n");
                // If response is "n,"
            } else if (response.equalsIgnoreCase("n")) {
                cashier.say("Thanks, come again :)");
                restart = false; // close main while-loop
                // if response is not "y," run block below
            } else if (!response.equalsIgnoreCase("y")) {
                // while response is not "y," ask for user input again
                while (!response.equalsIgnoreCase("y")) {
                    System.out.print("Invalid response, type in y/n: ");
                    response = input.nextLine();
                    // if response is "n," close while-loop
                    if (response.equalsIgnoreCase("n")) {
                        cashier.say("Thanks, come again :)");
                        restart = false; // close main while-loop
                        break; // break this inner while-loop
                    }
                }
            }
            input.close(); // close Scanner input object
        }
    }
}
