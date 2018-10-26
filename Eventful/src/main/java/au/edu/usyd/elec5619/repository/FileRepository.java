package au.edu.usyd.elec5619.repository;


import au.edu.usyd.elec5619.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}
