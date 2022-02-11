package idv.hailelectronic.dimensiontraveler.dimensions;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

//import javax.annotation.Nonnull;

import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntPair;

public abstract class MirrorOfTheVoidBiomeProvider extends BiomeProvider{
    /*Copy from Galactifun:
     *https://github.com/Slimefun-Addon-Community/Galactifun/blob/master/src/main/java/io/github/addoncommunity/galactifun/base/universe/saturn/TitanBiomeProvider.java
     */
    private volatile SimplexOctaveGenerator heat;
    private volatile SimplexOctaveGenerator humidity;

    /*private final Map<IntIntPair,MirrorOfTheVoidBiome> cachedData = Collections.synchronizedMap(new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<IntIntPair,MirrorOfTheVoidBiome> eldest) {
            return size() > 200;
        }
    });*/

    /*private final List<Biome> allBiomes = Arrays.stream(MirrorOfTheVoidBiome.values()).map(MirrorOfTheVoidBiome::biome).collect(Collectors.toList());

    @Override
    public Biome getBiome( WorldInfo worldInfo, int x, int y, int z) {
        return getBiome(worldInfo, x, z).biome;
    }

    MirrorOfTheVoidBiome getBiome(WorldInfo info, int x, int z) {
        init(info);

        //MirrorOfTheVoidBiome cached = cachedData.get(new IntIntImmutablePair(x, z));
        //if (cached != null) {
        //    return cached;
        //}

        double heat = this.heat.noise(x, z, 0.01, 0.03, true);
        double humidity = this.humidity.noise(x, z, 0.01, 0.03, true);

        MirrorOfTheVoidBiome biome;
        if (humidity > 0.2) {
            if (heat > 0.2) {
                biome = MirrorOfTheVoidBiome.FOREST;
            } else if (heat > 0) {
                biome = MirrorOfTheVoidBiome.MOUNTAINS;
            } else {
                biome = MirrorOfTheVoidBiome.OCEAN;
            }
        } else if (humidity > -0.1) {
            if (heat > 0.2) {
                biome = MirrorOfTheVoidBiome.PLAINS;
            } else {
                biome = MirrorOfTheVoidBiome.RIVER;
            }
        } else {
            biome = MirrorOfTheVoidBiome.CRACK;
        }

        //cachedData.put(new IntIntImmutablePair(x, z), biome);
        return biome;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return allBiomes;
    }

    private void init(WorldInfo worldInfo) {
        if (this.heat == null) {
            this.heat = new SimplexOctaveGenerator(worldInfo.getSeed(), 8);
            this.heat.setScale(0.001);
        }
        if (this.humidity == null) {
            this.humidity = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()).nextLong(), 8);
            this.humidity.setScale(0.003);
        }
    }*/

}
