package com.simon.demo.common.cache.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.ArrayList;

/**
 * cache key
 */
@FunctionalInterface
public interface CacheKeyBuilder {
    /**
     * key 前缀
     *
     * @return key 前缀
     */
    @NonNull
    String getPrefix();

    /**
     * 超时时间
     *
     * @return 超时时间
     */
    @Nullable
    default Duration getExpire() {
        return null;
    }

    /**
     * 构建通用KV模式 的 cache key
     * 兼容 redis caffeine
     *
     * @param suffix 参数
     * @return cache key
     */
    default CacheKey key(Object... suffix) {
        String field = suffix.length > 0 ? Convert.toStr(suffix[0], "") : "";
        return hashKey(field, suffix);
    }

    /**
     * 构建 redis 类型的 hash cache key
     *
     * @param field  field
     * @param suffix 参数
     * @return cache key
     */
    default CacheHashKey hashKey(@NonNull Object field, Object... suffix) {
        ArrayList<String> regionList = new ArrayList<>();
        String prefix = this.getPrefix();
        Assert.notEmpty(prefix, "缓存前缀不能为空");
        regionList.add(prefix);
        for (Object s : suffix) {
            if (ObjectUtil.isNotEmpty(s)) {
                regionList.add(String.valueOf(s));
            }
        }
        String key = CollUtil.join(regionList, StrUtil.COLON);
        Assert.notEmpty(key, "key 不能为空");
        Assert.notNull(field, "field 不能为空");
        return new CacheHashKey(key, field, getExpire());
    }
}
