package web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.googlecode.objectify.ObjectifyService;
import model.PlayerRating;


/**
 */
public final class ContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ObjectifyService.register(PlayerRating.class);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // empty
    }
}
