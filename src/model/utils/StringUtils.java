package model.utils;
import java.util.StringTokenizer;
public class StringUtils {
    
    /** 
     * Self-descriptive
     * @param str "RYANAIR_FLIGHT"
     * @return String "Ryanair Flight"
     */
    public static String capitalizeFirstLetter(String str) {
        str = str.replaceAll("_", " ");
        str = str.toLowerCase();
        StringTokenizer tokenizer = new StringTokenizer(str);
        StringBuilder result = new StringBuilder();

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            result.append(Character.toUpperCase(word.charAt(0)))
                .append(word.substring(1))
                .append(" ");
        }

        return result.toString().trim();
    }
    
    
    /** 
     * Formats a string to match the format present in the Enum files.
     * @param str "boeing 737"
     * @return String "BOEING_737"
     */
    public static String enumFormat(String str) {
        str = str.replaceAll(" ", "_");
        str = str.toUpperCase();
        return str;
    }
}