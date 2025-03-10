package com.joaod.ObjectStore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private Long uuid;
}
