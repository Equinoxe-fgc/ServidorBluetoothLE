import java.io.*;
import java.net.*;

public class ThreadHandler extends Thread {
    Socket newsock;
    int n;
    String sCadena;

    ThreadHandler(Socket s, int v) {
        newsock = s;
        n = v;
    }


    public void run() {
    	byte[] bytes = new byte[18];
    	InputStream in = null;
    	int iDatosRead;
    	
        try {          	
        	in = newsock.getInputStream();

            while (!newsock.isClosed()) {            	
            	iDatosRead = in.read(bytes);

            	if (iDatosRead == -1)
            		break;

            	sCadena = String.format("%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X:%02X", 
            								bytes[17], bytes[16], bytes[15], bytes[14], bytes[13], bytes[12], 
            								bytes[11], bytes[10], bytes[9] , bytes[8] , bytes[7] , bytes[6] , 
            								bytes[5] , bytes[4] , bytes[3] , bytes[2] , bytes[1] , bytes[0]);
            	System.out.println(sCadena);
            }
            System.out.println("Exit thread");
            	
        } catch (Exception e) {
        	try {
                System.out.println("Disconnected from client number: " + n);
        		in.close();
        		newsock.close();
        	} catch (Exception e2) {}
        }

    }
}
