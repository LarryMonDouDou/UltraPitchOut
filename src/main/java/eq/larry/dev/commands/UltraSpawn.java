package eq.larry.dev.commands;

import eq.larry.dev.UltraCore;
import eq.larry.dev.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UltraSpawn implements CommandExecutor {
    public static CustomConfig config;

    static {
        config = UltraCore.locations;
    }

    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        if (var1 instanceof Player && var4.length == 0 && var1.isOp()) {
            Location var5 = ((Player)var1).getLocation();
            String var6 = "Spawn.";
            config.getConfig().set(var6 + "W", var5.getWorld().getName());
            config.getConfig().set(var6 + "X", var5.getX());
            config.getConfig().set(var6 + "Y", var5.getY());
            config.getConfig().set(var6 + "Z", var5.getZ());
            config.getConfig().set(var6 + "YAW", var5.getYaw());
            config.getConfig().set(var6 + "PITCH", var5.getPitch());
            config.save();
            var1.sendMessage("§9Spawn as been set!");
        } else {
            var1.sendMessage("§4ERROR");
        }

        return false;
    }
}