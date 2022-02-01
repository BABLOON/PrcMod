package com.gob.prcmod.tileentity;

import com.gob.prcmod.block.ModBlocks;
import com.gob.prcmod.util.Registration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
    public static final RegistryObject<TileEntityType<GoomTile>> GOOM_TILE_ENTITY
            = Registration.TILE_ENTITY_TYPES.register("goom_tile_entity", () -> TileEntityType.Builder.create(
            () -> new GoomTile(), ModBlocks.GOOM.get()).build(null));

    public static void register() { }

}
