package PresentationLayer;

import BusinessLayer.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    private final RadioButtonGUI view;
    private final LogIn logIn;
    private final ClientGUI clientGUI;
    private JTableGUI jTableGUI;
    private final SearchGUI searchGUI;
    private final OrderGUI orderGUI;
    private final DeliveryService deliveryService;
    private final Set<MenuItem> csvProducts;

    public Controller(RadioButtonGUI gui) {
        this.view = gui;
        AdminEmployeeLogInGUI adminLog = new AdminEmployeeLogInGUI();
        AdminEmployeeLogInGUI employeeLog = new AdminEmployeeLogInGUI();
        ClientRegisterLogInGUI clientLog = new ClientRegisterLogInGUI();
        AdministratorGUI administratorGUI = new AdministratorGUI();
        clientGUI = new ClientGUI();
        EmployeeGUI employeeGUI = new EmployeeGUI();
        logIn = new LogIn();
        AddProductGUI addProductGUI = new AddProductGUI();
        DeleteProductGUI deleteProductGUI = new DeleteProductGUI();
        ModifyProductGUI modifyProductGUI = new ModifyProductGUI();
        Report1GUI report1GUI = new Report1GUI();
        Report2GUI report2GUI = new Report2GUI();
        Report3GUI report3GUI = new Report3GUI();
        Report4GUI report4GUI = new Report4GUI();
        searchGUI = new SearchGUI();
        orderGUI = new OrderGUI();
        deliveryService = new DeliveryService();
        deliveryService.importProducts();
        deliveryService.addObserver(employeeGUI);
        csvProducts = deliveryService.getS().deserializeProducts();
        view.addAdminButtonListener(e -> {
            clientLog.setVisible(false);
            employeeLog.setVisible(false);
            adminLog.setVisible(true);
            view.clientRadioButton.setSelected(false);
            view.employeeRadioButton.setSelected(false);
        });
        adminLog.addLogInButtonListener(e -> {
            String res = adminLog.adminLogIn(adminLog.getUsername(), adminLog.getPassword());
            if (res.equals("Wrong username") || res.equals("Wrong password"))
                JOptionPane.showMessageDialog(adminLog, res, res, JOptionPane.ERROR_MESSAGE);
            else {
                adminLog.setVisible(false);
                administratorGUI.setVisible(true);
            }
        });
        administratorGUI.addImportButtonListener(e -> jTableGUI = new JTableGUI(deliveryService.getProductsForTableFromSet(csvProducts)));
        administratorGUI.addAddButtonListener(e -> addProductGUI.setVisible(true));
        addProductGUI.addAddButtonListener(e -> {
            if (jTableGUI.getTable().getSelectedRowCount() == 0) {
                MenuItem base = new BaseProduct(addProductGUI.getTitle(), Double.parseDouble(addProductGUI.getRating()),
                        Integer.parseInt(addProductGUI.getCalories()), Integer.parseInt(addProductGUI.getProtein()),
                        Integer.parseInt(addProductGUI.getFat()), Integer.parseInt(addProductGUI.getSodium()),
                        Integer.parseInt(addProductGUI.getPrice()));
                deliveryService.addProduct(base);
            } else {
                int[] rows = jTableGUI.getTable().getSelectedRows();
                ArrayList<BaseProduct> products = new ArrayList<>();
                for (int row : rows) {
                    BaseProduct b = new BaseProduct((String) jTableGUI.getTable().getValueAt(row, 0),
                            Double.parseDouble((String) jTableGUI.getTable().getValueAt(row, 1)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 2)),
                            Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 3)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 4)),
                            Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 5)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 6)));
                    products.add(b);
                }
                CompositeProduct compositeProduct = new CompositeProduct(addProductGUI.getTitle(), products);
                deliveryService.addProduct(compositeProduct);
            }
        });
        administratorGUI.addDeleteButtonListener(e -> deleteProductGUI.setVisible(true));
        deleteProductGUI.addTitleButtonListener(new ActionListener() {
            String title;
            @Override
            public void actionPerformed(ActionEvent e) {
                title = (String) jTableGUI.getTable().getValueAt(jTableGUI.getTable().getSelectedRow(), 0);
                deleteProductGUI.setTitleString(title);
            }
        });
        deleteProductGUI.addDeleteButtonListener(e -> deliveryService.deleteProduct(deleteProductGUI.getTitleString()));
        administratorGUI.addModifyButtonListener(e -> modifyProductGUI.setVisible(true));
        modifyProductGUI.addTitleButtonListener(new ActionListener() {
            String title;
            @Override
            public void actionPerformed(ActionEvent e) {
                title = (String) jTableGUI.getTable().getValueAt(jTableGUI.getTable().getSelectedRow(), 0);
                modifyProductGUI.setTitleString(title);
            }
        });
        modifyProductGUI.addModifyButtonListener(e -> {
            String titluCurent, titluNou = "";
            double rating = -1;
            int calorie = -1, protein = -1, fat = -1, sodium = -1, price = -1;
            titluCurent = modifyProductGUI.getTitleString();
            System.out.println("Produsul inainte de modificare:");
            for (MenuItem m : deliveryService.getS().deserializeProducts())
                if (m.getTitle().equals(titluCurent))
                    System.out.println(m);
            if (!modifyProductGUI.getTitle().isBlank())
                titluNou = modifyProductGUI.getTitle();
            if (!modifyProductGUI.getRating().isBlank())
                rating = Double.parseDouble(modifyProductGUI.getRating());
            if (!modifyProductGUI.getCalories().isBlank())
                calorie = Integer.parseInt(modifyProductGUI.getCalories());
            if (!modifyProductGUI.getProtein().isBlank())
                protein = Integer.parseInt(modifyProductGUI.getProtein());
            if (!modifyProductGUI.getFat().isBlank())
                fat = Integer.parseInt(modifyProductGUI.getFat());
            if (!modifyProductGUI.getSodium().isBlank())
                sodium = Integer.parseInt(modifyProductGUI.getSodium());
            if (!modifyProductGUI.getPrice().isBlank())
                price = Integer.parseInt(modifyProductGUI.getPrice());
            deliveryService.modifyProduct(titluCurent, titluNou, rating, calorie, protein, fat, sodium, price);
            System.out.println("Produsul dupa modificare:");
            for (MenuItem m : deliveryService.getS().deserializeProducts())
                if (titluNou.isBlank()) {
                    if (m.getTitle().equals(titluCurent))
                        System.out.println(m);
                } else {
                    if (m.getTitle().equals(titluNou))
                        System.out.println(m);
                }
        });
        administratorGUI.addRaport1ButtonListener(e -> report1GUI.setVisible(true));
        report1GUI.addReportButtonListener(e -> deliveryService.generateProducts1(report1GUI.getStartHour(), report1GUI.getEndHour()));
        administratorGUI.addRaport2ButtonListener(e -> report2GUI.setVisible(true));
        report2GUI.addReportButtonListener(e -> deliveryService.generateProducts2(Integer.parseInt(report2GUI.getNumberOfTimes())));
        administratorGUI.addRaport3ButtonListener(e -> report3GUI.setVisible(true));
        report3GUI.addReportButtonListener(e -> deliveryService.generateProducts3(Integer.parseInt(report3GUI.getNumberOfTimes()), Integer.parseInt(report3GUI.getAmount())));
        administratorGUI.addRaport4ButtonListener(e -> report4GUI.setVisible(true));
        report4GUI.addReportButtonListener(e -> deliveryService.generateProducts4(Integer.parseInt(report4GUI.getDay())));
        administratorGUI.addViewButtonListener(e -> jTableGUI = new JTableGUI(deliveryService.getProductsForTable()));
        view.addClientButtonListener(e -> {
            adminLog.setVisible(false);
            employeeLog.setVisible(false);
            clientLog.setVisible(true);
            view.employeeRadioButton.setSelected(false);
            view.adminRadioButton.setSelected(false);
        });
        clientLog.addRegisterButtonListener(e -> {
            if (!logIn.register(clientLog.getRegisterUsername(), clientLog.getRegisterPassword())) {
                JOptionPane.showMessageDialog(clientLog, "Username already in use", "Username already in use", JOptionPane.ERROR_MESSAGE);
            } else {
                clientLog.setVisible(false);
                clientGUI.setVisible(true);
                clientGUIMethods();
            }
        });
        clientLog.addLogInButtonListener(e -> {
            if (!logIn.logIn(clientLog.getLogInUsername(), clientLog.getLogInPassword())) {
                JOptionPane.showMessageDialog(clientLog, "Wrong username/password", "Wrong username/password", JOptionPane.ERROR_MESSAGE);
            } else {
                clientLog.setVisible(false);
                clientGUI.setVisible(true);
                clientGUIMethods();
            }
        });
        view.addEmployeeButtonListener(e -> {
            adminLog.setVisible(false);
            clientLog.setVisible(false);
            employeeLog.setVisible(true);
            view.adminRadioButton.setSelected(false);
            view.clientRadioButton.setSelected(false);
        });
        employeeLog.addLogInButtonListener(e -> {
            String res = employeeLog.employeeLogIn(employeeLog.getUsername(), employeeLog.getPassword());
            if (res.equals("Wrong username") || res.equals("Wrong password"))
                JOptionPane.showMessageDialog(employeeLog, res, res, JOptionPane.ERROR_MESSAGE);
            else {
                employeeLog.setVisible(false);
                employeeGUI.setVisible(true);
            }
        });
    }
    public void clientGUIMethods() {
        clientGUI.addSearchButtonListener(e -> searchGUI.setVisible(true));
        searchGUI.addSearchButtonListener(e -> {
            Set<MenuItem> searched=new HashSet<>();
            String titlu = "";
            double rating = -1;
            int calorie = -1, protein = -1, fat = -1, sodium = -1, price = -1;
            if (!searchGUI.getTitle().isBlank())
                titlu = searchGUI.getTitle();
            if (!searchGUI.getRating().isBlank())
                rating = Double.parseDouble(searchGUI.getRating());
            if (!searchGUI.getCalories().isBlank())
                calorie = Integer.parseInt(searchGUI.getCalories());
            if (!searchGUI.getProtein().isBlank())
                protein = Integer.parseInt(searchGUI.getProtein());
            if (!searchGUI.getFat().isBlank())
                fat = Integer.parseInt(searchGUI.getFat());
            if (!searchGUI.getSodium().isBlank())
                sodium = Integer.parseInt(searchGUI.getSodium());
            if (!searchGUI.getPrice().isBlank())
                price = Integer.parseInt(searchGUI.getPrice());
            searched=deliveryService.search(titlu, rating, calorie, protein, fat, sodium, price);
            jTableGUI = new JTableGUI(deliveryService.getProductsForTableFromSet(searched));
        });
        clientGUI.addViewButtonListener(e -> jTableGUI = new JTableGUI(deliveryService.getProductsForTable()));
        clientGUI.addOrderButtonListener(e -> orderGUI.setVisible(true));
        orderGUI.addCreateOrderButtonListener(e -> {
            int[] rows = jTableGUI.getTable().getSelectedRows();
            Set<MenuItem> products = new HashSet<>();
            for (int row : rows) {
                if (deliveryService.isCompositeProduct((String) jTableGUI.getTable().getValueAt(row, 0))) {
                    for (MenuItem m : deliveryService.getS().deserializeProducts()) {
                        if (m.getTitle().equals(jTableGUI.getTable().getValueAt(row, 0))) {
                            MenuItem c = new CompositeProduct((String) jTableGUI.getTable().getValueAt(row, 0), ((CompositeProduct) m).getCompositeProduct());
                            products.add(c);
                        }
                    }
                } else {
                    MenuItem b = new BaseProduct((String) jTableGUI.getTable().getValueAt(row, 0),
                            Double.parseDouble((String) jTableGUI.getTable().getValueAt(row, 1)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 2)),
                            Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 3)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 4)),
                            Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 5)), Integer.parseInt((String) jTableGUI.getTable().getValueAt(row, 6)));
                    products.add(b);
                }
            }
            deliveryService.createOrder(products, Integer.parseInt(orderGUI.getClientID()));
        });
    }
}
