package com.apex.luckyitem;

import com.apex.luckyitem.database.ItemManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Created by shs86 on 2018-10-10.
 */
@SuppressWarnings("DefaultFileTemplate")
public class LuckyItem extends JavaPlugin {
    @Getter
    private static LuckyItem instance;

    @Getter
    private ItemManager itemManager;

    public void onDisable() {
    }

    public void onEnable() {

        instance = this;
        itemManager = new ItemManager();

    }


    public static void severe(String string){
        log(Level.SEVERE, string);
    }

    public static void fine(String string) {
        log(Level.FINE,string);
    }

    private static void log(Level level, String str) {
        getInstance().getLogger().log(level, str);
    }

    public static boolean getChances(double percent) {

        return percent >= (Math.random() * 100d);
    }
}
