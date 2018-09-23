package common.validator;

import common.dto.SearchDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SearchFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {return SearchDTO.class.equals(aClass);}

    @Override
    public void validate(Object target, Errors errors) {

        SearchDTO searchDTO = (SearchDTO) target;



    }
}
