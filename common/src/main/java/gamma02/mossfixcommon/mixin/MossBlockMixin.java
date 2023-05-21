package gamma02.mossfixcommon.mixin;

import gamma02.mossfixcommon.MossFix;
import gamma02.mossfixcommon.features.MossWG;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MossBlock.class)
public class MossBlockMixin {


    @Inject(method = "isValidBonemealTarget", at = @At("HEAD"), cancellable = true)
    public void alwaysBonemeal(LevelReader blockGetter, BlockPos blockPos, BlockState blockState, boolean bl, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }

    /**
     * @author gamma_02
     * @reason to make the mod work lol
     */
    @Overwrite
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        //FOR 1.19.2 AND BELOW:
//        (MossWG.CONFIGURED_MOSS_BONEMEAL.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above());
        //1.19.3 AND ABOVE(PROBABLY):
//        serverLevel.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap(arg -> arg.getHolder(MossWG.CONFIGURED_MOSS_BONEMEAL))
//                .ifPresent(arg4 -> (arg4.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above()));
        MossFix.FIXED_MOSS_BONEMEAL.get().place(MossFix.MOSS_CONFIG, serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above());
    }
}
