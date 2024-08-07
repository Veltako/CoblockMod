package net.veltako.coblockmod.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CoblockMod.MOD_ID);


    public static final RegistryObject<PlacedFeature> COBLOCK_ORE_PLACED = PLACED_FEATURES.register("coblock_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.COBLOCK_ORE.getHolder().get(),
                    commonOrePlacement(1, // Veines Par Chunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32))))
    );

    public static final RegistryObject<PlacedFeature> END_COBLOCK_ORE_PLACED = PLACED_FEATURES.register("end_coblock_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.END_COBLOCK_ORE.getHolder().get(), commonOrePlacement(1, // Veines Par Chunk
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))))
    );

    public static final RegistryObject<PlacedFeature> NETHER_COBLOCK_ORE_PLACED = PLACED_FEATURES.register("nether_coblock_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_COBLOCK_ORE.getHolder().get(), commonOrePlacement(1, // Veines Par Chunk
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(4), VerticalAnchor.absolute(124))))
    );

    public static final RegistryObject<PlacedFeature> NETHER_PHENICIUM_ORE_PLACED = PLACED_FEATURES.register("nether_phenicium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_PHENICIUM_ORE.getHolder().get(), commonOrePlacement(2, // Veines Par Chunk
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(4), VerticalAnchor.absolute(124))))
    );

    public static final RegistryObject<PlacedFeature> ELEKIUM_ORE_PLACED = PLACED_FEATURES.register("elekium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ELEKIUM_ORE.getHolder().get(), commonOrePlacement(3, // Veines Par Chunk
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(-10))))
    );

    public static final RegistryObject<PlacedFeature> NETHER_AGRIUM_ORE_PLACED = PLACED_FEATURES.register("nether_agrium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_AGRIUM_ORE.getHolder().get(), commonOrePlacement(5, // Veines Par Chunk
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(4), VerticalAnchor.absolute(124))))
    );


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}