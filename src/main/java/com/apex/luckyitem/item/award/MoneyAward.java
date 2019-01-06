package com.apex.luckyitem.item.award;

/**
 * Created by shs86 on 2018-10-14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MoneyAward extends Award {
    public MoneyAward(double percent, String... args) {
        super(AwardType.MONEY, percent, args);
    }

    @Override
    public void giveAward() {

    }
}
