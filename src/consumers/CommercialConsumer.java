package consumers;

import tariff.ConsumerType;

public class CommercialConsumer extends Consumer {

    public CommercialConsumer(String id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public ConsumerType getType() {
        return ConsumerType.COMMERCIAL;
    }
}