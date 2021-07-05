package zw.co.zss.interview.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private int status;
    private String message;
    private T body;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(int status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public ApiResponse(int status, T body) {
        this.status = status;
        this.body = body;
    }

    public ApiResponse(int status, String message,String userType,String userId, T body) {
        this.status = status;
        this.message = message;
        this.body = body;

    }

}
