package goosum.goosum.woollywonders.common.client.model.layer;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.entity.ExtraWoollySheepEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExtraWoollySheepFurModel<T extends ExtraWoollySheepEntity> extends QuadrupedModel<T> {

    private float headXRot;

    public static ModelLayerLocation EXTRA_WOOLLY_SHEEP_FUR_MODEL = new ModelLayerLocation(new ResourceLocation(WoollyWonders.MODID, "extra_woolly_sheep"), "fur");

    public ExtraWoollySheepFurModel(ModelPart modelPart) {
        super(modelPart, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    //(sheep's left & right)cubebuilder.addBox(lr[-l, r] offset, fb[-f, b] offset, du[-d, u], )

    public static LayerDefinition createFurLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(2.2F)), PartPose.offset(0.0F, 6.0F, -8.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -9.0F, -7.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(3.75F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(1.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
        partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, -5.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void prepareMobModel(T p_103661_, float p_103662_, float p_103663_, float p_103664_) {
        super.prepareMobModel(p_103661_, p_103662_, p_103663_, p_103664_);
        this.head.y = 6.0F + p_103661_.getHeadEatPositionScale(p_103664_) * 9.0F;
        this.headXRot = p_103661_.getHeadEatAngleScale(p_103664_);
    }

    public void setupAnim(T p_103666_, float p_103667_, float p_103668_, float p_103669_, float p_103670_, float p_103671_) {
        super.setupAnim(p_103666_, p_103667_, p_103668_, p_103669_, p_103670_, p_103671_);
        this.head.xRot = this.headXRot;
    }

}
