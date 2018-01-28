import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class NewsFeedMocker implements Runnable{

    private static Socket socket;

    public void run(){
        System.out.println("Current thread" + Thread.currentThread().getName());
        try
        {
            String host = "192.168.0.115";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);

            int msgFrequency = Integer.parseInt(System.getProperty("message-frequency"));

            for (int i=0; i< msgFrequency; i++) {
                NewsItem newsItem = NewsItemGenerator.getNewsItem();

                String sendMessage = newsItem.getHeadline() + "\n";
                System.out.println("Current thread" + Thread.currentThread().getName());
                objectOutputStream.writeObject(newsItem);
                objectOutputStream.flush();
                System.out.println("Message sent to the server : " + newsItem.getHeadline() + " "+ newsItem.getPriority());
                sleep(1000);
           }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }


}
