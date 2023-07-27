package eq.larry.dev.Events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Security implements Listener {
    public Security() {
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent var1) {
        if (!var1.getWhoClicked().isOp()) {
            var1.setCancelled(true);
        }

    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent var1) {
        if (!var1.getPlayer().isOp()) {
            var1.setCancelled(true);
        }

    }

    @EventHandler
    public void onPickItem(PlayerPickupItemEvent var1) {
        if (!var1.getPlayer().isOp()) {
            var1.setCancelled(true);
        }

    }
}
