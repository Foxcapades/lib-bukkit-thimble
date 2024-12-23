@file:Suppress("NOTHING_TO_INLINE")

package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.data.BlockData
import org.bukkit.craftbukkit.v1_21_R2.block.impl.*
import org.bukkit.inventory.meta.BlockDataMeta

fun BlockData.createMeta(): BlockDataMeta =
  this::class.simpleName?.get(5)?.let {
    when (it) {
      'A'  -> createMetaA()
      'B'  -> createMetaB()
      'C'  -> createMetaC()
      'D'  -> createMetaD()
      'E'  -> createMetaE()
      'F'  -> createMetaF()
      'G'  -> createMetaG()
      'H'  -> createMetaH()
      'I'  -> createMetaI()
      'J'  -> createMetaJ()
      'K'  -> createMetaK()
      'L'  -> createMetaL()
      'M'  -> createMetaM()
      'N'  -> createMetaN()
      'O'  -> createMetaO()
      'P'  -> createMetaP()
      'R'  -> createMetaR()
      'S'  -> createMetaS()
      'T'  -> createMetaT()
      'V'  -> createMetaV()
      'W'  -> createMetaW()
      else -> null
    }
  } ?: throw IllegalStateException("unrecognized BlockData type: $this")

private inline fun Material.toMeta() =
  Bukkit.getItemFactory().getItemMeta(this) as BlockDataMeta

private inline fun BlockData.createMetaA(): BlockDataMeta? =
  when (this) {
    is CraftAmethystCluster -> Material.AMETHYST_CLUSTER
    is CraftAnvil           -> Material.ANVIL
    else                    -> null
  }?.toMeta()

private inline fun BlockData.createMetaB(): BlockDataMeta? =
  when (this) {
    is CraftBamboo          -> Material.BAMBOO
    is CraftBanner          -> Material.BLUE_BANNER
    is CraftBannerWall      -> Material.BLUE_BANNER
    is CraftBarrel          -> Material.BARREL
    is CraftBarrier         -> Material.BARRIER
    is CraftBed             -> Material.BLUE_BED
    is CraftBeehive         -> Material.BEEHIVE
    is CraftBell            -> Material.BELL
    is CraftBigDripleaf     -> Material.BIG_DRIPLEAF
    is CraftBigDripleafStem -> Material.BIG_DRIPLEAF_STEM
    is CraftBlastFurnace    -> Material.BLAST_FURNACE
    is CraftBrewingStand    -> Material.BREWING_STAND
    is CraftBrushable       -> Material.SUSPICIOUS_SAND
    is CraftBubbleColumn    -> Material.BUBBLE_COLUMN
    is CraftButtonAbstract  -> Material.BIRCH_BUTTON
    else                    -> null
  }?.toMeta()

@Suppress("UnstableApiUsage")
private inline fun BlockData.createMetaC(): BlockDataMeta? =
  when (this) {
    is CraftCactus                -> Material.CACTUS
    is CraftCake                  -> Material.CAKE
    is CraftCalibratedSculkSensor -> Material.CALIBRATED_SCULK_SENSOR
    is CraftCampfire              -> Material.CAMPFIRE
    is CraftCandle                -> Material.CANDLE
    is CraftCandleCake            -> Material.CANDLE_CAKE
    is CraftCarrots               -> Material.CARROTS
    is CraftCaveVines             -> Material.CAVE_VINES
    is CraftCaveVinesPlant        -> Material.CAVE_VINES_PLANT
    is CraftCeilingHangingSign    -> Material.BIRCH_HANGING_SIGN
    is CraftChain                 -> Material.CHAIN
    is CraftCherryLeaves          -> Material.CHERRY_LEAVES
    is CraftChest                 -> Material.CHEST
    is CraftChestTrapped          -> Material.TRAPPED_CHEST
    is CraftChiseledBookShelf     -> Material.CHISELED_BOOKSHELF
    is CraftChorusFlower          -> Material.CHORUS_FLOWER
    is CraftChorusFruit           -> Material.CHORUS_FRUIT
    is CraftCobbleWall            -> Material.COBBLESTONE_WALL
    is CraftCocoa                 -> Material.COCOA
    is CraftCommand               -> Material.COMMAND_BLOCK
    is CraftComposter             -> Material.COMPOSTER
    is CraftConduit               -> Material.CONDUIT
    is CraftCopperBulb            -> Material.COPPER_BULB
    is CraftCoralDead             -> Material.DEAD_FIRE_CORAL
    is CraftCoralFan              -> Material.FIRE_CORAL_FAN
    is CraftCoralFanAbstract      -> Material.FIRE_CORAL_FAN
    is CraftCoralFanWall          -> Material.FIRE_CORAL_WALL_FAN
    is CraftCoralFanWallAbstract  -> Material.FIRE_CORAL_WALL_FAN
    is CraftCoralPlant            -> Material.FIRE_CORAL
    is CraftCrafter               -> Material.CRAFTER
    is CraftCreakingHeart         -> Material.CREAKING_HEART
    is CraftCrops                 -> Material.WHEAT
    else                    -> null
  }?.toMeta()

private inline fun BlockData.createMetaD(): BlockDataMeta? =
  when (this) {
    is CraftDaylightDetector -> Material.DAYLIGHT_DETECTOR
    is CraftDecoratedPot     -> Material.DECORATED_POT
    is CraftDirtSnow         -> Material.DIRT
    is CraftDispenser        -> Material.DISPENSER
    is CraftDoor             -> Material.BIRCH_DOOR
    is CraftDropper          -> Material.DROPPER
    else                     -> null
  }?.toMeta()

private inline fun BlockData.createMetaE(): BlockDataMeta? =
  when (this) {
    is CraftEnderChest       -> Material.ENDER_CHEST
    is CraftEnderPortalFrame -> Material.END_PORTAL_FRAME
    is CraftEndRod           -> Material.END_ROD
    else                     -> null
  }?.toMeta()

private inline fun BlockData.createMetaF(): BlockDataMeta? =
  when (this) {
    is CraftFence         -> Material.BIRCH_FENCE
    is CraftFenceGate     -> Material.BIRCH_FENCE_GATE
    is CraftFire          -> Material.FIRE
    is CraftFloorSign     -> Material.BIRCH_SIGN
    is CraftFluids        -> Material.WATER
    is CraftFurnaceFurace -> Material.FURNACE
    else                  -> null
  }?.toMeta()

private inline fun BlockData.createMetaG(): BlockDataMeta? =
  when (this) {
    is CraftGlazedTerracotta -> Material.BLACK_GLAZED_TERRACOTTA
    is CraftGlowLichen       -> Material.GLOW_LICHEN
    is CraftGrass            -> Material.GRASS_BLOCK
    is CraftGrindstone       -> Material.GRINDSTONE
    else                     -> null
  }?.toMeta()

@Suppress("UnstableApiUsage")
private inline fun BlockData.createMetaH(): BlockDataMeta? =
  when (this) {
    is CraftHangingMoss  -> Material.PALE_HANGING_MOSS
    is CraftHangingRoots -> Material.HANGING_ROOTS
    is CraftHay          -> Material.HAY_BLOCK
    is CraftHeavyCore    -> Material.HEAVY_CORE
    is CraftHopper       -> Material.HOPPER
    is CraftHugeMushroom -> Material.RED_MUSHROOM_BLOCK
    else                 -> null
  }?.toMeta()

private inline fun BlockData.createMetaI(): BlockDataMeta? =
  when (this) {
    is CraftIceFrost              -> Material.FROSTED_ICE
    is CraftInfestedRotatedPillar -> Material.QUARTZ_PILLAR
    is CraftIronBars              -> Material.IRON_BARS
    else                          -> null
  }?.toMeta()

private inline fun BlockData.createMetaJ(): BlockDataMeta? =
  when (this) {
    is CraftJigsaw  -> Material.JIGSAW
    is CraftJukeBox -> Material.JUKEBOX
    else            -> null
  }?.toMeta()

private inline fun BlockData.createMetaK(): BlockDataMeta? =
  when (this) {
    is CraftKelp -> Material.KELP
    else         -> null
  }?.toMeta()

private inline fun BlockData.createMetaL(): BlockDataMeta? =
  when (this) {
    is CraftLadder          -> Material.LADDER
    is CraftLantern         -> Material.LANTERN
    is CraftLayeredCauldron -> Material.WATER_CAULDRON
    is CraftLeaves          -> Material.BIRCH_LEAVES
    is CraftLectern         -> Material.LECTERN
    is CraftLever           -> Material.LEVER
    is CraftLight           -> Material.LIGHT
    is CraftLightningRod    -> Material.LIGHTNING_ROD
    is CraftLoom            -> Material.LOOM
    else                    -> null
  }?.toMeta()

private inline fun BlockData.createMetaM(): BlockDataMeta? =
  when (this) {
    is CraftMangroveLeaves    -> Material.MANGROVE_LEAVES
    is CraftMangrovePropagule -> Material.MANGROVE_PROPAGULE
    is CraftMangroveRoots     -> Material.MANGROVE_ROOTS
    is CraftMinecartDetector  -> Material.DETECTOR_RAIL
    is CraftMinecartTrack     -> Material.RAIL
    is CraftMossyCarpet       -> Material.MOSS_CARPET
    is CraftMycel             -> Material.MYCELIUM
    else                      -> null
  }?.toMeta()

private inline fun BlockData.createMetaN(): BlockDataMeta? =
  when (this) {
    is CraftNetherWart -> Material.NETHER_WART
    is CraftNote       -> Material.NOTE_BLOCK
    else               -> null
  }?.toMeta()

private inline fun BlockData.createMetaO(): BlockDataMeta? =
  when (this) {
    is CraftObserver -> Material.OBSERVER
    else             -> null
  }?.toMeta()

private inline fun BlockData.createMetaP(): BlockDataMeta? =
  when (this) {
    is CraftPiglinWallSkull       -> Material.PIGLIN_HEAD
    is CraftPinkPetals            -> Material.PINK_PETALS
    is CraftPiston                -> Material.PISTON
    is CraftPistonExtension       -> Material.PISTON_HEAD
    is CraftPistonMoving          -> Material.MOVING_PISTON
    is CraftPitcherCrop           -> Material.PITCHER_CROP
    is CraftPointedDripstone      -> Material.POINTED_DRIPSTONE
    is CraftPortal                -> Material.NETHER_PORTAL
    is CraftPotatoes              -> Material.POTATOES
    is CraftPoweredRail           -> Material.POWERED_RAIL
    is CraftPressurePlateBinary   -> Material.OAK_PRESSURE_PLATE
    is CraftPressurePlateWeighted -> Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    is CraftPumpkinCarved         -> Material.JACK_O_LANTERN
    else                          -> null
  }?.toMeta()

private inline fun BlockData.createMetaR(): BlockDataMeta? =
  when (this) {
    is CraftRedstoneComparator -> Material.COMPARATOR
    is CraftRedstoneLamp       -> Material.REDSTONE_LAMP
    is CraftRedstoneOre        -> Material.REDSTONE_ORE
    is CraftRedstoneTorch      -> Material.REDSTONE_TORCH
    is CraftRedstoneTorchWall  -> Material.REDSTONE_WALL_TORCH
    is CraftRedstoneWire       -> Material.REDSTONE_WIRE
    is CraftReed               -> Material.SUGAR_CANE
    is CraftRepeater           -> Material.REPEATER
    is CraftRespawnAnchor      -> Material.RESPAWN_ANCHOR
    is CraftRotatable          -> Material.BLUE_BANNER
    else                       -> null
  }?.toMeta()

private inline fun BlockData.createMetaS(): BlockDataMeta? =
  when (this) {
    is CraftSapling          -> Material.BIRCH_SAPLING
    is CraftScaffolding      -> Material.SCAFFOLDING
    is CraftSculkCatalyst    -> Material.SCULK_CATALYST
    is CraftSculkSensor      -> Material.SCULK_SENSOR
    is CraftSculkShrieker    -> Material.SCULK_SHRIEKER
    is CraftSculkVein        -> Material.SCULK_VEIN
    is CraftSeaPickle        -> Material.SEA_PICKLE
    is CraftShulkerBox       -> Material.SHULKER_BOX
    is CraftSkull            -> Material.SKELETON_SKULL
    is CraftSkullPlayer      -> Material.PLAYER_HEAD
    is CraftSkullPlayerWall  -> Material.PLAYER_WALL_HEAD
    is CraftSkullWall        -> Material.SKELETON_WALL_SKULL
    is CraftSmallDripleaf    -> Material.SMALL_DRIPLEAF
    is CraftSmoker           -> Material.SMOKER
    is CraftSnifferEgg       -> Material.SNIFFER_EGG
    is CraftSnow             -> Material.SNOW
    is CraftSoil             -> Material.FARMLAND
    is CraftStainedGlassPane -> Material.BLUE_STAINED_GLASS
    is CraftStairs           -> Material.BIRCH_STAIRS
    is CraftStem             -> Material.MELON_STEM
    is CraftStemAttached     -> Material.ATTACHED_MELON_STEM
    is CraftStepAbstract     -> Material.BIRCH_SLAB
    is CraftStonecutter      -> Material.STONECUTTER
    is CraftStructure        -> Material.STRUCTURE_BLOCK
    is CraftSweetBerryBush   -> Material.SWEET_BERRY_BUSH
    else                     -> null
  }?.toMeta()

private inline fun BlockData.createMetaT(): BlockDataMeta? =
  when (this) {
    is CraftTallPlant       -> Material.TALL_GRASS
    is CraftTallPlantFlower -> Material.SUNFLOWER
    is CraftTallSeagrass    -> Material.TALL_SEAGRASS
    is CraftTarget          -> Material.TARGET
    is CraftTNT             -> Material.TNT
    is CraftTorchflowerCrop -> Material.TORCHFLOWER_CROP
    is CraftTorchWall       -> Material.WALL_TORCH
    is CraftTrapdoor        -> Material.BIRCH_TRAPDOOR
    is CraftTrialSpawner    -> Material.TRIAL_SPAWNER
    is CraftTripwire        -> Material.TRIPWIRE
    is CraftTripwireHook    -> Material.TRIPWIRE_HOOK
    is CraftTurtleEgg       -> Material.TURTLE_EGG
    is CraftTwistingVines   -> Material.TWISTING_VINES
    else                    -> null
  }?.toMeta()

private inline fun BlockData.createMetaV(): BlockDataMeta? =
  when (this) {
    is CraftVault -> Material.VAULT
    is CraftVine  -> Material.VINE
    else          -> null
  }?.toMeta()

private inline fun BlockData.createMetaW(): BlockDataMeta? =
  when (this) {
    is CraftWallHangingSign          -> Material.BIRCH_WALL_HANGING_SIGN
    is CraftWallSign                 -> Material.BIRCH_WALL_SIGN
    is CraftWaterloggedTransparent   -> Material.COPPER_GRATE
    is CraftWeatheringCopperBulb     -> Material.COPPER_BULB
    is CraftWeatheringCopperDoor     -> Material.COPPER_DOOR
    is CraftWeatheringCopperGrate    -> Material.COPPER_GRATE
    is CraftWeatheringCopperSlab     -> Material.CUT_COPPER_SLAB
    is CraftWeatheringCopperStair    -> Material.CUT_COPPER_STAIRS
    is CraftWeatheringCopperTrapDoor -> Material.COPPER_TRAPDOOR
    is CraftWeepingVines             -> Material.WEEPING_VINES
    is CraftWitherSkull              -> Material.WITHER_SKELETON_SKULL
    is CraftWitherSkullWall          -> Material.WITHER_SKELETON_WALL_SKULL
    else                             -> null
  }?.toMeta()
