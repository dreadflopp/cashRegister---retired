/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: Exception with files
 */
package cashregister;

/**
 *
 * @author matti
 */
public class FileNotLoadedException extends Exception {
    public FileNotLoadedException() {
        super("File not loaded");
    }
    
    public FileNotLoadedException(String message) {
        super(message);
    }
    
}
