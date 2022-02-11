package idv.hailelectronic.dimensiontraveler.dimensions;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class MirrorOfTheVoid extends ChunkGenerator {
    /*Copy from：
     *https://www.mcbbs.net/forum.php?mod=viewthread&tid=811614
     */
    private SimplexOctaveGenerator noise0;
    private SimplexOctaveGenerator noise1;
    private PerlinNoiseGenerator noise2;

    private final Map<Material, SlimefunItemStack> blockMappings = new EnumMap<>(Material.class);
    //private volatile MirrorOfTheVoidBiomeProvider provider;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);

        // 我们需要的噪声生成器
        if (noise0 == null) {
            noise0 = new SimplexOctaveGenerator(world.getSeed(), 8);
            noise0.setScale(0.005D);
        }
        if (noise1 == null) {
            noise1 = new SimplexOctaveGenerator(world.getSeed(), 8);
            noise0.setScale(0.01D);
        }
        if (noise2 == null) {
            noise2 = new PerlinNoiseGenerator(world.getSeed());
        }
        /*if (noise1 == null) {
            int[][] elevation = new int[0][0];
            int lx = chunkX * 16 ;
            int lz = chunkX * 16 ;
            noise1 = new PerlinNoiseGenerator(world.getSeed());
            double e = noise1.noise(lx * 0.01F, lz * 0.01F, 6, 2.0F, 0.5F);
            e = Math.pow(e, 2.5);
            elevation[lx][lz] = (int) (64 + e * 64F);
        }*/
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // 方块的真实坐标
                int realX = chunkX * 16 + x;
                int realZ = chunkZ * 16 + z;
                // noise 方法返回 -1 到 1 之间的随机数
                double noiseTop = noise0.noise(realX, realZ, 0.5D, 0.5D);
                double noiseTopMove = noise2.noise(realX, realZ, 0.5D, 0.5D);
                double noiseMid = noise1.noise(realX, realZ, 0.5D, 0.5D);
                double noiseBed = noise1.noise(realX, realZ, 0.5D, 0.5D);
                // 将 noise 值放大，作为该点的高度
                int height_top = (int) (noiseTop * 40D +127d+noiseTopMove*5d);
                int height_mid = (int) (noiseMid);
                int height_bedrock = (int) (noiseBed - 64d);
                int height_cloud = (int) (noiseTop*4d);
                // 中间石头
                for (int y = -5; y < height_top ; y++) {
                    chunkData.setBlock(x, y, z, Material.STONE);
                }
                for (int y = -64; y < height_mid; y++) {
                    chunkData.setBlock(x, y, z, Material.DEEPSLATE);
                }
                // 表面覆盖泥土和草方块
                chunkData.setBlock(x, height_top - 1, z, Material.DIRT);
                chunkData.setBlock(x, height_top, z, Material.GRASS_BLOCK);
                // 底层基岩
                chunkData.setBlock(x, -64, z, Material.BEDROCK);
                chunkData.setBlock(x, height_bedrock, z, Material.BEDROCK);
                //云
                //for (int y = 60; y < height_cloud ; y++) {
                //    chunkData.setBlock(x, y, z, getItem("CAST_SLATE"));
                //}
            }
        }
        return chunkData;
    }
    /*Copy from Galactifun:
     *https://github.com/Slimefun-Addon-Community/Galactifun/blob/master/src/main/java/io/github/addoncommunity/galactifun/base/universe/saturn/Titan.java
     *Line:124~137
     */
    /*
    public BiomeProvider getDefaultBiomeProvider( WorldInfo worldInfo) {
        return getBiomeProvider(worldInfo);
    }
    protected BiomeProvider getBiomeProvider( WorldInfo info) {
        init(info);
        return this.provider;
    }
    private void init(WorldInfo info) {
        if (this.provider == null) {
            this.provider = new MirrorOfTheVoidBiomeProvider();
        }
    }*/
    /*Copy from ExoticGarden:
     *https://github.com/TheBusyBiscuit/ExoticGarden/blob/master/src/main/java/io/github/thebusybiscuit/exoticgarden/ExoticGarden.java
     * Line:332~336
     */
    /*static Material getItem(String id) {
        SlimefunItem item = SlimefunItem.getById(id);
        return item != null ? item.getItem() : null;
    }*/
    /*Copy from Galactifun:
     *https://github.com/Slimefun-Addon-Community/Galactifun/blob/master/src/main/java/io/github/addoncommunity/galactifun/api/worlds/AlienWorld.java
     *Line:124~137
     */
    public final void addBlockMapping(Material vanillaItem, SlimefunItemStack slimefunItem) {
        this.blockMappings.put(vanillaItem, slimefunItem);
    }

}
