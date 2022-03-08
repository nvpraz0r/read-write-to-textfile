/**
 * StringUtils is called by Main class
 * used for formatting data display
 */
public class StringUtils {

    /**
     * parameters are from Main class
     * which are entered into a string builder object
     * String s being the information
     * int length being the spaces between information
     * @return sb object to Main class
     * @param String s int length
    */
    public static String padWithSpaces(String s, int length) {
        if (s.length() < length) {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}
