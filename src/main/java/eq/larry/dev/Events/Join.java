package eq.larry.dev.Events;

import java.util.Iterator;

import eq.larry.dev.UltraCore;
import eq.larry.dev.utils.Check;
import eq.larry.dev.utils.CustomConfig;
import eq.larry.dev.utils.SimpleScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Join implements Listener {
    public static FileConfiguration c = UltraCore.getInstance().getConfig();
    public static CustomConfig l;
    public static boolean cs;

    static {
        l = UltraCore.locations;
        cs = true;
    }

    public Join() {
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent var1) {
        Player var2 = var1.getPlayer();
        var1.setJoinMessage(c.getString("Join.Msg").replaceAll("&([0-9a-fk-o,r])", "§$1").replaceAll("%p", var2.getName()).replaceAll("%co", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("%maxco", String.valueOf(c.getInt("Players.Max"))));
        var2.teleport(new Location(Bukkit.getWorld(l.getConfig().getString("Spawn.W")), l.getConfig().getDouble("Spawn.X"), l.getConfig().getDouble("Spawn.Y"), l.getConfig().getDouble("Spawn.Z"), (float)l.getConfig().getInt("Spawn.YAW"), (float)l.getConfig().getInt("Spawn.PITCH")));
        var2.setGameMode(GameMode.SURVIVAL);
        var2.getInventory().clear();
        var2.getInventory().setArmorContents((ItemStack[])null);
        var2.removePotionEffect(PotionEffectType.INVISIBILITY);
        Iterator var4 = Bukkit.getOnlinePlayers().iterator();

        while(var4.hasNext()) {
            Player var3 = (Player)var4.next();
            SimpleScoreboard var5 = new SimpleScoreboard(c.getString("Scoreboard.Wait.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
            var5.blankLine();
            String var6 = "" + Bukkit.getOnlinePlayers().size();
            String var7 = "" + c.getInt("Players.Max");
            var5.add(c.getString("Scoreboard.Wait.Players").replaceAll("%co", var6).replaceAll("%maxco", var7).replaceAll("&([0-9a-fk-o,r])", "§$1"));
            var5.build();
            var5.send(new Player[]{var3});
        }

        SimpleScoreboard var8 = new SimpleScoreboard(c.getString("Scoreboard.Wait.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
        var8.blankLine();
        String var9 = "" + Bukkit.getOnlinePlayers().size();
        String var10 = "" + c.getInt("Players.Max");
        var8.add(c.getString("Scoreboard.Wait.Players").replaceAll("%co", var9).replaceAll("%maxco", var10).replaceAll("&([0-9a-fk-o,r])", "§$1"));
        var8.build();
        var8.send(new Player[]{var2});
        if (Check.canStart() && cs) {
            Check.start();
            cs = false;
        }

    }
}