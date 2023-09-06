package fr.univ_lyon1.info.m1.mes.observer;

public interface Observable {
    /**.
     * Ajoute un Observer à la liste d'Observer
     * @param o une vue qui vient d'être créée
     */
    void registerObserver(Observer o);

    /**.
     * Supprime un Observer de la liste d'Observer
     * @param o vue à supprimer
     */
    void unRegisterObserver(Observer o);

    /**.
     * Notifie tous les Observer qu'une modification a été effectuée
     */
    void notifyObservers();
}
