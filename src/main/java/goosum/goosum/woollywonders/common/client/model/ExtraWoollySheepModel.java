package goosum.goosum.woollywonders.common.client.model;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExtraWoollySheepModel<T extends ExtraWoollySheepEntity> extends SheepModel<T> {


    public static ModelLayerLocation EXTRA_WOOLLY_SHEEP = new ModelLayerLocation(new ResourceLocation(WoollyWonders.MODID, "extra_woolly_sheep"), "body");

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void prepareMobModel(T woollySheepEntity, float p_103688_, float p_103689_, float p_103690_) {
        super.prepareMobModel(woollySheepEntity, p_103688_, p_103689_, p_103690_);
    }

    public ExtraWoollySheepModel(ModelPart part) {
        super(part);
    }
}
