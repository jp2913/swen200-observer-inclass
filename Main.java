interface Observer {
    public void update();
}
interface Subject {
    public void addObserver(Observer o);
    public void notifyObservers();
    public void removeObserver(Observer o);
}
class Signal implements Subject {
    private List<Observer> obs = new ArrayList<>();
    public void addObserver(Observer o) {
        obs.add(o);
    }
    public void removeObserver(Observer o) {
        obs.remove(o);
    }
    public void notifyObservers() {
        for ( Observer ob: obs) {
            ob.update();
        }
    }
}
class Counter implements Observer {
    private String name; private int cnt = 0;
    public Counter(String n) { name = n; }
    public void update() {
        cnt++;
        System.out.println(name + " counts " + cnt);
    }
}
class Probe implements Observer {
    public void update() { System.out.println("Beep"); }
}
