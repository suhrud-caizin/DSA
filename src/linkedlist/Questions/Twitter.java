package linkedlist.Questions;

import java.util.*;

class Tweet {
    long timestamp;
    int tweetId;
    int userId;

    public Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.timestamp = System.nanoTime(); // Ensure unique timestamp for each tweet
    }
}

public class Twitter {
    private final Map<Integer, Set<Integer>> followMap;
    private final Map<Integer, List<Tweet>> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        new ArrayDeque<>(5);
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Tweet(userId, tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {
        // Get the list of users to fetch tweets from
        Set<Integer> allFollowing = new HashSet<>();
        allFollowing.add(userId); // Include user's own tweets
        var temp = followMap.getOrDefault(userId, Collections.emptySet());
        allFollowing.addAll(temp);

        // Use a PriorityQueue to get the 10 most recent tweets
        PriorityQueue<Tweet> feed = new PriorityQueue<>((t1, t2) -> Long.compare(t2.timestamp, t1.timestamp));
        for (var s : allFollowing) {
            var tweets = tweetMap.getOrDefault(s, Collections.emptyList());
            int size = tweets.size();
            int startIndex = Math.max(0, size - 10);
            feed.addAll(tweets.subList(startIndex, size));
        }

        // Collect the top 10 tweets
        List<Integer> result = new ArrayList<>();
        while (!feed.isEmpty() && result.size() < 10) {
            result.add(feed.poll().tweetId);
        }

        return result;

    }

    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        followMap.getOrDefault(followerId, Collections.emptySet()).remove(followeeId);
    }
}
