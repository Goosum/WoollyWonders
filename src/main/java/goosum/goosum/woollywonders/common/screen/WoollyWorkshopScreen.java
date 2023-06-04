package goosum.goosum.woollywonders.common.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import goosum.goosum.woollywonders.common.recipe.WoollyWorkshopRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class WoollyWorkshopScreen extends AbstractContainerScreen<WoollyWorkshopMenu> {

    private static final ResourceLocation BG_LOCATION = new ResourceLocation("textures/gui/container/woolly_workshop_gui.png");
    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 52;
    private static final int RECIPES_Y = 14;

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public WoollyWorkshopScreen(WoollyWorkshopMenu pWoollyWorkshopMenu, Inventory pInventory, Component pComponent) {
        super(pWoollyWorkshopMenu, pInventory, pComponent);
        pWoollyWorkshopMenu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void render(PoseStack pPoseStack, int pInt1, int pInt2, float pFloat) {
        super.render(pPoseStack, pInt1, pInt2, pFloat);
        this.renderTooltip(pPoseStack, pInt1, pInt2);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pFloat1, int pInt1, int pInt2) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0 ,BG_LOCATION);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(pPoseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int k = (int)(41.0F * this.scrollOffs);
        this.blit(pPoseStack, i + 119, j + 15 + k, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
        int l = this.leftPos + 52;
        int i1 = this.topPos + 14;
        int j1 = this.startIndex + 12;
        this.renderButtons(pPoseStack, pInt1, pInt2, l, i1, j1);
        this.renderRecipes(l, i1, j1);
    }



    @Override
    protected void renderTooltip(PoseStack pPoseStack, int pInt1, int pInt2) {
        super.renderTooltip(pPoseStack, pInt1, pInt2);
        if(this.displayRecipes) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;
            List<WoollyWorkshopRecipe> list = this.menu.getRecipes();
            for(int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
                int i1 = l - this.startIndex;
                int j1 = i  + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if(pInt1 >= j1 && pInt1 < j1 + 16 && pInt2 >= k1 && pInt2 <k1 +18) {
                    this.renderTooltip(pPoseStack, list.get(l).getResultItem(), pInt1, pInt2);
                }
            }
        }
    }

    private void renderButtons(PoseStack pPoseStack, int pInt1, int pInt2, int pInt3, int pInt4, int pInt5) {
        for(int i = this.startIndex; i < pInt5 && i < this.menu.getNumRecipes(); ++i) {
            int j = i - this.startIndex;
            int k = pInt3 + j % 4 * 16;
            int l = j / 4;
            int i1 = pInt4 + l * 18 + 2;
            int j1 = this.imageHeight;
            if(i == this.menu.getSelectedRecipeIndex()) {
                j1 += 18;
            } else if(pInt1 >= k && pInt2 >= i1 && pInt1 < k + 16 && pInt2 < i1 + 18) {
                j1 += 36;
            }
            this.blit(pPoseStack, k, i1 - 1, 0, j1, 16, 18);
        }
    }

    private void renderRecipes(int pInt1, int pInt2, int pInt3) {
        List<WoollyWorkshopRecipe> list = this.menu.getRecipes();

        for(int i = this.startIndex; i < pInt3 && i < this.menu.getNumRecipes(); ++i) {
            int j = i - this.startIndex;
            int k = pInt1 + j % 4 * 16;
            int l = j / 4;
            int i1 = pInt2 + l * 18 + 2;
            this.minecraft.getItemRenderer().renderAndDecorateItem(list.get(i).getResultItem(), k, i1);
        }
    }

    @Override
    public boolean mouseClicked(double pDouble1, double pDouble2, int pDouble3) {
        this.scrolling = false;
        if(this.displayRecipes) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;

            for(int l = this.startIndex; l < k; ++l) {
                int i1 = l - this.startIndex;
                double d0 = pDouble1 - (double)(i + i1 % 4 * 16);
                double d1 = pDouble2 - (double)(j + i1 / 4 * 18);
                if(d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D && this.menu.clickMenuButton(this.minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_LOOM_SELECT_PATTERN, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if(pDouble1 >= (double)i && pDouble1 < (double)(i + 12) && pDouble2 >= (double)j && pDouble2 < (double)(j+54)) {
                this.scrolling = true;
            }
        }

        return super.mouseClicked(pDouble1, pDouble2, pDouble3);
    }

    @Override
    public boolean mouseDragged(double pDouble1, double pDouble2, int pInt1, double pDouble3, double pDouble4) {
        if(this.scrolling && this.isScrollBarActive()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollOffs = ((float)pDouble2 - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5D) * 4;
            return true;
        } else {
            return super.mouseDragged(pDouble1, pDouble2, pInt1, pDouble3, pDouble4);
        }
    }

    @Override
    public boolean mouseScrolled(double pDouble1, double pDouble2, double pDouble3) {
        if(this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float f = (float)pDouble3 / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5D) * 4;
        }

        return true;
    }

    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if(!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 -3;
    }
}
