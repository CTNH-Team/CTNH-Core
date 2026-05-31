# CTNH-Core KNOWLEDGE BASE

## OVERVIEW
CTNH-Core is the aggregate/core mod and CI release target. It hosts shared CTNH gameplay systems, GTCEu integration, large machine registries, generated data, Core-owned Ponder scenes, and cross-mod content.

## WHERE TO LOOK
- Mod entry: `src/main/java/io/github/cpearl0/ctnhcore/CTNHCore.java`. Forge mod initialization.
- GT addon: `src/main/java/io/github/cpearl0/ctnhcore/CTNHCoreGTAddon.java`. Registers GTCEu materials/recipes/generated recipe hooks.
- Config: `src/main/java/io/github/cpearl0/ctnhcore/CTNHConfig.java`. Core module config.
- Registries: `src/main/java/io/github/cpearl0/ctnhcore/registry/`. Items, machines, recipe types, blocks.
- Multiblocks: `src/main/java/io/github/cpearl0/ctnhcore/registry/machines/multiblock/`. Very large registries; `spotless:off/on` is intentional.
- Recipes/datagen: `src/main/java/io/github/cpearl0/ctnhcore/data/`. Source for `src/generated/resources`.
- Ponder/client: `src/main/java/io/github/cpearl0/ctnhcore/client/ponder/`. Core-owned Create Ponder scenes, tags, plugin, and Core adapter builder; shared base builder lives in CTNH-Lib.
- Mixins: `src/main/java/io/github/cpearl0/ctnhcore/mixin/`, `src/main/resources/ctnhcore.mixins.json`. Keep JSON and package entries synchronized.

## REGISTRATION ENTRYPOINTS
- Registrate/root: `registry/CTNHRegistrate.java`, `registry/CTNHRegistration.java`.
- Items/blocks/block entities: `registry/CTNHItems.java`, `registry/CTNHBlocks.java`, `registry/CTNHBlockEntities.java`.
- Creative tabs/tags/models: `registry/CTNHCreativeModeTabs.java`, `registry/CTNHTags.java`, `registry/CTNHModels.java`, `registry/CTNHModelLayers.java`.
- GTCEu machines: `registry/machines/CTNHMachines.java`; multiblocks under `registry/machines/multiblock/` plus `registry/CTNHMultiblockMachines.java`.
- Materials/worldgen: `registry/material/CTNHMaterials.java`, `registry/material/GTMaterialAddon.java`, `registry/CTNHTagPrefixes.java`, `registry/CTNHOres.java`, `registry/CTNHFluidVeins.java`, `registry/CTNHWorldgenLayers.java`.
- Recipe types/modifiers/conditions: `registry/CTNHRecipeTypes.java`, `registry/CTNHRecipeModifiers.java`, `registry/CTNHRecipeConditions.java`, `registry/CTNHRecipeCategories.java`.
- Recipe generation: `CTNHCoreGTAddon.addRecipes()` dispatches `data/recipe/**`; put most new cross-module recipes here.
- Datagen: `data/CTNHCoreDatagen.java` plus providers under `data/provider/`.
- Ponder: `client/ponder/CTNHCorePonderPlugin.java` registers `CTNHCorePonderScenes` and `CTNHCorePonderTags` from `ClientProxy.onClientSetupEvent()`. Core scenes are grouped under `Kinetic/` and `Electric/`. During client datagen, `CommonProxy.gatherData()` calls CTNH-Lib's `CTNHPonderLang.init(new CTNHCorePonderPlugin())`.

## CONVENTIONS
- Namespace is `io.github.cpearl0.ctnhcore`.
- `src/generated/resources` is large and produced by `:modules:CTNH-Core:runData`.
- CI builds this module only; changes in other modules should still be validated through `:modules:CTNH-Core:build` when they affect aggregation.
- Some generated recipe Java lives under `data/recipe/generated`; distinguish Java recipe generators from JSON generated resources.
- Ponder `CTNHCorePonderSceneBuilder` is only a Core adapter around Lib's shared builder; keep reusable builder/text behavior in CTNH-Lib.

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
