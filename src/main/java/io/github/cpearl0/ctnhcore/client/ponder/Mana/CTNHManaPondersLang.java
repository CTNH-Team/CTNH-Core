package io.github.cpearl0.ctnhcore.client.ponder.Mana;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Key;

public class CTNHManaPondersLang {

    // 奥法尖塔
    @Key("ponder.mystic_spire_scene1.title")
    @CN("奥法尖塔")
    @EN("Mystic Spire")
    static Lang MysticSpiresTitle;
    @Key("ponder.mystic_spire_scene1.header")
    @CN("奥法尖塔")
    @EN("Mystic Spire")
    static Lang MysticSpireScene1Header;
    @Key("ponder.mystic_spire_scene1.text_1")
    @CN("奥法尖塔用于远距离魔力传输")
    @EN("Mystic Spire is used for remote magic power transmission")
    static Lang MysticSpireScene1Text1;
    @Key("ponder.mystic_spire_scene1.text_2")
    @CN("尖塔结构成型后，会在塔尖生成德尔塔火花")
    @EN("After shaping the structure, a delta spark will be generated at the top of the tower")
    static Lang MysticSpireScene1Text2;
    @Key("ponder.mystic_spire_scene1.text_3")
    @CN("使用电子精灵法杖右键德尔塔火花绑定两个尖塔")
    @EN("Use the electronic spirit staff right-click the delta spark to bind two towers")
    static Lang MysticSpireScene1Text3;
    @Key("ponder.mystic_spire_scene1.text_4")
    @CN("第一个设置为接收端")
    @EN("The first one is set as the receiver")
    static Lang MysticSpireScene1Text4;
    @Key("ponder.mystic_spire_scene1.text_5")
    @CN("第二个设置为发射端")
    @EN("The second one is set as the sender")
    static Lang MysticSpireScene1Text5;
    @Key("ponder.mystic_spire_scene1.text_6")
    @CN("shift右键取消绑定")
    @EN("Shift right-click to cancel the binding")
    static Lang MysticSpireScene1Text6;
    @Key("ponder.mystic_spire_scene2.header")
    @CN("奥法尖塔的属性")
    @EN("Mystic Spire's properties")
    static Lang MysticSpireScene2Header;
    @Key("ponder.mystic_spire_scene2.text_1")
    @CN("奥法尖塔有四种属性：容量，输出速度，接收速度，范围")
    @EN("Mystic Spire has four properties: capacity, output speed, receiving speed, range")
    static Lang MysticSpireScene2Text1;
    @Key("ponder.mystic_spire_scene2.text_2")
    @CN("在尖塔主方块UI中放入尖塔升级以提升其属性")
    @EN("Put the mystic spire upgrade in the main block UI to improve its properties")
    static Lang MysticSpireScene2Text2;
    @Key("ponder.mystic_spire_scene2.text_3")
    @CN("容量是尖塔的魔力缓存量，初始为1000000，最大为INT")
    @EN("Capacity is the amount of magic power cached by the tower, initialized to 1000000, maximum is INT")
    static Lang MysticSpireScene2Text3;
    @Key("ponder.mystic_spire_scene2.text_4")
    @CN("输出速度决定尖塔主动输出的速度，初始为10000/tick，最大不超过容量的1/10")
    @EN("Output speed determines the speed at which the tower actively outputs, initialized to 10000/tick, maximum not exceeding 1/10 of the capacity")
    static Lang MysticSpireScene2Text4;
    @Key("ponder.mystic_spire_scene2.text_5")
    @CN("接收速度决定尖塔吸取魔力的速度，初始为10000/tick")
    @EN("Receiving speed determines the speed at which the tower absorbs magic power, initialized to 10000/tick")
    static Lang MysticSpireScene2Text5;
    @Key("ponder.mystic_spire_scene2.text_6")
    @CN("吸收产魔花魔力速度为接受速度的1/10，转位符文可提升此速度，取符文效果最大值")
    @EN("The absorption and production of magic power speed is 10% of the receiving speed, which can be boosted by the transposition rune, and the maximum value is taken")
    static Lang MysticSpireScene2Text6;
    @Key("ponder.mystic_spire_scene2.text_7")
    @CN("范围决定了尖塔的影响范围，初始为15，最大为50")
    @EN("Range determines the effect range of the tower, initialized to 15, maximum is 50")
    static Lang MysticSpireScene2Text7;
    @Key("ponder.mystic_spire_scene3.header")
    @CN("奥法尖塔的模式")
    @EN("Mystic Spire's mode")
    static Lang MysticSpireScene3Header;
    @Key("ponder.mystic_spire_scene3.text_1")
    @CN("奥法尖塔有四种工作模式：聚焦，火花扩散，广域扩散，中转")
    @EN("Mystic Spire has four working modes: focus, spark spread, wide spread, transfer")
    static Lang MysticSpireScene3Text1;
    @Key("ponder.mystic_spire_scene3.text_2")
    @CN("聚焦模式下，尖塔主动吸取周围同色火花或产魔花的魔力")
    @EN("In focus mode, the tower actively absorbs the magic power of nearby same-colored sparks or magic flowers")
    static Lang MysticSpireScene3Text2;
    @Key("ponder.mystic_spire_scene3.text_3")
    @CN("火花扩散模式下，尖塔将魔力主动输出到周围同色火花或者魔力凝聚仓中")
    @EN("In spark spread mode, the tower actively outputs magic power to nearby same-colored sparks or magic condensers")
    static Lang MysticSpireScene3Text3;
    @Key("ponder.mystic_spire_scene3.text_4")
    @CN("广域扩散模式下，尖塔无视一切限制，向范围内非德尔塔火花的魔力容器传递魔力")
    @EN("In wide spread mode, the tower ignores all restrictions and passes the magic power to the non-delta fireflower within the range")
    static Lang MysticSpireScene3Text4;
    @Key("ponder.mystic_spire_scene3.text_5")
    @CN("中转模式下，尖塔不执行任何操作，只实现尖塔间的魔力传递")
    @EN("In transfer mode, the tower does not perform any operations, only implementing the transfer of magic power between towers")
    static Lang MysticSpireScene3Text5;
    @Key("ponder.mystic_spire_scene3.text_6")
    @CN("注意：广域扩散不会向输出模式的魔力池转递魔力")
    @EN("Note: Wide Spread will not transfer magic power to the output mode's magic pool")
    static Lang MysticSpireScene3Text6;
}
