package zw.co.zss.interview.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import zw.co.zss.interview.commons.ApiResponse;
import zw.co.zss.interview.exception.BusinessException;
import zw.co.zss.interview.exception.RecordNotFound;
import zw.co.zss.interview.model.*;
import zw.co.zss.interview.purchases.PurchasesService;
import zw.co.zss.interview.utils.Status;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PurchasesService purchasesService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/list")
    public List<Book> getAllBooks()

    {
        return bookService.findAll(0,1000);
    }

    @PostMapping("/create")
    public ApiResponse<Book> create(@RequestBody Book book) {
      logger.info("book " +book);
        book = bookService.create(book).orElseThrow(() -> new BusinessException("Error Creating Book"));
        return new ApiResponse<>(HttpStatus.OK.value(), book);
    }

    @PostMapping("/update")
    public ApiResponse<Book> update(@RequestBody Book book) {
       // log.info("Updating Book:{}", book);
        book = bookService.update(book).orElseThrow(() -> new BusinessException("Error Updating Book"));
        return new ApiResponse<>(HttpStatus.OK.value(), book);
    }

    @GetMapping("/findbyid/{id}")
    public ApiResponse<Book> findById(@PathVariable("id") Long id){
        Book book = bookService.findById(id).orElseThrow(()->new RecordNotFound("Record Not Found"));
        logger.info("Test : "+book);
        return new ApiResponse<>(HttpStatus.OK.value(),book);
    }

    @GetMapping("/findbycategoryid/{categoryid}")
    public ApiResponse<List<Book>> findByCategoryId(@PathVariable("categoryid") Long categoryId){
        List<Book> books = bookService.findByCategoryId(categoryId);
        //logger.info("Test : "+book);
        return new ApiResponse<>(HttpStatus.OK.value(),books);
    }

    @GetMapping("/delete/{id}")
    public ApiResponse<Book> delete(@PathVariable("id") Long id){
        Book book = bookService.findById(id).orElseThrow(()->new RecordNotFound("Error Deleting. Record Not Found"));
        book.setStatus(Status.DELETED);
        bookService.update(book);
        return new ApiResponse<>(HttpStatus.OK.value(),book);
    }

    @PostMapping("/pucharsebook/")
    public String purchase(@RequestBody PurchaseRequest purchaseRequest) {
   // public ResponseEntity<ZssPurchaseResponse> purchaseBook(@RequestBody PurchaseRequest purchaseRequest){


        //fetch book details
        Optional<Book> book=bookService.findById(purchaseRequest.getBookId());
        Double price= book.get().getPrice();
        String title=book.get().getTitle();
        String bookId=book.get().getId().toString();
        logger.info("Price : "+price+" title -> "+title);
        ZssPurchaseResponse zssPurchaseResponse=new ZssPurchaseResponse();
        //build ZssPurchaseRequest
        Card card=new Card(purchaseRequest.getCardNumber(),purchaseRequest.getExpiryDate());
        Map<String,String> additionalData=new HashMap<>();
        additionalData.put("SampleKey","This is a sample value");
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"+"+02:00";

        //generate ref by concating date & a random #
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        String today = sdf.format(new Date());
        Random r = new Random();
        int low2 = 1000;
        int high2 = 9999;
        Integer reference = r.nextInt(high2-low2) + low2;
        String reference1=today+reference.toString();

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("en", "ZW"));
        String date = simpleDateFormat.format(new Date());
        ZssPurchaseRequest zssPurchaseRequest=new ZssPurchaseRequest("PURCHASE","NONE",10.0,date,card, reference1,"test",additionalData);

        //call zss api
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String token = "Bearer " + "9ca3d5ed-dc04-4700-8dd6-7d60c3cdf0fa";
        headers.set("Authorization", token);
        HttpEntity<ZssPurchaseRequest> entity = new HttpEntity<ZssPurchaseRequest>(zssPurchaseRequest,headers);
        String zssResponse= restTemplate.exchange(
                "https://lab.v.co.zw/interview/api/transaction", HttpMethod.POST, entity, String.class).getBody();
       // logger.info("Response : "+ZssResponse);
        try {
            //convert string json to object
            JSONObject jsonObject = new JSONObject(zssResponse);
           // logger.info("Reference -> : "+jsonObject.get("reference"));
            //assign value for purchases
            Purchases purchases=new Purchases();
            purchases.setBookId(bookId);
            purchases.setAmount(price);
            purchases.setDebitReference(jsonObject.get("debitReference").toString());
            purchases.setPaymentReference(jsonObject.get("reference").toString());
            purchases.setPaymentStatus(jsonObject.get("responseDescription").toString());
           //save purchases entry
            Purchases purchases1 = purchasesService.create(purchases).orElseThrow(() -> new BusinessException("Error Creating Purchase record"));

        }catch (JSONException err){
            logger.info("Error", err.toString());
        }

       return zssResponse;
    }


}
