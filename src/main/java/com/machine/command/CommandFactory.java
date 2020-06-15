package com.machine.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory(){
        Map<String, Command> tempMap = new HashMap<>();
        tempMap.put("/login", new LoginCommand());
        tempMap.put("/index", new IndexCommand());
        tempMap.put("/registration", new RegistrationCommand());
        tempMap.put("/logOut", new LogOutCommand());
        tempMap.put("/openCashBox", new OpenCashBoxCommand());
        tempMap.put("/closeCashBox", new CloseCashBoxCommand());
        tempMap.put("/openBill", new OpenBillCommand());
        tempMap.put("/addProductByCode", new AddByProductCodeCommand());
        tempMap.put("/addProductByName", new AddByProductNameCommand());
        tempMap.put("/addProductN", new AddProductNCommand());
        tempMap.put("/addProductC", new AddProductCCommand());
        tempMap.put("/closeBill", new CloseBillCommand());
        tempMap.put("/backToUser", new BackToUserCommand());
        tempMap.put("/cancelBill", new CancelBillCommand());
        tempMap.put("/makeX", new MakeXCommand());
        tempMap.put("/makeZ", new MakeZCommand());
        tempMap.put("/products", new ProductsCommand());
        tempMap.put("/addNewProduct", new AddNewProductCommand());
        tempMap.put("/confirmAddingProduct", new ConfirmAddingProductCommand());
        tempMap.put("/updateProductInfo", new UpdateProductInfoCommand());
        tempMap.put("/confirmUpdating", new ConfirmUpdatingCommand());


        commands = Collections.unmodifiableMap(tempMap);
    }

    public Command getCommand(HttpServletRequest request){
        String path = request.getServletPath();
        return commands.get(path);
    }

    public Command getCommand(String path){
        path = "/" + path;
        return commands.get(path);
    }

    public static CommandFactory getInstance(){
        return instance;
    }
}
