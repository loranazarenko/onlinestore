package com.epam.onlinestore.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger log = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put("logup", new LogupCommand());
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("logout", new LogoutCommand());
      /*   commandsMap.put("catalog", new CommandCatalog());
        commandsMap.put("cart", new CommandCart());
        commandsMap.put("signup", new CommandSingUp());
        commandsMap.put("ajax", new CommandAJAX());
        commandsMap.put("addorder", new CommandAddOrder());
        commandsMap.put("orders", new CommandOrders());
        commandsMap.put("locale",new CommandLocale());
        commandsMap.put("users",new CommandUsers());
        commandsMap.put("admincatalog",new CommandAdminCatalog());
        commandsMap.put("getalluserorders",new CommandAdminUserOrders());*/
    }

    public static Command getCommand(String commandName) {
        log.error("Get command from Controller: " + commandName);
        if (commandsMap == null || !commandsMap.containsKey(commandName)) {
            log.error("Command: " + commandName + " not found");
            return commandsMap.get("noCommand");
        }
        log.error(commandName);
        return commandsMap.get(commandName);
    }

}
