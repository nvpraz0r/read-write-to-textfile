import java.io.File;
import java.util.*;

/**
 * Main class that class methods from other classes
 */
public class Main {
    private static DAO<Product> productFile = new ProductTextFile();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * main method
     */
    public static void main(String[] args) {
        //
        String menuInput = "";
        String menu = """
                      \n
                      ========
                      Menu
                      --------
                      List\t -List all products
                      Add\t -Add a products
                      Change\t -Change product information
                      Delete\t -Delete a product
                      Menu\t -Show the menu
                      Exit\t -Exit the application
                      ========
                      \n""";
        
        //call checkFiles method
        checkFiles();
        
        //print menu
        System.out.println(menu);

        //do-while loop to check user intent
        do{
            System.out.println("\nEnter a command: ");
            menuInput = scanner.next();
            scanner.nextLine(); //discard rest of the line; only need one word for menu navigation

            //check user input
            if(menuInput.equalsIgnoreCase("list")){
                displayProducts();
            } else if(menuInput.equalsIgnoreCase("add")){
                addProduct();
            }else if(menuInput.equalsIgnoreCase("change")){
                changeProduct();
            }else if(menuInput.equalsIgnoreCase("delete")){
                deleteProduct();
            } else if (menuInput.equalsIgnoreCase("menu")){
                System.out.println(menu);
            } else if (!menuInput.equalsIgnoreCase("exit")) {
                System.out.println("Invalid command. Try again.");
            }
        }while(!menuInput.equalsIgnoreCase("exit"));
        System.out.println("Exiting . .");  //exit statement
    }
    
    /**
     * Method is called by main method
     * to check if products.txt file exists
     * @exception throw exception when an error occurs in I/O processing
     */
    public static void checkFiles(){
        File myFile = null;
        boolean doesExist = false;
        
        System.out.println("Checking files . .");
        
        try{
            myFile = new File("products.txt");
            doesExist = myFile.exists();
            if(doesExist == true){
                System.out.println("File exists!");
            } else if (doesExist == false){
                System.out.println("File does not exist . .");
                System.out.println("Creating file . .");
                myFile.createNewFile();
                System.out.println("File created!");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * Method is called by menu - main method
     * to display all products inside the products.txt file
     */
    public static void displayProducts(){
        //add code to display all products
        System.out.println("PRODUCT LIST");

        //create arraylist products
        List<Product> products = productFile.getAll();
        if (products == null) {
            System.out.println("No products were found.\n");
        } else {
            //create a stringbuilder object
            StringBuilder sb = new StringBuilder();
            //loop through products in object Product
            for (Product p : products) {
                sb.append(StringUtils.padWithSpaces(p.getCode(), 25));          //append code
                sb.append(StringUtils.padWithSpaces(p.getDescription(), 25));   //append description
                sb.append(StringUtils.padWithSpaces(p.getBarcode(), 40));       //append barcode
                sb.append(p.getPriceFormatted());                               //append formatted price
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }
    
    /**
     * Method prompts and collects all necessary data to
     * send information to ProductTextFile
     */
    public static void addProduct(){        
        //prompt user to enter product code
        System.out.println("\nEnter product code: ");
        //set code to nextLine
        String code = scanner.nextLine();

        //set description
        String description = Functions.getString();
        
        //set price
        double price = Functions.getDouble();

        //create barcode
        String barcode = Functions.createBarcode(code,description,price);
        
        //create object
        Product product = new Product();
        //store data in object product
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setBarcode(barcode);
        //commit object to ProductTextFile
        productFile.add(product);
        //confirm object was sent successfully
        System.out.println("Product " + code + ", '" + description + "' has been added.");
    }

    /**
     * Method changeProduct prompts user input
     * then searches for input
     * removes all data associated with input
     * then resubmits data as a new object
     */
    public static void changeProduct(){
        System.out.println("\nEnter product code to change: ");
        String code = scanner.next();
        scanner.nextLine();
        
        Product p = productFile.get(code);
        if(p != null){
            //delete previous
            productFile.delete(p);
            //set description
            String description = Functions.getString();
            //set price
            double price = Functions.getDouble();
            //create barcode
            String barcode = Functions.createBarcode(code, description, price);
            //createt object
            Product product = new Product();
            //store data in object product
            product.setCode(code);
            product.setDescription(description);
            product.setPrice(price);
            product.setBarcode(barcode);
            
            //create boolean object
            boolean success = productFile.add(product);
            //if returned bool is true: success
            if (success){
                System.out.println(p.getCode() + " was sucessfully updated.");
            } else {
                System.out.println("Something went wrong . .");
            }
        } else {
            System.out.println("No product matches that code. \n");
        }
    }


    /**
     * Method deleteProduct prompts user input
     * then searches for input
     * finally removes all data associated
     */
    public static void deleteProduct(){
        System.out.println("\nEnter product code to delete: ");
        String code = scanner.next();
        scanner.nextLine(); //discard unnecessary input

        //create boolean object
        Product p = productFile.get(code);
        //if returned bool is true: success
        if (p != null) {
            boolean success = productFile.delete(p);
            if (success) {
                System.out.println(p.getCode() + " was deleted.\n");                
            } else {
                System.out.println(p.getCode() + " had not properly deleted.\n");
            }            
        } else {
            System.out.println("No product matches that code.\n");
        }
    }
}