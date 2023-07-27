package eq.larry.dev.utils;

import java.util.HashSet;
import java.util.Set;

import eq.larry.dev.UltraCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spectator {
    public static CustomConfig l;
    public static Set<String> listspectator;

    static {
        l = UltraCore.locations;
        listspectator = new HashSet();
    }

    public static void setSpectator(Player var0) {
        var0.teleport(new Location(Bukkit.getWorld(l.getConfig().getString("Spectator.W")), l.getConfig().getDouble("Spectator.X"), l.getConfig().getDouble("Spectator.Y"), l.getConfig().getDouble("Spectator.Z"), (float)l.getConfig().getInt("Spectator.YAW"), (float)l.getConfig().getInt("Spectator.PITCH")));
        var0.setGameMode(GameMode.SPECTATOR);
        var0.setFlying(true);
        var0.setAllowFlight(true);
        listspectator.add(var0.getUniqueId().toString());
        var0.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        var0.getInventory().clear();
        var0.setMetadata("chain", new FixedMetadataValue(UltraCore.getInstance(), "chain"));
    }
}
