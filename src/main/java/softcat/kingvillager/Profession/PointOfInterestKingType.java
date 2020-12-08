package softcat.kingvillager.Profession;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestData;
import net.minecraft.village.PointOfInterestType;
import softcat.kingvillager.Registration.BlockRegistration;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class PointOfInterestKingType extends PointOfInterestType {

    public PointOfInterestKingType() {
        super("king", getAllStates(BlockRegistration.throne), 1, 1);
    }







}
