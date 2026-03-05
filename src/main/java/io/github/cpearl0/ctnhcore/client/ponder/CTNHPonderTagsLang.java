package io.github.cpearl0.ctnhcore.client.ponder;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Key;

public class CTNHPonderTagsLang {

    @Key("ponder.tag.kinetic")
    @CN("CTNH机械机器")
    @EN("CTNH Kinetic Machine")
    static Lang Kinetic;
    @Key("ponder.tag.electric")
    @CN("CTNH电力机器")
    @EN("CTNH Electric Machine")
    static Lang Electric;
    @Key("ponder.tag.kinetic.description")
    @CN("CTNH机械机器思索")
    @EN("CTNH Kinetic Machine Ponders")
    static Lang KineticDescription;
    @Key("ponder.tag.electric.description")
    @CN("CTNH电力机器思索")
    @EN("CTNH Electric Machine Ponders")
    static Lang ElectricDescription;
}
