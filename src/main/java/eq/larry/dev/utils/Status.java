package eq.larry.dev.utils;

import eq.larry.dev.Enums.EnumsStatus;
import eq.larry.dev.UltraCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class Status {
    public static FileConfiguration c = UltraCore.getInstance().getConfig();
    public static boolean start;
    public static boolean full;
    public static String motd;
    public static int mode;

    public static void setMode(EnumsStatus var0) {
        if (var0 == EnumsStatus.Waiting) {
            mode = 0;
            start = false;
            motd = EnumsStatus.Waiting.getMotd();
        } else if (var0 == EnumsStatus.Progress) {
            mode = 1;
            start = true;
            motd = EnumsStatus.Progress.getMotd();
        } else if (var0 == EnumsStatus.Finish) {
            mode = 2;
            start = true;
            motd = EnumsStatus.Finish.getMotd();
        }

    }

    public static String getMotd() {
        return motd;
    }

    public static boolean isStart() {
        return start;
    }

    public static boolean isWaiting() {
        return mode == 0;
    }

    public static boolean isFinish() {
        return mode == 2;
    }

    public static boolean isFull() {
        return Bukkit.getServer().getOnlinePlayers().size() >= c.getInt("Players.Max");
    }
}
