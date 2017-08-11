package com.matt.forgehax.mods;


import com.matt.forgehax.asm.events.PacketEvent;
import com.matt.forgehax.util.mod.loader.RegisterMod;
import com.matt.forgehax.util.mod.ToggleMod;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.matt.forgehax.asm.reflection.FastReflection;

@RegisterMod
public class NoRotate extends ToggleMod {
    public NoRotate() { super("NoRotate", true, "dont let server set pitch and yaw");}

    @SubscribeEvent
    public void onPacketRecieved(PacketEvent.Incoming.Pre event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook) event.getPacket();
            if (MC.player != null)
                if (MC.player.rotationYaw != -180 && MC.player.rotationPitch != 0) {
                    FastReflection.Fields.SPacketPlayer_yaw.set(packet, MC.player.rotationYaw);
                    FastReflection.Fields.SPacketPlayer_pitch.set(packet, MC.player.rotationPitch);
                }
        }
    }

}
