import java.util.Arrays; // package for printing an array
import java.util.HashMap; // package for creating a dictionary, key-value pairs
import java.text.NumberFormat; // package for formatting into currency values

/**
 * Billy's Den Menu: Creates sub menus, make a full menu from sub menus, and
 * creates a dictionary from full menu. Menu contains the food items and prices.
 * 
 * Testing method invocations (line 47, line 48). Testing methods (lines 267-302,
 * lines 335-347)
 *
 * @author Kyle Huang
 * @version 12.9
 * @since 5/30/2021
 */
public class BillysDenMenu {
    // sub menu labels array
    private String[] label = { "Food", "Appetizers", "Dessert", "Snacks", "Drinks" };
    // Predefined food sub menu array
    private Object[][] foodMenu = { { "Cheese Burger", 4.75 }, { "Hot Dog", 3.50 }, { "Pizza", 8.50 },
            { "Sandwich", 4.00 }, { "Steak", 14.50 }, };
    // Predefined appetizer sub menu array
    private Object[][] appetizerMenu = { { "Fried Chicken", 4.25 }, { "Fried Shrimps", 7.75 }, { "Salad", 3.75 },
            { "Toast", 2.00 }, { "Tomato Stew", 2.50 } };
    // Predefined dessert sub menu array
    private Object[][] dessertMenu = { { "Cheesecake", 2.25 }, { "Ice Cream", 2.00 }, { "Chocolate Fondue", 4.75 },
            { "Fruit Cup", 2.75 } };
    // Predefined snack sub menu array
    private Object[][] snackMenu = { { "Fries", 2.25 }, { "Biscuits", 1.75 }, { "Bread", 2.00 }, { "Chips", 1.75 } };
    // Predefined drink sub menu array
    private Object[][] drinkMenu = { { "Soda", 1.50 }, { "Smoothie", 2.00 }, { "Tea", 2.50 }, { "Juice", 1.75 } };
    private Object[][][] menu; // full 3D menu
    private int width = 30; // border line limiter to divide two menus side-by-side
    private int length; // array length place holder
    private HashMap<String, String> menuDict = new HashMap<String, String>(); // menu dictionary HashMap
    private NumberFormat number = NumberFormat.getCurrencyInstance(); // currency formatter object

    /**
     * Constructor: auto clear dictionary, executes all sub menus,
     * combines menu as one, and creates a dictionary menu when
     * this class gets createed into an object.
     */
    BillysDenMenu() {
        this.tare();
        this.combineMenus();
        this.setMenuDict(menuDict, menu);
        // test3DObject(menu);
        // testDictValues(menuDict);
    }

    /**
     * Deconstructor: clears the menu dictionary
     */
    public void tare() {
        menu = null;
        menuDict.clear();
        length = 0;
    }
    
    /**
     * This is the printed Menu
     */
    public void getMenu() {
        this.setFoodAppetizerMenus();
        this.setDessertSnackMenus();
        this.setDrinkMenu();
    }

    /**
     * This uses the 3D array to create a dictionary using a hash table. Creates
     * dictionary of the full menu.
     * 
     * @param obj 3D array
     */
    public void setMenuDict(HashMap<String, String> dict, Object[][][] obj) {
        // try and catch array out of bounds error
        try {
            // access the five sub menus
            for (int i = 0; i < obj.length; i++) {
                // access the food-price value pairs
                for (int j = 0; j < obj[i].length; j++) {
                    // access food and price
                    for (int k = 0; k < obj[i][j].length; k++) {
                        // put the food item as key and put the price as value in dictionary
                        dict.put(obj[i][j][0].toString(), String.valueOf(obj[i][j][1]));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Array Out of Bound Error: setMenuDict()");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: setMenuDict()");
        }
    }

    /**
     * getter: copy of the original menu dictionary
     * 
     * @return temp used for creating a copy of the menu dictionary
     */
    public HashMap<String, String> getMenuDict() {
        HashMap<String, String> temp = new HashMap<String, String>();
        // try and catch array out of bounds error
        try {
            temp = new HashMap<String, String>(); // create HashMap/dictionary
            // access the five sub menus
            for (int i = 0; i < menu.length; i++) {
                // access the food-price pairs
                for (int j = 0; j < menu[i].length; j++) {
                    // access food and price
                    for (int k = 0; k < menu[i][j].length; k++) {
                        // copy food item as key and price as value inside of temp dictionary
                        temp.put(menu[i][j][0].toString(), String.valueOf(menu[i][j][1]));
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Array Out of Bound Error: getMenuDict()");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: getMenuDict()");
        }
        return temp;
    }

    /**
     * combines 2D arrays of the sub menus into a one big 3D array menu. Fill with
     * each of the predefined sub menus.
     * 
     * @param obj 3D array that we're going to assign
     */
    public void combineMenus() {
        menu = new Object[5][][];
        // try and catch array out of bounds error
        try {
            // each outer layer gets filled with the predefined sub menus
            for (int i = 0; i < menu.length; i++) {
                if (i == 0) {
                    // first is the food menu that gets imported
                    menu[0] = foodMenu;
                } else if (i == 1) {
                    // second is the appetizer menu that gets imported
                    menu[1] = appetizerMenu;
                } else if (i == 2) {
                    // third is the dessert menu that gets imported
                    menu[2] = dessertMenu;
                } else if (i == 3) {
                    // fourth is the snack menu that gets imported
                    menu[3] = snackMenu;
                } else if (i == 4) {
                    // lastly the fifth is the drink menu that gets imported
                    menu[4] = drinkMenu;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Array Out of Bound Error: combineMenus()");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: combineMenus()");
        }
    }

    /**
     * Creates the first row of the menu board, starting with food main dishes and
     * appetizers
     */
    public void setFoodAppetizerMenus() {
        length = foodMenu.length; // length of food menu array
        System.out.printf("%36.22s%n", "Welcome to Billy's Den");
        System.out.println("=======================Menu=======================");
        if (foodMenu.length == appetizerMenu.length) {
            // prints the first two labels for the menu, "Food" and "Appetizers"
            System.out.printf("%10.4s%35.10s%n", label[0], label[1]);
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < foodMenu[i].length - 1; j++) {
                    // obtains each item name from food menu array
                    String food = foodMenu[i][j] + ": ";
                    System.out.print(food);
                    // obtains each price values from food menu array
                    double price = (double) foodMenu[i][j + 1];
                    // parses "price" and formats it into currency
                    String price2 = String.valueOf(number.format(price));
                    System.out.print(price2);
                    // gets total line length from of food and price subtracted from "width"
                    int spaceRemainders = width - (food.length() + price2.length());
                    // While there are spaces remaining to width value, it would keep on add
                    // spaces until it reaches the limit
                    while (spaceRemainders > 0) {
                        // adds space for remaining spaces until it reaches "width" value limit
                        System.out.print(" ");
                        // subtracts one from space remaining after each space print
                        spaceRemainders--;
                    }
                }
                for (int j = 0; j < appetizerMenu[i].length - 1; j++) {
                    // obtains each item name from appetizer menu array
                    String food = appetizerMenu[i][j] + ": ";
                    System.out.print(food);
                    // obtains each price values from appetizer menu array
                    double price = (double) appetizerMenu[i][j + 1];
                    // parses "price" and formats it into currency
                    String price2 = String.valueOf(number.format(price));
                    System.out.print(price2);
                    System.out.print("\n");
                }
            }
            System.out.print("\n");
        } else {
            System.out.println("Error: Array lengths are not the same! - setFoodAppetizerMenus()");
            System.exit(0);
        }
    }

    /**
     * Creates the second row of the menu board with a dessert menu and snack menu
     */
    public void setDessertSnackMenus() {
        length = dessertMenu.length;
        if (dessertMenu.length == snackMenu.length) {
            // prints the third and fourth labels for the menu, "Dessert" and "Snacks"
            System.out.printf("%13.7s%28.6s%n", label[2], label[3]);
            // for every item being food-pair pairs in food menu array
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < dessertMenu[i].length - 1; j++) {
                    // obtains each item name from dessert menu array
                    String food = dessertMenu[i][j] + ": ";
                    System.out.print(food);
                    // obtains each price values from dessert menu array
                    double price = (double) dessertMenu[i][j + 1];
                    // parses "price" and formats it into currency
                    String price2 = String.valueOf(number.format(price));
                    System.out.print(price2);
                    // gets total line length from of food and price subtracted from "width"
                    int spaceRemainders = width - (food.length() + price2.length());
                    // While there are spaces remaining to width value, it would keep on add
                    // spaces until it reaches the limit
                    while (spaceRemainders > 0) {
                        // adds space for remaining spaces until it reaches "width" value limit
                        System.out.print(" ");
                        // subtracts one from space remaining after each space print
                        spaceRemainders--;
                    }
                }
                // for every item being food-pair pairs in snack menu array
                for (int j = 0; j < snackMenu[i].length - 1; j++) {
                    // obtains item name from snack menu array
                    String food = snackMenu[i][j] + ": ";
                    System.out.print(food);
                    // obtains price values from snack menu array
                    double price = (double) snackMenu[i][j + 1];
                    // parses "price" and formats it into currency
                    String price2 = String.valueOf(number.format(price));
                    System.out.print(price2);
                    // System.out.printf("%.2f", price);
                    System.out.print("\n");
                }
            }
            System.out.print("\n");
        } else {
            System.out.println("Error: Array lengths are not the same! - setDessertMenu()");
            System.exit(0);
        }
    }

    /**
     * Creates the third row of the menu board with the drink menu
     */
    public void setDrinkMenu() {
        try {
            length = drinkMenu.length;
            // prints the last label, "Drinks"
            System.out.printf("%12.6s%n", label[4]);
            // for every item in drink menu array
            for (int i = 0; i < length; i++) {
                // for every sub item being food-price pairs
                for (int j = 0; j < drinkMenu[i].length - 1; j++) {
                    // obtains item name from drink menu array
                    String food = drinkMenu[i][j] + ": ";
                    System.out.print(food);
                    // obtains price values from drink menu array
                    double price = (double) drinkMenu[i][j + 1];
                    // parses "price" and formats it into currency
                    String price2 = String.valueOf(number.format(price));
                    System.out.print(price2);
                }
                System.out.print("\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: setDrinkMenu()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: setDrinkMenu()");
            e.printStackTrace();
        }
        System.out.println("\nAdditional Info:\n-Fried Chicken contains 10 pieces");
        System.out.println("-Fried Shrimps contains 12 pieces");
        System.out.println("-Chocolate Fondue comes with a fruit plate");
        System.out.println("-Pizza is full size\n-Bread comes with 4 pieces");
        System.out.println("=======================Menu=======================\n");
    }

    /**
     * Outputs the 3D array. Check if this 3D array works correctly. Checks menu if
     * it works.
     * 
     * @param arr 3D object array
     */
    public void test3DObject(Object[][][] arr) {
        System.out.println("------------Testing Full Menu------------");
        try {
            System.out.println(Arrays.deepToString(arr));
            // number of sub menus
            for (int i = 0; i < arr.length; i++) {
                // number of food-price pairs in sub menu
                for (int j = 0; j < arr[i].length; j++) {
                    // access to food and price values
                    for (int k = 0; k < arr[i][j].length; k++) {
                        // print food and price
                        System.out.println(arr[i][j][k]);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Out of Bound Error: test3DObject()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: test3DObject()");
            e.printStackTrace();
        }
        System.out.println("------------Testing Full Menu------------\n");
    }

    /**
     * Testing dictionary by getting value from key-value pairs. Here we test the
     * full menu dictionary.
     * 
     * @param dict dictionary of full menu
     */
    public void testDictValues(HashMap<String, String> dict) {
        System.out.println("--------Testing Dict--------");
        for (String name: dict.keySet()) {
            System.out.println(name + " = " + dict.get(name));
        }
        System.out.println("--------Testing Dict--------\n");
    }
}
