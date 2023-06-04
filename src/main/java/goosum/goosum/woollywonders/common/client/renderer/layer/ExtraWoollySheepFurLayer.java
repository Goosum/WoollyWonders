package goosum.goosum.woollywonders.common.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.common.client.model.layer.ExtraWoollySheepFurModel;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExtraWoollySheepFurLayer extends RenderLayer<ExtraWoollySheepEntity, ExtraWoollySheepModel<ExtraWoollySheepEntity>> {
    public static final ResourceLocation EXTRA_WOOLLY_SHEEP_FUR_LOCATION = new ResourceLocation(WoollyWonders.MODID, "textures/entity/layer/extra_woolly_sheep_fur.png");
    private final ExtraWoollySheepFurModel<ExtraWoollySheepEntity> model;
    public static final ModelLayerLocation EXTRA_WOOLLY_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(WoollyWonders.MODID, "extra_woolly_sheep"), "fur");

    public ExtraWoollySheepFurLayer(RenderLayerParent<ExtraWoollySheepEntity, ExtraWoollySheepModel<ExtraWoollySheepEntity>> woollySheepModelRenderLayerParent, EntityModelSet entityModelSet) {
        super(woollySheepModelRenderLayerParent);
        this.model = new ExtraWoollySheepFurModel<>(entityModelSet.bakeLayer(ExtraWoollySheepFurLayer.EXTRA_WOOLLY_SHEEP_FUR));
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i2, ExtraWoollySheepEntity extraWoollySheepEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!extraWoollySheepEntity.isSheared()) {
            if (extraWoollySheepEntity.isInvisible()) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = minecraft.shouldEntityAppearGlowing(extraWoollySheepEntity);
                if (flag) {
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.prepareMobModel(extraWoollySheepEntity, v, v1, v2);
                    this.model.setupAnim(extraWoollySheepEntity, v, v1, v3, v4, v5);
                    VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.outline(EXTRA_WOOLLY_SHEEP_FUR_LOCATION));
                    this.model.renderToBuffer(poseStack, vertexconsumer, i2, LivingEntityRenderer.getOverlayCoords(extraWoollySheepEntity, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
                }

            } else {
                float f;
                float f1;
                float f2;
                if (extraWoollySheepEntity.hasCustomName() && "jeb_".equals(extraWoollySheepEntity.getName().getString())) {
                    int i1 = 25;
                    int i = extraWoollySheepEntity.tickCount / 25 + extraWoollySheepEntity.getId();
                    int j = DyeColor.values().length;
                    int k = i % j;
                    int l = (i + 1) % j;
                    float f3 = ((float)(extraWoollySheepEntity.tickCount % 25) + v2) / 25.0F;
                    float[] afloat1 = Sheep.getColorArray(DyeColor.byId(k));
                    float[] afloat2 = Sheep.getColorArray(DyeColor.byId(l));
                    f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                    f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                    f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
                } else {
                    float[] afloat = Sheep.getColorArray(extraWoollySheepEntity.getColor());
                    f = afloat[0];
                    f1 = afloat[1];
                    f2 = afloat[2];
                }

                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, EXTRA_WOOLLY_SHEEP_FUR_LOCATION, poseStack, bufferSource, i2, extraWoollySheepEntity, v, v1, v3, v4, v5, v2, f, f1, f2);
            }
        }
    }
}
