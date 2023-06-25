package goosum.goosum.woollywonders.common.compat;

import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.recipe.WoollyWorkshopRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIWoollyWondersPlugin implements IModPlugin {

    public static RecipeType<WoollyWorkshopRecipe> WORKSHOP_TYPE =
            new mezz.jei.api.recipe.RecipeType<>(WoollyWorkshopRecipeCategory.UID, WoollyWorkshopRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(WoollyWonders.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WoollyWorkshopRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<WoollyWorkshopRecipe> recipesWorkshop = recipeManager.getAllRecipesFor(WoollyWorkshopRecipe.Type.INSTANCE);
        registration.addRecipes(WORKSHOP_TYPE, recipesWorkshop);
    }
}
