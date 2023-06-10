package goosum.goosum.woollywonders.common.block.stuffed_animals;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import org.jetbrains.annotations.Nullable;

import java.util.List;


public class AbstractStuffedAnimalBlock extends HorizontalDirectionalBlock {

    public enum Rarity {
        CHARMING,
        DARLING,
        ADORABLE,
        IRRESISTIBLE
    }

    private Rarity rarity;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public AbstractStuffedAnimalBlock(Properties pProperties) {
        super(pProperties);
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

    public void setRarity(Rarity pRarity) {
        rarity = pRarity;
    }
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {

        switch (rarity) {
            case CHARMING -> {
                pTooltip.add(Component.literal("Charming").withStyle(ChatFormatting.GREEN));
            }
            case DARLING -> {
                pTooltip.add(Component.literal("Darling").withStyle(ChatFormatting.BLUE));
            }
            case ADORABLE -> {
                pTooltip.add(Component.literal("Adorable").withStyle(ChatFormatting.RED));
            }
            case IRRESISTIBLE -> {
                pTooltip.add(Component.literal("Irresistible").withStyle(ChatFormatting.DARK_PURPLE));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}

