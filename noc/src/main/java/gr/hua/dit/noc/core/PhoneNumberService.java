package gr.hua.dit.noc.core;

import gr.hua.dit.noc.core.model.PhoneNumberValidationResult;

public interface PhoneNumberService {

    PhoneNumberValidationResult validatePhoneNumber(final String rawPhoneNumber);
}