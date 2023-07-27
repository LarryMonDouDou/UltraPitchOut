package eq.larry.dev.res;

import eq.larry.dev.UltraCore;
import eq.larry.dev.utils.Status;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

public class Response implements Listener {
    public Response(UltraCore var1) {
        PluginManager var2 = Bukkit.getPluginManager();
        var2.registerEvent(ServerListPingEvent.class, this, EventPriority.HIGHEST, new EventExecutor() {
            public void execute(Listener var1, Event var2) {
                Response.this.onMotd((ServerListPingEvent)var2);
            }
        }, UltraCore.getInstance());
    }

    public void onMotd(ServerListPingEvent var1) {
        String var2 = Status.getMotd();
        var1.setMotd(var2);
    }
}
