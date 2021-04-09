package store.games.qins.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import store.games.qins.entities.TStore;

@RepositoryRestResource
public interface StoreRepository extends JpaRepository<TStore, Long> {

}
