package softcat.kingvillager.Registration;

import com.google.common.base.Suppliers;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.util.datafix.fixes.PointOfInterestReorganizationFix;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.commons.lang3.reflect.FieldUtils;
import softcat.kingvillager.KingVillagerMod;
import softcat.kingvillager.Profession.KingProfession;
import softcat.kingvillager.Profession.PointOfInterestKingType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(KingVillagerMod.MOD_ID)
public class ProfessionRegistration {

    public static final VillagerProfession king = null;

    public static final PointOfInterestType kingpoi = null;


    @SubscribeEvent
    public static void registerPointOfInterest(RegistryEvent.Register<PointOfInterestType> event)
    {
        event.getRegistry().registerAll(new PointOfInterestKingType().setRegistryName(KingVillagerMod.MOD_ID, "kingpoi"));

    }





    @SubscribeEvent
    public static void registerProfession(RegistryEvent.Register<VillagerProfession> event) {

        event.getRegistry().registerAll(new KingProfession(kingpoi).setRegistryName(KingVillagerMod.MOD_ID, "king"));


        injectWorkstation();


    }

    // Reflexion magic
    // Inject the throne into the workstations block in order to be notice by unemployed villagers

    private static Method injectWorkstation;
    static void injectWorkstation()
    {
        try
        {

            injectWorkstation = PointOfInterestType.class.getDeclaredMethod("registerBlockStates", PointOfInterestType.class);
            injectWorkstation.setAccessible(true);
            injectWorkstation.invoke(kingpoi, kingpoi);



        }
        catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalAccessException e)
        {
            try
            {
                injectWorkstation = PointOfInterestType.class.getDeclaredMethod("func_221052_a", PointOfInterestType.class);
                injectWorkstation.setAccessible(true);
                injectWorkstation.invoke(kingpoi, kingpoi);
                e.printStackTrace();
            }
            catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalAccessException er) {

                er.printStackTrace();

            }
        }
    }



}
