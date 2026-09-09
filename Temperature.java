public class Temperature {
  private double k;

  public Temperature() {
    k = -1;
  }

  public double getKelvin() {
    return k;
  }

  public double getCelsius() {
    return k - 273.15;
  }

  public double getFahrenheit() {
    return (9.0 / 5.0) * k - 459.67;
  }

  public void setByKelvin(double k) {
    check(k);
    this.k = k;
  }

  public void setByCelsius(double c) {
    double temp = c + 273.15;
    check(temp);
    k = temp;
  }

  public void setByFahrenheit(double f) {
    double temp = (5.0 / 9.0) * (f + 459.67);
    check(temp);
    k = temp;
  }

  private void check(double k) {
    if (k < 0)
      throw new RuntimeException();
  }
}
