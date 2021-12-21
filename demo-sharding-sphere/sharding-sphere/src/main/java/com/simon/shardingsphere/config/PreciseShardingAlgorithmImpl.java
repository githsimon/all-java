package com.simon.shardingsphere.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;

public class PreciseShardingAlgorithmImpl implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        return null;
    }

    @Override
    public void init() {
    }

    @Override
    public String getType() {
        return null;
    }
}
