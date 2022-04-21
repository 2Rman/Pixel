package app.constant;

public class ConstantQuery {

    //language=sql GET-ALL-QUERY
    public static final String GET_ALL_ACCOUNTS = "";
    public static final String GET_ALL_CLIENTS = "";

    //language=sql CREATE-QUERY
    public static final String CREATE_ACCOUNT = "INSERT INTO account VALUES (?, ?, ?, ?, ?)";
    public static final String CREATE_CLIENT = "";

    //language=sql GET_BY_ID
    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id_account=?";
    public static final String GET_CLIENT_BY_ID = "";

    //language=sql GET_BY_PHONE
    public static final String GET_ACCOUNT_BY_PHONE_NUMBER = "SELECT * FROM account WHERE phone_number=?";

    //language=sql UPDATE_QUERY
    public static final String UPDATE_ACCOUNT = "";
    public static final String UPDATE_CLIENT = "";


}
