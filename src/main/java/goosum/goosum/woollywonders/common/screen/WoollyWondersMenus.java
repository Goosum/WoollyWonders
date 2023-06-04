package goosum.goosum.woollywonders.common.screen;

import goosum.goosum.woollywonders.WoollyWonders;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WoollyWondersMenus {
    public static DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, WoollyWonders.MODID);

    public static final RegistryObject<MenuType<WoollyWorkshopMenu>> WOOLLY_WORKSHOP_MENU = registerMenuType(WoollyWorkshopMenu::new, "woolly_workshop_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> pFactory, String pName) {
        return MENU_TYPES.register(pName, () -> IForgeMenuType.create(pFactory));
    }



    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

}
