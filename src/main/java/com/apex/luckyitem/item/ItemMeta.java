package com.apex.luckyitem.item;

import com.apex.luckyitem.item.award.Award;
import com.apex.luckyitem.item.trigger.Trigger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by shs86 on 2018-10-10.
 */
@SuppressWarnings("DefaultFileTemplate")
@Getter
@AllArgsConstructor
@ToString
public class ItemMeta {
    private String id;
    private String name;
    private String description;

    private int rank;

    private ItemStack figure;
    private ItemType type;

    private double minPercent;
    private double maxPercent;

    private Trigger trigger;

    private List<Award> awards;
    private List<String> extra;

}
