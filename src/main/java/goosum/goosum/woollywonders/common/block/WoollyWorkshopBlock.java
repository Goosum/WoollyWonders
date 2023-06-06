package goosum.goosum.woollywonders.common.block;

import goosum.goosum.woollywonders.common.block.entity.WoollyWondersBlockEntities;
import goosum.goosum.woollywonders.common.block.entity.WoollyWorkshopBlockEntity;
import goosum.goosum.woollywonders.common.screen.WoollyWorkshopMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class WoollyWorkshopBlock extends BaseEntityBlock {

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

    // BLOCK ENTITY


    @Override
    public RenderShape getRenderShape(BlockState pBlockState) {
        return RenderShape.MODEL;
    }

    /* If the BlockState is not equal to the other one(?) and the Block is an instance of our Block Entity call the drops method
    * to drop the contents of the inventory. */
    @Override
    public void onRemove(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, BlockState pNewBlockState, boolean pIsMoving) {
        if (pBlockState.getBlock() != pNewBlockState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pBlockPos);
            if(blockEntity instanceof WoollyWorkshopBlockEntity) {
                ((WoollyWorkshopBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pBlockState, pLevel, pBlockPos, pNewBlockState, pIsMoving);
    }


    /* If the level is not client side and the block is an instance of our BlockEntity than it will have the client open a screen for
    * the player at that block position. Otherwise, it will throw an error. */
    @Override
    public InteractionResult use(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer, InteractionHand pInteractionHand, BlockHitResult pBlockHitResult) {
        if(!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pBlockPos);
            if(blockEntity instanceof WoollyWorkshopBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, (WoollyWorkshopBlockEntity) blockEntity, pBlockPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    /* Tell the game this block is a new instance of the WoollyWorkshopBlockEntity. */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pBlockState, BlockState pBlockPos) {
        return new WoollyWorkshopBlockEntity(pBlockState, pBlockPos);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pBlockState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, WoollyWondersBlockEntities.WOOLLY_WORKSHOP_BLOCK_ENTITY.get(),
                WoollyWorkshopBlockEntity::tick);
    }
}
