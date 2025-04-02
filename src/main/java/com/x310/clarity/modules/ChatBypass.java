package com.x310.clarity.modules;

import com.x310.clarity.Main;
import meteordevelopment.meteorclient.events.game.SendMessageEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ChatBypass extends Module {
    private final SettingGroup sgUnicode = settings.createGroup("Unicode Bypass");
    private final SettingGroup sgMe = settings.createGroup("/m:me Bypass");

    private final Setting<Boolean> unicodeBypass = sgUnicode.add(new BoolSetting.Builder()
        .name("Unicode Bypass")
        .description("Replaces letters with Unicode lookalikes.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> meBypass = sgMe.add(new BoolSetting.Builder()
        .name("Me Bypass")
        .description("Adds /m:me before your message.")
        .defaultValue(false)
        .build()
    );

    public ChatBypass() {
        super(Main.CATEGORY, "Chat Bypass", "Bypass chat blocking plugins.");
    }

    @EventHandler()
    public void onSendMessage(SendMessageEvent event) {
        String message = event.message;

        if (unicodeBypass.get() && !message.startsWith("/")) {
            message = replaceWithUnicode(message);
        }

        if (meBypass.get() && !message.startsWith("/")) {
            message = "/minecraft:me " + message;

        }
        // im gonna kms thanks nxyi
        event.message = message;
        if (event.message.startsWith("/")){
            ClientPlayNetworkHandler handler = mc.getNetworkHandler();
            handler.sendChatCommand(message.substring(1));
            event.cancel();
        }
    }

    private String replaceWithUnicode(String input) {
        // shitcode pls ignore thanks
        return input.replace('a', 'а').replace('c', 'с').replace('e', 'е').replace('i', 'і')
            .replace('j', 'ј').replace('o', 'о').replace('p', 'р').replace('s', 'ѕ')
            .replace('x', 'х').replace('y', 'у').replace('A', 'А').replace('B', 'В')
            .replace('C', 'С').replace('E', 'Е').replace('H', 'Н').replace('I', 'І')
            .replace('K', 'Κ').replace('M', 'М').replace('N', 'Ν').replace('O', 'О')
            .replace('P', 'Р').replace('S', 'Ѕ').replace('T', 'Т').replace('X', 'Х')
            .replace('Y', 'Υ').replace('Z', 'Ζ');
    }
}
