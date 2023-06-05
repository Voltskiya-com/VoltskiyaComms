package com.voltskiya.comms.radio;

import com.voltskiya.comms.radio.pager.PagerCrafting;
import com.voltskiya.comms.radio.pager.PagerTuneListener;
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
        PagerCrafting.registerRecipes();
        new PagerTuneListener();
    }

    @Override
    public String getName() {
        return "Radio";
    }
}
