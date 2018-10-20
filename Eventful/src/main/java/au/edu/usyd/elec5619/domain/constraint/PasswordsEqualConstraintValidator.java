package au.edu.usyd.elec5619.domain.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import au.edu.usyd.elec5619.domain.User;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, Object> {

	@Override
	public void initialize(PasswordsEqualConstraint arg0) {
	}

	@Override
	public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
		User user = (User) candidate;
		System.out.println(user.getConfirmPassword());
		System.out.println(user.getPassword());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(user.getConfirmPassword(), user.getPassword()) || user.getConfirmPassword().equals(user.getPassword());
	}
}