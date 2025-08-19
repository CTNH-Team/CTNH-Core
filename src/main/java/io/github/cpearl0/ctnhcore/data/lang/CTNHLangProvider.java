package io.github.cpearl0.ctnhcore.data.lang;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonnullType;
import io.github.cpearl0.ctnhcore.data.CTNHCoreDatagen;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CTNHLangProvider extends RegistrateLangProvider {
    private final AbstractRegistrate<?> owner;
    private final String modId;
    private final RegistrateCNLangProvider simplifiedChinese;
    public CTNHLangProvider(AbstractRegistrate<?> owner, PackOutput packOutput) {
        super(owner, packOutput);
        this.owner = owner;
        this.simplifiedChinese = new RegistrateCNLangProvider(owner, packOutput);
        this.modId = owner.getModid();
    }
    @Override
    public String getName() {
        return "Lang (en_us/en_ud/zh_cn)";
    }

    @Override
    protected void addTranslations() {
        this.owner.genData(CTNHCoreDatagen.CTNHLANG, this);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf(super.run(cache), simplifiedChinese.run(cache));
    }

    public void add(String key, String en, String cn) {
        simplifiedChinese.add(key, cn);
        this.add(key, en);
    }

    public void addCN(String key, String cn) {
        simplifiedChinese.add(key, cn);
    }
    public void addBlock(Block block, String en, String cn) {
        simplifiedChinese.add(block, cn);
        this.add(block, en);
    }
    public void addBlockCN(Block block, String cn) {
        simplifiedChinese.add(block, cn);
    }
    public void addItemWithTooltip(NonNullSupplier<Item> item, String name, String tooltip) {
        addItem(item, name);
        addTooltip(item, tooltip);
    }

    public void addBlockWithTooltip(NonNullSupplier<Block> block, String name, List<@NonnullType String> tooltip) {
        addBlock(block, name);
        addTooltip(block, tooltip);
    }
    public void addItemWithTooltip(
            NonNullSupplier<Item> item,
            String enName,
            String cnName,
            String enTooltip,
            String cnTooltip
    ) {
        simplifiedChinese.addItemWithTooltip(item, cnName, List.of(cnTooltip));
        addItemWithTooltip(item, enName, List.of(enTooltip));
    }

    public void addItemWithTooltip(
            NonNullSupplier<Item> item,
            String cnName,
            String enTooltip,
            String cnTooltip
    ) {
        simplifiedChinese.addItemWithTooltip(item, cnName, List.of(cnTooltip));
        addTooltip(item, enTooltip);
    }

    public void addItemWithTooltip(
            NonNullSupplier<Item> item,
            String enName,
            String cnName,
            List<String> enTooltip,
            List<String> cnTooltip
    ) {
        simplifiedChinese.addItemWithTooltip(item, cnName, cnTooltip);
        addItemWithTooltip(item, enName, enTooltip);
    }

    public void addItemWithTooltip(
            NonNullSupplier<Item> item,
            String cnName,
            List<String> enTooltip,
            List<String> cnTooltip
    ) {
        simplifiedChinese.addItemWithTooltip(item, cnName, cnTooltip);
        addTooltip(item, enTooltip);
    }

    public void addBlockName(NonNullSupplier<Block> block, String enName, String cnName) {
        simplifiedChinese.addBlock(block, cnName);
        addBlock(block, enName);
    }

    public void addBlockName(NonNullSupplier<Block> block, String cnName) {
        simplifiedChinese.addBlock(block, cnName);
    }

    public void addBlockWithTooltip(
            NonNullSupplier<Block> block,
            String enName,
            String cnName,
            String enTooltip,
            String cnTooltip
    ) {
        simplifiedChinese.addBlockWithTooltip(block, cnName, List.of(cnTooltip));
        addBlockWithTooltip(block, enName, enTooltip);
    }

    public void addBlockWithTooltip(
            NonNullSupplier<Block> block,
            String cnName,
            String enTooltip,
            String cnTooltip
    ) {
        simplifiedChinese.addBlockWithTooltip(block, cnName, List.of(cnTooltip));
        addTooltip(block, enTooltip);
    }

    public void addBlockWithTooltip(
            NonNullSupplier<Block> block,
            String enName,
            String cnName,
            List<String> enTooltip,
            List<String> cnTooltip
    ) {
        simplifiedChinese.addBlockWithTooltip(block, cnName, cnTooltip);
        addBlockWithTooltip(block, enName, enTooltip);
    }

    public void addBlockWithTooltip(
            NonNullSupplier<Block> block,
            String cnName,
            List<String> enTooltip,
            List<String> cnTooltip
    ) {
        simplifiedChinese.addBlockWithTooltip(block, cnName, cnTooltip);
        addTooltip(block, enTooltip);
    }

    public void addBlockWithTooltip(String blockName, String enTooltip, String cnTooltip) {
        var key = getBlockKey(modId, blockName) + ".desc";
        simplifiedChinese.add(key, cnTooltip);
        add(key, enTooltip);
    }

    public void addBlockWithTooltip(String blockName, List<String> enTooltip, List<String> cnTooltip) {
        for (int i = 0 ; i < cnTooltip.size(); i++) {
            var key = getBlockKey(modId, blockName) + ".desc." + i;
            simplifiedChinese.add(key, cnTooltip.get(i));
            add(key, enTooltip.get(i));
        }
    }
    public String getBlockKey(String modId, String key) {
        return "block." + modId + "." + key;
    }

    public String getKey(String modId, String key, ResourceKey<Registry<?>> registry) {
        return registry.location().getPath() +  "." + modId + "." + key;
    }
}
