package com.voltskiya.comms.radio.pager;

import com.voltskiya.comms.VoltskiyaComms;
import org.bukkit.NamespacedKey;

public class Pager {

    public static final String PAGER_ID = "pager";
    public static final NamespacedKey PAGER_KEY = VoltskiyaComms.get().namespacedKey("pager");
    public static final NamespacedKey PAGER_FREQUENCY_KEY = VoltskiyaComms.get().namespacedKey("pager.frequency");
    public static final NamespacedKey PAGER_FREQUENCY_SELECTION_KEY = VoltskiyaComms.get().namespacedKey("pager.selection");
}
