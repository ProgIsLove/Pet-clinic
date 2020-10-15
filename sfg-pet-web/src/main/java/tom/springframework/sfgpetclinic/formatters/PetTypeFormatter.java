package tom.springframework.sfgpetclinic.formatters;

import java.util.Collection;
import java.util.Locale;
import java.text.ParseException;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import tom.springframework.sfgpetclinic.model.PetType;
import tom.springframework.sfgpetclinic.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
	
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}
