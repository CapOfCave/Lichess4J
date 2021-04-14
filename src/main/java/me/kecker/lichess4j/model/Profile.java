package me.kecker.lichess4j.model;

import lombok.Value;

@Value
public class Profile {
    private String country;
    private String location;
    private String bio;
    private String firstName;
    private String lastName;
    private int fideRating;
    private int uscfRating;
    private int ecfRating;
    private String links;
}
