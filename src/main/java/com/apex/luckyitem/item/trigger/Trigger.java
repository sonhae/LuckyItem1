package com.apex.luckyitem.item.trigger;

import com.apex.luckyitem.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;

/**
 * Created by shs86 on 2018-10-10.
 */
@SuppressWarnings("DefaultFileTemplate")
@AllArgsConstructor
public abstract class Trigger {
    @Getter
    private TriggerType triggerType;
    private String[] args;


    protected abstract void convert(String... args);



    public abstract boolean check(Event e, Item item);

}
