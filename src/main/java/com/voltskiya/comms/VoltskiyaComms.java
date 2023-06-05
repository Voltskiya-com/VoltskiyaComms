package com.voltskiya.comms;

import com.voltskiya.comms.radio.RadioModule;
import com.voltskiya.lib.AbstractModule;
import com.voltskiya.lib.AbstractVoltPlugin;
import java.util.Collection;
import java.util.List;

public class VoltskiyaComms extends AbstractVoltPlugin {
    private static VoltskiyaComms instance;

    public VoltskiyaComms() {
        instance = this;
    }

    public static VoltskiyaComms get() {
        return instance;
    }

    @Override
    public Collection<AbstractModule> getModules() {
        return List.of(new RadioModule());
    }
}
