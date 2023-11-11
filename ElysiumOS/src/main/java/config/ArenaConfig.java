```java
package config;

public class ArenaConfig {
    private String arenaName;
    private int maxPlayers;
    private boolean realTimeUpdates;
    private String worldDataIntegrationType;
    private String smartDeviceConnectivity;

    public ArenaConfig() {
        // Default configuration values
        this.arenaName = "Elysium Arena";
        this.maxPlayers = 100;
        this.realTimeUpdates = true;
        this.worldDataIntegrationType = "Dynamic";
        this.smartDeviceConnectivity = "Enabled";
    }

    // Getters and Setters
    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isRealTimeUpdates() {
        return realTimeUpdates;
    }

    public void setRealTimeUpdates(boolean realTimeUpdates) {
        this.realTimeUpdates = realTimeUpdates;
    }

    public String getWorldDataIntegrationType() {
        return worldDataIntegrationType;
    }

    public void setWorldDataIntegrationType(String worldDataIntegrationType) {
        this.worldDataIntegrationType = worldDataIntegrationType;
    }

    public String getSmartDeviceConnectivity() {
        return smartDeviceConnectivity;
    }

    public void setSmartDeviceConnectivity(String smartDeviceConnectivity) {
        this.smartDeviceConnectivity = smartDeviceConnectivity;
    }
}
```