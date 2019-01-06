package com.apex.luckyitem.item.award;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by shs86 on 2018-10-11.
 */
@SuppressWarnings("DefaultFileTemplate")
@Getter
@AllArgsConstructor
public enum AwardType {
    MONEY(MoneyAward.class);
    private Class<? extends Award> clazz;
}
