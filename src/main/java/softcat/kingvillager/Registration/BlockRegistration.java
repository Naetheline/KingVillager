package softcat.kingvillager.Registration;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import softcat.kingvillager.Block.ThroneBlock;
import softcat.kingvillager.KingVillagerMod;

@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(KingVillagerMod.MOD_ID)
public class BlockRegistration {

    public static final Block throne = null;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().registerAll(

                new ThroneBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(4).notSolid().sound(SoundType.METAL)).setRegistryName(KingVillagerMod.MOD_ID, "throne")

        );
    }

    @SubscribeEvent
    public static void registerItemBlock(RegistryEvent.Register<Item> event){

        event.getRegistry().registerAll(
                new BlockItem(throne, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(KingVillagerMod.MOD_ID, "throne")
        );
    }
}
