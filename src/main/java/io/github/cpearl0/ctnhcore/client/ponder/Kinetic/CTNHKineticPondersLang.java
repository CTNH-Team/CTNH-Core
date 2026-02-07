package io.github.cpearl0.ctnhcore.client.ponder.Kinetic;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Key;

public class KineticPonderLang {
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
    @CN("Use any terminal, sneak down, and right-click for one-click placement")
    @EN("Place Big Dam Controller Block")
    static Lang BigDamCommonText2;
}
