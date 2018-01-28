public class Main {

    public static void main(String args[]){

        String frequency = System.getProperty("message-frequency");
        System.out.println(frequency);


        NewsFeedMocker newsFeedMocker = new NewsFeedMocker();
        Thread t1 = new Thread(newsFeedMocker);
        Thread t2 = new Thread(newsFeedMocker);
        Thread t3 = new Thread(newsFeedMocker);

        t1.start();
        t2.start();
        t3.start();

    }
}
