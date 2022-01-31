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

public class GoodFood extends Item {
    public GoodFood()
    {
        super(new Properties().group(PrcMod.GOO_TAB)
                .food(new Food.Builder()
                        .hunger(3).saturation(5)
                        .build()));
    }
}
