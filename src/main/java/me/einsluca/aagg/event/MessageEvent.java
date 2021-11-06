package me.einsluca.aagg.event;

import me.einsluca.aagg.AutoGG;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

import java.util.Timer;
import java.util.TimerTask;

public class MessageEvent {

    @Subscribe
    public void onMessageReceive(MessageReceiveEvent event) {
        String message = event.getComponent().getString();
        if (AutoGG.instance.enabled && !message.contains(":") && message.contains("-= Statistiken dieser Runde =-") || message.contains("-= Statistics of this game =-") || message.contains("hat die PartyGames gewonnen!")) {
            new Timer("SendMessage").schedule(new TimerTask() {
                @Override
                public void run() {
                if (AutoGG.instance.getConfig().get("Message") != null) {
                    Minecraft.getInstance().player.sendChatMessage(AutoGG.instance.getConfig().get("Message").getAsString());
                } else {
                    Minecraft.getInstance().player.sendChatMessage("GG");
                }
                }
            }, 1000L);
        }
    }

}
