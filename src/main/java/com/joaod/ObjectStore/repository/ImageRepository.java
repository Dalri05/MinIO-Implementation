package com.joaod.ObjectStore.repository;

import com.joaod.ObjectStore.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
