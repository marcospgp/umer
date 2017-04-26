import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;

public class IO
{
   public static void main(String [] args){
       ArrayList<String> al = new ArrayList<String>();
       al.add("Sergio");
       al.add("Vitor");
       al.add("Marcos");
       Write(al);
       ArrayList<String> arraylist = new ArrayList<String>();
       Read(arraylist);
    }
    
   public static void Write(ArrayList<String> al) {
        // creating output stream variables
        FileOutputStream fileoutput = null;
        ObjectOutputStream objectoutput = null;
        try {
            // writing data
            fileoutput = new FileOutputStream("data.txt");
            // converting object to binary
            objectoutput = new ObjectOutputStream(fileoutput);
            // writing ArrayList to stream
            objectoutput.writeObject(al);
            objectoutput.flush();
            objectoutput.close();
        } 
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        System.out.println("ArrayList object saved to data.txt");
    }
    

    public static void Read(ArrayList<String> l) {
         // creating input stream variables
        FileInputStream fileinput = null;
        ObjectInputStream objectinput = null;
        try {
            // reading data
            fileinput = new FileInputStream("data.txt");
            // converting data to object
            objectinput = new ObjectInputStream(fileinput);
            // reading object's value and checking if input object is an Arraylist
            l = (ArrayList<String>) objectinput.readObject();
            if (!(l instanceof ArrayList)){
                l=null;
                System.out.println("ERROR - input object is not an Arraylist");
            }
       } 
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        } 
        catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }
       System.out.println("ArrayList object read from data.txt");
       
       // printing ArrayList to console
       System.out.println("Address:" +l);
   }
}