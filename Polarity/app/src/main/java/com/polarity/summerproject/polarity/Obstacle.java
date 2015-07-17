package com.polarity.summerproject.polarity;

public class Obstacle {
    Boolean color;
    Integer distance;
    Integer length;

    public Obstacle(Boolean color, Integer distance, Integer length) {
        this.color = color;
        this.distance = distance;
        this.length = length;
    }

    public void decreaseDistanceFromPlayer(Integer decreasedDistance) {
        this.distance =- decreasedDistance;
    }

    public void decreaseLength(Integer length) {
        this.length =- length;
    }
}
