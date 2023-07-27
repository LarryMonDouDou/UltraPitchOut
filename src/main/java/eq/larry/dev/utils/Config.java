package eq.larry.dev.utils;

import eq.larry.dev.UltraCore;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static FileConfiguration c = UltraCore.getInstance().getConfig();

    public static void drawBasicConfig() {
        c.addDefault("RestartCmd", "restart");
        c.addDefault("RestartTime", 20);
        c.addDefault("Players.Min", 2);
        c.addDefault("Players.Max", 8);
        c.addDefault("Scoreboard.Wait.Name", "&6&lUltraPitchout");
        c.addDefault("Scoreboard.Wait.Players", "&aJoueurs : &9%co/%maxco");
        c.addDefault("Scoreboard.Game.Name", "&6&lUltraPitchout");
        c.addDefault("Scoreboard.Game.Alive", "&aVie : &9%alive");
        c.addDefault("Scoreboard.Game.Dead", "&aMort : &9%dead");
        c.addDefault("NumberOfLifeStart", 5);
        c.addDefault("SecondOfArmorRespawn", 3);
        c.addDefault("WaterKill", true);
        c.addDefault("Motd.Waiting", "&9&lParty en attente");
        c.addDefault("Motd.Progress", "&9&lParty en cour ");
        c.addDefault("Motd.Finish", "&9&lParty fini ");
        c.addDefault("Kick.AlreadyStartedMsg", "&9Party a start ");
        c.addDefault("Kick.FullMsg", "&9Serveur complet ");
        c.addDefault("Join.Msg", "&6%p &9â rejoin la party ! &9[&6%co&c|&6%maxco&9]");
        c.addDefault("Quit.Msg", "&6%p &9â quitter la party ! &9[&6%co&c|&6%maxco&9]");
        c.addDefault("Cooldown.Msg1", "&9La party commence dans  &6%time&9 secondes !");
        c.addDefault("Cooldown.Msg2", "&9La Party Commence ");
        c.addDefault("Finish.Msg", "&6%p &9a WIN ! ! &9Retour au HUB dans  &6%time&9 secondes!");
        c.options().copyDefaults(true);
        UltraCore.getInstance().saveConfig();
    }
}
