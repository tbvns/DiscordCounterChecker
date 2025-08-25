package xyz.tbvns;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        EZConfig.registerClassPath("xyz.tbvns");
        EZConfig.load();
        EZConfig.save();

        JDA jda = JDABuilder.createDefault(BotConfig.token)
                .setEventManager(new AnnotatedEventManager())
                .addEventListeners(new EventHandler())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }

    public static String getRandomMessage() {
        return BotConfig.messages[new Random().nextInt(BotConfig.messages.length)];
    }
}