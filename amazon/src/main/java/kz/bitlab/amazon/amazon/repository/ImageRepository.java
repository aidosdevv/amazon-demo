package kz.bitlab.amazon.amazon.repository;

import kz.bitlab.amazon.amazon.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
