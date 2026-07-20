package io.github.cpearl0.ctnhcore.registry.adventure;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.ctnhlang.Key;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.common.enchantment.TemperatureEnchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CTNHEnchantments {

    @Key("enchantment.ctnhcore.cooling.desc")
    @CN("增强御寒能力")
    @EN("Improves resistance to cold")
    public static Lang enchantmentCoolingDesc;


    @Key("enchantment.ctnhcore.warming.desc")
    @CN("增强御暑能力")
    @EN("Improves resistance to heat")
    public static Lang enchantmentWarmingDesc;



    public static DeferredRegister<Enchantment> Enchantments = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
            CTNHCore.MODID);
    public static final RegistryObject<TemperatureEnchantment> WARMING = Enchantments.register("warming",
            () -> new TemperatureEnchantment(false));
    public static final RegistryObject<TemperatureEnchantment> COOLING = Enchantments.register("cooling",
            () -> new TemperatureEnchantment(true));
}
