package consumers;

import tariff.ConsumerType;

public class IndustrialConsumer extends Consumer {

    public IndustrialConsumer(String id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public ConsumerType getType() {
        return ConsumerType.INDUSTRIAL;
    }
}