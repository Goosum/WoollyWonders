
package goosum.goosum.woollywonders.common.block.entity;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WoollyWondersBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WoollyWonders.MODID);

    public static final RegistryObject<BlockEntityType<WoollyWorkshopBlockEntity>> WOOLLY_WORKSHOP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("woolly_workshop", () ->
                    BlockEntityType.Builder.of(WoollyWorkshopBlockEntity::new, WoollyWondersBlocks.WOOLLY_WORKSHOP.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }


}
