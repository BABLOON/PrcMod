package com.gob.prcmod.block;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.util.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final RegistryObject<Block> HEAP_OF_GOO = register("heap_of_goo",
            () -> new HeapOfGoo(AbstractBlock.Properties.create(Material.ORGANIC)
                    .hardnessAndResistance(3f, 10f)
                    .sound(SoundType.SLIME)
                    .harvestTool(ToolType.SHOVEL)
                    .harvestLevel(1)
                    .setRequiresTool()));

    public static final RegistryObject<Block> GOO_ORE = register("goo_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .hardnessAndResistance(5f, 3f)
                    .sound(SoundType.STONE)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()));

    public static final RegistryObject<Block> GOO_PRESSURE_PLATE =
            register("goo_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.create(Material.ORGANIC)));

    public static final RegistryObject<Block> GROP_CROP =
            Registration.BLOCKS.register("grop_crop",
                    () -> new GropCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));

    public static final RegistryObject<Block> GOOM =
            register("goom", () -> new Goom(AbstractBlock.Properties.create(Material.CLAY)
                    .hardnessAndResistance(2f)
                    .harvestTool(ToolType.PICKAXE)));



    public static void register() {}

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().group(PrcMod.GOO_TAB)));
        return toReturn;
    }
}
