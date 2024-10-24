import java.util.ArrayList;
import java.util.List;

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface Observer {
    void update(String headline);
}

class NewsAgency implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String headline;

    public void setHeadline(String headline) {
        this.headline = headline;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(headline);
        }
    }
}

class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String headline) {
        System.out.println(name + " received news: " + headline);
    }
}

public class ObserverP {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();

        NewsChannel channel1 = new NewsChannel("Channel 1");
        NewsChannel channel2 = new NewsChannel("Channel 2");
        NewsChannel channel3 = new NewsChannel("Channel 3");

        newsAgency.addObserver(channel1);
        newsAgency.addObserver(channel2);
        newsAgency.addObserver(channel3);

        newsAgency.setHeadline("Breaking News: Major Event!");
        newsAgency.setHeadline("Update: More Details on Major Event");
    }
}
