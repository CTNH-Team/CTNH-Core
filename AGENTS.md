# CTNH-Core KNOWLEDGE BASE

## OVERVIEW
CTNH-Core is the aggregate/core mod and CI release target. It hosts shared CTNH gameplay systems, GTCEu integration, large machine registries, generated data, Core-owned Ponder scenes, and cross-mod content.

## WHERE TO LOOK
- Mod entry: `src/main/java/io/github/cpearl0/ctnhcore/CTNHCore.java`. Forge mod initialization.
- GT addon: `src/main/java/io/github/cpearl0/ctnhcore/CTNHCoreGTAddon.java`. Registers GTCEu materials/recipes/generated recipe hooks.
- Config: `src/main/java/io/github/cpearl0/ctnhcore/CTNHConfig.java`. Core module config.
- Registries: `src/main/java/io/github/cpearl0/ctnhcore/registry/`. Items, machines, recipe types, blocks.
- Event/capability hooks: `src/main/java/io/github/cpearl0/ctnhcore/event/ForgeEventHandler.java`, `common/capability/`. EIO capacitor capabilities, namespace/remap helpers, and Forge-side runtime hooks.
- Jade integration: `src/main/java/io/github/cpearl0/ctnhcore/registry/jade/`. Registers Core providers through CTNH-Lib's Jade priority manager.
- Multiblocks: `src/main/java/io/github/cpearl0/ctnhcore/registry/machines/multiblock/`. Very large registries; `spotless:off/on` is intentional.
- Recipes/datagen: `src/main/java/io/github/cpearl0/ctnhcore/data/`. Source for `src/generated/resources`; recipe generators are split by chain, Create/addons, migrated scripts, mod modifies, mana/bio bridge, TConstruct, and multiblock domains.
- Ponder/client: `src/main/java/io/github/cpearl0/ctnhcore/client/ponder/`. Core-owned Create Ponder scenes, tags, plugin, and Core adapter builder; shared base builder lives in CTNH-Lib.
- Mixins: `src/main/java/io/github/cpearl0/ctnhcore/mixin/`, `src/main/resources/ctnhcore.mixins.json`. Keep JSON and package entries synchronized.

## REGISTRATION ENTRYPOINTS
- Registrate/root: `registry/CTNHRegistrate.java`, `registry/CTNHRegistration.java`.
- GT addon lifecycle: `CTNHCoreGTAddon.initializeAddon()` initializes items, blocks, block entities, and block maps; later hooks register tag prefixes, elements, ore/fluid veins, worldgen layers, recipes, and recipe removals.
- Common proxy: `common/CommonProxy.java` registers config, registrate, recipe conditions, machines, recipe types, datagen, creative tabs, and client/server setup listeners.
- Items/blocks/block entities: `registry/CTNHItems.java`, `registry/CTNHBlocks.java`, `registry/CTNHBlockEntities.java`.
- Creative tabs/tags/models: `registry/CTNHCreativeModeTabs.java`, `registry/CTNHTags.java`, `registry/CTNHModels.java`, `registry/CTNHModelLayers.java`.
- GTCEu machines: `registry/machines/CTNHMachines.java`; multiblocks under `registry/machines/multiblock/` plus `registry/CTNHMultiblockMachines.java`.
- Materials/worldgen: `registry/material/CTNHMaterials.java`, `registry/material/GTMaterialAddon.java`, `registry/CTNHTagPrefixes.java`, `registry/CTNHOres.java`, `registry/CTNHFluidVeins.java`, `registry/CTNHWorldgenLayers.java`.
- Recipe types/modifiers/conditions: `registry/CTNHRecipeTypes.java`, `registry/CTNHRecipeModifiers.java`, `registry/CTNHRecipeConditions.java`, `registry/CTNHRecipeCategories.java`.
- Recipe generation: `CTNHCoreGTAddon.addRecipes()` dispatches `data/recipe/**`; key areas include `chain/`, `create/`, `migrated/`, `modmodify/`, `mana/`, `tconstruct/`, `multiblock/`, and compatibility classes such as AE2, EIO, Sophisticated Storage/Backpacks, Immersive Aircraft, Omni Cells, and Create addon recipes.
- Recipe removal/filtering: `data/recipe/RecipeRemoval.java` registers ID-only filters, and `mixin/mc/RecipeManagerApplyMixin.java` removes matching incoming datapack entries at `RecipeManager.apply()` HEAD. Dynamic recipes are intentionally not filtered.
- Datagen: `data/CTNHCoreDatagen.java` plus providers under `data/provider/`.
- Ponder: `client/ponder/CTNHCorePonderPlugin.java` registers `CTNHCorePonderScenes` and `CTNHCorePonderTags` from `ClientProxy.onClientSetupEvent()`. Core scenes are grouped under `Kinetic/` and `Electric/`. During client datagen, `CommonProxy.gatherData()` calls CTNH-Lib's `CTNHPonderLang.init(new CTNHCorePonderPlugin())`.

## CONVENTIONS
- Namespace is `io.github.cpearl0.ctnhcore`.
- `src/generated/resources` is large and produced by `:modules:CTNH-Core:runData`.
- CI builds this module only; changes in other modules should still be validated through `:modules:CTNH-Core:build` when they affect aggregation.
- Some generated recipe Java lives under `data/recipe/generated`; distinguish Java recipe generators from JSON generated resources.
- Ponder `CTNHCorePonderSceneBuilder` is only a Core adapter around Lib's shared builder; keep reusable builder/text behavior in CTNH-Lib.
- `ctnhcore.mixins.json` covers broad integrations (AECs, Apotheosis, Ars Nouveau, Avaritia, Create, EIO/JEI, EMI, FTB Chunks, GTCEu, JAVD, LDLib, Legendary Survival, Minecraft reload/spawner, Sophisticated, TConstruct, TMRV, Vintage Improvements); inspect target mod versions before changing injection signatures.

## COMMANDS
```bash
./gradlew :modules:CTNH-Core:build
./gradlew :modules:CTNH-Core:runData
./gradlew :modules:CTNH-Core:spotlessCheck
./gradlew :modules:CTNH-Core:spotlessApply
```

## ANTI-PATTERNS
- Do not manually reformat huge multiblock registry sections protected by Spotless toggles.
- Do not patch `run/.tconstruct-dynamic-datagen` or `src/generated/resources` as the first choice; change datagen sources instead.
- Do not assume Core-only validation catches module-specific runtime/datagen issues.
- When referencing items/blocks/fluids in recipes, prefer static field references (`GTMaterials.Iron`, `CTNHBlocks.MY_BLOCK`, `TagPrefix.ingot`, `AEItems.X`) over registered tags (`CustomTags.X`), and prefer tags over `ResourceLocation` string parsing or `ForgeRegistries` lookups. Only fall back when no static constant exists for the target.
- Do not add broad cross-mod recipes to feature modules unless the feature module owns the whole mechanic; Core is the aggregator for most migrated/script compatibility recipes.
