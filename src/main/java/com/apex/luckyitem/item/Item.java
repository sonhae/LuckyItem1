package com.apex.luckyitem.item;

import com.apex.luckyitem.LuckyItem;
import com.apex.luckyitem.item.award.Award;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by shs86 on 2018-10-10.
 */
@SuppressWarnings("DefaultFileTemplate")
@Getter
@AllArgsConstructor
public class Item {
    private UUID owner;
    @Setter
    private int enhanced;
    private long creationTime;
    @Setter
    private boolean unique;
    private ItemMeta meta;

    public double getPercent() {
        return (meta.getMinPercent() + ((meta.getMaxPercent() - meta.getMinPercent()) / 7) * enhanced);
    }

    public void giveAward() {
        meta.getAwards().forEach(Award::giveAward);
        meta.getExtra().forEach(s -> {
            HashMap<String, ItemMeta> map = LuckyItem.getInstance().getItemManager().getMetaMap();
            map.get(s).getAwards().forEach(Award::checkAward);
        });
    }

    public void checkTrigger(Event e) {
        meta.getTrigger().check(e, this);
    }
}
