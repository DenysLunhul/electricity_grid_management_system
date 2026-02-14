package consumers;

import tariff.ConsumerType;

public class ResidentialConsumer extends Consumer {

    public ResidentialConsumer(String id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public ConsumerType getType() {
        return ConsumerType.RESIDENTIAL;
    }
}