package br.dev.anderanjos.model;

public final class Baggage {
    private Long baggageId;

    public Long getBaggageId() {
        return baggageId;
    }

    private String type;

    private Integer amount;

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }


    public static final class BaggageBuilder {
        private Long baggageId;
        private String type;
        private Integer amount;

        private BaggageBuilder() {
        }

        public static BaggageBuilder aBaggage() {
            return new BaggageBuilder();
        }

        public BaggageBuilder baggageId(Long baggageId) {
            this.baggageId = baggageId;
            return this;
        }

        public BaggageBuilder type(String type) {
            this.type = type;
            return this;
        }

        public BaggageBuilder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Baggage build() {
            Baggage baggage = new Baggage();
            baggage.type = this.type;
            baggage.baggageId = this.baggageId;
            baggage.amount = this.amount;
            return baggage;
        }
    }
}
