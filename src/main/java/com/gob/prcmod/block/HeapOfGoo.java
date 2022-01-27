package com.gob.prcmod.block;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.item.ModItems;
import com.gob.prcmod.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")

public class HeapOfGoo extends Block
{
    public HeapOfGoo(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        double nerf = Config.BOUNCE_NERF.get();
        double up = 1;

        Vector3d playerv = player.getPositionVec();

        double yvec = ((playerv.y - pos.getY())/nerf);;
        double xvec = ((playerv.x - pos.getX())/nerf);
        double zvec = ((playerv.z - pos.getZ())/nerf);
        if ((worldIn.isRemote) && ! (player.isCrouching()))
        {
            player.getPositionVec();
            Vector3d pbv = new Vector3d(xvec, yvec, zvec); //pbv is player-block vector

            player.setMotion(pbv);
        }
        super.onBlockClicked(state, worldIn, pos, player);
    }
}
