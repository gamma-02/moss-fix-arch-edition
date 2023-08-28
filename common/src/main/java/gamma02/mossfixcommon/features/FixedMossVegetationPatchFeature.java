package gamma02.mossfixcommon.features;


import net.minecraft.world.level.levelgen.feature.Feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class FixedMossVegetationPatchFeature extends Feature<VegetationPatchConfiguration> {
    public FixedMossVegetationPatchFeature(Codec<VegetationPatchConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<VegetationPatchConfiguration> context) {
        WorldGenLevel WorldGenLevel = context.level();
        VegetationPatchConfiguration VegetationPatchConfiguration = (VegetationPatchConfiguration)context.config();
        RandomSource random = context.random();
        BlockPos blockPos = context.origin();
        Predicate<BlockState> predicate = (state) -> {
            return state.is(VegetationPatchConfiguration.replaceable);
        };
        int xRadius = VegetationPatchConfiguration.xzRadius.sample(random) + 1;
        int zRaidus = VegetationPatchConfiguration.xzRadius.sample(random) + 1;
        Set<BlockPos> set = this.placeGroundAndGetPositions(WorldGenLevel, VegetationPatchConfiguration, random, blockPos, predicate, xRadius, zRaidus);
        this.generateVegetation(context, WorldGenLevel, VegetationPatchConfiguration, random, set, xRadius, zRaidus);
        return !set.isEmpty();
    }

    protected Set<BlockPos> placeGroundAndGetPositions(WorldGenLevel world, VegetationPatchConfiguration config, RandomSource random, BlockPos pos, Predicate<BlockState> replaceable, int radiusX, int radiusZ) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        BlockPos.MutableBlockPos mutable2 = mutable.mutable();
        Direction direction = config.surface.getDirection();
        Direction direction2 = direction.getOpposite();
        Set<BlockPos> set = new HashSet();

        for(int xRaidusIterator = -radiusX; xRaidusIterator <= radiusX; ++xRaidusIterator) {
            boolean reachedEndOrBeginnnng = xRaidusIterator == -radiusX || xRaidusIterator == radiusX;

            for(int zRaidusIterator = -radiusZ; zRaidusIterator <= radiusZ; ++zRaidusIterator) {
                boolean bl2 = zRaidusIterator == -radiusZ || zRaidusIterator == radiusZ;
                boolean bl3 = reachedEndOrBeginnnng || bl2;
                boolean bl4 = reachedEndOrBeginnnng && bl2;
                boolean bl5 = bl3 && !bl4;
                if (!bl4 && (!bl5 || config.extraEdgeColumnChance != 0.0F && !(random.nextFloat() > config.extraEdgeColumnChance))) {
                    mutable.setWithOffset(pos, xRaidusIterator, 0, zRaidusIterator);

                    int k;
                    for(k = 0; canGenerateUnderBlock(world, mutable) && k < config.verticalRange; ++k) {
                        mutable.move(direction);
                    }

                    for(k = 0; !canGenerateUnderBlock(world, mutable) && k < config.verticalRange; ++k) {
                        mutable.move(direction2);
                    }

                    mutable2.setWithOffset(mutable, (Direction)config.surface.getDirection());
                    BlockState blockState = world.getBlockState(mutable2);
                    if (
                            canGenerateUnderBlock(world, mutable) &&
                                    /*blockState.isSideSolidFullSquare(world, mutable2, config.surface.getDirection().getOpposite())*/ true) {
                        int l = config.depth.sample(random) + (config.extraBottomBlockChance > 0.0F && random.nextFloat() < config.extraBottomBlockChance ? 1 : 0);
                        BlockPos blockPos = mutable2.immutable();
                        boolean bl6 = this.placeGround(world, config, replaceable, random, mutable2, l);
                        if (bl6) {
                            set.add(blockPos);
                        }
                    }
                }
            }
        }

        return set;
    }

    protected void generateVegetation(FeaturePlaceContext<VegetationPatchConfiguration> context, WorldGenLevel world, VegetationPatchConfiguration config, RandomSource random, Set<BlockPos> positions, int radiusX, int radiusZ) {
        Iterator var8 = positions.iterator();

        while(var8.hasNext()) {
            BlockPos blockPos = (BlockPos)var8.next();
            if (config.vegetationChance > 0.0F && random.nextFloat() < config.vegetationChance) {
                this.generateVegetationFeature(world, config, context.chunkGenerator(), random, blockPos);
            }
        }

    }

    protected boolean generateVegetationFeature(WorldGenLevel world, VegetationPatchConfiguration config, ChunkGenerator generator, RandomSource random, BlockPos pos) {
        if(world.getBlockState(pos.relative(config.surface.getDirection().getOpposite())).isAir()) {
            return ((PlacedFeature) config.vegetationFeature.value()).place(world, generator, random, pos.relative(config.surface.getDirection().getOpposite()));
        }
        return false;
    }

    protected boolean placeGround(WorldGenLevel world, VegetationPatchConfiguration config, Predicate<BlockState> replaceable, RandomSource random, BlockPos.MutableBlockPos pos, int depth) {
        for(int i = 0; i < depth; ++i) {
            BlockState blockState = config.groundState.getState(random, pos);
            BlockState blockState2 = world.getBlockState(pos);
            if (!blockState.is(blockState2.getBlock())) {
                if (!replaceable.test(blockState2)) {
                    return i != 0;
                }

                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                pos.move(config.surface.getDirection());
            }
        }

        return true;
    }

    private static boolean canGenerateUnderBlock(BlockGetter world, BlockPos blockPos) {
        return !world.getBlockState(blockPos).isRedstoneConductor(world, blockPos);
    }
}
