package semesters.fourth.lab4;

public class ProductContract {

    public static final int DATABASE_VERSION = 1;

    public static final String USER_NAME = "u0523300_bogdanm";

    public static final String USER_PASSWORD = "bd_java_labs_spbstu_123321";

    public static final String CONNECTION_URL = "jdbc:mysql://atechnologies.ru:3306/u0523300_bogdanmendli24680?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String DATABASE_NAME = "u0523300_bogdanmendli24680";

    public static final String TABLE_PRODUCT_NAME = "products";

    public static abstract class ProductColumns {

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_PRODUCT_ID = "id_product";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_COST = "cost";

        public static final String[] COLUMNS =
                {COLUMN_ID, COLUMN_PRODUCT_ID, COLUMN_TITLE, COLUMN_COST,};

        public static final int COLUMN_INDEX_ID = 0;

        public static final int COLUMN_INDEX_PRODUCT_ID = 1;

        public static final int COLUMN_INDEX_TITLE = 2;

        public static final int COLUMN_INDEX_COST = 3;
    }
}
