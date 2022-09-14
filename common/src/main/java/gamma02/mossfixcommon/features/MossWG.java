package gamma02.mossfixcommon.features;

import gamma02.mossfixcommon.MossFix;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import static gamma02.mossfixcommon.MossFix.MOD_ID;
import static net.minecraft.data.worldgen.features.CaveFeatures.MOSS_VEGETATION;

public class MossWG {

    public static Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> CONFIGURED_MOSS_BONEMEAL = FeatureUtils.register(MOD_ID + "configured_moss_bonemeal", MossFix.FIXED_MOSS_BONEMEAL.get(), new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(MOSS_VEGETATION, new PlacementModifier[0]), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));

    public static void init(){}

}
