package net.knsh.cyclic.config;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.block.antipotion.AntiBeaconBlockEntity;
import net.knsh.cyclic.block.anvil.AnvilAutoBlockEntity;
import net.knsh.cyclic.block.anvilmagma.AnvilMagmaBlockEntity;
import net.knsh.cyclic.block.cable.energy.EnergyCableBlockEntity;
import net.knsh.cyclic.block.cable.fluid.FluidCableBlockEntity;
import net.knsh.cyclic.block.crafter.CrafterBlockEntity;
import net.knsh.cyclic.block.generatorfuel.GeneratorFuelBlockEntity;
import net.knsh.cyclic.enchant.*;
import net.knsh.cyclic.library.config.ConfigTemplate;
import net.knsh.cyclic.porting.neoforge.FluidFabricToForge;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.*;

public class ConfigRegistry extends ConfigTemplate {
    private static ForgeConfigSpec COMMON_CONFIG;
    private static ForgeConfigSpec CLIENT_CONFIG;

    public void setupMain() {
        COMMON_CONFIG.setConfig(setup(Cyclic.MOD_ID));
        ForgeConfigRegistry.INSTANCE.register(Cyclic.MOD_ID, ModConfig.Type.COMMON, ConfigRegistry.COMMON_CONFIG);
    }

    // Defaults
    private static final List<String> BEHEADING = new ArrayList<>();
    private static ForgeConfigSpec.ConfigValue<List<? extends String>> BEHEADING_SKINS;

    public void setupClient() {
        //CLIENT_CONFIG.setConfig(setup(Cyclic.MOD_ID + "-client"));
        //ForgeConfigRegistry.INSTANCE.register(Cyclic.MOD_ID, ModConfig.Type.CLIENT, ConfigRegistry.CLIENT_CONFIG);
    }

    private static final String WALL = "####################################################################################";

    static {
        buildDefaults();
        initConfig();
    }

    private static void buildDefaults() {
        //http://minecraft.gamepedia.com/Player.dat_format#Player_Heads
        //mhf https://twitter.com/Marc_IRL/status/542330244473311232  https://pastebin.com/5mug6EBu
        //other https://www.planetminecraft.com/blog/minecraft-playerheads-2579899/
        //NBT image data from  http://www.minecraft-heads.com/custom/heads/animals/6746-llama
        BEHEADING.add("minecraft:blaze:MHF_Blaze");
        BEHEADING.add("minecraft:cat:MHF_Ocelot");
        BEHEADING.add("minecraft:cave_spider:MHF_CaveSpider");
        BEHEADING.add("minecraft:chicken:MHF_Chicken");
        BEHEADING.add("minecraft:cow:MHF_Cow");
        BEHEADING.add("minecraft:enderman:MHF_Enderman");
        BEHEADING.add("minecraft:ghast:MHF_Ghast");
        BEHEADING.add("minecraft:iron_golem:MHF_Golem");
        BEHEADING.add("minecraft:magma_cube:MHF_LavaSlime");
        BEHEADING.add("minecraft:mooshroom:MHF_MushroomCow");
        BEHEADING.add("minecraft:ocelot:MHF_Ocelot");
        BEHEADING.add("minecraft:pig:MHF_Pig");
        BEHEADING.add("minecraft:zombie_pigman:MHF_PigZombie");
        BEHEADING.add("minecraft:sheep:MHF_Sheep");
        BEHEADING.add("minecraft:slime:MHF_Slime");
        BEHEADING.add("minecraft:spider:MHF_Spider");
        BEHEADING.add("minecraft:squid:MHF_Squid");
        BEHEADING.add("minecraft:villager:MHF_Villager");
        BEHEADING.add("minecraft:witch:MHF_Witch");
        BEHEADING.add("minecraft:wolf:MHF_Wolf");
        BEHEADING.add("minecraft:guardian:MHF_Guardian");
        BEHEADING.add("minecraft:elder_guardian:MHF_Guardian");
        BEHEADING.add("minecraft:snow_golem:MHF_SnowGolem");
        BEHEADING.add("minecraft:silverfish:MHF_Silverfish");
        BEHEADING.add("minecraft:endermite:MHF_Endermite");
    }

    private static void initConfig() {
        final ForgeConfigSpec.Builder CFG = builder();
        CFG.comment(WALL, " Enchantment related configs (if disabled, they may still show up as NBT on books and such but have functions disabled and are not obtainable in survival)", WALL)
                .push("enchantment");
        BeheadingEnchant.CFG = CFG.comment("If true, then the beheading enchantment will be enabled. \nThis enchantment increases the chance of mob heads dropping when killing mobs.").define(BeheadingEnchant.ID + ".enabled", true);
        BEHEADING_SKINS = CFG.comment("Beheading enchant add player skin head drop, add any mob id and any skin").defineList(BeheadingEnchant.ID + ".EntityMHF", BEHEADING,
                it -> it instanceof String);
        BeheadingEnchant.PERCDROP = CFG.comment("Base perecentage chance to drop a head on kill").defineInRange(BeheadingEnchant.ID + ".percent", 20, 1, 99);
        BeheadingEnchant.PERCPERLEVEL = CFG.comment("Percentage increase per level of enchant. Formula [percent + (level - 1) * per_level] ").defineInRange(BeheadingEnchant.ID + ".per_level", 25, 1, 99);
        TravellerEnchant.CFG = CFG.comment("If true, then the traveller enchantment will be enabled. \nThis enchantment reduces damage from cactus, sweet berry bushes, and fall damage. It also prevents elytra damage when flying.").define(TravellerEnchant.ID + ".enabled", true);
        AutoSmeltEnchant.CFG = CFG.comment("If true, then the auto smelt enchantment will be enabled. \nThis enchantment will smelt blocks as they are mined.").define(AutoSmeltEnchant.ID + ".enabled", true);
        ReachEnchant.CFG = CFG.comment("If true, then the reach enchantment will be enabled. \nThis enchantment increases the reach of the player by 5 blocks.").define(ReachEnchant.ID + ".enabled", true);
        ReachEnchant.REACH_BOOST = CFG.comment("How much reach to add to the player (in blocks). \nDefault is 11.").defineInRange(ReachEnchant.ID + ".reach_boost", 11, 0, 20);
        BeekeeperEnchant.CFG = CFG.comment("If true, then the beekeeper enchantment will be enabled. \nThis enchantment makes bees not attack the player.").define(BeekeeperEnchant.ID + ".enabled", true);
        CFG.pop(); //enchantment
        CFG.comment(WALL, " Block specific configs", WALL).push("blocks");
        AntiBeaconBlockEntity.HARMFUL_POTIONS = CFG.comment("If true, then all potions marked as harmful/negative will be used in addition to the 'anti_beacon.potion_list' for cures and immunities  (used by both sponge and artemisbeacon).")
                .define("harmful_potions", true);
        AntiBeaconBlockEntity.RADIUS = CFG.comment("Radius to protect players and entities from potion effects being applied (used by both sponge and artemisbeacon). ")
                .defineInRange("anti_beacon.radius", 16, 1, 128);
        AntiBeaconBlockEntity.TICKS = CFG.comment("Ticks to fire anti beacon and remove effects from entities (20 = 1 second).  Does not affect potion immunity which applies regardless of ticks. This only used if you gain a potion effect out of range and then walk into range, so keep this large.")
                .defineInRange("anti_beacon.ticks", 200, 20, 9999);
        AntiBeaconBlockEntity.POTIONS = CFG.comment("List of extra effects to clear. supports wildcard such as 'cyclic:*'. (This list is is used even if harmful_potions=false or true both)")
                .defineList("anti_beacon.potion_list", Arrays.asList("minecraft:poison", "minecraft:*_poison", "minecraft:wither",
                        "cyclic:gravity",
                        "minecraft:weakness", "minecraft:slowness"), it -> it instanceof String);
        GeneratorFuelBlockEntity.RF_PER_TICK = CFG.comment("RF energy per tick generated while burning furnace fuel in this machine.  Burn time in ticks is the same as furnace values, so 1 coal = 1600 ticks")
                .defineInRange("generator_fuel.rf_per_tick", 80, 1, 6400);
        CrafterBlockEntity.POWERCONF = CFG.comment("Power per use crafter").defineInRange("crafter.energy_cost", 500, 0, 64000);
        AnvilAutoBlockEntity.POWERCONF = CFG.comment("Power per repair anvil").defineInRange("anvil.energy_cost", 250, 0, 64000);
        AnvilMagmaBlockEntity.FLUIDCOST = CFG.comment("Cost of magma fluid per action").defineInRange("anvil_magma.fluid_cost", 100, 1, 64000);

        FluidCableBlockEntity.BUFFERSIZE = CFG.comment("How many buckets of buffer fluid the fluid cable can hold (for each direction. for example 2 here means 2000ub in each face)")
                .defineInRange("cables.fluid.buffer", (int) FluidFabricToForge.toDroplets(16), (int) FluidFabricToForge.toDroplets(1), (int) FluidFabricToForge.toDroplets(32));
        FluidCableBlockEntity.TRANSFER_RATE = CFG.comment("How many fluid units per tick can flow through these cables each tick (1 bucket = 1000) including normal flow and extraction mode")
                .defineInRange("cables.fluid.flow", (int) FluidFabricToForge.toDroplets(1000), (int) FluidFabricToForge.toDroplets(100), (int) FluidFabricToForge.toDroplets(32 * 1000));
        EnergyCableBlockEntity.BUFFERSIZE = CFG.comment("How much buffer the energy cables hold (must not be smaller than flow)")
                .defineInRange("cables.energy.buffer", 320000, 10, 320000 * 4);
        EnergyCableBlockEntity.TRANSFER_RATE = CFG.comment("How fast energy flows in these cables (must not be greater than buffer)")
                .defineInRange("cables.energy.flow", 10000, 1000, 32 * 10000);

        COMMON_CONFIG = CFG.build();
    }

    public static Map<String, String> getMappedBeheading() {
        Map<String, String> mappedBeheading = new HashMap<String, String>();
        for (String s : BEHEADING_SKINS.get()) {
            try {
                String[] stuff = s.split(":");
                String entity = stuff[0] + ":" + stuff[1];
                String skin = stuff[2];
                mappedBeheading.put(entity, skin);
            }
            catch (Exception e) {
                Cyclic.LOGGER.error("Beheading Enchantment: Invalid config entry " + s);
            }
        }
        return mappedBeheading;
    }
}
