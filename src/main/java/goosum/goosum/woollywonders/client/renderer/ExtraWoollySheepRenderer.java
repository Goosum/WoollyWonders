package goosum.goosum.woollywonders.client.renderer;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.client.renderer.layer.ExtraWoollySheepFurLayer;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExtraWoollySheepRenderer extends MobRenderer<ExtraWoollySheepEntity, ExtraWoollySheepModel<ExtraWoollySheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WoollyWonders.MODID, "textures/entity/extra_woolly_sheep.png");

    public ExtraWoollySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new ExtraWoollySheepModel<>(context.bakeLayer(ExtraWoollySheepModel.EXTRA_WOOLLY_SHEEP)), 0.7F);
        this.addLayer(new ExtraWoollySheepFurLayer(this, context.getModelSet()));
    }

    public ResourceLocation getTextureLocation(ExtraWoollySheepEntity extraWoollySheepEntity) {
        return TEXTURE;
    }


}
