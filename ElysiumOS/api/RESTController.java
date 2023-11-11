```java
package api;

import api.endpoints.AutomationEndpoint;
import api.endpoints.ArenaEndpoint;
import api.endpoints.BlockchainEndpoint;
import api.services.AuthenticationService;
import api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final AutomationEndpoint automationEndpoint;
    private final ArenaEndpoint arenaEndpoint;
    private final BlockchainEndpoint blockchainEndpoint;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public RESTController(AutomationEndpoint automationEndpoint, ArenaEndpoint arenaEndpoint, BlockchainEndpoint blockchainEndpoint, AuthenticationService authenticationService, UserService userService) {
        this.automationEndpoint = automationEndpoint;
        this.arenaEndpoint = arenaEndpoint;
        this.blockchainEndpoint = blockchainEndpoint;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    // Additional endpoints and methods to be implemented here
}
```