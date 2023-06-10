
package goosum.goosum.woollywonders.common.block.entity;

import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import goosum.goosum.woollywonders.common.item.WoollyWondersItems;
import goosum.goosum.woollywonders.common.recipe.WoollyWorkshopRecipe;
import goosum.goosum.woollywonders.common.screen.WoollyWondersMenus;
import goosum.goosum.woollywonders.common.screen.WoollyWorkshopMenu;
import goosum.goosum.woollywonders.util.WoollyWondersTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WoollyWorkshopBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    /* Lazy because block inventory may never be used. Optional is a container that might be non-null and should be used as a method return
    * type. No variable of Optional type should be null. It should point to a different Optional variable value. */
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();


    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;


    public WoollyWorkshopBlockEntity(BlockPos pBlockPos, BlockState pBlockState) {
        super(WoollyWondersBlockEntities.WOOLLY_WORKSHOP_BLOCK_ENTITY.get(), pBlockPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                  case 0 -> WoollyWorkshopBlockEntity.this.progress;
                  case 1 -> WoollyWorkshopBlockEntity.this.maxProgress;
                  default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> WoollyWorkshopBlockEntity.this.progress = pValue;
                    case 1 -> WoollyWorkshopBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.woollywonders.woollyworkshop");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pId, Inventory pInventory, Player pPlayer) {
        return new WoollyWorkshopMenu(pId, pInventory, this, this.data);
    }

    /* If the capability present is of the basic Forge item handler type then return our lazyItemHandler which is of
    * implied type IItemHandler. Then call the super getCapability function. */

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    /* On chunk loaded. Set the lazyItemHandler to the value of our block inventory represented by itemHandler. */
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    /* Prevents getCapability from returning a value and invalidates all the contained Capabilities. Generally called
    * when said Capabilities are removed from the world. */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    /* Breaking down data to save it. Saving the NBT data of the block inventory under the tag "inventory". */
    @Override
    protected void saveAdditional(CompoundTag pNbt) {
        pNbt.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pNbt);
    }

    /* Load block NBT data from a CompoundTag parameter. deserialize the previously serialized nbt data using the "inventory" key. */
    @Override
    public void load(CompoundTag pNbt) {
        super.load(pNbt);
        itemHandler.deserializeNBT(pNbt.getCompound("inventory"));
    }


    /* Create an empty SimpleContainer that is the same size as our itemHandler inventory. for every slot get the correct item in
    * said slot and then drop the contents of the inventory object at the end. */
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private void craftItem(WoollyWorkshopBlockEntity pBlockEntity) {
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());

        for(int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }


        Optional<WoollyWorkshopRecipe> recipe =
                level.getRecipeManager().getRecipeFor(WoollyWorkshopRecipe.Type.INSTANCE, inventory, level);

        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.itemHandler.extractItem(0, 1, false);
            pBlockEntity.itemHandler.extractItem(1, 1, false);
            pBlockEntity.itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(),
                    pBlockEntity.itemHandler.getStackInSlot(2).getCount() + 1));
        }

        pBlockEntity.resetProgress();
    }


    /* Checks if BlockEntity has a valid recipe. Checks all slots and then checks if slot 1 is stuffed white wool and then checks if slot 3
    * is a valid location to insert sheep stuffed animal. */
    private boolean hasRecipe(WoollyWorkshopBlockEntity pBlockEntity) {
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());

        for(int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }

        Optional<WoollyWorkshopRecipe> recipe =
                level.getRecipeManager().getRecipeFor(WoollyWorkshopRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() &&
                canInsertAmountIntoOutput(inventory) && canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private boolean canInsertItemIntoOutputSlot(SimpleContainer pInventory, ItemStack pItemStack) {
        return pInventory.getItem(2).getItem() == pItemStack.getItem() || pInventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutput(SimpleContainer pInventory) {
        return pInventory.getItem(2).getMaxStackSize() > pInventory.getItem(2).getCount();
    }

    // Every tick checks if a valid recipe is in the block entity. If so, crafts the item.


    public static void tick(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, WoollyWorkshopBlockEntity pBlockEntity) {
        if(pLevel.isClientSide()) {
            return;
        }
        if(pBlockEntity.hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pBlockPos, pBlockState);

            if(pBlockEntity.progress > 0 && pBlockEntity.progress <= pBlockEntity.maxProgress && pBlockEntity.progress % 5 == 0) {
                    pLevel.playSound(null, pBlockPos, SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                pBlockEntity.craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pBlockPos, pBlockState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
