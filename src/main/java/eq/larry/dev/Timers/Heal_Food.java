package eq.larry.dev.Timers;


import java.util.Iterator;

import eq.larry.dev.UltraCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Heal_Food {
    public Heal_Food() {
    }

    public static void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(UltraCore.getInstance(), new BukkitRunnable() {
            public void run() {
                Iterator var2 = Bukkit.getOnlinePlayers().iterator();

                while(var2.hasNext()) {
                    Player var1 = (Player)var2.next();
                    var1.setHealth(20.0);
                    var1.setFoodLevel(20);
                }

            }
        }, 20L, 20L);
    }
}
