package softcat.kingvillager.Registration;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.ResourceLocation;

import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import softcat.kingvillager.KingVillagerMod;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


@Mod.EventBusSubscriber(modid = KingVillagerMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(KingVillagerMod.MOD_ID)
public class CastleRegistration {

    private static  final int NUMBER_OF_TRIES = 100;



    @SubscribeEvent
    public static void registerPLainsCastle(FMLCommonSetupEvent event) {


        PlainsVillagePools.init();

        JigsawPattern jigsawpattern = JigsawManager.REGISTRY.get(new ResourceLocation(  "village/plains/houses"));

        System.out.println("----------------------------------------------------------" + jigsawpattern);

        try {
            Field listOfPieces = JigsawPattern.class.getDeclaredField("jigsawPieces");
            listOfPieces.setAccessible(true);

            Field immutablelistOfPair = JigsawPattern.class.getDeclaredField("field_214952_d");
            immutablelistOfPair.setAccessible(true);

            Field resourceLoc = JigsawPattern.class.getDeclaredField("field_214954_f");
            resourceLoc.setAccessible(true);

            Field name = JigsawPattern.class.getDeclaredField("name");
            name.setAccessible(true);


            System.out.println("name " + name.get(jigsawpattern));
            System.out.println("resource location " + resourceLoc.get(jigsawpattern));

            System.out.println("list of pieces " + listOfPieces.get(jigsawpattern));
            System.out.println("immutable list " + immutablelistOfPair.get(jigsawpattern));




            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(listOfPieces, listOfPieces.getModifiers() & ~Modifier.FINAL);

            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(immutablelistOfPair, immutablelistOfPair.getModifiers() & ~Modifier.FINAL);

            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(name, name.getModifiers() & ~Modifier.FINAL);


            List<Pair<JigsawPiece, Integer>> newListOfPieces = new ArrayList<Pair<JigsawPiece, Integer>>();


            for ( Pair<JigsawPiece, Integer>  p : ((ImmutableList<Pair<JigsawPiece, Integer>>) immutablelistOfPair.get(jigsawpattern)).asList()) {
                newListOfPieces.add(p);
            }

            newListOfPieces.add((new Pair<JigsawPiece, Integer>(new SingleJigsawPiece("village/plains/plains_castle/plains_castle"), NUMBER_OF_TRIES)));

            ImmutableList<Pair<JigsawPiece, Integer>> newImmutableListOfPieces = ImmutableList.copyOf(newListOfPieces);

            immutablelistOfPair.set(jigsawpattern, newImmutableListOfPieces);

            for (int i = 0; i < NUMBER_OF_TRIES; i = i + 1) {
                ((ArrayList) listOfPieces.get(jigsawpattern)).add(((new SingleJigsawPiece("village/plains/plains_castle/plains_castle").setPlacementBehaviour(JigsawPattern.PlacementBehaviour.RIGID))));
            }

           // name.set(jigsawpattern, new ResourceLocation("village/plains/plains_castle"));

            System.out.println("list of pieces " + listOfPieces.get(jigsawpattern));
            System.out.println("immutable list " + immutablelistOfPair.get(jigsawpattern));


            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(listOfPieces, listOfPieces.getModifiers() & ~Modifier.FINAL);

            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(immutablelistOfPair, immutablelistOfPair.getModifiers() & ~Modifier.FINAL);

            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(name, name.getModifiers() & ~Modifier.FINAL);




            JigsawManager.REGISTRY.register(jigsawpattern);

            }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }



    }
}

