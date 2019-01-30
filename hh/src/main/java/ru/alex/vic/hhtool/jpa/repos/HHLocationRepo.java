package ru.alex.vic.hhtool.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.vic.hhtool.jpa.HHLocation;
@Repository
public interface HHLocationRepo extends JpaRepository<HHLocation, Long> {
}
