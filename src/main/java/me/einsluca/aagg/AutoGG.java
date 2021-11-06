package me.einsluca.aagg;

import me.einsluca.aagg.event.MessageEvent;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.settings.elements.StringElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;


import java.util.List;

public class AutoGG extends LabyModAddon {

    public static AutoGG instance;
    public boolean enabled = true;
    public String ggMessage = "GG";


    @Override
    public void onEnable() {
        instance = this;


        getApi().getEventService().registerListener(new MessageEvent());
        System.out.println("[AdvancedAutoGG] Has started");
    }

    @Override
    public void loadConfig() {
        this.enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
        this.ggMessage = getConfig().has("Message") ? getConfig().get("Message").getAsString() : "GG";
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        BooleanElement booleanElement = new BooleanElement("Enabled", new ControlElement.IconData(Material.REDSTONE_LAMP), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean enableAddon) {
                AutoGG.this.enabled = enableAddon.booleanValue();
                AutoGG.this.getConfig().addProperty("enabled", Boolean.valueOf(AutoGG.this.enabled));
                AutoGG.this.saveConfig();
            }
        }, this.enabled);
        list.add(booleanElement);


        StringElement stringElement = new StringElement("Message", new ControlElement.IconData(Material.STRING), this.ggMessage, new Consumer<String>() {
            @Override
            public void accept(String s) {
                AutoGG.this.ggMessage = s;
                AutoGG.this.getConfig().addProperty("Message", AutoGG.this.ggMessage);
                AutoGG.this.saveConfig();
            }
        });
        list.add(stringElement);
    }
}
