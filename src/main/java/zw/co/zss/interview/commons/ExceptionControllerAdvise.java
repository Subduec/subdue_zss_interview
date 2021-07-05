
package zw.co.zss.interview.commons;

import zw.co.zss.interview.exception.AccountInactiveException;
import zw.co.zss.interview.exception.BusinessException;
import zw.co.zss.interview.exception.RecordExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvise {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exception(Exception e) {
     // log.error("Processing error:", e);

        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ApiResponse exception(BusinessException e) {
      //  log.error("Business exception:", e);
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(RecordExistException.class)
    @ResponseBody
    public ApiResponse recordExistException(RecordExistException e) {
       // log.error("Business exception:", e);
        return new ApiResponse(HttpStatus.FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(AccountInactiveException.class)
    @ResponseBody
    public ApiResponse accoutnInactiveException(AccountInactiveException e) {
      //  log.error("Account Inactive exception:", e);
        return new ApiResponse(HttpStatus.FOUND.value(), e.getMessage());
    }


}

