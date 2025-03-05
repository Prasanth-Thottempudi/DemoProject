package com.usermanagement.service;

import com.usermanagement.request.EmailDetails;

public interface EmailService {

	String sendSimpleMail(EmailDetails details);

	String sendMailWithAttachment(EmailDetails details);
}