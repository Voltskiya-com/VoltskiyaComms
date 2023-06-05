package com.voltskiya.comms.radio.pager;

import apple.mc.utilities.inventory.item.InventoryUtils;
import com.voltskiya.comms.VoltskiyaComms;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class PagerTuneListener implements Listener {

    public PagerTuneListener() {
        VoltskiyaComms.get().registerEvents(this);
    }


    private static void incrementFrequency(ItemStack item, int increment) {
        int selection = getSelection(item);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        int frequency = container.getOrDefault(Pager.PAGER_FREQUENCY_KEY, PersistentDataType.INTEGER, 1);
        int finalFreq = 0;
        for (int i = 0; i < 4; i++) {
            int digitI = getDigit(frequency, i);
            if (selection == i) {
                digitI += increment + 10;
                digitI %= 10;
            }
            int multiplier = (int) Math.pow(10, 3 - i);
            finalFreq += multiplier * digitI;
        }
        container.set(Pager.PAGER_FREQUENCY_KEY, PersistentDataType.INTEGER, finalFreq);
        item.setItemMeta(meta);
    }

    private static void incrementSelection(ItemStack item, int increment) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        int selection = container.getOrDefault(Pager.PAGER_FREQUENCY_SELECTION_KEY, PersistentDataType.INTEGER, 1);
        selection += increment;
        selection %= 4;
        if (selection < 0) selection += 4;
        container.set(Pager.PAGER_FREQUENCY_SELECTION_KEY, PersistentDataType.INTEGER, selection);
        item.setItemMeta(meta);
    }

    private static int getDigit(int frequency, int i) {
        int mod = (int) Math.pow(10, 4 - i);
        return (frequency / (mod / 10)) % 10;
    }

    private static int getSelection(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer()
            .getOrDefault(Pager.PAGER_FREQUENCY_SELECTION_KEY, PersistentDataType.INTEGER, 0);
    }

    private static int getFrequency(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().getOrDefault(Pager.PAGER_FREQUENCY_KEY, PersistentDataType.INTEGER, 0);
    }

    @EventHandler
    public void onTune(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType().isAir()) return;
        @NotNull String[] itemFlags = InventoryUtils.get().getItemFlags(item);
        if (Arrays.stream(itemFlags).noneMatch(Pager.PAGER_ID::equals)) return;
        if (event.getClickedBlock() != null) return;
        doPlayerAction(event, item);

        int selection = getSelection(item);
        int frequency = getFrequency(item);
        List<Component> messageParts = new ArrayList<>();
        messageParts.add(Component.text("Frequency: "));
        for (int i = 0; i < 4; i++) {
            int digit = getDigit(frequency, i);
            int color = i == selection ? 0x75ff93 : 0xd46363;
            messageParts.add(Component.text(String.valueOf(digit), TextColor.color(color)));
        }
        event.setCancelled(true);
        Component message = Component.join(JoinConfiguration.noSeparators(), messageParts);
        Times titleTimes = Times.times(Duration.ZERO, Duration.ofMillis(750), Duration.ofMillis(325));
        event.getPlayer().showTitle(Title.title(Component.empty(), message, titleTimes));
    }

    private void doPlayerAction(PlayerInteractEvent event, ItemStack item) {
        boolean isLeftClick = event.getAction().isLeftClick();
        if (event.getPlayer().isSneaking()) {
            if (isLeftClick) {
                incrementSelection(item, -1);
            } else {
                incrementSelection(item, 1);
            }
        } else {
            if (isLeftClick) {
                incrementFrequency(item, -1);
            } else {
                incrementFrequency(item, 1);
            }
        }
    }
}
