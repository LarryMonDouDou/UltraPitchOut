package eq.larry.dev.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    public BreakBlock() {
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent var1) {
        if (!var1.getPlayer().isOp()) {
            var1.setCancelled(true);
        }

    }
}

