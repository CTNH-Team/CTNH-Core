package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Key;

public class CTNHKineticPondersLang {
    //在思索内写的Lang转换成字符串时会变成滚木翻译键
    @Key("ponder.big_dam_common.title")
    @CN("三峡大坝")
    @EN("Big Dam")
    static Lang BigDamCommonTitle;
    @Key("ponder.big_dam_common.header")
    @CN("如何搭建三峡大坝")
    @EN("How to build Big Dam")
    static Lang BigDamCommonHeader;
    @Key("ponder.big_dam_common.text_1")
    @CN("放置三峡大坝主方块")
    @EN("Place Big Dam Controller Block")
    static Lang BigDamCommonText1;
    @Key("ponder.big_dam_common.text_2")
    @CN("使用任意终端蹲下右键进行一键放置")
    @EN("Use any terminal, sneak down, and right-click for one-click placement")
    static Lang BigDamCommonText2;
    @Key("ponder.big_dam_work.header")
    @CN("如何使三峡大坝工作")
    @EN("How to make the Big Dam work")
    static Lang BigDamWorkHeader;
    @Key("ponder.big_dam_work.text_1")
    @CN("你可以这些地方放置接口，如输入仓，应力输出仓等")
    @EN("You can place interfaces in these places, such as fluid input hatch, kinetic output hatch, etc")
    static Lang BigDamWorkText1;
    @Key("ponder.big_dam_work.text_2")
    @CN("不过需要注意的是，如果你没有放置足够的应力输出仓，三峡大坝的应力不会被完全输出")
    @EN("However, it should be noted that if you do not place enough kinetic output hatch, the stress of the Big Dam will not be fully outputted")
    static Lang BigDamWorkText2;
    @Key("ponder.big_dam_work.text_3")
    @CN("现在只需要输入润滑油，就能使三峡大坝输出应力了")
    @EN("Now, just by inputting lubricating oil, the Big Dam can output stress")
    static Lang BigDamWorkText3;
    @Key("ponder.smashing_factory_common.title")
    @CN("粉碎工厂")
    @EN("Smashing Factory")
    static Lang SmashingFactoryTitle;
    @Key("ponder.smashing_factory_common.header")
    @CN("如何搭建粉碎工厂")
    @EN("How to build Smashing Factory")
    static Lang SmashingFactoryHeader;
    @Key("ponder.smashing_factory_common.text_1")
    @CN("首先，你需要一个粉碎工厂主方块")
    @EN("First, you need a smashing factory main block")
    static Lang SmashingFactoryText1;
    @Key("ponder.smashing_factory_common.text_2")
    @CN("使用终端蹲下右键进行一键放置")
    @EN("One click placement using the terminal")
    static Lang SmashingFactoryText2;
    @Key("ponder.smashing_factory_common.text_3")
    @CN("由于终端的问题，终端放置的粉碎轮方向不正确，而是以这种形式出现")
    @EN("Due to issues with the terminal, the terminal placed crushing wheel is not placed in the correct direction, but rather appears in this form")
    static Lang SmashingFactoryText3;
    @Key("ponder.smashing_factory_common.text_4")
    @CN("此时，我们需要手动替换为正确方向的粉碎轮")
    @EN("At this point, we need to manually replace the crushing wheel in the correct direction")
    static Lang SmashingFactoryText4;
    @Key("ponder.smashing_factory_common.text_5")
    @CN("接入应力")
    @EN("Access stress")
    static Lang SmashingFactoryText5;
    @Key("ponder.smashing_factory_common.text_6")
    @CN("如果粉碎工厂中有多个不同等级的应力输入箱，实际运行速度将根据最高等级的应力输入箱计算")
    @EN("If there are multiple kinetic input hatch of different levels in the smashing factory, the actual operating speed will be calculated based on the highest level of kinetic input hatch")
    static Lang SmashingFactoryText6;
    @Key("ponder.smashing_factory_common.text_7")
    @CN("一些配方还需要使用机械升级仓进行升级后才能运行")
    @EN("Some recipes also require upgrading using a mechanical upgrade bus before they can be executed")
    static Lang SmashingFactoryText7;
    @Key("ponder.smashing_factory_common.text_8")
    @CN("现在你可以正常使用粉碎工厂了。请注意，此机器不会产生任何研磨副产物")
    @EN("Now you can use the smashing factory normally. Please note that this machine does not produce any grinding by-products")
    static Lang SmashingFactoryText8;
}
