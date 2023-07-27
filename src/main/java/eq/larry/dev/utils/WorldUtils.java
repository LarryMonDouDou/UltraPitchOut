package eq.larry.dev.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldUtils {
    public static boolean deleteWorld(File var0) {
        if (var0.exists()) {
            File[] var1 = var0.listFiles();

            for(int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].isDirectory()) {
                    deleteWorld(var1[var2]);
                } else {
                    var1[var2].delete();
                }
            }
        }

        return var0.delete();
    }

    public static void resetWorld(String var0, String var1) {
        File var2 = new File(var0);
        File var3 = new File(var1);
        deleteWorld(var3);
        copyFile(var2, var3);
    }

    public static void copyFile(File var0, File var1) {
        try {
            ArrayList var2 = new ArrayList(Arrays.asList("uid.dat", "session.dat"));
            if (!var2.contains(var0.getName())) {
                int var6;
                if (var0.isDirectory()) {
                    if (!var1.exists()) {
                        var1.mkdirs();
                    }

                    String[] var3 = var0.list();
                    String[] var7 = var3;
                    var6 = var3.length;

                    for(int var5 = 0; var5 < var6; ++var5) {
                        String var4 = var7[var5];
                        File var8 = new File(var0, var4);
                        File var9 = new File(var1, var4);
                        copyFile(var8, var9);
                    }
                } else {
                    FileInputStream var11 = new FileInputStream(var0);
                    FileOutputStream var12 = new FileOutputStream(var1);
                    byte[] var13 = new byte[1024];

                    while((var6 = var11.read(var13)) > 0) {
                        var12.write(var13, 0, var6);
                    }

                    var11.close();
                    var12.close();
                }
            }
        } catch (IOException var10) {
        }

    }
}
