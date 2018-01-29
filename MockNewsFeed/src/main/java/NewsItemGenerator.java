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

       DistributedRandomNumberGenerator distributedRandomNumberGenerator = new DistributedRandomNumberGenerator();
       addDistributions(distributedRandomNumberGenerator);

        for (int i=0; i< result; i++){
            int num = randomWordPicker.nextInt(headlineKeywords.size());

            if (i == result - 1){
                headline += headlineKeywords.get(num);
            }
            else
            headline += headlineKeywords.get(num) + " ";
        }
        return new NewsItem(headline , distributedRandomNumberGenerator.getDistributedRandomNumber());
    }

    private static void addDistributions(DistributedRandomNumberGenerator distributedRandomNumberGenerator){
        distributedRandomNumberGenerator.addNumber(0, 29.3d);
        distributedRandomNumberGenerator.addNumber(1, 19.3d);
        distributedRandomNumberGenerator.addNumber(2, 14.3d);
        distributedRandomNumberGenerator.addNumber(3, 10.9d);
        distributedRandomNumberGenerator.addNumber(4, 8.4d);
        distributedRandomNumberGenerator.addNumber(5, 6.5d);
        distributedRandomNumberGenerator.addNumber(6, 4.8d);
        distributedRandomNumberGenerator.addNumber(7, 3.4d);
        distributedRandomNumberGenerator.addNumber(8, 2.1d);
        distributedRandomNumberGenerator.addNumber(9, 1d);
    }
}
