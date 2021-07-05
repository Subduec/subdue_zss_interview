package zw.co.zss.interview.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.interview.model.Purchases;
import zw.co.zss.interview.utils.Status;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {

   // List<Purchases> findByName(String Name);

    Purchases save(Purchases purchases);



}

