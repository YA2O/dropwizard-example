package bzh.ya2o.dropwizardexample.phoneboook.model.repository;

import bzh.ya2o.dropwizardexample.phoneboook.nucleus.entity.*;

public interface ContactRepository {

    Contact getContactById(int id);

    int createContact(String firstName, String lastName, String phone);

    void updateContact(int id, String firstName, String lastName, String phone);

    void deleteContact(int id);
}
