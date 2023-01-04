package com.jdc.location.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.location.model.entity.District;
import com.jdc.location.model.entity.State;
import com.jdc.location.model.repo.DistrictRepo;
import com.jdc.location.model.repo.StateRepo;
import com.jdc.location.service.dto.DistrictDto;
import com.jdc.location.service.dto.StateDto;

@Service
@Transactional(readOnly = true)
public class LocationService {
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private DistrictRepo districtRepo;

	public Stream<State> findByRegionAsStream(String region) {
		return stateRepo.findBy(
				Example.of(
						new State(region),
						ExampleMatcher.matching()
							.withIgnorePaths("id", "porpulation"))
				, query -> query.stream());
	}
	
	public Optional<StateDto> findFirstByRegion(String region) {
		return stateRepo.findBy(
				Example.of(
					new State(region),
					ExampleMatcher.matching()
						.withIgnorePaths("id", "porpulation")), 
				query -> query
					.project("id", "name", "region")
					.as(StateDto.class)
					.sortBy(Sort.by("name")).first());
	}
	
	public List<DistrictDto> findDistrictByState(int stateId) {
		var probe = new District();
		var state = new State();
		state.setId(stateId);
		probe.setState(state);
		
		var example = Example.of(probe, ExampleMatcher.matching()
				.withIgnorePaths("id", "state.porpulation"));
		
		return districtRepo.findBy(example, query -> 
			query.as(DistrictDto.class).all());
	}
	
}
