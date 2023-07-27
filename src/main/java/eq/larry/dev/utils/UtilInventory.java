package eq.larry.dev.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class UtilInventory {
    public static Map<String, ItemStack[]> playerInventories = new HashMap();
    public static HashMap<Player, ItemStack[]> armor = new HashMap();

    public static ItemStack getColorArmor(Material var0, Color var1) {
        ItemStack var2 = new ItemStack(var0, 1);
        LeatherArmorMeta var3 = (LeatherArmorMeta)var2.getItemMeta();
        var3.setColor(var1);
        var2.setItemMeta(var3);
        return var2;
    }

    public static void saveInventory(Player var0) {
        PlayerInventory var1 = var0.getInventory();
        ArrayList var2 = new ArrayList();
        var2.addAll(Arrays.asList(var1.getContents()));
        var2.addAll(Arrays.asList(var1.getArmorContents()));
        ItemStack[] var3 = (ItemStack[])var2.toArray(new ItemStack[0]);
        playerInventories.put(var0.getName(), var3);
        var0.getInventory().clear();
        var0.getInventory().setArmorContents((ItemStack[])null);
    }

    public static void resetInventory(Player var0) {
        if (playerInventories.containsKey(var0.getName())) {
            var0.getInventory().clear();
            var0.getInventory().setArmorContents((ItemStack[])null);
            ItemStack[] var1 = (ItemStack[])playerInventories.get(var0.getName());
            ItemStack[] var2 = new ItemStack[var1.length - 4];
            ItemStack[] var3 = new ItemStack[4];
            int var4 = 0;

            for(int var5 = 0; var5 < var1.length; ++var5) {
                if (var5 < var1.length - 4) {
                    var2[var5] = var1[var5];
                } else {
                    var3[var4] = var1[var5];
                    ++var4;
                }
            }

            var0.getInventory().setContents(var2);
            var0.getInventory().setArmorContents(var3);
            var0.updateInventory();
        }
    }

    public static void clearArmor(Player var0) {
        var0.getInventory().setHelmet((ItemStack)null);
        var0.getInventory().setChestplate((ItemStack)null);
        var0.getInventory().setLeggings((ItemStack)null);
        var0.getInventory().setBoots((ItemStack)null);
    }

    public static void saveArmor(Player var0) {
        armor.put(var0, var0.getEquipment().getArmorContents());
    }

    public static void restoreArmor(Player var0) {
        if (armor.containsKey(var0)) {
            var0.getInventory().setArmorContents((ItemStack[])armor.get(var0));
        }

    }

    public static void forsakeArmor(Player var0) {
        armor.remove(var0);
    }

    public static int removeItem(Inventory var0, Material var1, int var2) {
        int var3 = var2;

        for(int var4 = 0; var4 < var0.getSize(); ++var4) {
            ItemStack var5 = var0.getItem(var4);
            if (var5 != null && var5.getType() == var1) {
                if (var3 >= var5.getAmount()) {
                    var3 -= var5.getAmount();
                    var0.clear(var4);
                } else {
                    if (var3 <= 0) {
                        break;
                    }

                    var5.setAmount(var5.getAmount() - var3);
                    var3 = 0;
                }
            }
        }

        return var2 - var3;
    }
}