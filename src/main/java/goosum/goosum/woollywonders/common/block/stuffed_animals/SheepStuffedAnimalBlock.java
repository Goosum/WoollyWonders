package goosum.goosum.woollywonders.common.block.stuffed_animals;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SheepStuffedAnimalBlock extends AbstractStuffedAnimalBlock {

    public SheepStuffedAnimalBlock(Properties pProperties) {
        super(pProperties);
        setRarity(Rarity.CHARMING);
    }

    private static final VoxelShape SHAPE = Block.box(5, 0,5, 11, 11, 11);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }
}
