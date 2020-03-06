package cz.sognus.nullspace;

import org.bukkit.block.Block;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NullSpaceGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        // Iteration of X Axis of blocks IN CHUNK
        for (int X = 0; X < 16; X++) {
            // Iteration of Z Axis of blocks IN CHUNK
            for (int Z = 0; Z < 16; Z++) {
                // Iteration of Y Axis of blocks IN CHUNK
                for (int Y = 0; Y < chunk.getMaxHeight(); Y++) {
                    // Set current block in chunk to AIR type
                    chunk.setBlock(X, Y, Z, Material.AIR);
                }
            }
        }
        return chunk;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        ArrayList<BlockPopulator> list = new ArrayList<BlockPopulator>();
        list.add((BlockPopulator) new NullSpacePopulator());
        return list;
    }
}