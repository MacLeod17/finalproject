package edu.neumont.csc150.c.finalproject.model;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questions = new ArrayList<>();

    public Questions() {
        setQuestions();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions() {
        questions.add(new Question("What color is your soul?", "Red", "Blue", "Green", "Grey"));

        questions.add(new Question("What do you see yourself doing after work?", "Going out for a drink",
                "Catching up on my studies", "Praying or going to church", "Looking for things to steal"));

        questions.add(new Question("What is your dream job?", "Boxer", "Librarian", "Religious Figure",
                "Lawyer"));

        questions.add(new Question("How do you spend your free time?", "Exercising", "Reading",
                "Community Service", "Parkour"));

        questions.add(new Question("Who do you look up to the most?", "Muhammad Ali and/or Bruce Lee",
                "Stephen Hawking and/or Elon Musk", "The Pope", "Donald Trump"));

        questions.add(new Question("You walk into a shop and you see a high-end, very rare item. You look at the price and " +
                "realize it is way out of your budget. What do you do?", "Haggle with the store owner", "Offer an item of " +
                "similar value in exchange", "Offer to work for a discount", "Sneak into the store at night and steal the item"));

        questions.add(new Question("You return home and find that your gold has been stolen by someone you know. What do you do?",
                "Go to their home and take my gold back by force", "Cast a Sleep spell on the thief and take my gold back",
                "Ask for the gold back", "Break into their house and steal my gold back, along with all of theirs"));

        questions.add(new Question("You walk into a room with lots of enemies. After assessing the situation, you realize you can't " +
                "fight them all. How do you respond?", "Take the enemies head-on so that my friends can escape", "Cast a " +
                "protection spell to get out of the room safely", "Protect my friends while we escape", "Create a distraction " +
                "so that my friends can escape"));

        questions.add(new Question("You are tasked with escorting a high value asset. How do you protect them?", "Escort " +
                "them through well-traveled paths, taking out any hazards", "Cast a protection spell on the asset and escort them " +
                "through a quiet path", "Pray for protection and good travels", "Take unknown paths and stop at safehouses " +
                "along the way"));

        questions.add(new Question("Your party member has been poisoned. What do you do?", "Look for an antidote",
                "Cast a Slow Poison spell to give me time to find an antidote", "Heal them with a spell", "Use a stolen potion and hope it works"));
    }
}
