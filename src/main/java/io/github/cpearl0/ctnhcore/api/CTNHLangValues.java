package io.github.cpearl0.ctnhcore.api;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

@Prefix("gui")
public class CTNHLangValues {
    @CN("§a已启用")
    @EN("§aEnabled")
    public static Lang ENABLED;
    @CN("§c已禁用")
    @EN("§cDisabled")
    public static Lang DISABLED;

    @CN("物品输入槽保护")
    @EN("Input Slot Protect")
    public static Lang allow_same_item_in_title;
    @CN("允许相同物品占据多个输入槽位")
    @EN("Allow same items to occupy multiple input slots")
    public static Lang allow_same_item_in_tooltip;
    @CN("物品输出槽保护")
    @EN("Output Slot Protect")
    public static Lang allow_same_item_out_title;
    @CN("允许相同物品占据多个输出槽位")
    @EN("Allow same items to occupy multiple output slots")
    public static Lang allow_same_item_out_tooltip;
    @CN("流体输入槽保护")
    @EN("Input Tank Protect")
    public static Lang allow_same_fluid_in_title;
    @CN("允许相同流体占据多个输入槽位")
    @EN("Allow same fluids to occupy multiple input tanks")
    public static Lang allow_same_fluid_in_tooltip;
    @CN("流体输出槽保护")
    @EN("Output Tank Protect")
    public static Lang allow_same_fluid_out_title;
    @CN("允许相同流体占据多个输出槽位")
    @EN("Allow same fluids to occupy multiple output tanks")
    public static Lang allow_same_fluid_out_tooltip;

    @CN("不")
    @EN("NOT ")
    public static Lang not_allow;

}
