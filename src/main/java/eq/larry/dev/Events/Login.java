package eq.larry.dev.Events;

import eq.larry.dev.UltraCore;
import eq.larry.dev.utils.Status;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class Login implements Listener {
    public static FileConfiguration c = UltraCore.getInstance().getConfig();

    public Login() {
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent var1) {
        if (Status.isStart()) {
            var1.disallow(Result.KICK_OTHER, c.getString("Kick.AlreadyStartedMsg").replaceAll("&([0-9a-fk-o,r])", "§$1"));
        } else if (Status.isFull()) {
            var1.disallow(Result.KICK_OTHER, c.getString("Kick.FullMsg").replaceAll("&([0-9a-fk-o,r])", "§$1"));
        }

    }
}
