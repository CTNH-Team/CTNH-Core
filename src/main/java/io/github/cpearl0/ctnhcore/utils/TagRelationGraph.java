package io.github.cpearl0.ctnhcore.utils;

import java.util.*;

public class TagRelationGraph {

    private final Map<String, Set<String>> relationGraph = new HashMap<>();

    public void addRelationGroup(List<String> relatedTags) {
        // 确保组内所有标签两两互相连接
        for (String tag1 : relatedTags) {
            for (String tag2 : relatedTags) {
                if (!tag1.equals(tag2)) {
                    relationGraph.computeIfAbsent(tag1, k -> new HashSet<>()).add(tag2);
                }
            }
        }
    }

    public Set<String> getRelatedTags(String sourceTag) {
        return relationGraph.getOrDefault(sourceTag, Collections.emptySet());
    }
}
