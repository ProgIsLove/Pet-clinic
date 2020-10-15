package tom.springframework.sfgpetclinic.boostrap;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tom.springframework.sfgpetclinic.services.VetService;
import tom.springframework.sfgpetclinic.services.VisitService;
import tom.springframework.sfgpetclinic.model.Owner;
import tom.springframework.sfgpetclinic.model.Pet;
import tom.springframework.sfgpetclinic.model.PetType;
import tom.springframework.sfgpetclinic.model.Speciality;
import tom.springframework.sfgpetclinic.model.Vet;
import tom.springframework.sfgpetclinic.model.Visit;
import tom.springframework.sfgpetclinic.services.OwnerService;
import tom.springframework.sfgpetclinic.services.PetTypeService;
import tom.springframework.sfgpetclinic.services.SpecialityService;

@Component
public class DataLoader implements CommandLineRunner{
	
	private final VetService vetService;
	private final OwnerService ownerService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialitiesService;
	private final VisitService visitService;
	
	public DataLoader(VetService vetService, OwnerService ownerService, 
			PetTypeService petTypeService, SpecialityService specialitiesService, VisitService visitService) {
		
		this.vetService = vetService;
		this.ownerService = ownerService;
		this.petTypeService = petTypeService;
		this.specialitiesService = specialitiesService;
		this.visitService = visitService;
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		int count = petTypeService.findAll().size();
		
		if(count == 0) {
			loadData();
		}
		
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		
		Speciality savedRadiology = specialitiesService.save(radiology);
		
		Owner o1 = new Owner();
		o1.setFirstName("Alex");
		o1.setLastName("Butcher");
		o1.setAddress("123 Green St.");
		o1.setCity("Green");
		o1.setTelephone("231 321 312");
		
		Pet alexPet = new Pet();
		alexPet.setPetType(savedDogPetType);
		alexPet.setOwner(o1);
		alexPet.setBirthDate(LocalDate.now());
		alexPet.setName("Buck");
		o1.getPets().add(alexPet);
		
		ownerService.save(o1);
		
		
		
		Visit dogVisit = new Visit();
		dogVisit.setPet(alexPet);
		dogVisit.setDate(LocalDate.now());
		dogVisit.setDescription("Sneezy dog");
	
		
		visitService.save(dogVisit);
		
		System.out.println("Loaded owner...");
		
		Vet v1 = new Vet();
		v1.setFirstName("Mia");
		v1.setLastName("Smart");
		v1.getSpecialities().add(savedRadiology);
		
		vetService.save(v1);
		
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);
		
		System.out.println("Loaded vet...");
		

		
		System.out.println();
	}
	
	

}
