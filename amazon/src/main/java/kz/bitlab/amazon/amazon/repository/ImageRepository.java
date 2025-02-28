package kz.bitlab.amazon.amazon.repository;

import kz.bitlab.amazon.amazon.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

}
