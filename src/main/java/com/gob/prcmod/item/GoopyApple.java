package com.gob.prcmod.item;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.util.keyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class GoopyApple extends Item {
    public GoopyApple()
    {
        super(new Properties().group(PrcMod.GOO_TAB)
                .food(new Food.Builder()
                        .hunger(5).saturation(5)
                        .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 300,3),1)
                        .effect(() -> new EffectInstance(Effects.POISON, 100,1),0.25f)
                        .effect(() -> new EffectInstance(Effects.INSTANT_DAMAGE, 1,100),0.0001f)
                        .build()));
    }
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if(keyboardUtil.isHoldingShift()) {
            tooltip.add(new StringTextComponent("Yummy goopy!!"));
        }else {
            tooltip.add(new StringTextComponent("Hold"+"\u00A7e"+" SHIFT"+"\u00A77"+" for important info"));
        }
        super.addInformation(stack, world, tooltip, flag);
    }
}
