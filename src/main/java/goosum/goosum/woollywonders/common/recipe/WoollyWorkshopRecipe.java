package goosum.goosum.woollywonders.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import goosum.goosum.woollywonders.WoollyWonders;
import goosum.goosum.woollywonders.common.block.WoollyWondersBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CompoundIngredient;
import org.jetbrains.annotations.Nullable;

public class WoollyWorkshopRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public WoollyWorkshopRecipe(ResourceLocation id, ItemStack output,
                                NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }



    @Override
    public boolean matches(SimpleContainer pSimpleContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(pSimpleContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pSimpleContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(WoollyWondersBlocks.WOOLLY_WORKSHOP.get());
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<WoollyWorkshopRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "woolly_workshopping";
    }

    public static class Serializer implements RecipeSerializer<WoollyWorkshopRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(WoollyWonders.MODID, "woolly_workshopping");

        @Override
        public WoollyWorkshopRecipe fromJson(ResourceLocation pResourceLocation, JsonObject pJsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJsonObject, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pJsonObject, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new WoollyWorkshopRecipe(pResourceLocation, output, inputs);
        }

        @Override
        public @Nullable WoollyWorkshopRecipe fromNetwork(ResourceLocation pResourceLocation, FriendlyByteBuf pFriendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pFriendlyByteBuf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pFriendlyByteBuf));
            }

            ItemStack output = pFriendlyByteBuf.readItem();
            return new WoollyWorkshopRecipe(pResourceLocation, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pFriendlyByteBuf, WoollyWorkshopRecipe pWoollyWorkshopRecipe) {
            pFriendlyByteBuf.writeInt(pWoollyWorkshopRecipe.getIngredients().size());

            for(Ingredient ing : pWoollyWorkshopRecipe.getIngredients()) {
                ing.toNetwork(pFriendlyByteBuf);
            }
            pFriendlyByteBuf.writeItemStack(pWoollyWorkshopRecipe.getResultItem(), false);
        }
    }

}
