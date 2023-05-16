package goosum.goosum.woollywonders.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.client.model.ExtraWoollySheepModel;
import goosum.goosum.woollywonders.client.model.layer.ExtraWoollySheepFurModel;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

public class ExtraWoollySheepFurLayer extends SheepFurLayer {
    public static final ResourceLocation EXTRA_WOOLLY_SHEEP_FUR_LOCATION = new ResourceLocation("textures/entity/extra_woolly_sheep_fur.png");
    private final ExtraWoollySheepFurModel model;
    public static final ModelLayerLocation EXTRA_WOOLLY_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(WoollyWonders.MODID, "extra_woolly_sheep"), "fur");

    public ExtraWoollySheepFurLayer(RenderLayerParent<Sheep, SheepModel<Sheep>> sheepModelRenderLayerParent, EntityModelSet entityModelSet) {
        super(sheepModelRenderLayerParent, entityModelSet);
        this.model = new ExtraWoollySheepFurModel(entityModelSet.bakeLayer(ExtraWoollySheepFurLayer.EXTRA_WOOLLY_SHEEP_FUR));
    }


}
