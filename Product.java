import java.text.NumberFormat;

/**
 * The Product class represents a product and is used by the ProductTextFile class
 */
public class Product{
	private String code;
	private String description;
	private double price;
        private String barcode;

        /**
         * Sets the Product with default values
         */
	public Product(){
            this("","",0,"");
	}
        
        /**
         * Sets the Product object
         * @param String code, String description, double price, String barcode
         */
	public Product(String code, String description, double price, String barcode){
            this.code = code;
            this.description = description;
            this.price = price;
            this.barcode = barcode;
	}
        
        /**
         * Sets the String that represents the barcode
         */
        public void setBarcode(String barcode){
            this.barcode = barcode;
        }
        
        /**
         * Returns a String that represents the product barcode
         * @return String barcode for the product
         */        
        public String getBarcode(){
            return barcode;
        }
        
        /**
         * Sets the product name that represents the product name
         */
	public void setCode(String code){
            this.code = code;
	}
        
        /**
         * Returns a String that represents the product name
         * @return String code for the product
         */
	public String getCode(){
            return code;
	}

        /**
         * Sets the product description that represents the product description
         */
	public void setDescription(String description){
            this.description = description;
	}

        /**
         * Returns a String that represents the product description
         * @return String description for the product
         */        
	public String getDescription(){
            return description;
	}
        
        /**
         * Sets the product price to the specified double
         */
	public void setPrice(double price){
            this.price = price;
	}
        
        /**
         * Returns a double that represents the product price
         * @return double price for the product
         */
	public double getPrice(){
            return price;
	}
        
        /**
         * Returns a String that represents the price converted to currency format
         * @return price in String currency object
         */        
        public String getPriceFormatted() {
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            return currency.format(price);
    }

}