package com.checkedin.backend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {
    private final EntityManager em;
}
