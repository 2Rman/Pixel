package app.constant;

public class ConstantQuery {

    //language=sql CREATE-QUERY
    public static final String CREATE_ACCOUNT = "INSERT INTO account VALUES (?, ?, ?, ?, ?)";
    public static final String CREATE_CLIENT = "";

    //language=sql GET-ALL-QUERY
    public static final String GET_ALL_ACCOUNTS = "";
    public static final String GET_ALL_CLIENTS = "";

    //language=sql GET_BY_ID
    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM account WHERE phone_number=?";
    public static final String GET_CLIENT_BY_ID = "";

    //language=sql UPDATE-QUERY
    public static final String UPDATE_ACCOUNT = "";
    public static final String UPDATE_CLIENT = "";
}
