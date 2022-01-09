/**
 * RESTAURANT ORDER PROGRAM
 * 
 * Billy's Den Restaurant: You're the customer of this restaurant and you would
 * like to order some food for "To-Go."
 * 
 * Note: when picking the food, the food names are case-sensitive, so type them
 * out accurately.
 * 
 * Main Driver class
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
        cashier.say("Thank you, come again");
    }
}