import com.apex.luckyitem.database.ItemManager;
import com.apex.luckyitem.item.ItemMeta;
import org.junit.Test;

/**
 * Created by shs86 on 2018-10-14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class TestTrigger {
    @Test
    public void testGetTrigger(){
        ItemManager itemManager = new ItemManager(null);
        try {
            ItemMeta m = itemManager.loadMeta("C:\\Users\\shs86\\Desktop\\새 폴더\\sex.yml");
            System.out.println(m.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
