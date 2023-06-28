package goosum.goosum.woollywonders.common.util;

import goosum.goosum.woollywonders.WoollyWonders;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class WoollyWondersTags {
    public static class Blocks {
        public static final TagKey<Block> STUFFED_WOOL
                = tag("stuffed_wool");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(WoollyWonders.MODID, name));
    }

    }

    public static class Items {
        public static final TagKey<Item> STUFFED_WOOL
                = tag("stuffed_wool");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(WoollyWonders.MODID, name));
        }
    }
}
