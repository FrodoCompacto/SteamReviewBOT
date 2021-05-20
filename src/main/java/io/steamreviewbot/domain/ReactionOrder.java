package io.steamreviewbot.domain;

public class ReactionOrder{
    public Integer count;
    public String url;

    public ReactionOrder(Integer count, String url) {
        this.count = count;
        this.url = url;
    }

    public ReactionOrder() {
    }
}