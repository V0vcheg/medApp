package fr.univ_lyon1.info.m1.mes.daos;


import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;
import java.io.Serializable;
import java.util.Collection;


public interface Dao<T> {
    /**
     * Ajoute un élément.
     * @param element L'élément à ajouter
     * @return La clé de l'élément ajouté
     */
    Serializable add(T element);

    /**
     * Supprime un élément.
     * @param element L'élément à supprimer
     * @throws NameNotFoundException Si l'élément à supprimer n'a pas été trouvé
     */
    void delete(T element) throws NameNotFoundException;

    /**
     * Supprime un élément.
     * @param id La clé de l'élément à supprimer
     */
    void deleteById(Serializable id);

    /**
     * Met à jour un élément ou le crée s'il n'existe pas.
     * @param id La clé de l'élément à mettre à jour
     * @param element L'élément par lequel remplacer l'ancien élément
     */
    void update(Serializable id, T element) throws InvalidNameException;

    /**
     * Renvoie la clé d'un élément.
     * @param element L'élément dont on recherche la clé
     * @return La clé de l'élément passé en paramètre
     * @throws NameNotFoundException Si l'élément à rechercher n'a pas été trouvé
     */
    Serializable getId(T element) throws NameNotFoundException;

    /**
     * Renvoie un élément à partir de sa clé.
     * @param id La clé de l'élément cherché
     * @return L'élément dont la clé est celle passée en paramètre
     */
    T findOne(Serializable id);


    /**
     * Renvoie tous les éléments.
     * @return La collection (potentiellement vide) d'éléments stockés
     */
    Collection<T> findAll();
}
