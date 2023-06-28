package goosum.goosum.woollywonders.common.compat;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import goosum.goosum.woollywonders.common.recipe.WoollyWorkshopRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class WoollyWorkshopRecipeCategory implements IRecipeCategory<WoollyWorkshopRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(WoollyWonders.MODID, "woolly_workshop");
    public static final ResourceLocation TEXTURE_LOCATION =
            new ResourceLocation(WoollyWonders.MODID, "textures/gui/container/woolly_workshop_gui_jei.png");

    private final IDrawable background;
    private final IDrawable icon;

    public WoollyWorkshopRecipeCategory(IGuiHelper pHelper) {
        this.background = pHelper.createDrawable(TEXTURE_LOCATION, 0, 0, 176, 82);
        this.icon = pHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WoollyWondersBlocks.WOOLLY_WORKSHOP.get()));
    }

    @Override
    public RecipeType<WoollyWorkshopRecipe> getRecipeType() {
        return JEIWoollyWondersPlugin.WORKSHOP_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Woolly Workshop");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoollyWorkshopRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 34).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 71, 34).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 34).addItemStack(recipe.getResultItem());
    }
}
