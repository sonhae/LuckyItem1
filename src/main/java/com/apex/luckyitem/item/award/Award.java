package com.apex.luckyitem.item.award;

import com.apex.luckyitem.LuckyItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;

/**
 * Created by shs86 on 2018-10-11.
 */
@SuppressWarnings("ALL")
@Getter
@AllArgsConstructor
public abstract class Award {
    private AwardType type;
    private double percent;
    private String[] args;

    public void checkAward(){
        if (LuckyItem.getChances(percent)){
            giveAward();
            Bukkit.getPluginManager().event
        }
    }

    public abstract void giveAward();

}
