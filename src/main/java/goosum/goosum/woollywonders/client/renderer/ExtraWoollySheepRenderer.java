package goosum.goosum.woollywonders.client.renderer;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.client.renderer.layer.ExtraWoollySheepFurLayer;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class ExtraWoollySheepRenderer extends MobRenderer<ExtraWoollySheepEntity, ExtraWoollySheepModel<ExtraWoollySheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WoollyWonders.MODID, "textures/entity/extra_wooly_sheep.png");

    public ExtraWoollySheepRenderer(EntityRendererProvider.Context context, ExtraWoollySheepModel<ExtraWoollySheepEntity> extraWoollySheepModel, float v) {
        super(context, extraWoollySheepModel, v);
        this.addLayer(new ExtraWoollySheepFurLayer(this, context.getModelSet()));
    }


    @Override
    public ResourceLocation getTextureLocation(ExtraWoollySheepEntity extraWoollySheepEntity) {
        return TEXTURE;
    }


}
