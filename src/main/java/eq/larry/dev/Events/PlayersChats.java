package eq.larry.dev.Events;

import eq.larry.dev.utils.Spectator;
import eq.larry.dev.utils.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayersChats implements Listener {
    public PlayersChats() {
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent var1) {
        Player var2 = var1.getPlayer();
        int var3 = (int)var2.getHealthScale();
        if (!Status.isWaiting()) {
            if (Spectator.listspectator.contains(var2.getUniqueId().toString())) {
                var1.setFormat("§8Spectator §f" + var1.getPlayer().getName() + " §8" + var1.getMessage());
            } else {
                var1.setFormat("§e" + var3 / 2 + " §f" + var1.getPlayer().getName() + " §a" + var1.getMessage());
            }
        }
    }
}