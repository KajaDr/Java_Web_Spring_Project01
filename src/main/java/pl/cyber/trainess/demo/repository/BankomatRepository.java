package pl.cyber.trainess.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cyber.trainess.demo.domain.BankomatEntry;

@Repository
public interface BankomatRepository  extends JpaRepository<BankomatEntry, String> {
    //interface to klasa abstrakcyjna, nie posiada implementacji dla jej metod
    //CRUF cread read update delete

    @Modifying
    @Query(value= "UPDATE BANKOMAT set name=:name where id=:id", nativeQuery = true)
    void updateName(@Param("id") String id, @Param("name") String name);
}
