package goosum.goosum.woollywonders;

import com.mojang.logging.LogUtils;
import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import goosum.goosum.woollywonders.common.entity.WoollyWondersEntities;
import goosum.goosum.woollywonders.common.item.WoollyWondersItems;
import goosum.goosum.woollywonders.common.recipe.WoollyWondersRecipeTypes;
import goosum.goosum.woollywonders.common.recipe.WoollyWondersRecipes;
import goosum.goosum.woollywonders.common.screen.WoollyWondersMenus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WoollyWonders.MODID)
public class WoollyWonders
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "woollywonders";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public WoollyWonders()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WoollyWondersItems.register(modEventBus);
        WoollyWondersEntities.register(modEventBus);
        WoollyWondersBlocks.register(modEventBus);
        WoollyWondersMenus.register(modEventBus);
        WoollyWondersRecipes.register(modEventBus);
        WoollyWondersRecipeTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
