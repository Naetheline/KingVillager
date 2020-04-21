package softcat.kingvillager.Registration;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import softcat.kingvillager.KingVillagerMod;

@Mod.EventBusSubscriber(modid=KingVillagerMod.MOD_ID, value=Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)



public class RenderRegistration {
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockRegistration.throne, RenderType.getCutout());
    }
}
