package goosum.goosum.woollywonders.common.screen;

import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import goosum.goosum.woollywonders.common.recipe.WoollyWondersRecipeTypes;
import goosum.goosum.woollywonders.common.recipe.WoollyWorkshopRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.commons.compress.utils.Lists;

import java.util.List;


public class WoollyWorkshopMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess access;
    private final Level level;
    final Slot inputSlot;
    final Slot outputSlot;
    private List<WoollyWorkshopRecipe> recipes = Lists.newArrayList();
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    long lastSoundTime;

    Runnable slotUpdateListener = () -> {};

    public final SimpleContainer container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            WoollyWorkshopMenu.this.slotsChanged(this);
            WoollyWorkshopMenu.this.slotUpdateListener.run();
        }
    };

    final ResultContainer resultContainer = new ResultContainer();

    public WoollyWorkshopMenu(int i, Inventory pInventory, FriendlyByteBuf pByteBuf) {
        this(i, pInventory, ContainerLevelAccess.NULL);
    }

    public WoollyWorkshopMenu(int pInt1, Inventory pInventory, final ContainerLevelAccess pContainerLevelAccess) {
        super(WoollyWondersMenus.WOOLLY_WORKSHOP_MENU.get(), pInt1);
        this.access = pContainerLevelAccess;
        this.level = pInventory.player.level;
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.outputSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack pItemStack) {
                return false;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pItemStack) {
                pItemStack.onCraftedBy(pPlayer.level, pPlayer, pItemStack.getCount());
                WoollyWorkshopMenu.this.resultContainer.awardUsedRecipes(pPlayer);
                ItemStack itemStack = WoollyWorkshopMenu.this.inputSlot.remove(1);
                if(!itemStack.isEmpty()) {
                    WoollyWorkshopMenu.this.setupResultSlot();
                }

                pContainerLevelAccess.execute((pLevel, pBlockPos) -> {
                    long l = pLevel.getGameTime();
                    if(WoollyWorkshopMenu.this.lastSoundTime != l) {
                        pLevel.playSound(null, pBlockPos, SoundEvents.UI_LOOM_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        WoollyWorkshopMenu.this.lastSoundTime = l;
                    }
                });
                super.onTake(pPlayer, pItemStack);
            }
        });
        //Fix Slot Pos
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(pInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        //Fix Slot Pos
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(pInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }


    private void setupResultSlot() {
        if(!this.recipes.isEmpty() && this.isValidRecipedIndex(this.selectedRecipeIndex.get())) {
            WoollyWorkshopRecipe woollyWorkshopRecipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.resultContainer.setRecipeUsed(woollyWorkshopRecipe);
            this.outputSlot.set(woollyWorkshopRecipe.assemble(this.container));
        }  else {
            this.outputSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    private boolean isValidRecipedIndex(int i) {
        return i >= 0 && i < this.recipes.size();
    }


    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pInt1) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pInt1);
        if(slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            Item item = itemStack1.getItem();
            itemStack = itemStack1.copy();
            if(pInt1 == 1) {
                item.onCraftedBy(itemStack1, pPlayer.level, pPlayer);
                if(!this.moveItemStackTo(itemStack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack1, itemStack);
            } else if(pInt1 == 0) {
                if(!this.moveItemStackTo(itemStack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if(this.level.getRecipeManager().getRecipeFor(WoollyWondersRecipeTypes.WOOLLY_WORKSHOPPING.get(), new SimpleContainer(itemStack1), this.level).isPresent()) {
                if(!this.moveItemStackTo(itemStack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if(pInt1 >= 2 && pInt1 < 29) {
                if(!this.moveItemStackTo(itemStack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if(pInt1 >= 29 && pInt1 < 38 && !this.moveItemStackTo(itemStack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if(itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemStack1);
            this.broadcastChanges();
        }

        return itemStack;
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((Level pLevel, BlockPos pBlockPos) -> {
            this.clearContainer(pPlayer, this.container);
        });
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, WoollyWondersBlocks.WOOLLY_WORKSHOP.get());
    }

    public void registerUpdateListener(Runnable pRunnable) {
        this.slotUpdateListener = pRunnable;
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.recipes.isEmpty();
    }

    public List<WoollyWorkshopRecipe> getRecipes() {
        return this.recipes;
    }

    public int getNumRecipes() {
        return this.recipes.size();
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }
}
