package com.gob.prcmod.item;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.util.keyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class NormalPills extends Item {
    public NormalPills(Properties properties)
    {
        super(properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack st = stack.copy();
        st.damageItem(1, entityLiving, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
        entityLiving.onFoodEaten(worldIn, stack);

        return st;
    }
}
