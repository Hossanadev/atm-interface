package Utils;

public class ErrorHandler {
    private static String errorMessage = "";

    public static void login(int ErrorCode) {
        switch (ErrorCode) {
            case 1:
                errorMessage = "Account id or pin field cannot be empty!";
                break;
            case 2:
                errorMessage = "Invalid credentials!";
                break;
            case 3:
                errorMessage = "Account id or pin must be a numeric value!";
                break;
            default:
        }
        System.err.println("-- ERROR: " + errorMessage + " --");
    }

    public static void createAccount(int ErrorCode) {
        switch (ErrorCode) {
            case 1:
                errorMessage = "Account name cannot be empty!";
                break;
            case 2:
                errorMessage = "Id cannot be empty!";
                break;
            case 3:
                errorMessage = "Pin cannot be empty!";
                break;
            case 4:
                errorMessage = "Id or Pin must be a numeric value!";
                break;
            case 5:
                errorMessage = "User with this name already exist, enter unique name!";
                break;
            case 6:
                errorMessage = "User with this id already exist, enter unique id!";
                break;
            case 7:
                errorMessage = "An error occurred while generating a unique account number for you!";
            default:
        }
        System.err.println("-- ERROR: " + errorMessage + " --");
    }

    public static void parseDataFromDatabase(int ErrorCode, Exception error) {
        switch (ErrorCode) {
            case 1:
                errorMessage = "Error parsing user: " + error;
                break;
            case 2:
                errorMessage = "Id cannot be empty!";
            default:
        }
        System.err.println("-- ERROR: " + errorMessage + " --");
    }
}