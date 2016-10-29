package com.example;

import java.util.ArrayList;
import java.util.Random;

public class TellJock {
    private ArrayList<String> jokes;

    public TellJock() {
        this.jokes = new ArrayList<>();
        jokes.add("Q: What did the DNA say to the other DNA?\n" +
                "A: Do these genes make my butt look fat.");
        jokes.add("The best part of Nintendo were the codes. We had codes that " +
                "got us to the end of the game immediately. Why can't we have that in real life? Just once I'd like to be on a date with a chick, when she starts talking about her cats and she's like, 'And this cat likes corn, and this one has diarrhea, and this one can fight crime...', I can be like, 'Up-up-down-down-left-right-left-right-B-A-select-start.' And then I'm in bed with her -- no more cats.");
        jokes.add("What's the difference between a blonde and Windows 95?\n" +
                "The blonde operates on more laptops!");
        jokes.add("Much like a shuttle launch, my rocket also comes in multiple stages.");
        jokes.add("Q: How many IT guys does it take to screw in a light bulb?\n" +
                "\n" +
                "A: None, that's a Facilities problem.");
        jokes.add("How many IBM employees does it take to screw in a light bulb?\n" +
                "10,000: one to hold up the light bulb, and 9,999 to turn the building around.");
        jokes.add("You look just like my girlfriend avatar!");
        jokes.add("I'm more machine now than man... and I think you know what I mean.");
    }

    //get random joke
    public String getJokes() {
        Random random = new Random();
        int i = random.nextInt(7);
        return jokes.get(i);
    }

}
