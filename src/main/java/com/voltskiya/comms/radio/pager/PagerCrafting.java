package com.voltskiya.comms.radio.pager;

import apple.mc.utilities.inventory.item.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class PagerCrafting {

    public static void registerRecipes() {
        Bukkit.addRecipe(pagerRecipe());
    }

    private static Recipe pagerRecipe() {
        @NotNull ItemStack result = InventoryUtils.get().makeItem(Material.PLAYER_HEAD, "Pager");
        InventoryUtils.get().addItemFlags(result, Pager.PAGER_ID);
        ShapedRecipe recipe = new ShapedRecipe(Pager.PAGER_KEY, result);
        recipe.shape("a  ", "   ", "  a");
        recipe.setIngredient('a', Material.BEDROCK);
        return recipe;
    }

}
