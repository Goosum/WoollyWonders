package goosum.goosum.woollywonders.common.block.stuffed_animals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ZombieStuffedAnimalBlock extends AbstractStuffedAnimalBlock {

    public ZombieStuffedAnimalBlock(Properties pProperties) {
        super(pProperties);
        setRarity(Rarity.CHARMING);
    }

    private static final VoxelShape SHAPE = Block.box(6, 0,6, 10, 10, 10);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }
}
