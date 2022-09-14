package gamma02.mossfixcommon.mixin_common;

import gamma02.mossfixcommon.features.MossWG;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MossBlock.class)
public class MossBlockMixin {


    @Inject(method = "isValidBonemealTarget", at = @At("HEAD"), cancellable = true)
    public void alwaysBonemeal(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean bl, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }

    /**
     * @author gamma_02
     * @reason to make the mod work lol
     */
    @Overwrite
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        (MossWG.CONFIGURED_MOSS_BONEMEAL.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above());
    }
}
