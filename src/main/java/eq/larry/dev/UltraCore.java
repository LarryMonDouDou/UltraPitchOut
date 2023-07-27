package eq.larry.dev;

import java.io.File;
import java.util.Map;

import eq.larry.dev.Enums.EnumsStatus;
import eq.larry.dev.Events.*;
import eq.larry.dev.Timers.Heal_Food;
import eq.larry.dev.commands.UltraPoint;
import eq.larry.dev.commands.UltraSpawn;
import eq.larry.dev.commands.UltraSpec;
import eq.larry.dev.res.Response;
import eq.larry.dev.utils.CustomConfig;
import eq.larry.dev.utils.Status;
import eq.larry.dev.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UltraCore extends JavaPlugin {
    public static UltraCore instance;
    public static CustomConfig locations;
    public static Map<String, Object> list;

    public void onEnable() {
        loadConfig0();
        instance = this;
        locations = new CustomConfig(new File(this.getDataFolder() + "/locations.yml"));
        this.saveDefaultConfig();
        Config.drawBasicConfig();
        PluginManager var1 = Bukkit.getServer().getPluginManager();
        var1.registerEvents(new Login(), this);
        var1.registerEvents(new Join(), this);
        var1.registerEvents(new EntityDamage(), this);
        var1.registerEvents(new BreakBlock(), this);
        var1.registerEvents(new Security(), this);
        var1.registerEvents(new Player_MoveQuit(), this);
        var1.registerEvents(new PlayersChats(), this);
        new Response(this);
        this.getCommand("ultraspawn").setExecutor(new UltraSpawn());
        this.getCommand("ultrapoint").setExecutor(new UltraPoint());
        this.getCommand("ultraspec").setExecutor(new UltraSpec());
        Heal_Food.start();
        Status.setMode(EnumsStatus.Waiting);
    }

    public static UltraCore getInstance() {
        return instance;
    }

}