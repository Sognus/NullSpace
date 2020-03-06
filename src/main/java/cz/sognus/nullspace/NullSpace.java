package cz.sognus.nullspace;


import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sognus
 */
public class NullSpace extends JavaPlugin {
    // Plugin's logger
    public static Logger log = Logger.getLogger("nullspace");
    // Plugin's config
    public static NullSpace plugin = null;

    // Configuration
    public Boolean generationAsteroidsEnabled = null;
    public Integer generationOffset = null;
    public Integer generationHeight = null;
    public Material generationBlock = null;


    @Override
    public void onEnable() {
        NullSpace.plugin = this;
        this.saveDefaultConfig();
        this.refreshConfig();

        // Inform console about plugin start
        log.info("Plugin started");
        log.info("Generator offset: " + this.generationOffset);
        log.info("Generator block: " + this.generationBlock.name());
        log.info("Generator height: " + this.generationHeight);
        log.info("Asteroid generation enabled: " + this.generationAsteroidsEnabled);

        // Register command
        this.getCommand("nullspace").setExecutor(new NullSpaceCommands());
    }

    @Override
    public void onDisable() {
        // Inform console that plugin was disabled
        log.info("Plugin disabled");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        // Create new custom generator
        return new NullSpaceGenerator();
    }

    /**
     * Refreshes data loaded from configuration file
     */
    public boolean refreshConfig() {
        try {
            this.generationOffset = this.getConfig().getInt("generation-offset");
            this.generationHeight = this.getConfig().getInt("generation-height");
            this.generationBlock = Material.getMaterial(this.getConfig().getString("generation-block"));
            this.generationAsteroidsEnabled = this.getConfig().getBoolean("generation-asteroids-enabled");
            return true;
        } catch(Exception e) {
            NullSpace.log.log(Level.SEVERE, "Could not parse configuration file: " + e.getMessage());
            onDisable();
            return false;
        }
    }



}