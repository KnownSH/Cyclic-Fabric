package net.knsh.cyclic.fluid;

import io.github.fabricators_of_create.porting_lib.util.SimpleFlowableFluid;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.fluid.block.MagmaFluidBlock;
import net.knsh.cyclic.fluid.block.XpJuiceFluidBlock;
import net.knsh.cyclic.registry.CyclicItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

public class FluidMagmaHolder {
    private static final String ID = "magma";
    public static final ResourceLocation FLUID_FLOWING = new ResourceLocation("minecraft:block/" + ID);
    public static final ResourceLocation FLUID_STILL = new ResourceLocation("minecraft:block/" + ID);
    public static final int COLOR = 0x4B261F;

    public static FlowingFluid STILL = Registry.register(BuiltInRegistries.FLUID, new ResourceLocation(Cyclic.MOD_ID, ID),
            new MagmaFluidBlock.Source(makeProperties()));
    public static FlowingFluid FLOWING = Registry.register(BuiltInRegistries.FLUID, new ResourceLocation(Cyclic.MOD_ID, ID + "_flowing"),
            new MagmaFluidBlock.Flowing(makeProperties()));
    public static LiquidBlock BLOCK = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Cyclic.MOD_ID, ID + "_block"),
            new MagmaFluidBlock(STILL, FabricBlockSettings.create().liquid().strength(100.0F).luminance(s -> 8).noLootTable()));
    public static Item BUCKET = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Cyclic.MOD_ID, ID + "_bucket"),
            new BucketItem(STILL, new FabricItemSettings().craftRemainder(Items.BUCKET).stacksTo(1)));

    static {
        CyclicItems.INSTANCE.add(BUCKET);
    }

    private static SimpleFlowableFluid.Properties makeProperties() {
        return new SimpleFlowableFluid.Properties(() -> STILL, () -> FLOWING)
                .bucket(() -> BUCKET)
                .block(() -> BLOCK);
    }
}
