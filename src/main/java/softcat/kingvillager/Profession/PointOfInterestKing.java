package softcat.kingvillager.Profession;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import softcat.kingvillager.Registration.BlockRegistration;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class PointOfInterestKing  {

   public  static PointOfInterestType kingpoi;

    public static void createPointOfInterestKing()
    {
        kingpoi =  injectPOI( "king",  getAllStates(BlockRegistration.throne),1, 1);

    }

    static Set<BlockState> getAllStates(Block block) {
        return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
    }


    private static Method POIInjector;

    static
    {
        try
        {
            POIInjector = PointOfInterestType.class.getDeclaredMethod("register", String.class, Set.class, int.class, int.class);
            POIInjector.setAccessible(true);

        }
        catch (NoSuchMethodException | SecurityException e)
        {
            e.printStackTrace();
        }
    }

    public static PointOfInterestType injectPOI(String name, Set<BlockState> stateSet, int dist, int number)
    {
        try
        {

           Object poit =  POIInjector.invoke(null, name, stateSet, dist, number);
           if(poit instanceof  PointOfInterestType) {
               return (PointOfInterestType) poit;
           }
           else {
               throw new IllegalArgumentException("POIT expected");
           }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
