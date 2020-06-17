package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.entity.CompanyEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer GOOGLE_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void testGetById(){
        Optional<CompanyEntity> company = companyRepository.findById(GOOGLE_ID);
        assertTrue(company.isPresent());
        company.ifPresent(companyEntity -> {
            assertEquals("Google", companyEntity.getName());
        });

    }

    @Test
    void testSave(){
        CompanyEntity intel = CompanyEntity.builder()
                .name("Intel")
                .build();
        companyRepository.save(intel);
        assertNotNull(intel.getId());
    }

}