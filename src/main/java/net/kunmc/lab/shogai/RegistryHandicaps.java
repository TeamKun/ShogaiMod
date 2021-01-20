package net.kunmc.lab.shogai;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Shogai.MOD_ID)
public class RegistryHandicaps {

    public static final Potion LIMB = new HandicapPotion(true, 0x000000).setPotionName("limbHandicap").setRegistryName("limbHandicap").registerPotionAttributeModifier(HandicapAttributes.LIMB, "B1B267C4-F259-11EA-ADC1-0242AC120002", 1, Constants.AttributeModifierOperation.ADD);
    public static final Potion NO_HEARING = new HandicapPotion(true, 0x000000).setPotionName("noHearing").setRegistryName("noHearing").registerPotionAttributeModifier(HandicapAttributes.NO_HEARING, "4896b258-587b-11eb-ae93-0242ac130002", 1, Constants.AttributeModifierOperation.ADD);
    public static final Potion COLOR_BLIND = new HandicapPotion(true, 0x000000).setPotionName("colorBlind").setRegistryName("colorBlind").registerPotionAttributeModifier(HandicapAttributes.COLOR_BLIND, "85c621b4-59ab-11eb-ae93-0242ac130002", 1, Constants.AttributeModifierOperation.ADD);
    public static final PotionType potion_limb = new PotionType(new PotionEffect(LIMB, 3600)).setRegistryName("potion_limbHandicap");
    public static final PotionType potion_noHearing = new PotionType(new PotionEffect(NO_HEARING, 3600)).setRegistryName("potion_noHearing");
    public static final PotionType potion_colorBlind = new PotionType(new PotionEffect(COLOR_BLIND, 3600)).setRegistryName("potion_colorBlind");

    @SubscribeEvent
    public static void RegisterPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(LIMB);
        event.getRegistry().register(NO_HEARING);
        event.getRegistry().register(COLOR_BLIND);
    }

    @SubscribeEvent
    public static void RegisterPotionTypes(RegistryEvent.Register<PotionType> event) {
        event.getRegistry().register(potion_limb);
        event.getRegistry().register(potion_noHearing);
        event.getRegistry().register(potion_colorBlind);
    }
}
