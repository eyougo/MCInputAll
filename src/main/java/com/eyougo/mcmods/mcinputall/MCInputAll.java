package com.eyougo.mcmods.mcinputall;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.Level;

import java.util.Map;

/**
 * User: mei
 * Date: 1/20/14
 * Time: 10:55
 */

@IFMLLoadingPlugin.Name("MCInputAll")
@IFMLLoadingPlugin.MCVersion("")
@IFMLLoadingPlugin.TransformerExclusions("com.eyougo.mcmods.mcinputall.")
public class MCInputAll implements IFMLLoadingPlugin{
    public static boolean RUNTIME_DEOBF = true;
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {"com.eyougo.mcmods.mcinputall.MCInputAllTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "com.eyougo.mcmods.mcinputall.MCInputAllDummyContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        RUNTIME_DEOBF = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
