package gamma02.mossfixcommon.mixin;

//import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CaveFeatures.class)
public class CaveFeaturesMixin {

//    @Redirect(method = "bootstrap", at = @At(value="INVOKE", ordinal = 12 , target="Lnet/minecraft/data/worldgen/features/FeatureUtils;register(Lnet/minecraft/data/worldgen/BootstapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V"))
//    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration) {
//        System.out.println("RESOURCE KEY IS " + resourceKey.location());
//        bootstapContext.register(resourceKey, new ConfiguredFeature(feature, featureConfiguration));
//    }

}
