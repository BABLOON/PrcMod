package com.gob.prcmod.item;

import com.gob.prcmod.PrcMod;
import com.gob.prcmod.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems
{
    public static final RegistryObject<Item> GOO_INGOT
            = Registration.ITEMS.register("goo_ingot",
            () -> new Item(new Item.Properties().group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> UNREFINED_GOO
            = Registration.ITEMS.register("unrefined_goo",
            () -> new Item(new Item.Properties().group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOOPY_APPLE
            = Registration.ITEMS.register("goopy_apple",
            () -> new GoopyApple());

    public static final RegistryObject<Item> NORMAL_PILLS = Registration.ITEMS.register("normal_pills",
            () -> new NormalPills(new Item.Properties().group(PrcMod.GOO_TAB)
                    .food(new Food.Builder().hunger(0).saturation(0.1f)
                            .fastToEat()
                            .effect(() -> new EffectInstance(Effects.SPEED, 300,0),1)
                            .setAlwaysEdible()
                            .build()).maxDamage(30)));

    //=========================TOOLS=========================
    public static final RegistryObject<Item> GOO_SHOVEL
            = Registration.ITEMS.register("goo_shovel",
            () -> new ShovelItem(ModItemTier.GOO, 0f,-2f,
                    new Item.Properties()
                            .defaultMaxDamage(100)
                            .addToolType(ToolType.SHOVEL, 0)
                            .group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_SWORD
            = Registration.ITEMS.register("goo_sword",
            () -> new SwordItem(ModItemTier.GOO, 1, -1f,
                    new Item.Properties()
                            .defaultMaxDamage(100)
                            .group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_PICKAXE
            = Registration.ITEMS.register("goo_pickaxe",
            () -> new PickaxeItem(ModItemTier.GOO, 0, -2f,
                    new Item.Properties()
                            .defaultMaxDamage(100)
                            .addToolType(ToolType.PICKAXE, 0)
                            .group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_HOE
            = Registration.ITEMS.register("goo_hoe",
            () -> new HoeItem(ModItemTier.GOO, 0, -2f,
                    new Item.Properties()
                            .defaultMaxDamage(100)
                            .addToolType(ToolType.HOE, 0)
                            .group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_AXE
            = Registration.ITEMS.register("goo_axe",
            () -> new AxeItem(ModItemTier.GOO, 2, -3f,
                    new Item.Properties()
                            .defaultMaxDamage(100)
                            .addToolType(ToolType.AXE, 0)
                            .group(PrcMod.GOO_TAB)));

    public static void register() {}

    public enum ModItemTier implements IItemTier
    {
        GOO(0, 100, 0, 0, 25,
                Ingredient.fromStacks(new ItemStack(ModItems.GOO_INGOT.get())));

        //TO ADD: PERFECTED_GOO

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }


        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return null;
        }
    }

    //=========================ARMOR=========================

    public static final RegistryObject<Item> GOO_HELMET =
            Registration.ITEMS.register("goo_helmet",
                    () -> new ArmorItem(ModArmorMaterial.GOO,
                    EquipmentSlotType.HEAD,
                    new Item.Properties().group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_CHESTPLATE =
            Registration.ITEMS.register("goo_chestplate",
                    () -> new ArmorItem(ModArmorMaterial.GOO,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_LEGGINGS =
            Registration.ITEMS.register("goo_leggings",
                    () -> new ArmorItem(ModArmorMaterial.GOO,
                            EquipmentSlotType.LEGS,
                            new Item.Properties().group(PrcMod.GOO_TAB)));

    public static final RegistryObject<Item> GOO_BOOTS =
            Registration.ITEMS.register("goo_boots",
                    () -> new ArmorItem(ModArmorMaterial.GOO,
                            EquipmentSlotType.FEET,
                            new Item.Properties().group(PrcMod.GOO_TAB)));


    public enum ModArmorMaterial implements IArmorMaterial {
        GOO(50,
                new int[] {2, 7, 5, 3},
                10,
                SoundEvents.BLOCK_SLIME_BLOCK_HIT,
                Ingredient.fromStacks(new ItemStack(ModItems.GOO_INGOT.get())),
                PrcMod.MOD_ID + ":goo",
                0,
                0);

        private final int durability;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmorMaterial(int durability,
                         int[] damageReductionAmountArray,
                         int enchantability,
                         SoundEvent soundEvent,
                         Ingredient repairMaterial,
                         String name,
                         float toughness,
                         float knockbackResistance)
        {
            this.durability = durability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float func_230304_f_() {             //knock-back resistance
            return knockbackResistance;
        }
    }


}
