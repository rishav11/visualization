package logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class Logger {

    static protected PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("test.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void closeWriter(){
        writer.close();
    }

    public static void log(String text) {
        writer.print(text);
    }

    /**
     * Access fields of Object using Reflection.
     * @param obj
     */
    public static void AccessFieldsUsingReflection(Object obj){
        /* Get array of fields declared in Class */
        Field[] fields = obj.getClass().getDeclaredFields();
        /* Loop through all fields */
        for(int i = 0; i < fields.length ; i++){
            /* Get field name */
            String fieldName = fields[i].getName();
            /* Get generic/base type of field (int, short, long, String, etc...) */
            Object fieldType = fields[i].getGenericType();

            log("Class name: " + obj + "\n");
            log("Field(variable) name: " + fieldName + "\n");
            log("Generic Type: " + fieldType + "\n");

            /* Set<E>, List<E> and Map<K,V> are ParameterizedType */
            if(fieldType instanceof ParameterizedType){
                /**
                 * This will give you Actual Type of Set<E>, List<E> and Map<K,V>.
                 * Example:
                 * List<String> -> String is ActualType
                 * Set<Integer> -> Integer is ActualType
                 * Map<String, Long> -> String, Long is ActualType
                 */
                System.out.println("Actual Type:");
                for(Object objActualType : ((ParameterizedType) fieldType).getActualTypeArguments()){
                    log("-- "+objActualType + "\n");
                }
            }
            log("+++++++++++++++++++++++++++++++\n");
        }
    }
}
