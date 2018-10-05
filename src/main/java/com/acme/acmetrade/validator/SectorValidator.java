package com.acme.acmetrade.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.acme.acmetrade.domain.Sector;

@Component
public class SectorValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {		
		return Sector.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sector sector = (Sector) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectorName", "1234", "The sectorName is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectorDesc", "1234", "The sectorDesc is empty");

		if (sector.getId() < 0) {
			errors.rejectValue("id", "1235", "The id can not be less than 0");
		}
		
	}

}
