package gamma02.mossfixcommon;

import com.google.common.base.Suppliers;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
//import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import gamma02.mossfixcommon.features.FixedMossVegetationPatchFeature;
import gamma02.mossfixcommon.features.MossWG;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.function.Supplier;

import static net.minecraft.data.worldgen.features.CaveFeatures.MOSS_VEGETATION;

public class MossFix {
    public static final String MOD_ID = "mossfixcommon";

    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
    public static final Registrar<Feature<?>> FEATURES = REGISTRIES.get().get(Registries.FEATURE);

    public static RegistrySupplier<FixedMossVegetationPatchFeature> FIXED_MOSS_BONEMEAL = FEATURES.register(new ResourceLocation(MOD_ID, "fixed_moss_bonemeal"), () -> new FixedMossVegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    //Jesus Christ the Lord Almighty bless this code, may it always work and never cause issues. Amen. - gamma
    public static VegetationPatchConfiguration MOSS_CONFIG =  new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(VanillaRegistries.createLookup().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MOSS_VEGETATION), new PlacementModifier[0]), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F);

    public static void init() {

        LifecycleEvent.SETUP.register(() ->{
            MossWG.init();


        });

    }
}
