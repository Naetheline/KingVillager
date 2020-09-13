package softcat.kingvillager.Profession;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.datafix.fixes.VillagerProfessionFix;
import net.minecraft.village.PointOfInterestType;
import softcat.kingvillager.KingVillagerMod;
import softcat.kingvillager.Registration.BlockRegistration;
import softcat.kingvillager.Registration.ProfessionRegistration;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KingProfession  {

public static VillagerProfession king;
     public static void CreateKingProfession(PointOfInterestType poi) {
       king = injectProfession("king", poi, null);

    }




    private static Method professionInjector;

    static
    {
        try
        {
            professionInjector = VillagerProfession.class.getDeclaredMethod("register", String.class, PointOfInterestType.class, SoundEvent.class);
            professionInjector.setAccessible(true);

        }
        catch (NoSuchMethodException | SecurityException e)
        {
            e.printStackTrace();
        }
    }

    public static VillagerProfession injectProfession(String name, PointOfInterestType poiType, @Nullable SoundEvent soundEvent)
    {
        try
        {
           Object king = professionInjector.invoke(null, name, poiType, soundEvent);
           if(king instanceof  VillagerProfession)
           {
               return (VillagerProfession) king;
           }
           else
           {
               throw new IllegalArgumentException("Expected VillagerProfesssion");
           }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
