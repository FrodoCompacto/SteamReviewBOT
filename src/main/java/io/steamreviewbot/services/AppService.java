package io.steamreviewbot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.steamreviewbot.domain.App;
import io.steamreviewbot.dto.AppDTO;
import io.steamreviewbot.repositories.AppRepository;

@Service
public class AppService {
	
	@Autowired
	private AppRepository repo;
	
	
	
	public void insert(ArrayList<AppDTO> listApp) {
		
		listApp.forEach(app -> {
			Optional<App> aux = repo.findById(app.getAppid());
			if (aux.isPresent()) {
				aux.get().addInterest();
				repo.save(aux.get());
			} else {
				repo.save(app.toApp(1));
			}
		});
		
		
	}

	public Page<App> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public List<App> findAll() {
		return repo.findAll();
	}

}
