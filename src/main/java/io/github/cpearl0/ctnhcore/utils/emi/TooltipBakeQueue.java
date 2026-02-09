package io.github.cpearl0.ctnhcore.utils.emi;

import net.minecraft.client.searchtree.SuffixArray;
import net.minecraft.network.chat.Component;

import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.runtime.EmiLog;
import dev.emi.emi.search.SearchStack;

import java.util.Iterator;
import java.util.List;

public class TooltipBakeQueue {

    private final Iterator<EmiStack> iterator;
    private final int batchSize = 32;
    public SuffixArray<SearchStack> tooltips = new SuffixArray();

    public static boolean ready = false;

    public static TooltipBakeQueue INSTANCE;

    public TooltipBakeQueue(List<EmiStack> stacks) {
        this.iterator = stacks.iterator();
    }

    public boolean tick() {
        int processed = 0;

        while (iterator.hasNext() && processed++ < batchSize) {
            EmiStack stack = iterator.next();
            try {
                SearchStack searchStack = new SearchStack(stack);
                List<Component> tooltip = stack.getTooltipText();
                if (tooltip != null) {
                    for (int i = 1; i < tooltip.size(); i++) {
                        Component c = tooltip.get(i);
                        if (c != null) {
                            tooltips.add(
                                    searchStack,
                                    c.getString().toLowerCase());
                        }
                    }
                }
            } catch (Exception e) {
                EmiLog.error("Error baking tooltip for " + stack, e);
            }
        }

        return !iterator.hasNext();
    }
}
