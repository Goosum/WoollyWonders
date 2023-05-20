package goosum.goosum.woollywonders.common.item;

import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import goosum.goosum.woollywonders.common.entity.WoollyWondersEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WoollyClumpItem extends Item {

    public WoollyClumpItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pItemStack, Player pPlayer, LivingEntity pLivingEntity, InteractionHand pInteractionHand) {
        if(pLivingEntity instanceof Sheep pSheep) {
            if(pSheep.isAlive() && !(pSheep instanceof ExtraWoollySheepEntity)) {
                if(!pPlayer.level.isClientSide && pInteractionHand == InteractionHand.MAIN_HAND) {
                    pSheep.level.playSound(pPlayer, pSheep, SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    convertSheep(pSheep, pPlayer);
                    pItemStack.shrink(1);
                }
                return InteractionResult.sidedSuccess(pPlayer.level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }


    private void convertSheep(LivingEntity pEntity, Player pPlayer) {
        if (pEntity instanceof Sheep pSheep && pSheep.isAlive() && !pPlayer.level.isClientSide) {
            DyeColor sheepColor = pSheep.getColor();
            float sheepXRot = pSheep.getXRot();
            float sheepYRot = pSheep.getXRot();
            float sheepYHeadRot = pSheep.getYHeadRot();
            ExtraWoollySheepEntity woollySheep = pSheep.convertTo(WoollyWondersEntities.EXTRA_WOOLLY_SHEEP.get(), false);
            woollySheep.setColor(sheepColor);
            woollySheep.setXRot(sheepXRot);
            woollySheep.setYBodyRot(sheepYRot);
            woollySheep.setYHeadRot(sheepYHeadRot);
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(pSheep, woollySheep);
            woollySheep.level.broadcastEntityEvent(pSheep, (byte) 4);
        }
    }

}
