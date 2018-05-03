package com.android.constructionprojectassistant;

import java.util.Date;

/**
 * Created by Dymyll on 4/1/2018.
 *
 */

public class CustomerDbSchema {
    public  static final class CustomerTable{
        public static final String NAME = "customers";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String USERNAME = "username";
            public static final String EMAILADDRESS = "emailaddress";
            public static final String PASSWORD = "password";
          //  public static final String DATEOFBIRTH = "date_of_birth";

        }
    }

}
