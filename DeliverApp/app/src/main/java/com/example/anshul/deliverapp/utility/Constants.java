package com.example.anshul.deliverapp.utility;

/**
 * Created by GJS280 on 13/4/2015.
 */
public class Constants {

    /**
     * General
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String PULL_DATE_FORMAT = "MM/dd/yyyy";
    public static final String DELIVERY_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String EMP_CODE_SUFFIX = "_gojavas";
    public static final String OTP_ACTION = "otp_action";
    public static final String CUSTOMER_CARE = "18001022646";

    /**
     * Amazon
     */
    public static final String COGNITO_POOL_ID = "us-east-1:18da8708-16d6-4db8-9263-beb08fed5e09"; // Identity Pool Id
    public static final String BUCKET_NAME = "godelivery"; // Bucket Name

    /**
     * Alarm manager keys542051
     */
    public static final int ALARM_TRACK_KEY = 100;
    public static final int ALARM_SYNC_KEY = 200;
    public static final int ALARM_POD_KEY = 300;
    public static final int ALARM_FILE_DELETE_KEY = 400;
    public static final int ALARM_LOGOUT_SHUTDOWN_KEY = 500;

    /**




    public static final String LOG_SHUT_DATE_TIME = "MobileTime";



    /**
     * Docket detail fields
     */
    public static final String JOB_TYPE = "JobType";
    public static final String JOB_TYPE_MOBILE_PICKUP = "MobilePickup";
    public static final String JOB_TYPE_PICKUP = "Pickup";
    public static final String JOB_TYPE_90_MINUTES = "90Minutes";
    public static final String JOB_TYPE_TNB = "TNB";
    public static final String JOB_TYPE_FIRST_PICKUP = "FirstPickup";
    public static final String JOB_TYPE_DELIVERY = "Delivery";
    public static final String JOB_TYPE_DELIVERY1 = "Delivery1";
    public static final String JOB_TYPE_EXCHANGE = "Exchange";
    public static final String PARTIAL_RETURN = "Partial";

    /**
     * Intent fields
     */
    public static final String SCREEN = "Screen";
    public static final String STATUS_SUCCESS = "1";
    public static final String STATUS_FAIL = "0";
    public static final String DOCKET_NUMBER = "DocketNumber";
    public static final String DRS_NUMBER = "DrsNumber";
    public static final String DRS_DOCKET = "DrsDocket";
    public static final String COD_AMOUNT = "CODAmount";
    public static final String PAYMENT_MESSAGE = "PaymentMessage";
    public static final String PAYMENT_AMOUNT = "PaymentAmount";
    public static final int MINIMUM_AMOUNT = 30;
    public static final int MAXIMUM_AMOUNT = 49;
    public static final String SIGNATURE_IMAGE = "SignatureImage";
    public static final String FAIL_CATEGORY = "FailCategory";

    /**
     * Paths
     */
    public static final String PATH_CAPTURED_IMAGE = "/CapturedImage/";

    /**
     * Payment type
     */
    public static final String PAYMENT_TYPE_CASH = "Cash";
    public static final String PAYMENT_TYPE_EZETAP = "Ezetap";
    public static final String EZETAP_PAYMENT_DONE = "AUTHORIZED";
    public static final String PAYMENT_TYPE_MSWIPE = "Mswipe";
    public static final String PAYMENT_TYPE_TRUPAY = "TP";
    public static final String PAYMENT_TYPE_FREE_RECHARGE = "FC";
    public static final String PAYMENT_TYPE_CHEQUE = "CH";
    public static final String TRANSACTION_NO = "TransactionNo";
    public static final String AUTH_CODE = "AuthCode";
    public static final String CARD_TYPE = "CardType";
    public static final String BANK_NAME = "BankName";
    public static final String MODE_OF_PAYMENT = "ModeOfPayment";
    public static final String RECEIPT_URL = "ReceiptUrl";

    /**
     * Design Json keys
     */
    public static final String DESIGN_JSON = "DesignJson";
    public static final String DESIGN_PENDING = "Pending";
    public static final String DESIGN_SUCCESS = "Success";
    public static final String DESIGN_FAIL = "Fail";
    public static final String DESIGN_ACCEPT = "Accept";
    public static final String DESIGN_REJECT = "Reject";
    public static final String DESIGN_PRIMARY_ACTION = "PrimaryAction";
    public static final String DESIGN_ACTION = "Action";
    public static final String DESIGN_TYPE = "Type";
    public static final String DESIGN_TITLE = "Title";
    public static final String DESIGN_COLUMN_NAME = "Column";
    public static final String DESIGN_ICON = "Icon";
    public static final String DESIGN_COLOR = "Color";
    public static final String DESIGN_REASON_ID = "ReasonId";
    public static final String DESIGN_VALUES = "Values";
    public static final String DESIGN_CLIENT = "client";
    public static final String DESIGN_CLIENT_SOD = "client_sod";
    public static final String DESIGN_CLIENT_OLX = "client_cheque";
    public static final String DESIGN_COMPULSORY = "Compulsory";
    public static final String DESIGN_HAPPY_DELIVERY = "Happy Delivery";
    public static final String DESIGN_HAPPY_DELIVERY_AUTO_ACTION = "Yes";
    public static final String DESIGN_CUSTOMER_INITIATED_DELAY = "Customer Initiated Delay";
    public static final String[] DESIGN_CUSTOMER_INITIATED_DELAY_AUTO_ACTION = new String[] {"CONSIGNEE REQUESTED DELIVERY ON ANOTHER DATE", "PICK UP ON OTHER DATE"};
//    public static final String DESIGN_CUSTOMER_INITIATED_DELAY_AUTO_ACTION = "CONSIGNEE REQUESTED DELIVERY ON ANOTHER DATE";
    // Action types
    public static final String DESIGN_TYPE_PAYMENT = "Payment";
    public static final String DESIGN_TYPE_DROP_DOWN_SINGLE_CHOICE = "DropDownSingleChoice";
    public static final String DESIGN_TYPE_SINGLE_CHOICE = "SingleChoice";
    public static final String DESIGN_TYPE_USER_INPUT = "UserInput";
    public static final String DESIGN_TYPE_RATING = "Rating";
    public static final String DESIGN_TYPE_SIGNATURE = "Signature";
    public static final String DESIGN_TYPE_CAMERA = "Camera";
    public static final String DESIGN_TYPE_ITEMS_ARRAY = "ItemsArray";
    public static final String DESIGN_TYPE_SCANNER = "Scanner";
    public static final String DESIGN_TYPE_CHECKBOX = "CheckBox";
    public static final String DESIGN_TYPE_DATE = "Date";
    public static final String DESIGN_TYPE_COMPLETE = "Complete";

    /**
     * Schedular Response
     */
    public static final String SCHEDULAR_RESPONSE_KEY = "message";
    public static final String SCHEDULAR_RESPONSE_MESSAGE = "errormessage";
    public static final String SUCCESS_RESPONSE = "success";
    public static final String FALIURE_RESPONSE = "faliure";
    public static final String SCHEDULAR_SYNC_ACTION = "schedular_sync_action";

    /**
     * call check professional or personel in SMS or Call logginfg
     */
    public static final String PROFESSIONAL = "Professional";
    public static final String PERSONAL = "Personal";
    public static final String CALL = "call";
    public static final String SMS = "sms";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Logitude";
    public static final String PROVIDER = "Provider";

    /**
     * Toast messages
     */
    public static final String PLEASE_WAIT = "Please wait...";
    public static final String TRY_AGAIN = "Server error. Please try again";
    public static final String ERROR_INTERNET_CONNECTION = "Not connected to internet";
    public static final String ERROR_SIM_CARD_TITLE = "Sim Card Error";
    public static final String ERROR_SIM_CARD_MESSAGE = "Sim card not detected. Please insert sim card";
    public static final String LOGOUT_MESSAGE = "Are you sure you want to logout?";
    public static final String INCORRECT_DATE_TITLE = "Incorrect Date and Time";
    public static final String INCORRECT_DATE_MESSAGE = "Kindly select automatic Date and Time";
    public static final String INCORRECT_TIME_ZONE_TITLE = "Incorrect Time Zone";
    public static final String INCORRECT_TIME_ZONE_MESSAGE = "Kindly select automatic Time Zone";

    /**
     * Swipe messages
     */
    public static final String MSWIPE_TRANSACTION_ERROR = "transaction will not be processed as invoice/ref no already used(error code MS13)";
    public static final String EZETAP_TRANSACTION_ERROR = "You have already made a similar payment for the same";
    public static final String TruPay_TRANSACTION_ERROR = "Due to some Error Transaction is cancelled";
}
