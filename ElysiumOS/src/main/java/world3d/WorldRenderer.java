```java
package world3d;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.SceneGraphObject;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class WorldRenderer {

    private Canvas3D canvas3D;
    private SimpleUniverse universe;
    private BranchGroup sceneRoot;

    public WorldRenderer() {
        initialize();
    }

    private void initialize() {
        // Set up the canvas for rendering the 3D world
        canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        universe = new SimpleUniverse(canvas3D);

        // Set the viewing platform
        universe.getViewingPlatform().setNominalViewingTransform();

        // Create the root of the scene graph
        sceneRoot = new BranchGroup();
        sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        sceneRoot.setCapability(SceneGraphObject.ALLOW_BOUNDS_READ);
        sceneRoot.setCapability(SceneGraphObject.ALLOW_BOUNDS_WRITE);

        // Add the scene to the universe
        universe.addBranchGraph(sceneRoot);
    }

    public Canvas3D getCanvas3D() {
        return canvas3D;
    }

    public void addSceneToRoot(BranchGroup scene) {
        sceneRoot.addChild(scene);
    }

    public void render() {
        // This method will be responsible for rendering the 3D world
        // It will be called periodically to update the canvas
    }

    public void setViewpoint(Point3d point) {
        // This method can be used to set the viewpoint of the user in the 3D world
    }

    // Additional methods to manage the 3D world can be added here
}
```