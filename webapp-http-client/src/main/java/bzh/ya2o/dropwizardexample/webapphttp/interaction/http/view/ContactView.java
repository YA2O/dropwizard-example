package bzh.ya2o.dropwizardexample.webapphttp.interaction.http.view;

import bzh.ya2o.dropwizardexample.webapphttp.model.*;
import io.dropwizard.views.*;

public class ContactView extends View {

    private final Contact contact;

    public ContactView(Contact contact) {
        super("/views/contact.mustache");
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }
}
