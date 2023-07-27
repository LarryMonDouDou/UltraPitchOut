package eq.larry.dev.Events;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import eq.larry.dev.UltraCore;
import eq.larry.dev.utils.*;
import net.PitchOut.PitchOut;
import net.PitchOut.Utils.Check;
import net.PitchOut.Utils.CustomConfig;
import net.PitchOut.Utils.SimpleScoreboard;
import net.PitchOut.Utils.Spectator;
import net.PitchOut.Utils.Status;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Player_MoveQuit implements Listener {
    public static FileConfiguration c = UltraCore.getInstance().getConfig();
    public static CustomConfig l;

    static {
        l = UltraCore.locations;
    }

    public Player_MoveQuit() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent var1) {
        boolean var2 = Spectator.listspectator.contains(var1.getPlayer().getUniqueId().toString());
        boolean var3 = Status.isStart();
        boolean var4 = c.getBoolean("WaterKill");
        Material var5 = var1.getPlayer().getLocation().getBlock().getType();
        double var6 = var1.getPlayer().getLocation().getY();
        double var8 = var1.getPlayer().getHealthScale();
        if (var3) {
            if (var6 <= 0.0 || var4 && var5.equals(Material.WATER) || var4 && var5.equals(Material.STATIONARY_WATER)) {
                if (var2) {
                    var1.getPlayer().teleport(new Location(Bukkit.getWorld(l.getConfig().getString("Spectator.W")), l.getConfig().getDouble("Spectator.X"), l.getConfig().getDouble("Spectator.Y"), l.getConfig().getDouble("Spectator.Z"), (float)l.getConfig().getInt("Spectator.YAW"), (float)l.getConfig().getInt("Spectator.PITCH")));
                } else {
                    Player var10;
                    String var13;
                    if (var8 != 2.0) {
                        var10 = var1.getPlayer();
                        Bukkit.getScheduler().runTaskLater(UltraCore.getInstance(), new 1(this, var10), (long)(20 * c.getInt("SecondOfArmorRespawn")));
                        var1.getPlayer().setHealthScale(var8 - 2.0);
                        UltraCore.list = Check.locations.getConfig().getConfigurationSection("Points").getValues(false);
                        Check.loc.clear();
                        Iterator var12 = UltraCore.list.entrySet().iterator();

                        while(var12.hasNext()) {
                            Map.Entry var11 = (Map.Entry)var12.next();
                            var13 = var11.getValue().toString().replace("MemorySection[path='", "").replace("', root='YamlConfiguration']", "");
                            Location var14 = new Location(Bukkit.getWorld(Check.locations.getConfig().getString(var13 + ".W")), Check.locations.getConfig().getDouble(var13 + ".X"), Check.locations.getConfig().getDouble(var13 + ".Y"), Check.locations.getConfig().getDouble(var13 + ".Z"), (float)Check.locations.getConfig().getInt(var13 + ".YAW"), (float)Check.locations.getConfig().getInt(var13 + ".PITCH"));
                            Check.loc.add(var14);
                        }

                        Location var15 = (Location)Check.loc.get((new Random()).nextInt(Check.loc.size()));
                        Check.loc.remove(var15);
                        var10.teleport(var15);
                        var10.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                        var10.setMetadata("chain", new FixedMetadataValue(UltraCore.getInstance(), "chain"));
                    } else {
                        Spectator.setSpectator(var1.getPlayer());
                        Check.checkWin();
                        Iterator var16 = Bukkit.getOnlinePlayers().iterator();

                        while(var16.hasNext()) {
                            var10 = (Player)var16.next();
                            SimpleScoreboard var17 = new SimpleScoreboard(c.getString("Scoreboard.Game.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                            var17.blankLine();
                            var13 = "" + (Bukkit.getOnlinePlayers().size() - Spectator.listspectator.size());
                            var17.add(c.getString("Scoreboard.Game.Alive").replaceAll("%alive", var13).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                            var17.blankLine();
                            String var18 = "" + Spectator.listspectator.size();
                            var17.add(c.getString("Scoreboard.Game.Dead").replaceAll("%dead", var18).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                            var17.build();
                            var17.send(new Player[]{var10});
                        }
                    }
                }
            }
        } else if (Status.isWaiting() && (var6 <= 0.0 || var4 && var5.equals(Material.WATER) || var4 && var5.equals(Material.STATIONARY_WATER))) {
            var1.getPlayer().teleport(new Location(Bukkit.getWorld(l.getConfig().getString("Spawn.W")), l.getConfig().getDouble("Spawn.X"), l.getConfig().getDouble("Spawn.Y"), l.getConfig().getDouble("Spawn.Z"), (float)l.getConfig().getInt("Spawn.YAW"), (float)l.getConfig().getInt("Spawn.PITCH")));
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent var1) {
        var1.setQuitMessage(c.getString("Quit.Msg").replaceAll("&([0-9a-fk-o,r])", "§$1").replaceAll("%p", var1.getPlayer().getName()).replaceAll("%co", String.valueOf(Bukkit.getServer().getOnlinePlayers().size() - 1)).replaceAll("%maxco", String.valueOf(c.getInt("Players.Max"))));
        Player var2;
        Iterator var3;
        SimpleScoreboard var4;
        String var5;
        String var6;
        if (Status.isStart()) {
            Spectator.setSpectator(var1.getPlayer());
            Check.checkWin();
            var3 = Bukkit.getOnlinePlayers().iterator();

            while(var3.hasNext()) {
                var2 = (Player)var3.next();
                var4 = new SimpleScoreboard(c.getString("Scoreboard.Game.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                var4.blankLine();
                var5 = "" + (Bukkit.getOnlinePlayers().size() - Spectator.listspectator.size());
                var4.add(c.getString("Scoreboard.Game.Alive").replaceAll("%alive", var5).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                var4.blankLine();
                var6 = "" + Spectator.listspectator.size();
                var4.add(c.getString("Scoreboard.Game.Dead").replaceAll("%dead", var6).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                var4.build();
                var4.send(new Player[]{var2});
            }
        } else {
            var3 = Bukkit.getOnlinePlayers().iterator();

            while(var3.hasNext()) {
                var2 = (Player)var3.next();
                var4 = new SimpleScoreboard(c.getString("Scoreboard.Wait.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                var4.blankLine();
                var5 = "" + Bukkit.getOnlinePlayers().size();
                var6 = "" + c.getInt("Players.Max");
                var4.add(c.getString("Scoreboard.Wait.Players").replaceAll("%co", var5).replaceAll("maxco", var6).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                var4.build();
                var4.send(new Player[]{var2});
            }
        }

    }
}
