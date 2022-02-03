package com.gob.prcmod.container;

import com.gob.prcmod.util.Registration;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;


public class ModContainers
{
    public static final RegistryObject<ContainerType<GoomContainer>> GOOM_CONTAINER
            = Registration.CONTAINERS.register("goom_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new GoomContainer(windowId, world, pos, inv, inv.player);
            })));

    public static void register() { }
}
