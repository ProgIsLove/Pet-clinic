package tom.springframework.sfgpetclinic.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import tom.springframework.sfgpetclinic.model.Owner;
import tom.springframework.sfgpetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnersController {
	
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owner/createOrUpdateOwnerForm";
	private final OwnerService ownerService;
	
	public OwnersController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
	
	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
			@PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}


	/*
	 * @RequestMapping({"","/","/index", "/index.html"}) public String
	 * listOwners(Model model) {
	 * 
	 * model.addAttribute("owners", ownerService.findAll());
	 * 
	 * return "owner/index"; }
	 */
	
	@RequestMapping("/find")
	public String findOwners(Model model) {

		model.addAttribute("owner", new Owner());
		
		return "owner/findOwners";
	}
	
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {
        // allow parameterless GET request for /owners to return all records
       if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
       }

        // find owners by last name
       Collection<Owner> results = ownerService.findAllByLastName(owner.getLastName());
        
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owner/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owner/ownersList";
        }
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView nav = new ModelAndView("owner/ownerDetails");
		nav.addObject(ownerService.findById(ownerId));
		return nav;
	}
}
