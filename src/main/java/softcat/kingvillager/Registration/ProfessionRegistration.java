package softcat.kingvillager.Registration;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import softcat.kingvillager.KingVillagerMod;
import softcat.kingvillager.Profession.KingProfession;
import softcat.kingvillager.Profession.PointOfInterestKing;

@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(KingVillagerMod.MOD_ID)
public class ProfessionRegistration {




    @SubscribeEvent
    public static void registerPOIT(RegistryEvent.Register<PointOfInterestType> event) {

        PointOfInterestKing.createPointOfInterestKing();
    }

    @SubscribeEvent
    public static void registerProfession(RegistryEvent.Register<VillagerProfession> event)
    {
        KingProfession.CreateKingProfession(PointOfInterestKing.kingpoi);

    }



}
