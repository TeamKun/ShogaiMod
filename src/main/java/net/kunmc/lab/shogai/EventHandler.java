package net.kunmc.lab.shogai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Shogai.MOD_ID)
public class EventHandler {
    @SubscribeEvent
    public static void attachAttributes(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            final EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            final AbstractAttributeMap map = entity.getAttributeMap();
            if (map.getAttributeInstance(HandicapAttributes.LIMB) == null) {
                map.registerAttribute(HandicapAttributes.LIMB);
            }
            if (map.getAttributeInstance(HandicapAttributes.NO_HEARING) == null) {
                map.registerAttribute(HandicapAttributes.NO_HEARING);
            }
            if (map.getAttributeInstance(HandicapAttributes.COLOR_BLIND) == null) {
                map.registerAttribute(HandicapAttributes.COLOR_BLIND);
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent e) {
        float damage = e.getAmount();
        IAttributeInstance attribute = e.getEntityLiving().getEntityAttribute(HandicapAttributes.LIMB);
        attribute.setBaseValue(attribute.getBaseValue() + Math.floor(damage / 4));
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent e) {
        IAttributeInstance attribute = e.player.getEntityAttribute(HandicapAttributes.LIMB);
        double value = attribute.getAttributeValue();
        if (value < 2) {
            return;
        }
        e.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 7, 2));
        if (value >= 4) {
            e.player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
        }
        return;
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent e) {
        IAttributeInstance attribute = e.getEntityLiving().getEntityAttribute(HandicapAttributes.LIMB);
        double value = attribute.getAttributeValue();
        if (value < 4) {
            return;
        }
        e.getEntityLiving().motionY = 0;
    }
}
