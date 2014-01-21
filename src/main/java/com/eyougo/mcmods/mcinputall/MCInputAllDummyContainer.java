package com.eyougo.mcmods.mcinputall;

import java.util.Arrays;
import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class MCInputAllDummyContainer extends DummyModContainer
{

    public MCInputAllDummyContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "MCInputAll";
        meta.name = "MCInputAll";
        meta.version = "1.7.x-beta";
        meta.authorList = Arrays.asList("eyougo");
        meta.description = "MCInputAll is a way to enable multi-byte input method in Minecraft. (all platform)";
        meta.credits = "eyougo";
        meta.url = "https://github.com/eyougo/MCInputAll";
        meta.updateUrl = "https://github.com/eyougo/MCInputAll/releases";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return true;
    }

}