package io.github.cpearl0.ctnhcore.client.ponder;

import net.createmod.ponder.api.element.TextElementBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;

public class CTNHPonderSceneBuilder extends CreateSceneBuilder {

    public CTNHPonderSceneBuilder(SceneBuilder builder) {
        super(builder);
    }

    public void init5x5(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 5);
        this.scaleSceneView(0.9f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void init7x7(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 7);
        this.scaleSceneView(0.75f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void init9x9(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 9);
        this.scaleSceneView(0.6f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void initAll(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 32);
        this.scaleSceneView(0.3f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void rotateAround(int duration) {
        int time = duration / 4;
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
    }

    public CreateSceneBuilder getSceneBuilder() {
        return this;
    public TextElementBuilder showText(int duration) {
        return overlay().showText(duration).text("");
    }

    public void title(String sceneId) {
        title(sceneId, "");
    }
}
