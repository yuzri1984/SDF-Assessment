package com.java_assignment;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class implements network client application of client side in java
 */
public class SocketClientExample {

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        //ObjectInputStream ois = null;

        try {
            // establish socket connection to server at port 80
            socket = new Socket("68.183.239.26", 80);
            oos = new ObjectOutputStream(socket.getOutputStream());
            // Message if connection build Successfully
            System.out.println("Connected to to Server running at 68.183.239.26\n");
            for (int i = 0; i < 5; i++) {

                // writing to socket using ObjectOutputStream
                if (i == 0) {
                    System.out.println("Writing Request message back  to the server running at IP 68.183.239.26 : \n");
                    oos.writeObject("Request ID from server:");
                } else if (i == 1) {
                    System.out.println("Writing  Name as per NRIC back  to the server running at IP 68.183.239.26 :\n ");
                    oos.writeObject("Your Name as per NRIC Here");
                } else if (i == 2) {
                    System.out.println("Writing Email address back  to the server running at IP 68.183.239.26 :\n ");

                    oos.writeObject("Your email Here  ");
                } else if (i == 3) {
                    System.out.println(
                            "Writing Average for list of numbers back  to the server running at IP 68.183.239.26 :\n ");
                    oos.writeObject("Average for the list of numbers:");
                }

                //pause of 100 millis seconds after every write
                Thread.sleep(100);
            }
            // closing the socket connection
            oos.close();
            System.out.println("\n\n****** Success ****** : \n");

        } catch (Exception e) {
            System.out.println("Unable to connect to the server running at IP 68.183.239.26 : ");
        }

    }
}