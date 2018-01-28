import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NewsItemGenerator {

    private static List<String> headlineKeywords = Arrays.asList("up", "down", "rise", "fall", "good", "bad", "success", "failure", "high", "low", "über", "unter");

    public static NewsItem getNewsItem(){

        Random numberOfWords = new Random();
        int Low = 3;
        int High = 6;
        int result = numberOfWords.nextInt(High-Low) + Low;

        String headline = "";
        Random randomWordPicker = new Random();

        Random priorityRandom = new Random();
        int lowerbound = 1;
        int upperbound = 10;
        int priority = priorityRandom.nextInt(upperbound-lowerbound) + lowerbound;

        for (int i=0; i< result; i++){
            int num = randomWordPicker.nextInt(headlineKeywords.size());

            if (i == result - 1){
                headline += headlineKeywords.get(num);
            }
            else
            headline += headlineKeywords.get(num) + " ";
        }
        return new NewsItem(headline , priorityRandom.nextInt(priority));
    }
}
