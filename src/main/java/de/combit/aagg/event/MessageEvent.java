package de.combit.aagg.event;/*

 __                           _      _  _   
/  |                         | |    (_)| |  
`| |   ___   ___   _ __ ___  | |__   _ | |_ 
 | |  / __| / _ \ | '_ ` _ \ | '_ \ | || __|
_| |_| (__ | (_) || | | | | || |_) || || |_ 
\___/ \___| \___/ |_| |_| |_||_.__/ |_| \__|

» Class created by 1combit | Luca
» Date: 01.06.2021 | 19:28
» 1combit#8044


 */

import de.combit.aagg.AutoGG;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class MessageEvent {

    @Subscribe
    public void onMessageReceive(MessageReceiveEvent event) {
        String message = event.getComponent().getString();
        if (AutoGG.instance.enabled && message.contains("-= Statistiken dieser Runde =-") || message.contains("-= Statistics of this game =-")) {
            new Timer("SendMessage").schedule(new TimerTask() {
                @Override
                public void run() {
                    Minecraft.getInstance().player.sendChatMessage(AutoGG.instance.getConfig().get("Message").getAsString());
                }
            }, 1000L);
        }
    }

}