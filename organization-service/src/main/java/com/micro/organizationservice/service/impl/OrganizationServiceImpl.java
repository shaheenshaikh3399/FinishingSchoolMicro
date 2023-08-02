package com.micro.organizationservice.service.impl;

import org.springframework.stereotype.Service;

import com.micro.organizationservice.dto.OrganizationDto;
import com.micro.organizationservice.entity.Organization;
import com.micro.organizationservice.mapper.OrganizationMapper;
import com.micro.organizationservice.repository.OrganizationRepository;
import com.micro.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
	
	private OrganizationRepository organizationRepository;

	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		
		
		// convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
	}

	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
		
	}
	
	

}
