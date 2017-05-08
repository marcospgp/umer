import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class IO
{
   public static void WriteHashMap(HashMap<String, ?> l, int ID) {  
       
        // creating output stream variables
        
        FileOutputStream fileoutput = null;
        ObjectOutputStream objectoutput = null;
        
        try {
            
            // writing data
            
            if (ID==1) fileoutput = new FileOutputStream("vehicles.txt");
            if (ID==2) fileoutput = new FileOutputStream("drivers.txt");
            if (ID==3) fileoutput = new FileOutputStream("clients.txt");
            
            // converting object to binary
            objectoutput = new ObjectOutputStream(fileoutput);
            
            // writing HashMap to stream
            
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
        
        System.out.println("HashMap " + ID + " saved");
    }
    

    public static void ReadHashMap(HashMap<String, ?> l, int ID) {
        
       // creating input stream variables
       FileInputStream fileinput = null;
       ObjectInputStream objectinput = null;
       
       try {
            
            // reading data
            
            if (ID==1) fileinput = new FileInputStream("vehicles.txt");
            if (ID==2) fileinput = new FileInputStream("drivers.txt");
            if (ID==3) fileinput = new FileInputStream("clients.txt");
            
            // converting data to object
            objectinput = new ObjectInputStream(fileinput);
            
            // reading object's value and checking if input object is HashMap
            
            l = (HashMap<String, ?>) objectinput.readObject();
            if (!(l instanceof HashMap)){
                l=null;
                System.out.println("ERROR - input object is not Hashmap");
            }
            objectinput.close();
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
       
       System.out.println("HashMap " + ID + " read");
       
       // printing HashMap to console
       System.out.println("Read:" +l);
   }
   
      public static void WriteArrayList(ArrayList<Trip> l, int ID) {
          
        // creating output stream variables
        
        FileOutputStream fileoutput = null;
        ObjectOutputStream objectoutput = null;
        
        try {
            
            // writing data
            
            if (ID==1) fileoutput = new FileOutputStream("triphistory.txt");
            if (ID==2) fileoutput = new FileOutputStream("tripsunderway.txt");
            
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
        
        System.out.println("ArrayList " + ID + " saved");
    }
    
    public static void ReadArrayList(ArrayList<Trip> l, int ID) {
        
       // creating input stream variables
       
       FileInputStream fileinput = null;
       ObjectInputStream objectinput = null;
       
       try {
            // reading data
            
            if (ID==1) fileinput = new FileInputStream("triphistory.txt");
            if (ID==2) fileinput = new FileInputStream("tripsunderway.txt");
            
            // converting data to object
            objectinput = new ObjectInputStream(fileinput);
            
            // reading object's value and checking if input object is an Arraylist
            
            l = (ArrayList<Trip>) objectinput.readObject();
            if (!(l instanceof ArrayList)){
                l=null;
                System.out.println("ERROR - input object is not an Arraylist");
            }
            objectinput.close();
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
        
       System.out.println("ArrayList " + ID + " read");
       
       // printing ArrayList to console
       //System.out.println("Read:" +l);
   }
    
    
}