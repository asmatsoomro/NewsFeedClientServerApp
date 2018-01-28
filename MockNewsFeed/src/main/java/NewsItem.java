import java.io.Serializable;

public class NewsItem implements Serializable{

    private String headline;
    private int priority;

    public NewsItem(String headline, int priority) {
        this.headline = headline;
        this.priority = priority;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "headline='" + headline + '\'' +
                ", priority=" + priority +
                '}';
    }
}
