package ng.mymoney.util;

    public enum AppConstants {
        KAFKA_ENDPOINT ( "kafka_broker"),
        TOPIC_NAME ("topic_name"),

        KAFKA_GROUPID("group_id");

        private String value;

        AppConstants(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


