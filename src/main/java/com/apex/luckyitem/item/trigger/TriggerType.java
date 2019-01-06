package com.apex.luckyitem.item.trigger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by shs86 on 2018-10-11.
 */
@SuppressWarnings("DefaultFileTemplate")
@Getter
@AllArgsConstructor
public enum TriggerType {
    MINE(MineTrigger.class);

    private Class<? extends Trigger> clazz;
}
