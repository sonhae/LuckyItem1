package com.apex.luckyitem.item.trigger;

import com.apex.luckyitem.LuckyItem;
import com.apex.luckyitem.item.Item;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shs86 on 2018-10-10.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MineTrigger extends Trigger {
    private List<Material> materials;

    public MineTrigger(String... s) {
        super(TriggerType.MINE, s);
        convert(s);
    }

    protected void convert(String... args) {
        materials = new ArrayList<>();
        for (String s : args) {
            if (StringUtils.isNumeric(s)) {
                int id = Integer.parseInt(s);
                materials.add(Material.getMaterial(id));
            } else {
                materials.add(Material.matchMaterial(s));
            }
        }
    }

    public boolean check(Event e, Item item) {
        if (!(e instanceof BlockBreakEvent)) {
            return false;
        }

        BlockBreakEvent event = (BlockBreakEvent) e;
        if (materials.contains(event.getBlock().getType()) && LuckyItem.getChances(item.getPercent())) {
            item.giveAward();
            return true;
        }
        return true;
    }
}
