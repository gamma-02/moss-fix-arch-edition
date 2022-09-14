package gamma02.mossfixcommon.forge;

import dev.architectury.platform.forge.EventBuses;
import gamma02.mossfixcommon.MossFix;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MossFix.MOD_ID)
public class MossFixForge {
    public MossFixForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(MossFix.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        MossFix.init();
    }
}