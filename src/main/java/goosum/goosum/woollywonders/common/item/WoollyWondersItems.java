package goosum.goosum.woollywonders.common.item;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.WoollyWondersCreativeModeTab;
import goosum.goosum.woollywonders.common.entity.WoollyWondersEntities;
import goosum.goosum.woollywonders.common.item.mob_essence.*;
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

    public static final RegistryObject<Item> CHICKEN_SMALL_MOB_ESSENCE = ITEMS.register("chicken_small_mob_essence",
            () -> new ChickenMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> COD_SMALL_MOB_ESSENCE = ITEMS.register("cod_small_mob_essence",
            () -> new CodMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> COW_SMALL_MOB_ESSENCE = ITEMS.register("cow_small_mob_essence",
            () -> new CowMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> CREEPER_SMALL_MOB_ESSENCE = ITEMS.register("creeper_small_mob_essence",
            () -> new CreeperMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> FOX_SMALL_MOB_ESSENCE = ITEMS.register("fox_small_mob_essence",
            () -> new FoxMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> HORSE_SMALL_MOB_ESSENCE = ITEMS.register("horse_small_mob_essence",
            () -> new HorseMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> OCELOT_SMALL_MOB_ESSENCE = ITEMS.register("ocelot_small_mob_essence",
            () -> new OcelotMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> PIG_SMALL_MOB_ESSENCE = ITEMS.register("pig_small_mob_essence",
            () -> new PigMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> RABBIT_SMALL_MOB_ESSENCE = ITEMS.register("rabbit_small_mob_essence",
            () -> new RabbitMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SALMON_SMALL_MOB_ESSENCE = ITEMS.register("salmon_small_mob_essence",
            () -> new SalmonMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SHEEP_SMALL_MOB_ESSENCE = ITEMS.register("sheep_small_mob_essence",
            () -> new SheepMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SKELETON_SMALL_MOB_ESSENCE = ITEMS.register("skeleton_small_mob_essence",
            () -> new SkeletonMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SPIDER_SMALL_MOB_ESSENCE = ITEMS.register("spider_small_mob_essence",
            () -> new SpiderMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SQUID_SMALL_MOB_ESSENCE = ITEMS.register("squid_small_mob_essence",
            () -> new SquidMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> WOLF_SMALL_MOB_ESSENCE = ITEMS.register("wolf_small_mob_essence",
            () -> new WolfMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> ZOMBIE_SMALL_MOB_ESSENCE = ITEMS.register("zombie_small_mob_essence",
            () -> new ZombieMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));

    public static final RegistryObject<Item> CHICKEN_LARGE_MOB_ESSENCE = ITEMS.register("chicken_large_mob_essence",
            () -> new ChickenMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> COD_LARGE_MOB_ESSENCE = ITEMS.register("cod_large_mob_essence",
            () -> new CodMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> COW_LARGE_MOB_ESSENCE = ITEMS.register("cow_large_mob_essence",
            () -> new CowMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> CREEPER_LARGE_MOB_ESSENCE = ITEMS.register("creeper_large_mob_essence",
            () -> new CreeperMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> FOX_LARGE_MOB_ESSENCE = ITEMS.register("fox_large_mob_essence",
            () -> new FoxMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> HORSE_LARGE_MOB_ESSENCE = ITEMS.register("horse_large_mob_essence",
            () -> new HorseMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> OCELOT_LARGE_MOB_ESSENCE = ITEMS.register("ocelot_large_mob_essence",
            () -> new OcelotMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> PIG_LARGE_MOB_ESSENCE = ITEMS.register("pig_large_mob_essence",
            () -> new PigMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> RABBIT_LARGE_MOB_ESSENCE = ITEMS.register("rabbit_large_mob_essence",
            () -> new RabbitMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SALMON_LARGE_MOB_ESSENCE = ITEMS.register("salmon_large_mob_essence",
            () -> new SalmonMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SHEEP_LARGE_MOB_ESSENCE = ITEMS.register("sheep_large_mob_essence",
            () -> new SheepMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SKELETON_LARGE_MOB_ESSENCE = ITEMS.register("skeleton_large_mob_essence",
            () -> new SkeletonMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SPIDER_LARGE_MOB_ESSENCE = ITEMS.register("spider_large_mob_essence",
            () -> new SpiderMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> SQUID_LARGE_MOB_ESSENCE = ITEMS.register("squid_large_mob_essence",
            () -> new SquidMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> WOLF_LARGE_MOB_ESSENCE = ITEMS.register("wolf_large_mob_essence",
            () -> new WolfMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    public static final RegistryObject<Item> ZOMBIE_LARGE_MOB_ESSENCE = ITEMS.register("zombie_large_mob_essence",
            () -> new ZombieMobEssenceItem(new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
