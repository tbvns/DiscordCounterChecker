package xyz.tbvns;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import java.awt.*;
import java.util.Objects;

public class EventHandler {
    static boolean isBest = false;
    @SubscribeEvent
    public void onMessage(MessageReceivedEvent event) {
        if (event.isWebhookMessage()) return;
        if (event.getAuthor().isBot()) return;

        if (event.getChannel().getId().equals(BotConfig.channelID)) {
            String message = event.getMessage().getContentRaw();
            if (BotConfig.sanitize) {
                message = message.replaceAll("[^0-9]", "");
            }
            try {
                int number = Integer.parseInt(message);
                if (BotSave.count + 1 == number) {
                    BotSave.count++;
                    EZConfig.save();

                    if (BotSave.maxCount < BotSave.count && !isBest) {
                        if (!BotSave.maxMessageID.isEmpty()) {
                            event.getMessage().reply("").addEmbeds(
                                    new EmbedBuilder()
                                            .addField(new MessageEmbed.Field("New best score !", "The previous best score was " + BotSave.maxCount + " broken by <@" + BotSave.maxLooserID + "> at " + event.getChannel().asTextChannel().retrieveMessageById(BotSave.maxMessageID).complete().getJumpUrl(), false))
                                            .setColor(Color.GREEN)
                                            .build()
                            ).queue();
                        }
                        isBest = true;
                    }
                } else {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.addField(new MessageEmbed.Field("Score of " + BotSave.count + " lost!", Main.getRandomMessage().replaceAll("%mention%", event.getAuthor().getAsMention()).replaceAll("%count%", String.valueOf(BotSave.count)), false))
                            .setColor(Color.RED);
                    if (!BotSave.maxMessageID.isEmpty() && !isBest) {
                        builder.addField(new MessageEmbed.Field("Best score", "The best score is " + BotSave.maxCount + " broken by <@" + BotSave.maxLooserID + "> here: " + event.getChannel().asTextChannel().retrieveMessageById(BotSave.maxMessageID).complete().getJumpUrl(), false));
                    }

                    if (isBest) {
                        builder.addField(new MessageEmbed.Field("Best score", "This was the best score yet.", false));
                    }

                    event.getMessage().reply("").addEmbeds(
                                    builder.build()
                    ).queue();
                    if (isBest) {
                        BotSave.maxCount = BotSave.count;
                        BotSave.maxMessageID = event.getMessageId();
                        BotSave.maxLooserID = event.getAuthor().getId();
                    }
                    BotSave.count = 0;
                    EZConfig.save();
                    isBest = false;

                    if (BotConfig.grantRole) {
                        event.getGuild().addRoleToMember(event.getAuthor(), Objects.requireNonNull(event.getGuild().getRoleById(BotConfig.grantedRoleId))).queue();
                    }
                }
            } catch (Exception e) {
                System.out.println("Got an error while parsing to int, failing the count.\n" + e.getMessage());
            }
        }
    }
}
