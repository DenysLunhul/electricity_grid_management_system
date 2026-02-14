package consumers;

import java.util.ArrayList;
import meters.Meter;
import tariff.ConsumerType;

public abstract class Consumer {
    private final String id;
    private final String name;
    private final String address;
    private ArrayList<Meter> meters;

    public Consumer(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.meters = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public ArrayList<Meter> getMeters() {
        return meters;
    }

    public void addMeter(Meter meter){
        this.getMeters().add(meter);
    }

    public void removeMeter(String id){
        for (Meter meter : this.getMeters()){
            if (meter.getId().equals(id)){
                this.getMeters().remove(meter);
                return;
            }
        }
    }

    public Meter getMeterById(String id){
        for (Meter meter : this.getMeters()){
            if (meter.getId().equals(id)){
                return meter;
            }
        }
        return null;
    }

    public abstract ConsumerType getType();
}
