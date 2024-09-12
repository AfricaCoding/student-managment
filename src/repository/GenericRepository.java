package repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {

    // Sauvegarder l'entité dans la collection
    void save(T entity);

    // Trouver une entité par son ID
    Optional<T> findById(ID id);

    // Retourner toutes les entités
    List<T> findAll();

    // Supprimer une entité par son ID
    void deleteById(ID id);
}
