package net.kunmc.lab.shogai;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class HandicapAttributes {
    public static final IAttribute LIMB = new RangedAttribute((IAttribute) null, "handicap.limb", 0.0F, 0.0F, Float.MAX_VALUE).setDescription("Handicap Limb").setShouldWatch(true);
    public static final IAttribute NO_HEARING = new RangedAttribute((IAttribute) null, "handicap.noHearing", 0.0F, 0.0F, Float.MAX_VALUE).setDescription("Handicap no Hearing").setShouldWatch(true);
}
