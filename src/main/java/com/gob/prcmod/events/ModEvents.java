package com.gob.prcmod.events;

import com.gob.prcmod.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.EventListener;

public class ModEvents {
    @SubscribeEvent
    public void onGooSwordHit(AttackEntityEvent event) {
        if (event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.GOO_SWORD.get()) {
            if(event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity)event.getTarget();
                if(target instanceof MobEntity) {
                    PlayerEntity player = event.getPlayer();

                    Vector3d pos = target.getPositionVec();
                    double nerf = 1;
                    double up = 1;

                    Vector3d playerv = player.getPositionVec();

                    double yvec = 10;
                    double xvec = ((pos.getX() - playerv.x)/nerf);
                    double zvec = ((pos.getZ() - playerv.z)/nerf);
                    if (!(player.isCrouching()) && ! (player.world.isRemote()))
                    {
                        Vector3d pbv = new Vector3d(xvec, yvec, zvec); //pbv is player-target vector
                        target.setMotion(pbv);

                        String msg = TextFormatting.GREEN + "Boioioing!";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }

                }
            }
        }
    }
    @SubscribeEvent
    public void appleGooGuy(AttackEntityEvent event) {
        if(event.getPlayer().getHeldItemMainhand().getItem() == ModItems.GOOPY_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity)event.getTarget();
                PlayerEntity player = event.getPlayer();
                if (target instanceof MobEntity) {
                    player.getHeldItemMainhand().shrink(1);
                    target.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 100));

                    if(!player.world.isRemote())
                    {
                        String msg = TextFormatting.RED + "Its weak body could not handle sweet, sweet goo.";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void pillDurability(LivingEntityUseItemEvent event) {
    }
}
