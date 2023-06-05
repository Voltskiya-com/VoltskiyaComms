package com.voltskiya.comms.radio;

import com.voltskiya.lib.AbstractModule;

public class RadioModule extends AbstractModule {

    private static RadioModule instance;

    public RadioModule(){
        instance = this;
    }

    public static RadioModule get() {
        return instance;
    }
    @Override
    public void enable() {
        new PagerTuneListener();
    }

    @Override
    public String getName() {
        return "Radio";
    }
}
