import PresentationLayer.Controller;
import PresentationLayer.RadioButtonGUI;

public class MainClass {
    public static void main(String[] args) {
        RadioButtonGUI radio = new RadioButtonGUI();
        Controller controller = new Controller(radio);
        radio.setVisible(true);
    }
}
