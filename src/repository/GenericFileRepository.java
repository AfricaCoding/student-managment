package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericFileRepository<T, ID> implements GenericRepository<T, ID> {

    private Map<ID, T> store;
    private final String nomFichier;
    private final KeyExtractor<T, ID> keyExtractor; // Fonction pour extraire la clé de l'entité

    public GenericFileRepository(String nomFichier, KeyExtractor<T, ID> keyExtractor) {
        this.nomFichier = nomFichier;
        this.keyExtractor = keyExtractor;
        try {
            loadAll();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    @Override
    public void save(T entity) {
        ID id = keyExtractor.extractKey(entity);
        store.put(id, entity);
        try {
            saveAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
        try {
            saveAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(store);
        }
    }

    @Override
    public void loadAll() throws IOException, ClassNotFoundException {
        File fichier = new File(nomFichier);
        if (fichier.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
                store = (Map<ID, T>) ois.readObject();
            }
        } else {
            store = new HashMap<>();
        }
    }

    // Interface fonctionnelle pour extraire l'ID de l'entité
    @FunctionalInterface
    public interface KeyExtractor<T, ID> {
        ID extractKey(T entity);
    }
}
