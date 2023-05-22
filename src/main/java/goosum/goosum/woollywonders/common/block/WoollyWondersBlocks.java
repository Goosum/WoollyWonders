package goosum.goosum.woollywonders.common.block;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.WoollyWondersCreativeModeTab;
import goosum.goosum.woollywonders.common.block.stuffed_animals.SheepStuffedAnimalBlock;
import goosum.goosum.woollywonders.common.item.WoollyWondersItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class WoollyWondersBlocks {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WoollyWonders.MODID);

    public static final RegistryObject<Block> STUFFED_WHITE_WOOL = registerBlock("stuffed_white_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.SNOW).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_ORANGE_WOOL = registerBlock("stuffed_orange_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_ORANGE).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_MAGENTA_WOOL = registerBlock("stuffed_magenta_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_MAGENTA).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_LIGHT_BLUE_WOOL = registerBlock("stuffed_light_blue_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_BLUE).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_YELLOW_WOOL = registerBlock("stuffed_yellow_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_YELLOW).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_LIME_WOOL = registerBlock("stuffed_lime_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GREEN).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_PINK_WOOL = registerBlock("stuffed_pink_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_PINK).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_GRAY_WOOL = registerBlock("stuffed_gray_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_GRAY).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_LIGHT_GRAY_WOOL = registerBlock("stuffed_light_gray_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GRAY).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_CYAN_WOOL = registerBlock("stuffed_cyan_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_CYAN).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_PURPLE_WOOL = registerBlock("stuffed_purple_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_PURPLE).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_BLUE_WOOL = registerBlock("stuffed_blue_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_BLUE).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_BROWN_WOOL = registerBlock("stuffed_brown_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_BROWN).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_GREEN_WOOL = registerBlock("stuffed_green_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_GREEN).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_RED_WOOL = registerBlock("stuffed_red_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> STUFFED_BLACK_WOOL = registerBlock("stuffed_black_wool",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_BLACK).strength(0.8F).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> SHEEP_STUFFED_ANIMAL = registerBlock("sheep_stuffed_animal",
            () -> new SheepStuffedAnimalBlock(BlockBehaviour.Properties.of(Material.WOOL)
                    .strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Block> WOOLLY_WORKSHOP = registerBlock("woolly_workshop",
            () -> new WoollyWorkshopBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2.0F).sound(SoundType.WOOD).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return WoollyWondersItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(WoollyWondersCreativeModeTab.WOOLLY_WONDERS_TAB)));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
