package com.ascrud.mybatisplus.test.plugins.optimisticLocker.entity;

import com.ascrud.mybatisplus.annotations.Version;

public class IntVersionFather {

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
