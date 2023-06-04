package goosum.goosum.woollywonders.common.block;

import goosum.goosum.woollywonders.common.screen.WoollyWorkshopMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WoollyWorkshopBlock extends Block {

    private static final Component CONTAINER_TITLE = Component.translatable("container.woollywonders.woollyworkshop");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.box(1, 0,1, 15, 15, 15);


    protected WoollyWorkshopBlock(Properties p_54120_) {
        super(p_54120_);
    }

    // Placement
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.setValue(FACING, pMirror.getRotation(pState.getValue(FACING)).rotate(pState.getValue(FACING)));
    }

    // Usage/Menu


    @Override
    public InteractionResult use(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer, InteractionHand pInteractionHand, BlockHitResult pBlockHitResult) {
        if(pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pBlockState.getMenuProvider(pLevel, pBlockPos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState pBlockState, Level pLevel, BlockPos pBlockPos) {
        return new SimpleMenuProvider((pInt, pInventory, pPlayer) -> new WoollyWorkshopMenu(pInt, pInventory, ContainerLevelAccess.create(pLevel, pBlockPos)), CONTAINER_TITLE);
    }


}
