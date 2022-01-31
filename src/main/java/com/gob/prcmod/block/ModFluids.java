package com.gob.prcmod.block;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.item.ModItems;
import com.gob.prcmod.util.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class ModFluids {
    public static final ResourceLocation SLUDGE_STILL_RL = new ResourceLocation(PrcMod.MOD_ID,
            "block/sludge_still");
    public static final ResourceLocation SLUDGE_FLOWING_RL = new ResourceLocation(PrcMod.MOD_ID,
            "block/sludge_flowing");
    public static final ResourceLocation SLUDGE_OVERLAY_RL = new ResourceLocation(PrcMod.MOD_ID,
            "block/sludge_overlay");


    public static final RegistryObject<FlowingFluid> SLUDGE_FLUID
            = Registration.FLUIDS.register("sludge_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SLUDGE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SLUDGE_FLOWING
            = Registration.FLUIDS.register("sludge_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SLUDGE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SLUDGE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> SLUDGE_FLUID.get(),
            () -> SLUDGE_FLOWING.get(),
            FluidAttributes.builder(SLUDGE_STILL_RL, SLUDGE_FLOWING_RL)
                    .density(20)
                    .luminosity(1)
                    .viscosity(10)
                    .rarity(Rarity.RARE)
                    .sound(SoundEvents.ENTITY_SLIME_SQUISH))
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .tickRate(50)
            .block(() -> ModFluids.SLUDGE_BLOCK.get()).bucket(() -> ModItems.SLUDGE_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> SLUDGE_BLOCK = Registration.BLOCKS.register("sludge",
            () -> new FlowingFluidBlock(() -> SLUDGE_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    public static void register() { }
}
