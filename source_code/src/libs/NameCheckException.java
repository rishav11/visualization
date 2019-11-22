package libs;

/**
 * Created by Rishav on 2019-10-13.
 */
public class NameCheckException extends RuntimeException {

    public NameCheckException(String s) {
        System.out.println("Name Check Fail! " + s + " is not a predefined expression");
    }
}
