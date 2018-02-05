package com.njxz.exam.modle;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class SystemConfig {
    private Byte id;

    @NotEmpty(message="{systemConfig.key.notNullOrEmpty}")
    @NotNull(message="{systemConfig.key.notNullOrEmpty}")
    private String key;

    private String value;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}