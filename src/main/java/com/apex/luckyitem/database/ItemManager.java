package com.apex.luckyitem.database;

import com.apex.luckyitem.LuckyItem;
import com.apex.luckyitem.item.ItemMeta;
import com.apex.luckyitem.item.ItemType;
import com.apex.luckyitem.item.award.Award;
import com.apex.luckyitem.item.award.AwardType;
import com.apex.luckyitem.item.trigger.Trigger;
import com.apex.luckyitem.item.trigger.TriggerType;
import com.google.common.io.Files;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shs86 on 2018-10-11.
 */
@SuppressWarnings("DefaultFileTemplate")
public class ItemManager {

    private static final Pattern PATTERN = Pattern.compile("(\\{[a-zA-Z0-9:,]+})");

    @Getter
    private HashMap<String, ItemMeta> metaMap = new HashMap<>();

    public boolean containsMeta(String id) {
        return metaMap.containsKey(id);
    }

    public ItemMeta getMeta(String id) {
        return metaMap.get(id);
    }

    public ItemMeta loadMeta(String path) {
        return loadMeta(new File(path));
    }

    public void loadMetaAll(String dir) {
        File f = new File(dir);
        if (f.isDirectory()) {
            for (File f1 : f.listFiles(File::isFile)) {
                String name = Files.getNameWithoutExtension(f1.getName());
                ItemMeta meta = loadMeta(f1);
                metaMap.put(name, meta);
            }
        }
    }

    public ItemMeta loadMeta(File file) {
        try {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
            String id = Files.getNameWithoutExtension(file.getName());
            String name = yml.getString("name");
            String description = yml.getString("description");

            int rank = yml.getInt("rank");

            ItemStack figure = yml.getItemStack("figure");
            ItemType type = ItemType.valueOf(yml.getString("type"));

            double minPercent = yml.getDouble("minPercent");
            double maxPercent = yml.getDouble("maxPercent");

            Trigger trigger = getTrigger(yml.getString("trigger"));
            List<Award> awards = getAwards(yml.getString("awards"));
            List<String> extra = yml.getStringList("extras");

            return new ItemMeta(id, name, description, rank, figure, type, minPercent, maxPercent, trigger, awards, extra);
        } catch (Exception e) {
            LuckyItem.severe(file.getName() + "을 로딩하는중 오류 발생");
            return null;
        }
    }

    private List<Award> getAwards(String s) throws Exception {

        List<Award> l = new ArrayList<>();

        Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            String str = matcher.group(1);

            str = str.replaceAll("([{}])", "");

            String[] args = str.split(":");

            AwardType type = AwardType.valueOf(args[0].toUpperCase());
            double d = 100d;

            if (!args[1].isEmpty()) {
                d = Double.parseDouble(args[1]);
            }

            Class<? extends Award> clazz = type.getClazz();
            Constructor<? extends Award> constructor = clazz.getConstructor(double.class, String[].class);

            Award a = constructor.newInstance(d, args[2].split(","));

            l.add(a);
        }
        return l;
    }

    private Trigger getTrigger(String s) throws Exception {
        Matcher matcher = PATTERN.matcher(s);

        String str = matcher.group(1);

        str = str.replaceAll("([{}])", "");

        String[] args = str.split(":");
        TriggerType type = TriggerType.valueOf(args[0].toUpperCase());

        Class<? extends Trigger> clazz = type.getClazz();
        Constructor<? extends Trigger> constructor = clazz.getConstructor(String[].class);

        return constructor.newInstance(new Object[]{args[1].split(",")});
    }
}
