package zw.co.zss.interview.purchases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.zss.interview.model.Purchases;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasesServiceImpl implements PurchasesService {

	@Autowired
	private PurchasesRepository purchasesRepository;

	@Override
	public Optional<Purchases> findById(Object id) {
		return purchasesRepository.findById((Long) id);
	}

	@Override
	public List<Purchases> findAll(int page, int size) {
		return purchasesRepository.findAll();
	}

	@Override
	public Optional<Purchases> create(Purchases purchases) {
		return Optional.ofNullable(purchasesRepository.save(purchases));
	}

	@Override
	public Optional<Purchases> update(Purchases purchases) {
		return Optional.ofNullable(purchasesRepository.save(purchases));
	}

	@Override
	public void delete(Purchases purchases) {
		purchasesRepository.delete(purchases);
	}
}