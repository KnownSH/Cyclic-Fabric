package net.knsh.cyclic.block.battery;

import net.knsh.cyclic.gui.ScreenHandlerBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;

public class BatteryScreenHandler extends ScreenHandlerBase {
    protected BatteryBlockEntity tile;
    private final Container inventory;

    public BatteryScreenHandler(int syncId, Inventory playerInventory, Level world, BlockPos pos) {
        this(syncId, playerInventory, new SimpleContainer(1), world, pos);
    }

    public BatteryScreenHandler(int syncId, Inventory playerInventory, Container inventorysent, Level world, BlockPos pos) {
        super(null, syncId);
        this.inventory = inventorysent;
        this.tile = (BatteryBlockEntity) world.getBlockEntity(pos);
        this.playerEntity = playerInventory.player;
        this.playerInventory = playerInventory;

        this.addSlot(new Slot(inventory, 0, 134, 54) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void setChanged() {
                tile.setChanged();
            }
        });
        this.endInv = 1;
        layoutPlayerInventorySlots(playerInventory, 8, 84);
        this.trackAllIntFields(tile, BatteryBlockEntity.Fields.values().length);
    }

    public int getEnergy() {
        return (int) tile.getEnergy().getAmount();
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }
}
