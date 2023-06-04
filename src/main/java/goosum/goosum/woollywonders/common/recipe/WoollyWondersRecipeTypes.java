package goosum.goosum.woollywonders.common.recipe;

import goosum.goosum.woollywonders.WoollyWonders;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class WoollyWondersRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, WoollyWonders.MODID);

    public static final Supplier<RecipeType<WoollyWorkshopRecipe>> WOOLLY_WORKSHOPPING = registerRecipeType("woolly_workshopping");

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String pRecipeTypeString) {
        return RECIPE_TYPES.register(pRecipeTypeString, () -> new RecipeType<>() {
            public String toString() {
                return pRecipeTypeString;
            }
        });
    }


    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

}
