package gamma02.mossfixcommon.fabric;

import gamma02.mossfixcommon.fabriclike.MossFixFabricLike;
import net.fabricmc.api.ModInitializer;

public class MossFixFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MossFixFabricLike.init();
    }
}
