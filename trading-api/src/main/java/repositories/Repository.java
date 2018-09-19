package repositories;

public interface Repository<T, K> {
    public void save(T entityToSave);

    public void delete(K key);

    public Object fetchOne(K key);
}
