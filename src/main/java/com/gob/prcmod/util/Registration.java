package com.gob.prcmod.util;

import com.gob.prcmod.PrcMod;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration
{
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, PrcMod.MOD_ID);

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, PrcMod.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, PrcMod.MOD_ID);

    public static void register()
    {
        IEventBus eventbus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventbus);
        ITEMS.register(eventbus);
        FLUIDS.register(eventbus);
    }
}
