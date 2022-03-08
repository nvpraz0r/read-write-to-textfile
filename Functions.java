import java.util.*;

/**
 * The Functions class stores methods
 * the methods are used by the Main class
 */
public class Functions {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Method prompts user to enter data then stores the data in String description
     * @return String description to Main class
     */
    public static String getString(){
        System.out.println("Enter description: ");
        String description = scanner.nextLine(); // nextLine used to take whole line for item description
        return description;
    }
    
    /**
     * Method starts a while loop
     * prompts user to enter data
     * stores the data in double price
     * then sets the while loop to false if input is a double
     * @return double price to Main class
     */
    public static double getDouble(){
        double price = 0.00;
        boolean running = true;
        while(running){
            System.out.println("Enter price: ");
            if(scanner.hasNextDouble()){
                price = scanner.nextDouble();
                if(price > 0 && price < 1000000){
                    running = false;
                } else{
                    running = true;
                }
            } else {
                System.out.println("Input has to be a double, try again . . ");
            }
            scanner.nextLine();    //discard remaining data
        }
        return price;
    }
    
    /**
     * Method first appends code to stringbuilder object "sb"
     * second portion determines which word in the string is the first word
     * then appends the first word to the object sb
     * third a random number is appended to the object sb
     * fourth price is converted to a String then the decimals are replaced
     * String price "s" is appended to the object sb
     * lastly the object sb is converted to a String then returned to the Main class
     * @param String code, String description, double price
     * @return String sb to Main class
     */
    public static String createBarcode(String code, String description, double price){
        
        //create stringbuilder object sb
        StringBuilder sb = new StringBuilder();
        //create random object
        Random random = new Random();
        
        // start barcode according to instructions
        sb.append(code);    //send item code to object
        
        
        description = description.trim();   //discard unnecessary spaces
        
        // "algorithm" determines how many words are in description input
        int index1 = description.indexOf(" ");  //baseline to measure rest of the object
        if(index1 == -1){
            sb.append(description); //send description to object
        } else {
            int index2 = description.indexOf(" ", index1 + 1);
            if(index2 == -1){
                description = description.substring(0,index1);
                sb.append(description); //send description to object
            } else{
                int index3 = description.indexOf(" ", index2 + 1);
                if(index3 == -1){
                    description = description.substring(0,index1);
                    sb.append(description); //send description to object
                    
                } else {
                    System.out.println("index3 error");
                }
            }
        }
        
        //create number variable
        int num = random.nextInt(1000);
        sb.append(num); //send random number to object
        
        //remove demcimal point
        String s = Double.toString(price);
        s = s.replace(".","");  //replace decimal point
        sb.append(s);   //send price without decimal points to object
        
        return sb.toString();
    }
}