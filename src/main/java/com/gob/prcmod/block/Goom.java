package com.gob.prcmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class Goom extends Block {
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(5, 1, 5, 11, 13, 11),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 5),
            Block.makeCuboidShape(4, 1, 5, 5, 8, 11),
            Block.makeCuboidShape(11, 1, 5, 12, 8, 11),
            Block.makeCuboidShape(4, 1, 11, 12, 8, 12),
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(5, 8, 5, 11, 10, 6),
            Block.makeCuboidShape(5, 8, 10, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 6, 10, 11),
            Block.makeCuboidShape(10, 8, 5, 11, 10, 11),
            Block.makeCuboidShape(4.5, 10, 4.5, 11.5, 11, 5.5),
            Block.makeCuboidShape(4.5, 10, 5.5, 5.5, 11, 10.5),
            Block.makeCuboidShape(4.5, 10, 10.5, 11.5, 11, 11.5),
            Block.makeCuboidShape(10.5, 10, 5.5, 11.5, 11, 10.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(5, 1, 5, 11, 13, 11),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(4, 1, 4, 5, 8, 12),
            Block.makeCuboidShape(5, 1, 11, 11, 8, 12),
            Block.makeCuboidShape(5, 1, 4, 11, 8, 5),
            Block.makeCuboidShape(11, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(5, 8, 5, 6, 10, 11),
            Block.makeCuboidShape(10, 8, 5, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 10, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 11, 10, 6),
            Block.makeCuboidShape(4.5, 10, 4.5, 5.5, 11, 11.5),
            Block.makeCuboidShape(5.5, 10, 10.5, 10.5, 11, 11.5),
            Block.makeCuboidShape(10.5, 10, 4.5, 11.5, 11, 11.5),
            Block.makeCuboidShape(5.5, 10, 4.5, 10.5, 11, 5.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(5, 1, 5, 11, 13, 11),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(11, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(5, 1, 4, 11, 8, 5),
            Block.makeCuboidShape(5, 1, 11, 11, 8, 12),
            Block.makeCuboidShape(4, 1, 4, 5, 8, 12),
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(10, 8, 5, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 6, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 11, 10, 6),
            Block.makeCuboidShape(5, 8, 10, 11, 10, 11),
            Block.makeCuboidShape(10.5, 10, 4.5, 11.5, 11, 11.5),
            Block.makeCuboidShape(5.5, 10, 4.5, 10.5, 11, 5.5),
            Block.makeCuboidShape(4.5, 10, 4.5, 5.5, 11, 11.5),
            Block.makeCuboidShape(5.5, 10, 10.5, 10.5, 11, 11.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(5, 1, 5, 11, 13, 11),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 12),
            Block.makeCuboidShape(4, 1, 11, 12, 8, 12),
            Block.makeCuboidShape(11, 1, 5, 12, 8, 11),
            Block.makeCuboidShape(4, 1, 5, 5, 8, 11),
            Block.makeCuboidShape(4, 1, 4, 12, 8, 5),
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(5, 8, 10, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 11, 10, 6),
            Block.makeCuboidShape(10, 8, 5, 11, 10, 11),
            Block.makeCuboidShape(5, 8, 5, 6, 10, 11),
            Block.makeCuboidShape(4.5, 10, 10.5, 11.5, 11, 11.5),
            Block.makeCuboidShape(10.5, 10, 5.5, 11.5, 11, 10.5),
            Block.makeCuboidShape(4.5, 10, 4.5, 11.5, 11, 5.5),
            Block.makeCuboidShape(4.5, 10, 5.5, 5.5, 11, 10.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();


    public Goom(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
