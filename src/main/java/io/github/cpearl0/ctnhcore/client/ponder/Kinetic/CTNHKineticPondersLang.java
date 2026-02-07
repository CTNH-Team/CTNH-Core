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
}
