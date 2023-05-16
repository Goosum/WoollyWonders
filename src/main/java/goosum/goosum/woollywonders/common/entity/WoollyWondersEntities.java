package goosum.goosum.woollywonders.common.entity;

import goosum.goosum.woollywonders.WoollyWonders;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WoollyWonders.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoollyWondersEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WoollyWonders.MODID);

    public static final RegistryObject<EntityType<ExtraWoollySheepEntity>> EXTRA_WOOLLY_SHEEP = ENTITIES.register("extra_woolly_sheep",
            () -> registerEntity(EntityType.Builder.of(ExtraWoollySheepEntity::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10), "extra_woolly_sheep"));

    private static EntityType registerEntity(EntityType.Builder builder, String entityName)
    {
        return builder.build(entityName);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event)
    {
        event.put(EXTRA_WOOLLY_SHEEP.get(), ExtraWoollySheepEntity.createAttributes().build());
    }

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
