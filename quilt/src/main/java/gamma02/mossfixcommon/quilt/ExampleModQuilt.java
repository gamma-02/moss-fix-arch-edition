package gamma02.mossfixcommon.quilt;

import gamma02.mossfixcommon.fabriclike.MossFixFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ExampleModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        MossFixFabricLike.init();
    }
}
