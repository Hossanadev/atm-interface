package Utils;

import view.auth.LandingPage;
import view.auth.LoginService;

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
        System.out.println("-- ERROR: " + errorMessage + " --");
    }
}
