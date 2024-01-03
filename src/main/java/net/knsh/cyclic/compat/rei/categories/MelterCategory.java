package net.knsh.cyclic.compat.rei.categories;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.block.melter.RecipeMelter;
import net.knsh.cyclic.compat.rei.CyclicDisplayREI;
import net.knsh.cyclic.compat.rei.CyclicPluginREI;
import net.knsh.cyclic.library.util.ChatUtil;
import net.knsh.cyclic.registry.CyclicBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

public class MelterCategory implements DisplayCategory<CyclicDisplayREI<RecipeMelter>> {
    private static final int FONT = 10592673;
    public static final ResourceLocation TEXTURE = new ResourceLocation(Cyclic.MOD_ID, "textures/jei/melter_recipe.png");

    @Override
    public CategoryIdentifier<? extends CyclicDisplayREI<RecipeMelter>> getCategoryIdentifier() {
        return CyclicPluginREI.MELTER;
    }

    @Override
    public Component getTitle() {
        return ChatUtil.ilang(CyclicBlocks.MELTER.block().getDescriptionId());
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(CyclicBlocks.MELTER.block().asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(CyclicDisplayREI<RecipeMelter> display, Rectangle bounds) {
        List<Widget> widgets = new LinkedList<>();
        final Point startPosition = new Point(bounds.getCenterX() - 85, bounds.getCenterY() - 34);

        //widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPosition.x, startPosition.y, 169, 69)));
        widgets.add(Widgets.createRecipeBase(new Rectangle(startPosition.x, startPosition.y, 169, 69)));

        widgets.add(Widgets.createLabel(new Point(startPosition.x + 95, startPosition.y + 30),
                Component.literal(display.getRecipe().getEnergy().getTicks() + " t")).color(FONT).shadow());
        widgets.add(Widgets.createLabel(new Point(startPosition.x + 95, startPosition.y + 40),
                Component.literal(display.getRecipe().getEnergy().getRfPertick() + " RF/t")).color(FONT).shadow());
        widgets.add(Widgets.createLabel(new Point(startPosition.x + 95, startPosition.y + 50),
                Component.literal(display.getRecipe().getEnergy().getEnergyTotal() + " RF")).color(FONT).shadow());

        widgets.add(Widgets.createSlot(new Point(startPosition.x + 12, startPosition.y + 19)).entries(display.getInputEntries().get(0)));
        if (display.getInputEntries().size() > 1) {
            widgets.add(Widgets.createSlot(new Point(startPosition.x + 30, startPosition.y + 19)).entries(display.getInputEntries().get(1)));
        } else {
            widgets.add(Widgets.createSlot(new Point(startPosition.x + 30, startPosition.y + 19)));
        }
        widgets.add(Widgets.createSlot(new Point(startPosition.x + 140, startPosition.y + 19)).markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 69;
    }
}
