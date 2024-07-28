package net.veltako.coblockmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.veltako.coblockmod.CoblockMod;
import net.veltako.coblockmod.block.AgriumBlocks;
import net.veltako.coblockmod.block.CoblockBlocks;
import net.veltako.coblockmod.block.ElekiumBlocks;
import net.veltako.coblockmod.block.PheniciumBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, CoblockMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_COBLOCK_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, CoblockBlocks.COBLOCK_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, CoblockBlocks.DEEPSLATE_COBLOCK_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_COBLOCK_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), CoblockBlocks.END_STONE_COBLOCK_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_COBLOCK_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, CoblockBlocks.NETHER_COBLOCK_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_PHENICIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, PheniciumBlocks.NETHER_PHENICIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ELEKIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ElekiumBlocks.ELEKIUM_BLOCK.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ElekiumBlocks.DEEPSLATE_ELEKIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_AGRIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, AgriumBlocks.NETHER_AGRIUM_ORE.get().defaultBlockState())));





    public static final RegistryObject<ConfiguredFeature<?, ?>> COBLOCK_ORE = CONFIGURED_FEATURES.register("coblock_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_COBLOCK_ORES.get(),1 //taille de la veine
            )));

    public static final RegistryObject<ConfiguredFeature<?, ?>> END_COBLOCK_ORE = CONFIGURED_FEATURES.register("end_coblock_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_COBLOCK_ORES.get(), 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_COBLOCK_ORE = CONFIGURED_FEATURES.register("nether_coblock_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_COBLOCK_ORES.get(), 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_PHENICIUM_ORE = CONFIGURED_FEATURES.register("nether_phenicium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_PHENICIUM_ORES.get(), 2)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ELEKIUM_ORE = CONFIGURED_FEATURES.register("elekium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ELEKIUM_ORES.get(),2)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_AGRIUM_ORE = CONFIGURED_FEATURES.register("nether_agrium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_AGRIUM_ORES.get(),6)));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}