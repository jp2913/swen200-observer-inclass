interface Observer {
    public void update();
}
interface Subject {
    public void addObserver(Observer o);
    public void notifyObservers();
}
class Signal implements Subject {
    private List<Observer> obs = new ArrayList<>();
    public void addObserver(Observer o) {
        obs.add(o);
    }
    public void notifyObservers() {
        obs.forEach(ob -> ob.update());
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
