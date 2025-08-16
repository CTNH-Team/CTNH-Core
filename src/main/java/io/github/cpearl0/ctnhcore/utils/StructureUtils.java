package io.github.cpearl0.ctnhcore.utils;

import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StructureUtils {
    public static List<MultiblockShapeInfo> getMatchingShapes(BlockPattern blockPattern, int maxIndex) {
        int[][] aisleRepetitions = blockPattern.aisleRepetitions;
        List<MultiblockShapeInfo> pages = repetitionCandidates(blockPattern, new ArrayList<>(), aisleRepetitions, new Stack<>());

        if (pages.size() < maxIndex) {
            int[] repetition = new int[aisleRepetitions.length];
            for (int i = 0; i < aisleRepetitions.length; i++) {
                repetition[i] = aisleRepetitions[i][1];
            }

            for (int i = pages.size(); i < maxIndex; i++) {
                pages.add(new MultiblockShapeInfo(blockPattern.getPreview(repetition)));
            }
        }

        return pages;
    }
    private static List<MultiblockShapeInfo> repetitionCandidates(
            BlockPattern pattern,
            List<MultiblockShapeInfo> pages,
            int[][] aisleRepetitions,
            Stack<Integer> repetitionStack
    ) {
        if (repetitionStack.size() == aisleRepetitions.length) {
            int[] repetition = new int[repetitionStack.size()];
            for (int i = 0; i < repetitionStack.size(); i++) {
                repetition[i] = repetitionStack.get(i);
            }
            pages.add(new MultiblockShapeInfo(pattern.getPreview(repetition)));
        } else {
            int currentLevel = repetitionStack.size();
            for (int i = aisleRepetitions[currentLevel][0]; i <= aisleRepetitions[currentLevel][1]; i++) {
                repetitionStack.push(i);
                repetitionCandidates(pattern, pages, aisleRepetitions, repetitionStack);
                repetitionStack.pop();
            }
        }
        return pages;
    }
}
