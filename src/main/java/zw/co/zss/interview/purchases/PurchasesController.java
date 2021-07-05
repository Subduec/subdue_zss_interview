package zw.co.zss.interview.purchases;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.interview.commons.ApiResponse;
import zw.co.zss.interview.exception.BusinessException;
import zw.co.zss.interview.exception.RecordNotFound;
import zw.co.zss.interview.model.Purchases;
import zw.co.zss.interview.utils.Status;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PurchasesRepository purchasesRepository;

    @Autowired
    private PurchasesService purchasesService;

    @GetMapping("/list")
    public List<Purchases> getAllPurchasess()

    {
        return purchasesService.findAll(0,1000);
    }

    @PostMapping("/create")
    public ApiResponse<Purchases> create(@RequestBody Purchases purchases) {
      logger.info("purchases " +purchases);
        purchases = purchasesService.create(purchases).orElseThrow(() -> new BusinessException("Error Creating Purchases"));
        return new ApiResponse<>(HttpStatus.OK.value(), purchases);
    }

    @PostMapping("/update")
    public ApiResponse<Purchases> update(@RequestBody Purchases purchases) {
       // log.info("Updating Purchases:{}", purchases);
        purchases = purchasesService.update(purchases).orElseThrow(() -> new BusinessException("Error Updating Purchases"));
        return new ApiResponse<>(HttpStatus.OK.value(), purchases);
    }

    @GetMapping("/findbyid/{id}")
    public ApiResponse<Purchases> findById(@PathVariable("id") Long id){
        Purchases purchases = purchasesService.findById(id).orElseThrow(()->new RecordNotFound("Record Not Found"));
        logger.info("Test : "+purchases);
        return new ApiResponse<>(HttpStatus.OK.value(),purchases);
    }



}
