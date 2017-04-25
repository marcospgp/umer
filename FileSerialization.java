import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;

public class FileSerialization {

public static void main(String[] args) {

try {
    Client1 cliente = new Client1("Vítor", "Marcos");
     
     // escrever objeto no ficheiro
     FileOutputStream fileoutput = new FileOutputStream("myclient.txt");
     ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
     objectoutput.writeObject(cliente);
     objectoutput.close();

     // ler objeto do ficheiro
     FileInputStream fileinput = new FileInputStream("myclient.txt");
     ObjectInputStream objectinput = new ObjectInputStream(fileinput);
     Client1 client1loaded = (Client1) objectinput.readObject();
     objectinput.close();

     System.out.println("qual é o um? " + client1loaded.getum() + ", qual é o dois? " + client1loaded.getdois());

 
		} catch (EOFException ex) { 
            System.out.println("End of file reached.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
}