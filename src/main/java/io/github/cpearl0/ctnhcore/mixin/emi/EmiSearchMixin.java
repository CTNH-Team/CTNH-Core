package io.github.cpearl0.ctnhcore.mixin.emi;

import net.minecraft.network.chat.Component;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.screen.EmiScreenManager;
import dev.emi.emi.search.EmiSearch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = EmiSearch.class, remap = false)
public class EmiSearchMixin {

    @Unique
    private static volatile int ctnhcore$searchVersion = 0;

    @Redirect(method = "bake",
              at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getTooltipText()Ljava/util/List;"))
    private static List<Component> noTooltip(EmiStack instance) {
        return null;
    }

    /**
     * @author gpt-5
     * @reason fast
     */
    @Overwrite(remap = false)
    public static void search(String query) {
        final List<? extends EmiIngredient> source = EmiScreenManager.getSearchSource();

        final EmiSearch.CompiledQuery compiled = new EmiSearch.CompiledQuery(query);
        EmiSearch.compiledQuery = compiled;

        // 空查询直接返回
        if (compiled.isEmpty()) {
            synchronized (EmiSearch.class) {
                EmiSearch.stacks = source;
            }
            return;
        }

        // ===== Phase 1: 主线程快速匹配 =====
        final ArrayList<EmiIngredient> fastResult = new ArrayList<>(source.size());
        final ArrayList<EmiIngredient> slowCandidates = new ArrayList<>();

        final boolean bakedReady = EmiSearch.bakedStacks != null;

        for (EmiIngredient ingredient : source) {
            List<EmiStack> stacks = ingredient.getEmiStacks();
            if (stacks.size() != 1) {
                continue;
            }

            EmiStack stack = stacks.get(0);

            boolean matched = false;

            if (bakedReady && EmiSearch.bakedStacks.contains(stack)) {
                matched = compiled.fullQuery.matches(stack);
            }

            if (matched) {
                fastResult.add(ingredient);
            } else {
                slowCandidates.add(ingredient);
            }
        }

        // 立即应用快速结果
        synchronized (EmiSearch.class) {
            EmiSearch.stacks = fastResult;
            ctnhcore$searchVersion++;
        }

        // ===== Phase 2: 后台慢匹配 =====
        // Thread slowThread = new Thread(() -> {
        // try {
        // ArrayList<EmiIngredient> finalResult = new ArrayList<>(fastResult);
        // var version = ctnhcore$searchVersion;
        // for (EmiIngredient ingredient : slowCandidates) {
        // if(version != ctnhcore$searchVersion)
        // return;
        // List<EmiStack> stacks = ingredient.getEmiStacks();
        // if (stacks.size() != 1) {
        // continue;
        // }
        //
        // EmiStack stack = stacks.get(0);
        //
        // if (compiled.fullQuery.matchesUnbaked(stack)) {
        // finalResult.add(ingredient);
        // }
        // }
        //
        // // 原子替换结果
        // synchronized (EmiSearch.class) {
        // // 确保 query 未发生变化
        // if (EmiSearch.compiledQuery == compiled) {
        // EmiSearch.stacks = finalResult;
        // }
        // }
        // } catch (Exception e) {
        // EmiLog.error("Error during async EMI search:", e);
        // }
        // }, "EMI-Search-Unbaked");
        //
        // slowThread.setDaemon(true);
        // slowThread.start();
    }
}
