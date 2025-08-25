package xyz.tbvns;

public class BotConfig implements Config {
    public static String token = "0123456789abcdefghijklmnop";
    public static String channelID = "0123456789";
    public static boolean sanitize = true;
    public static String[] messages = new String[]{
            "%mention% broke a streak of %count%! Shame on you!",
            "User %mention% forgot how to count! A streak of %count% was lost.",
            "%mention% just destroyed %count% numbers of hard work. Everyone point and laugh.",
            "Nice job, %mention%. You can’t even count to %count%.",
            "Math teachers everywhere are disappointed in you, %mention%.",
            "Wow, %mention%… failing at counting? That’s impressive in the worst way.",
            "The streak was %count%, until %mention% decided numbers are too hard.",
            "%mention% is living proof that math class was useless.",
            "Congratulations %mention%, you invented negative IQ.",
            "%mention% fumbled the most basic human skill: counting.",
            "Streak of %count% obliterated, thanks to %mention%’s genius move.",
            "%mention%, Sesame Street called. They want their counting lessons back.",
            "Big brain %mention% has entered the chat… and ruined %count%.",
            "Counting is literally 1, 2, 3… but somehow %mention% messed it up."
    };
    public static boolean grantRole = false;
    public static String grantedRoleId = "0123456789";
}
