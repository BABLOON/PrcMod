package com.gob.prcmod.setup;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = PrcMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy{
    @Override
    public void init() {
        RenderTypeLookup.setRenderLayer(ModBlocks.GROP_CROP.get(), RenderType.getCutout());
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
