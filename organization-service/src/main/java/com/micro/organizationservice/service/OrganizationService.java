package com.micro.organizationservice.service;

import com.micro.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
	 OrganizationDto saveOrganization(OrganizationDto organizationDto);

	    OrganizationDto getOrganizationByCode(String organizationCode);

}
