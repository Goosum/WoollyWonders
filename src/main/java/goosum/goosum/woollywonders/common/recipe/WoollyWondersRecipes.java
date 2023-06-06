package goosum.goosum.woollywonders.common.recipe;

import goosum.goosum.woollywonders.WoollyWonders;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WoollyWondersRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WoollyWonders.MODID);

    public static final RegistryObject<RecipeSerializer<WoollyWorkshopRecipe>> WOOLLY_WORKSHOP_SERIALIZER =
            SERIALIZERS.register("woolly_workshop", () -> WoollyWorkshopRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
