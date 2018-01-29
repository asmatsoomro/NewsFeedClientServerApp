import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class NewsFeedAnalyzer {

    private static Socket socket;

    private static List<String> goodNewsKeywords = Arrays.asList("up", "rise", "good", "success", "high", "über");


    public static void main(String[] args) {
        try {

            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port, 100);
            System.out.println("Server Started and listening to the port 25000");
            socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(is);
            InputStreamReader isr = new InputStreamReader(is);

            int goodNewsCount = 0;
            long timeAftertenSecs = System.currentTimeMillis() + 10000;
            List<NewsItem> topHeadlines = new ArrayList<NewsItem>();
            while (true) {

                try {
                    NewsItem news = (NewsItem) objectInputStream.readObject();

                    if (!"".equals(news.getHeadline()) || news != null) {
                        boolean isAGoodNews = isGoodNews(news.getHeadline());

                        if (isAGoodNews) {
                            goodNewsCount++;
                            topHeadlines = topHeadlines(news, topHeadlines);
                            if (timeAftertenSecs < System.currentTimeMillis()) {
                                System.out.println("Number of Good News in last 10 seconds::" + goodNewsCount);
                                topHeadlines.forEach(System.out::println);
                                timeAftertenSecs = System.currentTimeMillis() + 10000;
                                goodNewsCount = 0;
                            }
                        }
                    }
                } catch (EOFException eofException) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    private static List<NewsItem> topHeadlines(final NewsItem newsItem, List<NewsItem> topHeadlines) {

        if (topHeadlines.size() < 3) {
            topHeadlines.add(newsItem);
        } else {
            for (int i = 0; i < topHeadlines.size(); i++) {
                if (topHeadlines.get(i).getPriority() < newsItem.getPriority()) {
                    topHeadlines.remove(i);
                    topHeadlines.add(newsItem);
                }
            }
        }

        return topHeadlines;
    }

    private static boolean isGoodNews(String news) {
        if ("".equals(news) || news == null) {
            return false;
        }

        int goodWordCount = 0;
        String[] newsTokens = news.split(" ");

        for (int i = 0; i < newsTokens.length; i++) {
            if (goodNewsKeywords.contains(newsTokens[i])) {
                goodWordCount++;
            }
        }
        if (calculateGoodWordsPercentage(goodWordCount, newsTokens.length) > 50.0)
            return true;
        else
            return false;
    }

    private static float calculateGoodWordsPercentage(int goodWords, int totalWords) {

        float percentage = (float) goodWords / (float) totalWords * 100;
        return percentage;

    }
}
