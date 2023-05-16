package goosum.goosum.woollywonders.client.renderer;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.client.renderer.layer.ExtraWoollySheepFurLayer;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.resources.ResourceLocation;


public class ExtraWoollySheepRenderer extends SheepRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WoollyWonders.MODID, "textures/entity/extra_wooly_sheep.png");

    public ExtraWoollySheepRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.addLayer(new ExtraWoollySheepFurLayer(this, context.getModelSet()));

    }

    public ResourceLocation getTextureLocation(ExtraWoollySheepEntity extraWoollySheepEntity) {
        return TEXTURE;
    }


}
