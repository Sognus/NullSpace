package cz.sognus.nullspace;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * @author Sognus
 */
public class NullSpacePopulator extends BlockPopulator {

    /**
     * @Author Sognus
     *
     * This method generates asteroids in grid defined in
     * plugin's configuration
     *
     * @param world world to generate in
     * @param random instance of random
     * @param chunk chunk in world to generate in
     */
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // Stop generation of Asteroids if they are disabled
        if(!NullSpace.plugin.generationAsteroidsEnabled) {
            return;
        }

        // Read Y coordination loaded from configuration
        int blockY = NullSpace.plugin.generationHeight;

        // If Y coordination loaded from configuration is outside of range, generate asteroids on random Y coordination.
        if(blockY < 0 || blockY > 255) {
            blockY = this.generateRandomNumberInRange(random, 0, 255);
        }

        // Iteration of blocks on X axis IN CHUNK (0-15)
        for (int blockX = 0; blockX < 16; blockX++) {
            // Iteration of block on Z axis IN CHUNK (0-15)
            for (int blockZ = 0; blockZ < 16; blockZ++) {
                // Retrieval of block instance from chunk
                Block b = chunk.getBlock(blockX, blockY, blockZ);
                // Retrival of blocks GLOBAL location
                Location l = b.getLocation();

                // If location of current block is on Asteroids GRID.
                if(this.isAsteroidLocation(l.getBlockX(), blockY, l.getBlockZ())) {
                    // Set block to block defined by configuration
                    chunk.getBlock(blockX, blockY, blockZ).setType(NullSpace.plugin.generationBlock);
                }
            }
        }
    }

    /**
     * @author Tarasicodissa
     * @author Sognus
     *
     *
     * @param X X coordinate of block
     * @param Y Y coordinate of block
     * @param Z Z coordinate of block
     * @return boolean value
     */
    private boolean isAsteroidLocation(int X, int Y, int Z) {
        int n = NullSpace.plugin.generationOffset;
        return (((X % n) == 0 && (Z % n) == 0)) || (((Math.abs(X % n)) == (n / 2) && ((Math.abs((Z % n)) == (n / 2)))));
    }

    /**
     * Author Sognus
     *
     * @param random instance of Random
     * @param minimum minimal value to generate
     * @param maximum maximal value to generate
     * @return random generated number
     */
    private int generateRandomNumberInRange(Random random, int minimum, int maximum) {
        return random.nextInt((maximum - minimum) + 1) + minimum;
    }
}