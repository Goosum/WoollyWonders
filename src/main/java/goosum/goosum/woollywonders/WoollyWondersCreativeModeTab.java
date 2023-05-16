package goosum.goosum.woollywonders;

import goosum.goosum.woollywonders.common.item.WoollyWondersItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WoollyWonders.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoollyWondersCreativeModeTab extends CreativeModeTab{


    public WoollyWondersCreativeModeTab(int i, String s) {
        super(i, s);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(WoollyWondersItems.WOOLLY_CLUMP.get());
    }

    public static final CreativeModeTab WOOLLY_WONDERS_TAB =
            new WoollyWondersCreativeModeTab(CreativeModeTab.TABS.length, "woolly_wonders_tab");

}