package com.epam.onlinestore.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger log = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put("signup", new SignUpCommand());
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("logout", new LogoutCommand());
        commandsMap.put("catalog", new CatalogCommand());
        commandsMap.put("addToBasket", new AddCartCommand());
        commandsMap.put("editProduct", new EditProductCommand());
        commandsMap.put("confirmEditProduct", new ConfirmEditProduct());
        commandsMap.put("addProduct", new AddProduct());
        commandsMap.put("confirmAddProduct", new ConfirmAddProduct());
        commandsMap.put("deleteProducts", new DeleteProducts());
        commandsMap.put("pageCart", new PageCartCommand());
        commandsMap.put("userOrders", new UserOrdersCommand());
        commandsMap.put("makeOrder", new MakeReceiptCommand());
        commandsMap.put("adminUserOrders", new AdminUserOrdersCommand());
        commandsMap.put("adminUsers", new CommandUsers());
        commandsMap.put("incDec", new QuantityIncDec());
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
