package BusinessLayer;

import java.util.Set;

public interface IDeliveryServiceProcessing {
    // ADMINISTRATOR
    /**
     * Importa setul initial de produse
     * @pre products.csv exista
     * @post setul de produse importat din products.csv
     */
    void importProducts();

    /**
     * Adauga un nou produs in meniu
     * @param menuItem produsul care trebuie adaugat
     * @pre menuItem != null
     * @post setul de produse contine menuItem
     */
    void addProduct(MenuItem menuItem);

    /**
     * Sterge un produs din meniu
     * @param titlu titlul produsului care trebuie sters
     * @pre titlu != null
     * @post setul de produse nu mai contine produsul cu titlul dat
     */
    void deleteProduct(String titlu);

    /**
     * Modifica detaliile unui produs din meniu
     * @param titluCurent titlul produsului care trebuie modificat
     * @param titluNou noul titlu, in caz ca se doreste modificarea titlului
     * @param rating noul rating, in caz ca se doreste modificarea rating-ului
     * @param calories noile calorii, in caz ca se doreste modificarea caloriilor
     * @param protein noile proteine, in caz ca se doreste modificarea proteinelor
     * @param fat noile grasimi, in caz ca se doreste modificarea grasimilor
     * @param sodium noul sodiu, in caz ca se doreste modificarea sodiului
     * @param price noul pret, in caz ca se doreste modificarea pretului
     * @pre titluCurent != null
     * @post produsul modificat
     */
    void modifyProduct(String titluCurent, String titluNou, double rating, int calories, int protein,int fat, int sodium, int price);

    /**
     * Comenzile plasate intr-un anumit interval orar
     * @param startHour ora minima din intervalul orar
     * @param endHour ora maxima din intervalul orar
     * @pre startHour != null, endHour != null
     * @post comenzile plasate in intervalul orar dat
     */
    void generateProducts1(String startHour,String endHour);

    /**
     * Produsele comandate de mai multe ori decat numarul dat
     * @param specifiedNumberOfTimes numarul minim de ori
     * @pre specifiedNumberOfTimes > 0
     * @post produsele comandate de mai multe ori decat specifiedNumberOfTimes
     */
    void generateProducts2(int specifiedNumberOfTimes);

    /**
     * Clientii care au comandat de mai multe ori decat numarul specificat si comanda carora a fost mai mare decat suma specificata
     * @param specifiedNumberOfTimes numarul de ori
     * @param specifiedAmount suma minima
     * @pre specifiedNumberOfTimes > 0, specifiedAmount > 0
     * @post clientii care respecta criteriile
     */
    void generateProducts3(int specifiedNumberOfTimes, int specifiedAmount);

    /**
     * Produsele care au fost comandate intr-o anumita zi impreuna cu frecventa lor
     * @param specifiedDay ziua
     * @pre specifiedDay != 0
     * @post produsele care respecta criteriile
     */
    void generateProducts4(int specifiedDay);

    // CLIENT

    /**
     * Cautarea produselor din meniu dupa un anumit criteriu
     * @param titlu titlul dupa care se cauta
     * @param rating rating-ul dupa care se cauta
     * @param calories caloriile dupa care se cauta
     * @param protein proteinele dupa care se cauta
     * @param fat grasimile dupa care se cauta
     * @param sodium sodiul dupa care se cauta
     * @param price pretul dupa care se cauta
     * @return setul de produse gasite
     * @pre unul din parametrii valid
     * @post setul de produse cautate
     */
    Set<MenuItem> search(String titlu, double rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * Plasarea unei comenzi
     * @param products produsele dorite de client
     * @param clientID ID-ul clientului
     * @pre products != null, clientID > 0
     * @post comanda plasata
     */
    void createOrder(Set<MenuItem> products,int clientID);
}
