package goosum.goosum.woollywonders.common.item;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.WoollyWondersCreativeModeTab;
import goosum.goosum.woollywonders.common.entity.WoollyWondersEntities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WoollyWondersItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WoollyWonders.MODID);

    public static final RegistryObject<Item> WOOLLY_CLUMP = ITEMS.register("woolly_clump",
            () -> new WoollyClumpItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> EXTRA_WOOLLY_SHEEP_SPAWN_EGG = ITEMS.register("extra_woolly_sheep_spawn_egg",
            () -> new ForgeSpawnEggItem(WoollyWondersEntities.EXTRA_WOOLLY_SHEEP, 0xdedede, 0xe39191,
            new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));

    public static final RegistryObject<Item> CHARMING_STUFFED_ANIMAL_TEMPLATE = ITEMS.register("charming_stuffed_animal_template",
            () -> new Item(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> DARLING_STUFFED_ANIMAL_TEMPLATE = ITEMS.register("darling_stuffed_animal_template",
            () -> new Item(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> ADORABLE_STUFFED_ANIMAL_TEMPLATE = ITEMS.register("adorable_stuffed_animal_template",
            () -> new Item(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> IRRESISTIBLE_STUFFED_ANIMAL_TEMPLATE = ITEMS.register("irresistible_stuffed_animal_template",
            () -> new Item(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> MOB_ESSENCE = ITEMS.register("mob_essence",
            () -> new Item(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
