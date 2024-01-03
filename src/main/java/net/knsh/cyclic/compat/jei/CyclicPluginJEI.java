package net.knsh.cyclic.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.block.melter.ContainerMelter;
import net.knsh.cyclic.block.melter.ScreenMelter;
import net.knsh.cyclic.compat.jei.categories.MelterRecipeCategory;
import net.knsh.cyclic.registry.CyclicBlocks;
import net.knsh.cyclic.registry.CyclicItems;
import net.knsh.cyclic.registry.CyclicRecipeTypes;
import net.knsh.cyclic.registry.CyclicScreens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class CyclicPluginJEI implements IModPlugin {
    private static final int PLAYER_INV_SIZE = 4 * 9;
    private static final ResourceLocation ID = new ResourceLocation(Cyclic.MOD_ID, "jei");

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new MelterRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(CyclicBlocks.CRAFTER.block()), RecipeTypes.CRAFTING);
        registration.addRecipeCatalyst(new ItemStack(CyclicItems.CRAFTING_BAG), RecipeTypes.CRAFTING);
        registration.addRecipeCatalyst(new ItemStack(CyclicItems.CRAFTING_STICK), RecipeTypes.CRAFTING);
        registration.addRecipeCatalyst(new ItemStack(CyclicBlocks.MELTER.block()), MelterRecipeCategory.TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        ClientLevel world = Objects.requireNonNull(Minecraft.getInstance().level);
        RecipeManager rm = world.getRecipeManager();
        registry.addRecipes(RecipeTypes.CRAFTING, rm.getAllRecipesFor(RecipeType.CRAFTING));
        registry.addRecipes(MelterRecipeCategory.TYPE, List.copyOf(rm.getAllRecipesFor(CyclicRecipeTypes.MELTER)));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registry) {
        registry.addRecipeClickArea(ScreenMelter.class,
                75, 20,
                40, 26, MelterRecipeCategory.TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
        registry.addRecipeTransferHandler(ContainerMelter.class, CyclicScreens.MELTER, MelterRecipeCategory.TYPE,
                0, 2, //recipeSLotStart, recipeSlotCount
                2, PLAYER_INV_SIZE); // inventorySlotStart, inventorySlotCount
    }
}
