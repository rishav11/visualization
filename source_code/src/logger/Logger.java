package logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class Logger {

    protected static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("variables_log.txt", "UTF-8");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static void write(String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("variables_log.txt", true));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isChangedVar(Object reflectionVar, Object realVar) {
        return (reflectionVar.equals(realVar));
    }

    public static void log(Object obj, Object var){
        for(Field field : obj.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try {
                if (isChangedVar(field.get(obj), var)) {
                    write("Class name: " + obj + "\n");
                    write("Variable name: " + field.getName() + "\n");
                    write("Variable value: " + var + "\n");
                    write("+++++++++++++++++++++++++++++++\n");
                };
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
