import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;

/* ESTA CLASSE ESTÁ APENAS PREPARADA PARA 2 ARRAYLISTS */

public class IO
{
   public static void main(String [] args){
       ArrayList<String> a1 = new ArrayList<String>();
       a1.add("Sergio1");
       a1.add("Vitor1");
       a1.add("Marcos1");
       ArrayList<String> a2 = new ArrayList<String>();
       a2.add("Sergio2");
       a2.add("Vitor2");
       a2.add("Marcos2");
       Write(a1,1);
       Write(a2,2);
       ArrayList<String> arraylist1 = new ArrayList<String>();
       ArrayList<String> arraylist2 = new ArrayList<String>();
       Read(arraylist1,2);
    }
    
   public static void Write(ArrayList<String> l, int ID) {
        // creating output stream variables
        FileOutputStream fileoutput = null;
        ObjectOutputStream objectoutput = null;
        try {
            // writing data
            if (ID==1) fileoutput = new FileOutputStream("data1.txt");
            if (ID==2) fileoutput = new FileOutputStream("data2.txt");
            // converting object to binary
            objectoutput = new ObjectOutputStream(fileoutput);
            // writing ArrayList to stream
            objectoutput.writeObject(l);
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
    

    public static void Read(ArrayList<String> l, int ID) {
         // creating input stream variables
        FileInputStream fileinput = null;
        ObjectInputStream objectinput = null;
        try {
            // reading data
            if (ID==1) fileinput = new FileInputStream("data1.txt");
            if (ID==2) fileinput = new FileInputStream("data2.txt");
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