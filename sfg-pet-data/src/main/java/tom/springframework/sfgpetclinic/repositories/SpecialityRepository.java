package tom.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import tom.springframework.sfgpetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

}
