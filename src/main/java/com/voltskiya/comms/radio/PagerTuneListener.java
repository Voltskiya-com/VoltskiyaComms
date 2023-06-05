package com.voltskiya.comms.radio;

import com.voltskiya.comms.VoltskiyaComms;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PagerTuneListener implements Listener {
    public PagerTuneListener(){
        VoltskiyaComms.get().registerEvents(this);
    }
}
