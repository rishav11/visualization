package libs;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Node {
    public static ArrayList<String> names = new ArrayList<>() ;
    public static HashMap<String, String> outputs = new HashMap<>();
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;
    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }

    abstract public void parse();
    abstract public void nameCheck();
    abstract public void typeCheck();
    abstract public String evaluate();


}
