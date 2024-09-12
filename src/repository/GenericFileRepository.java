package repository;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenericFileRepository<T, ID> implements GenericRepository<T, ID> {

    private Map<ID, T> store;
    private final String nomFichier;
    private final KeyExtractor<T, ID> keyExtractor;

    public GenericFileRepository(String nomFichier, KeyExtractor<T, ID> keyExtractor) {
        this.nomFichier = nomFichier;
        this.keyExtractor = keyExtractor;
        store = new HashMap<>();
        loadData();
    }

    private void loadData() {
        File fichier = new File(nomFichier);
        if (fichier.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
                store = (Map<ID, T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erreur lors du chargement des données : " + e.getMessage());
            }
        } else {
            store = new HashMap<>();
        }
    }

    @Override
    public void save(T entity) {
        ID id = keyExtractor.extractKey(entity);
        store.put(id, entity);
        saveData();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return store.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
        saveData();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(store);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface KeyExtractor<T, ID> {
        ID extractKey(T entity);
    }
}
