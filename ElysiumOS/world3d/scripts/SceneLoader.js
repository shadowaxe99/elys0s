// SceneLoader.js
class SceneLoader {
  constructor(renderer, scene) {
    this.renderer = renderer;
    this.scene = scene;
  }

  loadAssets(models, textures, onComplete) {
    const promises = [];

    // Load models
    models.forEach(modelPath => {
      promises.push(new Promise((resolve, reject) => {
        this.renderer.loadModel(modelPath, (model) => {
          if (model) {
            this.scene.add(model);
            resolve(model);
          } else {
            reject(new Error(`Failed to load model at ${modelPath}`));
          }
        });
      }));
    });

    // Load textures
    textures.forEach(texturePath => {
      promises.push(new Promise((resolve, reject) => {
        this.renderer.loadTexture(texturePath, (texture) => {
          if (texture) {
            this.scene.add(texture);
            resolve(texture);
          } else {
            reject(new Error(`Failed to load texture at ${texturePath}`));
          }
        });
      }));
    });

    Promise.all(promises).then(onComplete).catch(error => {
      console.error('Error loading assets:', error);
    });
  }

  loadScene(sceneConfigPath) {
    fetch(sceneConfigPath)
      .then(response => response.json())
      .then(sceneConfig => {
        const { models, textures } = sceneConfig;
        this.loadAssets(models, textures, () => {
          console.log('Scene loaded successfully.');
        });
      })
      .catch(error => {
        console.error('Error loading scene configuration:', error);
      });
  }
}

// Example usage:
// Assuming we have a renderer and scene object from our 3D engine
const sceneLoader = new SceneLoader(worldRenderer, sceneBuilder);
sceneLoader.loadScene('ElysiumOS/world3d/scenes/arenaScene.json');