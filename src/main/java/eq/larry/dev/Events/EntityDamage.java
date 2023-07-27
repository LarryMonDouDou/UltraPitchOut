package eq.larry.dev.Events;

import eq.larry.dev.utils.Spectator;
import eq.larry.dev.utils.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {
    public EntityDamage() {
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent var1) {
        if (var1.getEntity() instanceof Player && var1.getDamager() instanceof Player) {
            Player var2 = (Player)var1.getEntity();
            Player var3 = (Player)var1.getDamager();
            if (!Spectator.listspectator.contains(var3.getUniqueId().toString()) && Status.isStart()) {
                if (var2.hasMetadata("chain")) {
                    var1.setCancelled(true);
                    return;
                }

                var1.setDamage(0.0);
                return;
            }

            var1.setCancelled(true);
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent var1) {
        if (var1.getCause() == DamageCause.FALL) {
            var1.setCancelled(true);
        }
    }
}

