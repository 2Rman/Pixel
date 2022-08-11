package app.constant;

public class ConstantQuery {

    //-------------------------------------------------ACCOUNT----------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_ACCOUNTS = "SELECT * FROM account;";

    //language=sql GET_BY_ID
    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id_account=?;";

    //language=sql GET_BY_PHONE
    public static final String GET_ACCOUNT_BY_PHONE_NUMBER = "SELECT * FROM account WHERE phone_number=?;";

    //language=sql CREATE
    public static final String CREATE_ACCOUNT = "INSERT INTO account VALUES (?, ?, ?, ?, ?);";

    //language=sql UPDATE
    public static final String UPDATE_ACCOUNT = "";


    //-------------------------------------------------CLIENT-----------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_CLIENTS = "SELECT distinct c.id_client, c.first_name, c.last_name " +
            "FROM client c\n" +
            "JOIN income i on c.id_client = i.id_client\n" +
            "WHERE id_account = ?;";

    //language=sql GET_BY_ID
    public static final String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE id_client = ?;";

    //language=sql CREATE_CLIENT
    public static final String CREATE_CLIENT = "INSERT INTO client VALUES (?,?,?);";

    //language=sql GET_CLIENTS_LIKE
    public static final String GET_CLIENTS_LIKE = "select distinct first_name, last_name from client\n" +
            "join income i on client.id_client = i.id_client\n" +
            "join account a on i.id_account = a.id_account where a.id_account = ?\n" +
            "and (first_name like ? or last_name like ?)";

    //language=sql GET_CLIENT_BY_NAME
    public static final String GET_ID_CLIENT_BY_NAME = "select id_client from client\n" +
            "where first_name = ?\n" +
            "and last_name = ?;";

    //-------------------------------------------------INCOME-----------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_INCOME = "SELECT date, service_name, amount, first_name, last_name, commentary " +
            "FROM income\n" +
            "LEFT JOIN client on income.id_client = client.id_client\n" +
            "LEFT JOIN service_type st on income.id_service_type = st.id_service_type\n" +
            "WHERE (id_account = ?)\n" +
            "ORDER BY date;";

    //language=sql GET_DATA_BY_ID
    public static final String GET_DATA_INCOME_BY_ID = "SELECT date, service_name, amount, first_name, last_name, commentary " +
            "FROM income\n" +
            "LEFT JOIN client on income.id_client = client.id_client\n" +
            "LEFT JOIN service_type st on income.id_service_type = st.id_service_type\n" +
            "WHERE (id_income = ?);";

    //language=sql GET_BY_ID
    public static final String GET_INCOME_BY_ID = "SELECT * FROM income WHERE id_income = ?;";

    //language=sql
    public static final String GET_DAILY_INCOME = "SELECT date, service_name, amount, first_name, last_name, commentary " +
            "FROM income\n" +
            "LEFT JOIN client on income.id_client = client.id_client\n" +
            "LEFT JOIN service_type st on income.id_service_type = st.id_service_type\n" +
            "WHERE (id_account = ? AND date = ? )";

    //language=sql
    public static final String GET_PERIOD_INCOME = "SELECT date, service_name, amount, first_name, last_name, commentary " +
            "FROM income\n" +
            "LEFT JOIN client on income.id_client = client.id_client\n" +
            "LEFT JOIN service_type st on income.id_service_type = st.id_service_type\n" +
            "WHERE (id_account = ? AND (date BETWEEN ? AND ?))\n" +
            "ORDER BY date;";

    //language=sql
    public static final String GET_ID_SERVICE_TYPE_BY_NAME = "select id_service_type from service_type\n" +
            "where service_name = ?";

    //language=sql
    public static final String INSERT_NEW_NOTE = "insert into income values (?,?,?,?,?,?,?)";

    //-----------------------------------------------SERVICE_TYPE---------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_INCOME_TYPES = "SELECT distinct st.id_service_type, st.service_name " +
            "FROM service_type st\n" +
            "JOIN income i on st.id_service_type = i.id_service_type\n" +
            "WHERE id_account = ?;";

    //language=sql GET_BY_ID
    public static final String GET_SERVICE_TYPE_BY_ID = "SELECT * FROM service_type WHERE id_service_type = ?;";


    //--------------------------------------------------EXPENSE----------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_EXPENSE = "SELECT date, expense_name, amount, commentary " +
            "FROM expense\n" +
            "LEFT JOIN expense_type ex on expense.id_expense_type = ex.id_expense_type\n" +
            "WHERE (id_account = ?)\n" +
            "ORDER BY date;";

    //language=sql GET_DATA_BY_ID
    public static final String GET_DATA_EXPENSE_BY_ID = "SELECT date, expense_name, amount, commentary " +
            "FROM expense\n" +
            "LEFT JOIN expense_type ex on expense.id_expense_type = ex.id_expense_type\n" +
            "WHERE id_expense = ?;";

    //language=sql GET_BY_ID
    public static final String GET_EXPENSE_BY_ID = "SELECT * FROM expense WHERE id_expense = ?;";

    //language=sql
    public static final String GET_DAILY_EXPENSE = "SELECT date, expense_name, amount, commentary " +
            "FROM expense\n" +
            "LEFT JOIN expense_type et on expense.id_expense_type = et.id_expense_type\n" +
            "WHERE (id_account = ? AND date = ? )";

    //language=sql
    public static final String GET_PERIOD_EXPENSE = "SELECT date, expense_name, amount, commentary " +
            "FROM expense\n" +
            "LEFT JOIN expense_type ex on expense.id_expense_type = ex.id_expense_type\n" +
            "WHERE (id_account = ? AND (date BETWEEN ? AND ?))\n" +
            "ORDER BY date;";

    //------------------------------------------------EXPENSE_TYPE--------------------------------------------------
    //language=sql GET_ALL
    public static final String GET_ALL_EXPENSE_TYPES = "SELECT distinct et.id_expense_type, et.expense_name " +
            "FROM expense_type et\n" +
            "JOIN expense e on et.id_expense_type = e.id_expense_type\n" +
            "WHERE id_account = ?";

    //language=sql GET_BY_ID
    public static final String GET_EXPENSE_TYPE_BY_ID = "SELECT * FROM expense_type WHERE id_expense_type = ?;";

}
