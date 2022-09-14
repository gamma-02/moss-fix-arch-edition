package gamma02.mossfixcommon;

import com.google.common.base.Suppliers;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import gamma02.mossfixcommon.features.FixedMossVegetationPatchFeature;
import gamma02.mossfixcommon.features.MossWG;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

import java.util.function.Supplier;

public class MossFix {
    public static final String MOD_ID = "mossfixcommon";

    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
    public static final Registrar<Feature<?>> FEATURES = REGISTRIES.get().get(Registry.FEATURE_REGISTRY);

    public static RegistrySupplier<FixedMossVegetationPatchFeature> FIXED_MOSS_BONEMEAL = FEATURES.register(new ResourceLocation(MOD_ID, "fixed_moss_bonemeal"), () -> new FixedMossVegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static void init() {

        LifecycleEvent.SETUP.register(() ->{
            MossWG.init();


        });

    }
}