package tom.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tom.springframework.sfgpetclinic.model.Owner;
import tom.springframework.sfgpetclinic.repositories.OwnerRepository;
import tom.springframework.sfgpetclinic.repositories.PetRepository;
import tom.springframework.sfgpetclinic.repositories.PetTypeRepository;
import tom.springframework.sfgpetclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	
	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<Owner> findAll() {
		// TODO Auto-generated method stub
		
		Set<Owner> owners = new HashSet<>();
		
		ownerRepository.findAll().forEach(owners::add);
		
		return owners;
	}

	@Override
	@Transactional
	public Owner findById(Long id) {
		// TODO Auto-generated method stub
		//Optional<Owner> optionalOwner =  ownerRepository.findById(id);
		
		/*
		 * if(optionalOwner.isPresent()) { return optionalOwner.get(); }else { return
		 * null; }
		 */
		
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Owner save(Owner object) {
		// TODO Auto-generated method stub
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		// TODO Auto-generated method stub
		
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Collection<Owner> findAllByLastName(String lastName) {
		// TODO Auto-generated method stub
		
		return ownerRepository.findAllByLastName(lastName);
	}



}
