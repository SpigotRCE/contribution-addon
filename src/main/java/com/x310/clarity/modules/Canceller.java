package com.x310.clarity.modules;

import com.x310.clarity.Main;
import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Canceller extends Module {

    public Canceller() {
        super(Main.CATEGORY, "Canceller", "cancel packet shit");
    }

    @EventHandler(priority = EventPriority.HIGHEST + 1)
    public static void onSendPacket(PacketEvent.Send event) {
        if (event.packet instanceof PlayerMoveC2SPacket.OnGroundOnly
            || event.packet instanceof PlayerMoveC2SPacket.PositionAndOnGround) {
            event.cancel();
        }
    }

}

