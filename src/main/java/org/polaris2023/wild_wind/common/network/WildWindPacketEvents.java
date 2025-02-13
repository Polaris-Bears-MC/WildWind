package org.polaris2023.wild_wind.common.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.network.packets.EggShootPacket;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/13 19:54:00}
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class WildWindPacketEvents {
    @SubscribeEvent
    public static void setupPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(WildWindMod.MOD_ID).versioned(WildWindMod.MOD_VERSION).optional();
        registrar.playToServer(EggShootPacket.TYPE, EggShootPacket.STREAM_CODEC, EggShootPacket::handle);
    }
}
