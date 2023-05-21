package gamma02.mossfixcommon.features;

import gamma02.mossfixcommon.MossFix;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MossWG {

    public static ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_MOSS_BONEMEAL = FeatureUtils.createKey(MossFix.MOD_ID + ":configured_moss_bonemeal");

    public static void init() {
    }
//    public static Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> CONFIGURED_MOSS_BONEMEAL;


}
