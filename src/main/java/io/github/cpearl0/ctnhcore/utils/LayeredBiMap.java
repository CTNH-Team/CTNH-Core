package io.github.cpearl0.ctnhcore.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.ForwardingMap;

import java.util.*;

public final class LayeredBiMap<K, V>
                               extends ForwardingMap<K, V>
                               implements BiMap<K, V> {

    private final BiMap<K, V> primary;
    private final Map<K, V> fallback;

    private BiMap<V, K> inverse;

    public LayeredBiMap(BiMap<K, V> primary, Map<K, V> fallback) {
        this.primary = Objects.requireNonNull(primary);
        this.fallback = Objects.requireNonNull(fallback);
    }

    // ================= Map =================

    @Override
    protected Map<K, V> delegate() {
        return new AbstractMap<>() {

            @Override
            public V get(Object key) {
                return primary.containsKey(key) ? primary.get(key) : fallback.get(key);
            }

            @Override
            public boolean containsKey(Object key) {
                return primary.containsKey(key) || fallback.containsKey(key);
            }

            @Override
            public V put(K key, V value) {
                return primary.put(key, value);
            }

            @Override
            public V remove(Object key) {
                return primary.remove(key);
            }

            @Override
            public void clear() {
                primary.clear();
            }

            @Override
            public Set<Entry<K, V>> entrySet() {
                Map<K, V> merged = new LinkedHashMap<>();
                merged.putAll(fallback);
                merged.putAll(primary); // primary 覆盖
                return Collections.unmodifiableSet(merged.entrySet());
            }
        };
    }

    // ================= BiMap =================

    @Override
    public V forcePut(K key, V value) {
        return primary.forcePut(key, value);
    }

    @Override
    public Set<V> values() {
        Set<V> values = new LinkedHashSet<>(fallback.values());
        values.addAll(primary.values());
        return Collections.unmodifiableSet(values);
    }

    @Override
    public BiMap<V, K> inverse() {
        if (inverse == null) {
            inverse = new Inverse();
        }
        return inverse;
    }

    // ================= Inverse =================

    private final class Inverse
                                extends ForwardingMap<V, K>
                                implements BiMap<V, K> {

        @Override
        protected Map<V, K> delegate() {
            return new AbstractMap<>() {

                @Override
                public K get(Object value) {
                    K k = primary.inverse().get(value);
                    if (k != null) {
                        return k;
                    }
                    for (Entry<K, V> e : fallback.entrySet()) {
                        if (Objects.equals(e.getValue(), value)) {
                            return e.getKey();
                        }
                    }
                    return null;
                }

                @Override
                public boolean containsKey(Object value) {
                    return primary.inverse().containsKey(value) || fallback.containsValue(value);
                }

                @Override
                public K put(V value, K key) {
                    return primary.inverse().put(value, key);
                }

                @Override
                public K remove(Object value) {
                    return primary.inverse().remove(value);
                }

                @Override
                public void clear() {
                    primary.clear();
                }

                @Override
                public Set<Entry<V, K>> entrySet() {
                    Map<V, K> merged = new LinkedHashMap<>();

                    for (Entry<K, V> e : fallback.entrySet()) {
                        merged.put(e.getValue(), e.getKey());
                    }
                    merged.putAll(primary.inverse());

                    return Collections.unmodifiableSet(merged.entrySet());
                }
            };
        }

        @Override
        public K forcePut(V value, K key) {
            return primary.inverse().forcePut(value, key);
        }

        @Override
        public Set<K> values() {
            Set<K> keys = new LinkedHashSet<>(fallback.keySet());
            keys.addAll(primary.keySet());
            return Collections.unmodifiableSet(keys);
        }

        @Override
        public BiMap<K, V> inverse() {
            return LayeredBiMap.this;
        }
    }
}
