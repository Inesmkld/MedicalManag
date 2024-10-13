package org.example.dentelmanag.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManager {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();

    // Ouvrir ou obtenir la session actuelle
    public static Session getSession() {
        Session session = threadLocalSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocalSession.set(session);
        }
        return session;
    }

    // Fermer la session actuelle
    public static void closeSession() {
        Session session = threadLocalSession.get();
        if (session != null) {
            session.close();
            threadLocalSession.remove();
        }
    }

    // Gérer les transactions
    public static void executeTransaction(TransactionAction action) {
        Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            action.execute(session);  // Exécute l'action transactionnelle
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSession();  // Ferme la session après la transaction
        }
    }

    // Interface pour définir l'action transactionnelle
    public interface TransactionAction {
        void execute(Session session);
    }
}
