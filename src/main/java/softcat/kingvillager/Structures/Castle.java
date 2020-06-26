package softcat.kingvillager.Structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Function;


// code from https://github.com/grossik/FarmCraft/blob/master/src/main/java/cz/grossik/farmcraft/test/TestStructures.java

public class Castle extends Feature<VillageConfig> {


    public Castle(Function<Dynamic<?>, ? extends VillageConfig> p_i49873_1_){
        super(p_i49873_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
                         BlockPos pos, VillageConfig config) {

        System.out.println("************************************************* TRYING TO PLACE A CASTLE ***********************************************");

        Random random = worldIn.getRandom();
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        Template template = ((ServerWorld) worldIn.getWorld()).getSaveHandler().getStructureTemplateManager()
                .getTemplateDefaulted(new ResourceLocation("village/plains/plains_castle/plains_castle"));

        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation)
                // .setBoundingBox defines until where the structure should spawn, for example
                // here it's max 25600 X, 256 Y and 25600 Z
                .setBoundingBox(new MutableBoundingBox((0 - 25600), (0 - 25600), (0 - 25600), 25600, 256, 25600))
                .setRandom(random).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        // j k l are respectively x y z , and define the width, height and depth of the
        // structure
        BlockPos blockpos = template.transformedSize(rotation);
        int j = random.nextInt(32 - 0);
        int k = random.nextInt(32 - 0);
        int l = 256;
        for (int i1 = 0; i1 < blockpos.getX(); ++i1) {
            for (int j1 = 0; j1 < blockpos.getZ(); ++j1) {
                l = Math.min(l, worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ()));
            }
        }
        // This is what actually generates the structure
        BlockPos blockpos1 = template.getZeroPositionWithTransform(
                new BlockPos(pos.add(j, 0, k).getX(), l, pos.add(j, 0, k).getZ()), Mirror.NONE, rotation);
        IntegrityProcessor integrityprocessor = new IntegrityProcessor(0.9F);
        placementsettings.clearProcessors().addProcessor(integrityprocessor);
        template.addBlocksToWorld(worldIn, blockpos1, placementsettings, 4);
        placementsettings.func_215220_b(integrityprocessor);
        return true;

    }


}
