package fr.univ_lyon1.info.m1.mes.daos;


import javax.naming.NameNotFoundException;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractMapDao<T> implements Dao<T> {
    private Map<Serializable, T> collection = new HashMap<>();

    @Override
    public Serializable add(final T element)  {
        Serializable key = getKeyForElement(element);
        this.collection.put(key, element);
        return key;
    }

    @Override
    public void delete(final T element) throws NameNotFoundException {
        Serializable key = getKeyForElement(element);
        if (!this.collection.containsKey(key)) {
            throw new NameNotFoundException(key.toString());
        }
        this.collection.remove(key);
    }

    @Override
    public void deleteById(final Serializable id)  {
        this.collection.remove(id);
    }

    @Override
    public void update(final Serializable id, final T element) {
        this.collection.put(id, element);
    }

    @Override
    public Serializable getId(final T element) {
        return getKeyForElement(element);
    }

    @Override
    public T findOne(final Serializable id) {
        return this.collection.get(id);
    }

    @Override
    public List<T> findAll() {
        List<T> all = new ArrayList<T>();
        for (T element : this.collection.values()) {
            all.add(element);
        }
        return all;
    }

    
    public Map<Serializable, T> getCollection() {
        return this.collection;
    }

    
    /**
     * Renvoie la clé correspondant au type spécifique de l'élément<br>
     * Exemples : un champ "id" d'une classe, un hash des champs de l'objet...
     * @param element élément du type de la classe à stocker dans le DAO
     * @return une clé qui est une instance d'une sous-classe de <code>Serializable</code>
     */
    protected abstract Serializable getKeyForElement(T element);
}
