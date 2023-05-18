package goosum.goosum.woollywonders.client;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.client.model.layer.ExtraWoollySheepFurModel;
import goosum.goosum.woollywonders.client.renderer.layer.ExtraWoollySheepFurLayer;
import goosum.goosum.woollywonders.common.entity.WoollyWondersEntities;
import goosum.goosum.woollywonders.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.client.renderer.ExtraWoollySheepRenderer;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WoollyWonders.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void initialize(FMLClientSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ExtraWoollySheepModel.EXTRA_WOOLLY_SHEEP, ExtraWoollySheepModel::createBodyLayer);
        event.registerLayerDefinition(ExtraWoollySheepFurModel.EXTRA_WOOLLY_SHEEP_FUR_MODEL, ExtraWoollySheepFurModel::createFurLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(WoollyWondersEntities.EXTRA_WOOLLY_SHEEP.get(), ExtraWoollySheepRenderer::new);
    }




}
