import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public class NewsFeedMocker implements Runnable{

    private static Socket socket;

    public void run(){
        System.out.println("Current thread" + Thread.currentThread().getName());
        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

            OutputStream os = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);


            final List<NewsItem> newsItemsList = new CopyOnWriteArrayList<>();
            int msgFrequency = Integer.parseInt(System.getProperty("message-frequency"));
            socket.setSendBufferSize(msgFrequency);
            while (true) {
                NewsItem newsItem = NewsItemGenerator.getNewsItem();

                System.out.println("Current thread" + Thread.currentThread().getName());

                synchronized (newsItemsList) {
                    if (newsItemsList.size() < msgFrequency)
                        newsItemsList.add(newsItem);
                    else
                        Thread.currentThread().wait();

                }
                socket.setSendBufferSize(msgFrequency);


                newsItemsList.forEach( item -> {
                    try {
                        objectOutputStream.writeObject(item);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    newsItemsList.remove(item);
                });

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
