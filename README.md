# NewsFeedClientServerApp

#Instructions

1. Open two different Instances of Eclipse or Intellij
2. In first Instance import NewFeedAnalyzer and run NewsFeedAnalyzer.java
3. In second Instance, import MockNewsFeed, provide system property for message-frequency and run Main.java. 


MockNewsFeed run several instances of NewsFeedMocker that generate random news headlines with priority. The news feed are emitted  by NewsFeedMocker continuosly for NewsFeedAnalyzer to analyze the news.

NewsFeedAnalyzer analyzes the news and if the news headline contain more than 50% of good words, it characterizes as a good news and display the number of good news in last 10 seconds.
