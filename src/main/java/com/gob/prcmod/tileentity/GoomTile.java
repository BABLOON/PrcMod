package com.gob.prcmod.tileentity;

import com.gob.prcmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jline.utils.InfoCmp;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;

public class GoomTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemhandler = createHandler();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemhandler);

    public GoomTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public GoomTile() {
        this(ModTileEntities.GOOM_TILE_ENTITY.get());
    }

    private int tick = 0;
    private int gooLevel = 0;

    public int getGooLevel() {
        return gooLevel;
    }

    @Override
    public void tick() {
        tick++;
        if(tick > 10) {  //make configurable
            if(this.itemhandler.getStackInSlot(0).getItem() == ModItems.SLUDGE_BUCKET.get() && this.gooLevel < 64) {
                itemhandler.extractItem(0, 1, false); //deletes item
                itemhandler.insertItem(0, new ItemStack(Items.BUCKET), false);
                gooLevel = 64;

            }
            if((this.itemhandler.getStackInSlot(1).getItem() == Items.EGG)
                && this.gooLevel > 0 && this.itemhandler.getStackInSlot(2).getCount() < 64) {
                itemhandler.extractItem(1, 1, false);
                itemhandler.insertItem(2, new ItemStack(ModItems.GOO_SEED.get()), false);
                gooLevel --;
            }

            tick = 0;
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        itemhandler.deserializeNBT(tag.getCompound("inv"));

        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("inv", itemhandler.serializeNBT());
        return super.write(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

                switch (slot) {
                    case 0: return (stack.getItem() == ModItems.SLUDGE_BUCKET.get() || stack.getItem() == Items.BUCKET);
                    case 1: return stack.getItem() == Items.EGG;
                    case 2: return stack.getItem() == ModItems.GOO_SEED.get();
                    default: return false;
                }
            }

            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(! isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nonnull Direction side) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(capability, side);
    }

}
