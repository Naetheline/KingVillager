package softcat.kingvillager.Registration;

import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import softcat.kingvillager.KingVillagerMod;
import softcat.kingvillager.Profession.KingProfession;
import softcat.kingvillager.Profession.RandomTradeBuilder;


@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TradesRegistration {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event){

        if(event.getType() == ProfessionRegistration.king)
        {
            event.getTrades().get(1).add((new RandomTradeBuilder(16, 10, 0.05F).setEmeraldPrice(2).setForSale(Items.IRON_INGOT, 1, 2).build()));
            event.getTrades().get(1).add((new RandomTradeBuilder(16, 10, 0.05F).setPrice(Items.GOLD_NUGGET, 8,16).setForSale(Items.EMERALD, 1, 1).build()));
            event.getTrades().get(2).add((new RandomTradeBuilder(8, 10, 0.05F).setEmeraldPrice(4).setForSale(Items.GOLD_INGOT, 1, 2).build()));
            event.getTrades().get(3).add((new RandomTradeBuilder(8, 10, 0.05F).setEmeraldPrice(8).setForSale(Items.PRISMARINE_SHARD, 1, 2).build()));
            event.getTrades().get(3).add((new RandomTradeBuilder(8, 10, 0.05F).setEmeraldPrice(8).setForSale(Items.PRISMARINE_CRYSTALS, 1, 2).build()));
            event.getTrades().get(4).add((new RandomTradeBuilder(5, 10, 0.05F).setEmeraldPrice(20).setForSale(Items.GOLD_BLOCK, 1, 1).build()));
            event.getTrades().get(4).add((new RandomTradeBuilder(5, 10, 0.05F).setEmeraldPrice(16).setForSale(Items.DIAMOND, 1, 1).build()));
            event.getTrades().get(5).add((new RandomTradeBuilder(3, 12, 0.05F).setEmeraldPrice(36).setForSale(Items.TOTEM_OF_UNDYING, 1, 1).build()));
        }
    }
}
