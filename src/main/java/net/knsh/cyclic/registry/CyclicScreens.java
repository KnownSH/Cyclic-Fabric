package net.knsh.cyclic.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.block.anvil.AnvilAutoContainer;
import net.knsh.cyclic.block.anvilmagma.AnvilMagmaContainer;
import net.knsh.cyclic.block.anvilvoid.AnvilVoidContainer;
import net.knsh.cyclic.block.battery.BatteryContainer;
import net.knsh.cyclic.block.beaconpotion.BeaconPotionContainer;
import net.knsh.cyclic.block.cable.fluid.FluidCableContainer;
import net.knsh.cyclic.block.cable.item.ItemCableContainer;
import net.knsh.cyclic.block.crafter.CrafterContainer;
import net.knsh.cyclic.block.generatorfuel.GeneratorFuelContainer;
import net.knsh.cyclic.gui.ContainerBase;
import net.knsh.cyclic.item.crafting.CraftingBagContainer;
import net.knsh.cyclic.item.crafting.simple.CraftingStickContainer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class CyclicScreens {
    public static MenuType<AnvilVoidContainer> ANVIL_VOID = registerScreen("anvil_void",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new AnvilVoidContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<GeneratorFuelContainer> GENERATOR_FUEL = registerScreen("generator_fuel",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new GeneratorFuelContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<CrafterContainer> CRAFTER = registerScreen("crafter",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new CrafterContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<BeaconPotionContainer> BEACON = registerScreen("beacon",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new BeaconPotionContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<AnvilMagmaContainer> ANVIL_MAGMA = registerScreen("anvil_magma",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new AnvilMagmaContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<AnvilAutoContainer> ANVIL = registerScreen("anvil",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new AnvilAutoContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<ItemCableContainer> ITEM_PIPE = registerScreen("item_pipe",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new ItemCableContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<FluidCableContainer> FLUID_PIPE = registerScreen("fluid_pipe",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new FluidCableContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<BatteryContainer> BATTERY = registerScreen("battery",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new BatteryContainer(syncId, inventory, inventory.player.level(), buf.readBlockPos()))));
    public static MenuType<CraftingBagContainer> CRAFTING_BAG = registerScreen("crafting_bag",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new CraftingBagContainer(syncId, inventory, inventory.player, buf.readInt()))));
    public static MenuType<CraftingStickContainer> CRAFTING_STICK = registerScreen("crafting_stick",
            new ExtendedScreenHandlerType<>(((syncId, inventory, buf) -> new CraftingStickContainer(syncId, inventory, inventory.player, buf.readInt()))));

    private static <T extends ContainerBase> MenuType<T> registerScreen(String id, ExtendedScreenHandlerType factory) {
        return Registry.register(BuiltInRegistries.MENU, new ResourceLocation(Cyclic.MOD_ID, id), factory);
    }

    public static void register() {}
}
