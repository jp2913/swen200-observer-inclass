import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WeatherDisplay {
  public static void main(String[] args) {
    WeatherStation shippensburgStation = new WeatherStation("Shippensburg");
    shippensburgStation.setLocation(0, 0);
    WeatherStation harrisburgStation = new WeatherStation("Harrisburg");
    harrisburgStation.setLocation(0, 100);
    Display display1 = new Display("Kelvin");
    display1.setLocation(0, 200);
    Display display2 = new Display("Fahrenheit");
    display2.setLocation(200, 200);
    Display display3 = new Display("Celsius");
    display3.setLocation(400, 200);

    // C: "Attach" the three kinds of displays to each station
  }
}

interface Subject {
  // A: Complete the Subject interface. Hint: It's straightforward.
  // Just look at the method(s) overridden by the implementer
  public void addObserver(Observer o);
  public void removeObserver(Observer o);
  public void notifyObservers();
}

interface Observer {
  public void update(Subject s, Temperature a);
}

class Display extends JFrame implements Observer {

  TextArea text = new TextArea();
  String unit;

  Display(String u) {
    unit = u;
    setSize(200, 150);
    setTitle(String.format("Displays %s", unit));
    add(text);
    setVisible(true);
  }

  @Override
  public void update(Subject s, Temperature a) {
    Consumer<Double> f = d -> text
        .append(String.format("%s: %.2f\n", s.toString(), d));
    switch (unit) {
      case "Celsius":
        f.accept(a.getCelsius());
        break;
      case "Fahrenheit":
        f.accept(a.getFahrenheit());
        break;
      case "Kelvin":
        f.accept(a.getKelvin());
        break;
      default:
        f.accept(a.getKelvin());
    }
  }

}

class WeatherStation extends JFrame implements Subject {
  private String cityName;
  
  // D: Declare and initialize "obs" which is the "collection" of Observers
  private List<Observer> obs = new ArrayList<>();
  
  private Temperature t = new Temperature();

  WeatherStation(String n) {
    setSize(800, 250);
    cityName = n;

    setTitle(String.format("Weather Station @ %s", cityName));
    setLayout(new GridLayout(1, 3));

    add(new JLabel("Enter in Kelvin: "));
    JTextField text = new JTextField("kelvin", 20);
    add(text);
    JButton b = new JButton("Submit");
    add(b);

    b.addActionListener(e -> {
      t.setByKelvin(Double.parseDouble(text.getText()));
      notifyObservers();
    });
    setSize(600, 100);
    setVisible(true);
  }

  public String toString() {
    return cityName;
  }

  @Override
  public void addObserver(Observer o) {
    obs.addObserver(o);
  }

  @Override
  public void notifyObservers() {
    obs.removeObserver(o);
  }
}
