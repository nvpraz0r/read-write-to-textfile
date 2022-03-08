import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * ProductTextFile class stores methods and overrides DAO declared methods
 */
public final class ProductTextFile implements DAO<Product> {
    private List<Product> products = null;
    private Path productsPath = null;
    private File productsFile = null;
    private final String FIELD_SEP = "\t";

    /**
     * Creates ProductTextFile object with default values
     */
    public ProductTextFile() {
        productsPath = Paths.get("products.txt");
        productsFile = productsPath.toFile();
        products = this.getAll();
    }

    /**
     * getAll method retrieves data from products.txt file
     * stores the data in an arraylist 
     * overrides DAO interface
     * @exception throw exception when an error occurs in I/O processing
     * @return arraylist products
     */
    @Override
    public List<Product> getAll() {
        // if the products file has already been read, don't read it again
        if (products != null) {
            return products;
        }

        products = new ArrayList<>();
        if (Files.exists(productsPath)) {
            try (BufferedReader in = new BufferedReader(
                                     new FileReader(productsFile))) {

                // read products from file into array list
                String line = in.readLine();
                while (line != null) {
                    String[] fields = line.split(FIELD_SEP);
                    String code = fields[0];        //set code to array array position 0
                    String description = fields[1]; //set description to array position 1
                    String barcode = fields[2];     //set barcode to array position 2
                    String price = fields[3];       //set price to array position 3

                    //declare object
                    Product p = new Product(
                            code, description, Double.parseDouble(price),barcode);
                    //add object to products arraylist
                    products.add(p);
                    line = in.readLine();
                }
            } catch (IOException e) {
                 System.out.println(e);
                return null;
            }
        } else {
             System.out.println(productsPath.toAbsolutePath() + " doesn't exist.");
            return null;            
        }
        return products;
    }

    /**
     * @param String code
     * @return all data stored in products object
     * @return return null if there is no data in object
     */
    @Override
    public Product get(String code) {
        for (Product p : products) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    /**
     * saveAll method formats information in products object
     * @return boolean operator to indicate if the process was a success
     * @exception throws an exception when an error occurs in I/O processing
     */
    private boolean saveAll() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(productsFile)))) {
            
            //write data in array list to the file
            for (Product p : products) {
                out.print(p.getCode() + FIELD_SEP);
                out.print(p.getDescription() + FIELD_SEP);
                out.print(p.getBarcode() + FIELD_SEP);
                out.println(p.getPrice());
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Method add overrides DAO interface
     * adds indicated object to products arraylist
     * @param object p
     * @return boolean true
     */
    @Override
    public boolean add(Product p) {
        products.add(p);
        return this.saveAll();
    }

    /**
     * Method delete overrides DAO interface
     * removes indicated object from products arraylist
     * @param object p
     * @return boolean true
     */
    @Override
    public boolean delete(Product p) {
        products.remove(p);
        return this.saveAll();
    }

    /**
     * 
     * @return boolean true
     */
    @Override
    public boolean update(Product newProduct) {
        // get the old product and remove it
        Product oldProduct = this.get(newProduct.getCode());
        int i = products.indexOf(oldProduct);
        products.remove(i);

        // add the updated product
        products.add(i, newProduct);

        return this.saveAll();
    }
}