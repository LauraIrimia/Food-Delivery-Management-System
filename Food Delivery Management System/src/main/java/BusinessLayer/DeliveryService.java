package BusinessLayer;

import DataLayer.FileWriter;
import DataLayer.Serializator;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private Serializator s = new Serializator();
    private FileWriter f = new FileWriter();

    public boolean isWellFormed() {
        if (s.deserializeProducts().isEmpty())
            return false;
        return true;
    }

    /**
     * Importa setul initial de produse
     *
     * @pre products.csv exista
     * @post setul de produse importat din products.csv
     */
    @Override
    public void importProducts() {
        File tempFile = new File("src/main/resources/products.csv");
        assert tempFile.exists();
        Set<MenuItem> baseProducts = s.deserializeProducts();
        Set<MenuItem> distinctBaseProducts = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            String lineInCSV;
            reader.readLine();
            while ((lineInCSV = reader.readLine()) != null) {
                String[] data = lineInCSV.split(",");
                BaseProduct b = new BaseProduct(data[0].stripTrailing(), Double.parseDouble(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                baseProducts.add(b);
            }
            distinctBaseProducts = (Set<MenuItem>) baseProducts.stream()
                    .filter(distinctByKey(baseProduct -> baseProduct.getTitle()))
                    .collect(Collectors.toSet());
            s.serializeProducts(distinctBaseProducts);
            assert !distinctBaseProducts.isEmpty();
            assert isWellFormed();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adauga un nou produs in meniu
     *
     * @param menuItem produsul care trebuie adaugat
     * @pre menuItem != null
     * @post setul de produse contine menuItem
     */
    @Override
    public void addProduct(MenuItem menuItem) {
        assert menuItem != null;
        Set<MenuItem> m = s.deserializeProducts();
        m.add(menuItem);
        s.serializeProducts(m);
        assert m.contains(menuItem);
        assert isWellFormed();
    }

    /**
     * Sterge un produs din meniu
     *
     * @param titlu titlul produsului care trebuie sters
     * @pre titlu != null
     * @post setul de produse nu mai contine produsul cu titlul dat
     */
    @Override
    public void deleteProduct(String titlu) {
        assert !titlu.isBlank();
        Set<MenuItem> m = s.deserializeProducts();
        MenuItem t = null;
        for (MenuItem mi : m) {
            if (mi.getTitle().equals(titlu)) {
                m.remove(mi);
                t = mi;
                break;
            }
        }
        s.serializeProducts(m);
        assert !m.contains(t);
        assert isWellFormed();
    }

    /**
     * Modifica detaliile unui produs din meniu
     *
     * @param titluCurent titlul produsului care trebuie modificat
     * @param titluNou    noul titlu, in caz ca se doreste modificarea titlului
     * @param rating      noul rating, in caz ca se doreste modificarea rating-ului
     * @param calories    noile calorii, in caz ca se doreste modificarea caloriilor
     * @param protein     noile proteine, in caz ca se doreste modificarea proteinelor
     * @param fat         noile grasimi, in caz ca se doreste modificarea grasimilor
     * @param sodium      noul sodiu, in caz ca se doreste modificarea sodiului
     * @param price       noul pret, in caz ca se doreste modificarea pretului
     * @pre titluCurent != null
     * @post produsul modificat
     */
    @Override
    public void modifyProduct(String titluCurent, String titluNou, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert !titluCurent.isBlank();
        Set<MenuItem> m = s.deserializeProducts();
        MenuItem t = null;
        int ok = 1;
        for (MenuItem mi : m) {
            if (mi.getTitle().equals(titluCurent)) {
                t = mi;
                if (!titluNou.equals("")) {
                    for (MenuItem mi1 : m) {
                        if (mi1.getTitle().equals(titluNou)) ok = 0;
                    }
                    if (ok == 1) mi.setTitle(titluNou);
                }
                if (rating != -1) mi.setRating(rating);
                if (calories != -1) mi.setCalories(calories);
                if (protein != -1) mi.setProtein(protein);
                if (fat != -1) mi.setFat(fat);
                if (sodium != -1) mi.setSodium(sodium);
                if (price != -1) mi.setPrice(price);
                for (MenuItem mi1 : m) {
                    if (mi1 instanceof CompositeProduct) {
                        for (BaseProduct b : ((CompositeProduct) mi1).getCompositeProduct())
                            if (b.getTitle().equals(titluCurent)) {
                                b.setTitle(mi.getTitle());
                                b.setRating(mi.getRating());
                                b.setCalories(mi.getCalories());
                                b.setProtein(mi.getProtein());
                                b.setFat(mi.getFat());
                                b.setSodium(mi.getSodium());
                                b.setPrice(mi.getPrice());
                            }
                    }
                }
            }
        }
        s.serializeProducts(m);
        assert !m.contains(t);
        assert isWellFormed();
    }

    /**
     * Comenzile plasate intr-un anumit interval orar
     *
     * @param startHour ora minima din intervalul orar
     * @param endHour   ora maxima din intervalul orar
     * @pre startHour != null, endHour != null
     * @post comenzile plasate in intervalul orar dat
     */
    @Override
    public void generateProducts1(String startHour, String endHour) {
        assert !startHour.isBlank() || !endHour.isBlank();
        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
        Set<Order> orders = orderSetHashMap.keySet();
        orders = orders.stream()
                .filter(order -> order.getOrderDate().getHours() > Integer.parseInt(startHour) && order.getOrderDate().getHours() < Integer.parseInt(endHour))
                .collect(Collectors.toCollection(HashSet::new));
        orders.stream().forEach(System.out::println);
        assert !orders.isEmpty();
        assert isWellFormed();
    }

    /**
     * Produsele comandate de mai multe ori decat numarul dat
     *
     * @param specifiedNumberOfTimes numarul minim de ori
     * @pre specifiedNumberOfTimes > 0
     * @post produsele comandate de mai multe ori decat specifiedNumberOfTimes
     */
    @Override
    public void generateProducts2(int specifiedNumberOfTimes) {
        assert specifiedNumberOfTimes > 0;
        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
        Collection<Set<MenuItem>> set = orderSetHashMap.values();
        ArrayList<MenuItem> products = new ArrayList<>();
        set.stream().forEach(s -> {
            s.stream().forEach(s1 -> {
                products.add(s1);
            });
        });
        int[] f = new int[products.size()];
        for (int i = 0; i < products.size(); i++) {
            f[i] = Collections.frequency(products, products.get(i));
        }
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    f[j] = 0;
                }
            }
        }
        for (int i = 0; i < f.length; i++) {
            if (f[i] >= specifiedNumberOfTimes) {
                System.out.println(products.get(i));
            }
        }
        assert !products.isEmpty();
        assert isWellFormed();
    }
//    @Override
//    public void generateProducts2(int specifiedNumberOfTimes) {
//        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
//        Collection<Set<MenuItem>> set = orderSetHashMap.values();
//        ArrayList<String> products = new ArrayList<>();
//        set.stream().forEach(s -> {
//            s.stream().forEach(s1 -> {
//                products.add(s1.getTitle());
//            });
//        });
//        products.stream()
//                .filter(p -> Collections.frequency(products, p) >= specifiedNumberOfTimes)
//                .collect(Collectors.toSet()).forEach(System.out::println);
//    }

    /**
     * Clientii care au comandat de mai multe ori decat numarul specificat si comanda carora a fost mai mare decat suma specificata
     *
     * @param specifiedNumberOfTimes numarul de ori
     * @param specifiedAmount        suma minima
     * @pre specifiedNumberOfTimes > 0, specifiedAmount > 0
     * @post clientii care respecta criteriile
     */
    @Override
    public void generateProducts3(int specifiedNumberOfTimes, int specifiedAmount) {
        assert specifiedNumberOfTimes > 0 || specifiedAmount > 0;
        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
        Set<Order> ord = orderSetHashMap.keySet();
        ArrayList<Order> orders = new ArrayList<>();
        orders.addAll(ord);
        ArrayList<Integer> clientsID = new ArrayList<>();
        orders.stream().forEach(o -> {
            clientsID.add(o.getClientID());
        });
        int[] f = new int[clientsID.size()];
        for (int i = 0; i < clientsID.size(); i++) {
            f[i] = Collections.frequency(clientsID, clientsID.get(i));
        }
        for (int i = 0; i < clientsID.size() - 1; i++) {
            for (int j = i + 1; j < clientsID.size(); j++) {
                if (clientsID.get(i).equals(clientsID.get(j))) {
                    f[j] = 0;
                }
            }
        }
        for (int i = 0; i < f.length; i++) {
            if (f[i] >= specifiedNumberOfTimes) {
                for (Order o : orders) {
                    int price = 0;
                    if (o.getClientID() == orders.get(i).getClientID()) {
                        Set<MenuItem> o1 = orderSetHashMap.get(o);
                        for (MenuItem m : o1) {
                            price += m.computePrice();
                        }
                        if (price >= specifiedAmount) {
                            System.out.println(o.getClientID() + " " + price);
                            break;
                        }
                    }
                }
            }
        }
        assert !clientsID.isEmpty();
        assert isWellFormed();
    }
//    @Override
//    public void generateProducts3(int specifiedNumberOfTimes, int specifiedAmount) {
//        assert specifiedNumberOfTimes > 0 || specifiedAmount > 0;
//        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
//        Set<Order> ord = orderSetHashMap.keySet();
//        ArrayList<Order> orders = new ArrayList<>();
//        orders.addAll(ord);
//        ArrayList<Integer> clientsID = new ArrayList<>();
//        Set<Integer> id;
//        orders.stream().forEach(o -> {
//            clientsID.add(o.getClientID());
//        });
//        id=clientsID.stream()
//                .filter(c -> Collections.frequency(clientsID, c) >= specifiedNumberOfTimes)
//                .collect(Collectors.toSet());
//        id.forEach(System.out::println);
//        id.stream()
//                .forEach(c -> {
//                    for (Order o : orders) {
//                        int price = 0;
//                        if (o.getClientID() == c) {
//                            Set<MenuItem> o1 = orderSetHashMap.get(o);
//                            for (MenuItem m : o1) {
//                                price += m.computePrice();
//                            }
//                            if (price >= specifiedAmount) {
//                                System.out.println(o.getClientID() + " " + price);
//                            }
//                        }
//                    }
//                });
//        assert !clientsID.isEmpty();
//        assert isWellFormed();
//    }

    /**
     * Produsele care au fost comandate intr-o anumita zi impreuna cu frecventa lor
     *
     * @param specifiedDay ziua
     * @pre specifiedDay != 0
     * @post produsele care respecta criteriile
     */
    @Override
    public void generateProducts4(int specifiedDay) {
        assert specifiedDay > 0;
        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
        Set<Order> orders = orderSetHashMap.keySet();
        ArrayList<MenuItem> products = new ArrayList<>();
        orders = orders.stream()
                .filter(order -> order.getOrderDate().getDate() == specifiedDay)
                .collect(Collectors.toSet());
        orders.stream().forEach(o -> {
            products.addAll(orderSetHashMap.get(o));
        });
        int[] f = new int[products.size()];
        for (int i = 0; i < products.size(); i++) {
            f[i] = Collections.frequency(products, products.get(i));
        }
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    f[j] = 0;
                }
            }
        }
        for (int i = 0; i < f.length; i++) {
            if (f[i] != 0) {
                System.out.println(f[i] + " " + products.get(i));
            }
        }
        assert !products.isEmpty();
        assert isWellFormed();
    }
//    @Override
//    public void generateProducts4(int specifiedDay) {
//        assert specifiedDay > 0;
//        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
//        Set<Order> orders = orderSetHashMap.keySet();
//        ArrayList<String> products = new ArrayList<>();
//        orders = orders.stream()
//                .filter(order -> order.getOrderDate().getDate() == specifiedDay)
//                .collect(Collectors.toSet());
//        orders.stream().forEach(o -> {
//            orderSetHashMap.get(o).forEach(o1 -> {
//                products.add(o1.getTitle());
//            });
//        });
//        products.stream()
//                .distinct()
//                .forEach(p -> System.out.println(Collections.frequency(products, p) + " " + p));
//        assert !products.isEmpty();
//        assert isWellFormed();
//    }

    /**
     * Cautarea produselor din meniu dupa un anumit criteriu
     *
     * @param titlu    titlul dupa care se cauta
     * @param rating   rating-ul dupa care se cauta
     * @param calories caloriile dupa care se cauta
     * @param protein  proteinele dupa care se cauta
     * @param fat      grasimile dupa care se cauta
     * @param sodium   sodiul dupa care se cauta
     * @param price    pretul dupa care se cauta
     * @return setul de produse gasite
     * @pre unul din parametrii valid
     * @post setul de produse cautate
     */
    @Override
    public Set<MenuItem> search(String titlu, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert !titlu.isBlank() || rating != -1 || calories != -1 || protein != -1 || fat != -1 || sodium != -1 || price != -1;
        Set<MenuItem> m = s.deserializeProducts();
        if (!titlu.isBlank()) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getTitle().toLowerCase(Locale.ROOT).contains(titlu.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (rating != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getRating() == rating)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (calories != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getCalories() == calories)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (protein != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getProtein() == protein)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (fat != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getFat() == fat)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (sodium != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getSodium() == sodium)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if (price != -1) {
            m = m.stream()
                    .filter(menuItem -> menuItem.getPrice() == price)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        //s.serializeProducts(m);
        assert !m.isEmpty();
        assert isWellFormed();
        return m;
    }

    /**
     * Plasarea unei comenzi
     *
     * @param products produsele dorite de client
     * @param clientID ID-ul clientului
     * @pre products != null, clientID > 0
     * @post comanda plasata
     */
    @Override
    public void createOrder(Set<MenuItem> products, int clientID) {
        assert !products.isEmpty() || clientID > 0;
        HashMap<Order, Set<MenuItem>> orderSetHashMap = s.deserializeOrders();
        if (orderSetHashMap.isEmpty()) {
            orderSetHashMap = new HashMap<>();
        }
        Order order = new Order(clientID);
        orderSetHashMap.put(order, products);
        f.createBill(orderSetHashMap, order);
        s.serializeOrders(orderSetHashMap);
        setChanged();
        notifyObservers(order.toString());
        assert !orderSetHashMap.containsKey(order);
        assert isWellFormed();
    }

    public String[][] getProductsForTableFromSet(Set<MenuItem> csvProducts) {
        String[][] data = new String[csvProducts.size()][7];
        ArrayList<MenuItem> prd = new ArrayList<>();
        prd.addAll(csvProducts);
        for (int i = 0; i < prd.size(); i++) {
            data[i][0] = prd.get(i).getTitle();
            data[i][1] = String.valueOf(prd.get(i).getRating());
            data[i][2] = String.valueOf(prd.get(i).getCalories());
            data[i][3] = String.valueOf(prd.get(i).getProtein());
            data[i][4] = String.valueOf(prd.get(i).getFat());
            data[i][5] = String.valueOf(prd.get(i).getSodium());
            data[i][6] = String.valueOf(prd.get(i).getPrice());
        }
        System.out.println(prd.size());
        return data;
    }

    public String[][] getProductsForTable() {
        String[][] data = new String[s.deserializeProducts().size()][7];
        ArrayList<MenuItem> prd = new ArrayList<>();
        prd.addAll(s.deserializeProducts());
        for (int i = 0; i < prd.size(); i++) {
            data[i][0] = prd.get(i).getTitle();
            data[i][1] = String.valueOf(prd.get(i).getRating());
            data[i][2] = String.valueOf(prd.get(i).getCalories());
            data[i][3] = String.valueOf(prd.get(i).getProtein());
            data[i][4] = String.valueOf(prd.get(i).getFat());
            data[i][5] = String.valueOf(prd.get(i).getSodium());
            data[i][6] = String.valueOf(prd.get(i).getPrice());
        }
        System.out.println(prd.size());
        return data;
    }

    public boolean isCompositeProduct(String title) {
        for (MenuItem m : s.deserializeProducts()) {
            if (m.getTitle().equals(title)) {
                if (m instanceof CompositeProduct)
                    return true;
            }
        }
        return false;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public Serializator getS() {
        return s;
    }

    public void setS(Serializator s) {
        this.s = s;
    }

}
