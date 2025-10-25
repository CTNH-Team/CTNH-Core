package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.tterrag.registrate.util.entry.ItemEntry;
import io.github.cpearl0.ctnhcore.common.item.*;
import io.github.cpearl0.ctnhcore.data.materials.ChemicalItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;
import vazkii.botania.common.lib.BotaniaTags;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

@Prefix("item")
public class CTNHItems {
    static {
        REGISTRATE.creativeModeTab(() -> CTNHCreativeModeTabs.ITEM);
    }
    @CN("§7用于制作T1重型合金板")
    @EN("§7Used for making Heavy Alloy Plate T1")
    static Lang heavy_ingot_t1;
    @CN("§7用于制作T2重型合金板")
    @EN("§7Used for making Heavy Alloy Plate T2")
    static Lang heavy_ingot_t2;
    @CN("§7用于制作T3重型合金板")
    @EN("§7Used for making Heavy Alloy Plate T3")
    static Lang heavy_ingot_t3;
    @CN("§7用于制作T4重型合金板")
    @EN("§7Used for making Heavy Alloy Plate T4")
    static Lang heavy_ingot_t4;
    @CN("§71阶")
    @EN("§7T1")
    static Lang heavy_plate_t1;
    @CN("§72阶")
    @EN("§7T2")
    static Lang heavy_plate_t2;
    @CN("§73阶")
    @EN("§7T3")
    static Lang heavy_plate_t3;
    @CN("§74阶")
    @EN("§7T4")
    static Lang heavy_plate_t4;
    @CN("§7§o用于制作1阶火箭")
    @EN("§7Used for making Rocket T1")
    static Lang chip_t1;
    @CN("§7§o用于制作2阶火箭")
    @EN("§7Used for making Rocket T2")
    static Lang chip_t2;
    @CN("§7§o用于制作3阶火箭")
    @EN("§7Used for making Rocket T3")
    static Lang chip_t3;
    @CN("§7§o用于制作4阶火箭")
    @EN("§7Used for making Rocket T4")
    static Lang chip_t4;
    public static void registerItem() {
        GREAT_ASTRONOMY_CIRCUIT_1 = REGISTRATE
                .item("great_astronomy_circuit_1", Item::new)
                .cnlang("完善的一阶航天数据芯片")
                .lang("Great Astronomy Circuit I")
                .register();
        SIMPLE_NUTRITIOUS_MEAL = REGISTRATE
                .item("simple_nutritious_meal", ComponentItem::create)
                .cnlang("简易营养餐")
                .lang("Simple Nutritious meal")
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.simple_nutritious_meal.tooltip.1").withStyle(ChatFormatting.GRAY));
                })))
                .register();
        ECOLOGICAL_STAR = REGISTRATE
                .item("ecological_star", ComponentItem::create)
                .cnlang("生态之星")
                .lang("Ecological Star")
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("item.ctnh.ecological_star.desc").withStyle(ChatFormatting.GREEN));
                })))
                .register();
        SCULK_CELL = REGISTRATE
                .item("sculk_cell", ComponentItem::create)
                .cnlang("幽匿干细胞")
                .lang("Sculk Cell")
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("item.sculk_cell.desc").withStyle(ChatFormatting.DARK_GRAY));
                })))
                .register();
        ANIMAL_EXCRETA = REGISTRATE
                .item("animal_excreta",Item::new)
                .cnlang("动物排泄物")
                .lang("Animal Excreta")
                .register();
        TUMOR = REGISTRATE
                .item("tumor",Item::new)
                .cnlang("肿瘤")
                .lang("Tumor")
                .register();
        REFINED_IRON_INGOT = REGISTRATE
                .item("refined_iron_ingot",Item::new)
                .cnlang("精炼铁方坯")
                .lang("Refined Iron Ingot")
                .register();
        HORIZEN_RUNE = REGISTRATE
                .item("horizen_rune",ComponentItem::create)
                .cnlang("§5视域§r符文")
                .lang("§5Horizen§r Rune")
                .tag(BotaniaTags.Items.RUNES,CTNHTags.TIER5_RUNES)
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.item.runes.horizen_rune").withStyle(ChatFormatting.DARK_PURPLE));
                })))
                .register();
        STARLIGHT_RUNE = REGISTRATE
                .item("starlight_rune",ComponentItem::create)
                .cnlang("§9星光§r符文")
                .lang("§9Starlight§r Rune")
                .tag(BotaniaTags.Items.RUNES,CTNHTags.TIER5_RUNES)
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.item.runes.starlight_rune").withStyle(ChatFormatting.BLUE));
                })))
                .register();
        TWIST_RUNE = REGISTRATE
                .item("twist_rune",ComponentItem::create)
                .cnlang("§c扭曲§r符文")
                .lang("§cTwist§r Rune")
                .tag(BotaniaTags.Items.RUNES,CTNHTags.TIER5_RUNES)
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.item.runes.twist_rune").withStyle(ChatFormatting.RED));
                })))
                .register();
        QUASAR_RUNE = REGISTRATE
                .item("quasar_rune",ComponentItem::create)
                .cnlang("§k类星体§r符文")
                .lang("§kQuasar§r Rune")
                .tag(BotaniaTags.Items.RUNES,CTNHTags.TIER5_RUNES)
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.item.runes.quasar_rune").withStyle(ChatFormatting.LIGHT_PURPLE));
                })))
                .register();
        PROLIFERATION_RUNE = REGISTRATE
                .item("proliferation_rune",ComponentItem::create)
                .cnlang("§a增殖§r符文")
                .lang("§aProliferation§r Rune")
                .tag(BotaniaTags.Items.RUNES,CTNHTags.TIER5_RUNES)
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.item.runes.proliferation_rune").withStyle(ChatFormatting.GREEN));
                })))
                .register();
        ANTI_INF_MATTER =REGISTRATE
                .item("anti_inf_matter",ComponentItem::create)
                .cnlang("§0反无穷聚合体")
                .lang("§0Anti Inf Matter")
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.anti_inf_matter.1").withStyle(ChatFormatting.BLACK));
                    list.add(Component.translatable("ctnh.anti_inf_matter.2").withStyle(ChatFormatting.DARK_GRAY));
                })))
                .register();
        TESTING_TERMINAL = REGISTRATE
                .item("testing_terminal",ComponentItem::create)
                .cnlang("检测终端")
                .lang("Test Terminal")
                .properties(p -> p.stacksTo(1))
                .onRegister(attach(new TestingTerminalBehavior()))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.testing_terminal.tooltip.1").withStyle(ChatFormatting.GRAY));
                    list.add(Component.translatable("ctnh.testing_terminal.tooltip.2"));
                })))
                .register();
        ME_ADVANCED_TERMINAL = REGISTRATE
                .item("me_advanced_terminal",
                        MEAdvancedTerminalItem::new
                )
                .cnlang("GT-MBST-A v7.0.1")
                .lang("GT-MBST-A v7.0.1")
                .properties(p -> p.stacksTo(1))
                .onRegister(attach(new MEAdvancedTerminalBehavior()))
//            .model((ctx, prov) -> prov.generated(ctx))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.me_advanced_terminal.tooltip.1"));
                    list.add(Component.translatable("ctnh.me_advanced_terminal.tooltip.2").withStyle(ChatFormatting.GRAY));
                    //list.add(Component.translatable("ctnh.me_advanced_terminal.tooltip.3"));
                })))
                .register();
        BOSS_SUMMONER = REGISTRATE
                .item("boss_summoner", ThrowItem::new)
                .cnlang("boss召唤器")
                .lang("Boss Summoner")
                .onRegister(attach(new BossSummonerBehavior(1)))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.boss_summoner.use").withStyle(ChatFormatting.RED));
                })))
                .register();
        ADVANCED_BOSS_SUMMONER = REGISTRATE
                .item("advanced_boss_summoner", ThrowItem::new)
                .cnlang("进阶boss召唤器")
                .lang("Advanced Boss Summoner")
                .onRegister(attach(new BossSummonerBehavior(2)))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.boss_summoner.use").withStyle(ChatFormatting.DARK_RED));
                })))
                .register();
        PV_DRONE_PROTOTYPE = REGISTRATE
                .item("photovoltaic_drone_prototype",holder->new IDroneItem(holder,0,512,16, () -> Items.AIR))
                .cnlang("光伏无人机原型")
                .lang("pv_drone_prototype")
                .register();
        PV_DRONE_TIER1 = REGISTRATE
                .item("photovoltaic_drone_tier1",holder->new IDroneItem(holder,1,8192*2,64, () -> Items.AIR))
                .cnlang("标准化光伏无人机")
                .lang("pv_drone_tier1")
                .register();
        PV_DRONE_TIER2 = REGISTRATE
                .item("photovoltaic_drone_tier2",holder->new IDroneItem(holder,2,32678*2,256, () -> Items.AIR))
                .cnlang("共振结构化光伏无人机")
                .lang("pv_drone_tier2")
                .register();
        PV_TERMINAL = REGISTRATE
                .item("pv_terminal",holder->new ConnectTerminalItem(holder))
                .cnlang("光伏绑定终端")
                .lang("pv_terminal")
                .register();
        multiblockHelper = REGISTRATE
                .item("mutiblock_helper",holder->new MultiblockHelper(holder))
                .lang("mutiblock_helper")
                .register();
        RESEARCH_DATASET = REGISTRATE
                .item("research_dataset",holder->new IDataItem(holder))
                .cnlang("研究数据集")
                .lang("research_dataset")
                .register();
        RESEARCH_DATASET_LIVING_MATERIAL = REGISTRATE
                .item("research_dataset_lm",holder->new IDataItem(holder))
                .cnlang("研究数据集：活体金属")
                .lang("research_dataset_lm")
                .register();
        HEAVY_INGOT_T1 = REGISTRATE
                .item("heavy_ingot_t1", ComponentItem::create)
                .cnlang("T1重型锭")
                .lang("Heavy Alloy Ingot T1")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_ingot_t1.translate())
                )))
                .register();
        HEAVY_INGOT_T2 = REGISTRATE
                .item("heavy_ingot_t2", ComponentItem::create)
                .cnlang("T2重型锭")
                .lang("Heavy Alloy Ingot T2")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_ingot_t2.translate())
                )))
                .register();
        HEAVY_INGOT_T3 = REGISTRATE
                .item("heavy_ingot_t3", ComponentItem::create)
                .cnlang("T3重型锭")
                .lang("Heavy Alloy Ingot T3")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_ingot_t3.translate())
                )))
                .register();
        HEAVY_INGOT_T4 = REGISTRATE
                .item("heavy_ingot_t4", ComponentItem::create)
                .cnlang("T4重型锭")
                .lang("Heavy Alloy Ingot T4")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_ingot_t4.translate())
                )))
                .register();
        HEAVY_PLATE_T1 = REGISTRATE
                .item("heavy_plate_t1", ComponentItem::create)
                .cnlang("T1重型合金板")
                .lang("Heavy Alloy Plate T1")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_plate_t1.translate())
                )))
                .register();
        HEAVY_PLATE_T2 = REGISTRATE
                .item("heavy_plate_t2", ComponentItem::create)
                .cnlang("T2重型合金板")
                .lang("Heavy Alloy Plate T2")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_plate_t2.translate())
                )))
                .register();
        HEAVY_PLATE_T3 = REGISTRATE
                .item("heavy_plate_t3", ComponentItem::create)
                .cnlang("T3重型合金板")
                .lang("Heavy Alloy Plate T3")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_plate_t3.translate())
                )))
                .register();
        HEAVY_PLATE_T4 = REGISTRATE
                .item("heavy_plate_t4", ComponentItem::create)
                .cnlang("T4重型合金板")
                .lang("Heavy Alloy Plate T4")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(heavy_plate_t4.translate())
                )))
                .register();
        CHIP_T1 = REGISTRATE
                .item("t1_chip", ComponentItem::create)
                .cnlang("T1芯片")
                .lang("Chip T1")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(chip_t1.translate())
                )))
                .register();
        CHIP_T2 = REGISTRATE
                .item("t2_chip", ComponentItem::create)
                .cnlang("T2芯片")
                .lang("Chip T2")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(chip_t2.translate())
                )))
                .register();
        CHIP_T3 = REGISTRATE
                .item("t3_chip", ComponentItem::create)
                .cnlang("T3芯片")
                .lang("Chip T3")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(chip_t3.translate())
                )))
                .register();
        CHIP_T4 = REGISTRATE
                .item("t4_chip", ComponentItem::create)
                .cnlang("T4芯片")
                .lang("Chip T4")
                .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(text ->
                        text.add(chip_t4.translate())
                )))
                .register();
        ADVANCED_RAM_WAFER = REGISTRATE
                .item("advanced_ram_wafer", ComponentItem::create)
                .cnlang("进阶RAM晶圆")
                .lang("Advanced Ram Wafer")
                .properties(p -> new Item.Properties().rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.advanced_ram_wafer.tooltip").withStyle(ChatFormatting.YELLOW));
                })))
                .register();
        ADVANCED_RAM_CHIP = REGISTRATE
                .item("advanced_ram_chip", ComponentItem::create)
                .cnlang("进阶RAM芯片")
                .lang("Advanced Ram Chip")
                .properties(p -> new Item.Properties().rarity(Rarity.UNCOMMON))
                .onRegister(attach(new TooltipBehavior(list -> {
                    list.add(Component.translatable("ctnh.advanced_ram_chip.tooltip").withStyle(ChatFormatting.YELLOW));
                })))
                .register();
    }
    public static ItemEntry<Item> GREAT_ASTRONOMY_CIRCUIT_1;
    public static ItemEntry<ComponentItem> SIMPLE_NUTRITIOUS_MEAL;
    public static ItemEntry<ComponentItem> ECOLOGICAL_STAR;
    public static ItemEntry<ComponentItem> SCULK_CELL;
    public static ItemEntry<Item> ANIMAL_EXCRETA;
    public static ItemEntry<Item> TUMOR;
    public static ItemEntry<Item> REFINED_IRON_INGOT;
    public static ItemEntry<Item> CORROSIVE_CORE = REGISTRATE
            .item("corrosive_core",Item::new)
            .cnlang("腐蚀核心")
            .lang("Corrosive Core")
            .register();
    public static ItemEntry<Item> VENGEFUL_CORE = REGISTRATE
            .item("vengeful_core",Item::new)
            .cnlang("复仇核心")
            .lang("Vengeful Core")
            .register();
    public static ItemEntry<Item> DESTRUCTIVE_CORE = REGISTRATE
            .item("destructive_core",Item::new)
            .cnlang("破坏核心")
            .lang("Destructive Core")
            .register();
    public static ItemEntry<Item> STEADFAST_CORE = REGISTRATE
            .item("steadfast_core",Item::new)
            .cnlang("坚毅核心")
            .lang("Steadfast Core")
            .register();
    public static ItemEntry<Item> CRYSTAL_CATALYST = REGISTRATE
            .item("crystal_catalyst", Item::new)
            .cnlang("水晶催化剂")
            .lang("Crystal Catalyst")
            .register();
    public static ItemEntry<ComponentItem> HORIZEN_RUNE;
    public static ItemEntry<ComponentItem> STARLIGHT_RUNE;
    public static ItemEntry<ComponentItem> TWIST_RUNE;
    public static ItemEntry<ComponentItem> QUASAR_RUNE;
    public static ItemEntry<ComponentItem> PROLIFERATION_RUNE;
    public static ItemEntry<ComponentItem> ANTI_INF_MATTER;
    public static ItemEntry<ComponentItem> TESTING_TERMINAL;
    public static ItemEntry<MEAdvancedTerminalItem> ME_ADVANCED_TERMINAL;
    public static ItemEntry<AstronomyCircuitItem> ASTRONOMY_CIRCUIT_1 = REGISTRATE
            .item("astronomy_circuit_1", properties -> new AstronomyCircuitItem(properties, 1, GREAT_ASTRONOMY_CIRCUIT_1))
            .cnlang("一阶航天数据芯片")
            .lang("Astronomy Circuit I")
            .register();
    public static ItemEntry<ThrowItem> BOSS_SUMMONER;
    public static ItemEntry<ThrowItem> ADVANCED_BOSS_SUMMONER;
    public static ItemEntry<IDroneItem> PV_DRONE_PROTOTYPE;
    public static ItemEntry<IDroneItem> PV_DRONE_TIER1;
    public static ItemEntry<IDroneItem> PV_DRONE_TIER2;
    public static ItemEntry<ConnectTerminalItem> PV_TERMINAL;
    public static ItemEntry<MultiblockHelper> multiblockHelper;
    public static ItemEntry<IDataItem> RESEARCH_DATASET;
    public static ItemEntry<IDataItem> RESEARCH_DATASET_LIVING_MATERIAL;

    public static ItemEntry<Item> NUCLEAR_WASTE = REGISTRATE
            .item("nuclear_waste", Item::new)
            .cnlang("核废料")
            .lang("Nuclear Waste")
            .register();
    public static ItemEntry<Item> LEVEL_ITEM=REGISTRATE
            .item("level",Item::new)
            .lang("level")
            .register();
    public static ItemEntry<ProgramItem> PROGRAM_EMPTY = REGISTRATE
            .item("program_empty", ProgramItem::new)
            .cnlang("空白程序")
            .lang("Empty Program")
            .register();
    public static ItemEntry<ProgramItem> PROGRAM_ROCKET_CORE_1 = REGISTRATE
            .item("program_rocket_core_1", ProgramItem::new)
            .cnlang("一阶火箭核心代码")
            .lang("Tier 1 Rocket Core Program")
            .register();
    public static ItemEntry<ProgramItem> PROGRAM_ROCKET_1 = REGISTRATE
            .item("program_rocket_1", ProgramItem::new)
            .cnlang("一阶火箭控制代码")
            .lang("Tier 1 Rocket Control Program")
            .register();

    public static ItemEntry<Item> RADIOACTIVE_WASTE = REGISTRATE
            .item("radioactive_waste", Item::new)
            .cnlang("放射性废料")
            .lang("Radioactive Waste")
            .properties(properties -> properties.rarity(Rarity.UNCOMMON))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T1;
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T2;
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T3;
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T4;
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T1;
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T2;
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T3;
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T4;
    public static ItemEntry<ComponentItem> CHIP_T1;
    public static ItemEntry<ComponentItem> CHIP_T2;
    public static ItemEntry<ComponentItem> CHIP_T3;
    public static ItemEntry<ComponentItem> CHIP_T4;
    public static ItemEntry<ComponentItem> INVERTER = REGISTRATE
            .item("inverter", ComponentItem::create)
            .cnlang("逆变器")
            .lang("Inverter")
            .register();

    public static ItemEntry<ComponentItem> EncapsulatedUranium = REGISTRATE
            .item("encapsulated_uranium", ComponentItem::create)
            .cnlang("封装铀")
            .lang("Encapsulated Uranium")
            .register();

    public static ItemEntry<ComponentItem> EnrichedUraniumNugget = REGISTRATE
            .item("enriched_uranium_nugget", ComponentItem::create)
            .cnlang("浓缩铀粒")
            .lang("Enriched Uranium Nugget")
            .register();

    public static ItemEntry<ComponentItem> EnrichedUranium = REGISTRATE
            .item("enriched_uranium", ComponentItem::create)
            .cnlang("浓缩铀")
            .lang("Enriched Uranium")
            .register();

    public static ItemEntry<ComponentItem> EncapsulatedThorium = REGISTRATE
            .item("encapsulated_thorium", ComponentItem::create)
            .cnlang("封装钍")
            .lang("Encapsulated Thorium")
            .register();
    public static ItemEntry<ComponentItem> EnrichedThoriumNugget = REGISTRATE
            .item("enriched_thorium_nugget", ComponentItem::create)
            .cnlang("浓缩钍粒")
            .lang("Enriched Thorium Nugget")
            .register();

    public static ItemEntry<ComponentItem> EnrichedThorium = REGISTRATE
            .item("enriched_thorium", ComponentItem::create)
            .cnlang("浓缩钍")
            .lang("Enriched Thorium")
            .register();

    public static ItemEntry<ComponentItem> EncapsulatedPlutonium = REGISTRATE
            .item("encapsulated_plutonium", ComponentItem::create)
            .cnlang("封装钚")
            .lang("Encapsulated Plutonium")
            .register();

    public static ItemEntry<ComponentItem> EnrichedPlutoniumNugget = REGISTRATE
            .item("enriched_plutonium_nugget", ComponentItem::create)
            .cnlang("浓缩钚粒")
            .lang("Enriched Plutonium Nugget")
            .register();

    public static ItemEntry<ComponentItem> EnrichedPlutonium = REGISTRATE
            .item("enriched_plutonium", ComponentItem::create)
            .cnlang("浓缩钚")
            .lang("Enriched Plutonium")
            .register();

    public static ItemEntry<ComponentItem> NeutronSource = REGISTRATE
            .item("neutron_source", ComponentItem::create)
            .cnlang("中子源")
            .lang("Neutron Source")
            .register();

    public static ItemEntry<ComponentItem> QuarkCore = REGISTRATE
            .item("quark_core", ComponentItem::create)
            .cnlang("夸克核心")
            .lang("Quark Core")
            .register();

    public static ItemEntry<ComponentItem> PlateRadiationProtection = REGISTRATE
            .item("plate_radiation_protection", ComponentItem::create)
            .cnlang("防辐射板")
            .lang("Radiation Protection Plate")
            .register();

    public static ItemEntry<ComponentItem> COMPUTER = REGISTRATE
            .item("computer_circuit", ComponentItem::create)
            .cnlang("计算机芯片")
            .lang("Computer Chip")
            .properties(properties -> properties.rarity(Rarity.UNCOMMON))
            .register();

    public static ItemEntry<ComponentItem> COMPUTER_ADVANCED = REGISTRATE
            .item("computer_advanced_circuit", ComponentItem::create)
            .cnlang("高级计算机芯片")
            .lang("Advanced Computer Chip")
            .properties(properties -> properties.rarity(Rarity.RARE))
            .register();

    public static ItemEntry<ComponentItem> ADVANCED_RAM_WAFER;
    public static ItemEntry<ComponentItem> ADVANCED_RAM_CHIP;
    public static void init() {
        registerItem();
        ChemicalItems.init();
    }
}
