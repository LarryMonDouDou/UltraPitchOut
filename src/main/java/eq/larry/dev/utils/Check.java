package eq.larry.dev.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;

import eq.larry.dev.UltraCore;
import net.PitchOut.Events.Join;
import eq.larry.dev.Enums.EnumsStatus;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Check {
    public static FileConfiguration c = UltraCore.getInstance().getConfig();
    public static CustomConfig locations;
    public static ArrayList<Location> loc;

    static {
        locations = UltraCore.locations;
        loc = new ArrayList();
    }

    public static boolean canStart() {
        return Bukkit.getOnlinePlayers().size() >= c.getInt("Players.Min");
    }

    public static void start() {
        final String var0 = c.getString("Cooldown.Msg1").replaceAll("&([0-9a-fk-o,r])", "§$1");
        Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "60"));
        Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
            public void run() {
                Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "30"));
                Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                    public void run() {
                        Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "15"));
                        Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                            public void run() {
                                Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "5"));
                                Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                                    public void run() {
                                        Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "4"));
                                        Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                                            public void run() {
                                                Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "3"));
                                                Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                                                    public void run() {
                                                        Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "2"));
                                                        Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                                                            public void run() {
                                                                Bukkit.getServer().broadcastMessage(var0.replaceAll("%time", "1"));
                                                                Bukkit.getServer().getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                                                                    public void run() {
                                                                        if (Bukkit.getOnlinePlayers().size() >= Check.c.getInt("Players.Min")) {
                                                                            Status.setMode(EnumsStatus.Progress);
                                                                            Bukkit.getServer().broadcastMessage(Check.c.getString("Cooldown.Msg2").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                                                                            UltraCore.list = Check.locations.getConfig().getConfigurationSection("Points").getValues(false);
                                                                            Check.loc.clear();
                                                                            Iterator var2 = UltraCore.list.entrySet().iterator();

                                                                            while(var2.hasNext()) {
                                                                                Entry var1 = (Entry)var2.next();
                                                                                String var3 = var1.getValue().toString().replace("MemorySection[path='", "").replace("', root='YamlConfiguration']", "");
                                                                                Location var4 = new Location(Bukkit.getWorld(Check.locations.getConfig().getString(var3 + ".W")), Check.locations.getConfig().getDouble(var3 + ".X"), Check.locations.getConfig().getDouble(var3 + ".Y"), Check.locations.getConfig().getDouble(var3 + ".Z"), (float)Check.locations.getConfig().getInt(var3 + ".YAW"), (float)Check.locations.getConfig().getInt(var3 + ".PITCH"));
                                                                                Check.loc.add(var4);
                                                                            }

                                                                            var2 = Bukkit.getOnlinePlayers().iterator();

                                                                            while(var2.hasNext()) {
                                                                                Player var10 = (Player)var2.next();
                                                                                Location var11 = (Location)Check.loc.get((new Random()).nextInt(Check.loc.size()));
                                                                                Check.loc.remove(var11);
                                                                                var10.teleport(var11);
                                                                                var10.getInventory().clear();
                                                                                ItemStack var12 = new ItemStack(Material.BOW);
                                                                                var12.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                                                                                var12.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 4);
                                                                                var10.getInventory().addItem(new ItemStack[]{var12});
                                                                                ItemStack var5 = new ItemStack(Material.ARROW);
                                                                                var10.getInventory().setItem(9, var5);
                                                                                ItemStack var6 = new ItemStack(Material.getMaterial(269));
                                                                                var6.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
                                                                                var6.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                                                                                var10.getInventory().addItem(new ItemStack[]{var6});
                                                                                var10.setHealthScale((double)Check.c.getInt("NumberOfLifeStart") * 2.0D);
                                                                                SimpleScoreboard var7 = new SimpleScoreboard(Check.c.getString("Scoreboard.Game.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                                                                                var7.blankLine();
                                                                                String var8 = "" + (Bukkit.getOnlinePlayers().size() - Spectator.listspectator.size());
                                                                                var7.add(Check.c.getString("Scoreboard.Game.Alive").replaceAll("%alive", var8).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                                                                                var7.blankLine();
                                                                                String var9 = "" + Spectator.listspectator.size();
                                                                                var7.add(Check.c.getString("Scoreboard.Game.Dead").replaceAll("%dead", var9).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                                                                                var7.build();
                                                                                var7.send(new Player[]{var10});
                                                                            }

                                                                            Check.checkWin();
                                                                        } else {
                                                                            Join.cs = true;
                                                                        }

                                                                    }
                                                                }, 20L);
                                                            }
                                                        }, 20L);
                                                    }
                                                }, 20L);
                                            }
                                        }, 20L);
                                    }
                                }, 20L);
                            }
                        }, 200L);
                    }
                }, 300L);
            }
        }, 600L);
    }

    public static void checkWin() {
        if (!Status.isFinish()) {
            int var0 = 0;
            Iterator var2 = Bukkit.getOnlinePlayers().iterator();

            Player var1;
            while(var2.hasNext()) {
                var1 = (Player)var2.next();
                String var3 = var1.getUniqueId().toString();
                if (!Spectator.listspectator.contains(var3)) {
                    ++var0;
                }
            }

            if (var0 <= 1) {
                Status.setMode(EnumsStatus.Finish);
                Bukkit.getScheduler().runTaskLater(UltraCore.getInstance(), new BukkitRunnable() {
                    public void run() {
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Check.c.getString("RestartCmd"));
                    }
                }, (long)(c.getInt("RestartTime") * 20));
                var2 = Bukkit.getOnlinePlayers().iterator();

                while(var2.hasNext()) {
                    var1 = (Player)var2.next();
                    if (!Spectator.listspectator.contains(var1.getUniqueId().toString())) {
                        var1.setGameMode(GameMode.CREATIVE);
                        var1.getInventory().clear();
                        var1.getInventory().setArmorContents((ItemStack[])null);
                        fireworks(var1);
                        Bukkit.broadcastMessage(UltraCore.getInstance().getConfig().getString("Finish.Msg").replaceAll("&([0-9a-fk-o,r])", "§$1").replaceAll("%p", var1.getName()).replaceAll("%time", String.valueOf(c.getInt("RestartTime"))));
                        SimpleScoreboard var6 = new SimpleScoreboard(c.getString("Scoreboard.Game.Name").replaceAll("&([0-9a-fk-o,r])", "§$1"));
                        var6.blankLine();
                        String var4 = "1";
                        var6.add(c.getString("Scoreboard.Game.Alive").replaceAll("%alive", var4).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                        var6.blankLine();
                        String var5 = "" + Spectator.listspectator.size();
                        var6.add(c.getString("Scoreboard.Game.Dead").replaceAll("%dead", var5).replaceAll("&([0-9a-fk-o,r])", "§$1"));
                        var6.build();
                        var6.send(new Player[]{var1});
                    }
                }
            }
        }

    }

    public static void fireworks(final Player var0) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(UltraCore.getInstance(), new BukkitRunnable() {
            public void run() {
                Firework var1 = (Firework)var0.getWorld().spawnEntity(var0.getLocation(), EntityType.FIREWORK);
                FireworkMeta var2 = var1.getFireworkMeta();
                Random var3 = new Random();
                int var4 = var3.nextInt(5) + 1;
                Type var5 = Type.BALL;
                if (var4 == 1) {
                    var5 = Type.BALL;
                }

                if (var4 == 2) {
                    var5 = Type.BALL_LARGE;
                }

                if (var4 == 3) {
                    var5 = Type.BURST;
                }

                if (var4 == 4) {
                    var5 = Type.CREEPER;
                }

                if (var4 == 5) {
                    var5 = Type.STAR;
                }

                int var6 = var3.nextInt(256);
                int var7 = var3.nextInt(256);
                int var8 = var3.nextInt(256);
                Color var9 = Color.fromRGB(var6, var8, var7);
                var6 = var3.nextInt(256);
                var7 = var3.nextInt(256);
                var8 = var3.nextInt(256);
                Color var10 = Color.fromRGB(var6, var8, var7);
                FireworkEffect var11 = FireworkEffect.builder().flicker(var3.nextBoolean()).withColor(var9).withFade(var10).with(var5).trail(var3.nextBoolean()).build();
                var2.addEffect(var11);
                int var12 = var3.nextInt(2) + 1;
                var2.setPower(var12);
                var1.setFireworkMeta(var2);
                Check.fireworks(var0);
            }
        }, 10L);
    }
}
