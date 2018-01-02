package network;

import java.util.ArrayList;
import java.util.List;

import data.FeedEntity;

/**
 * Created by user on 12/29/2017.
 */

public class Detail
{
    List<FeedEntity> feed;
    List<ArticleEntity> articles;
    List<PublisherEntity> publishers;
    List<ImageEntity>  image;
    List<following> followingList;

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public List<FeedEntity> getFeed() {
        return feed;
    }

    public List<following> getFollowingList() {
        return followingList;
    }

    public List<ImageEntity> getImage() {
        return image;
    }

    public List<PublisherEntity> getPublishers() {
        return publishers;
    }
}
