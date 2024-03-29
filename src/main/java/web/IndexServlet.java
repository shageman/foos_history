package web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.PlayerRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.PlayerRatingRepository;


/**
*/
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory
    .getLogger(IndexServlet.class);

    private final PlayerRatingRepository playerRatingRepository = new PlayerRatingRepository();

    @Override
    protected void doGet(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {

        if (log.isDebugEnabled()) {
            log.debug("doGet");
        }

        // delete
        if (request.getParameter("id") != null) {
            deletePlayerRating(request);

            response.sendRedirect("index");

            return;
        }

        // get
        final Collection<PlayerRating> playerRatings = this.playerRatingRepository.getAll();
        request.setAttribute("playerRatings", playerRatings);

        if (log.isDebugEnabled()) {
            log.debug("playerRatings: " + playerRatings);
        }

        forward(request, response, "index.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {

        if (log.isDebugEnabled()) {
            log.debug("doPost");
        }

        // create
        createPlayerRating(request);
        response.sendRedirect("index");
    }

    protected void createPlayerRating(final HttpServletRequest request) {
        final String text = request.getParameter("text");

        if (log.isDebugEnabled()) {
            log.debug("creating playerRating with text: " + text);
        }

        final PlayerRating playerRating = new PlayerRating();
        this.playerRatingRepository.create(playerRating);
    }

    protected void deletePlayerRating(final HttpServletRequest request) {
        final Long id = Long.valueOf(request.getParameter("id"));

        if (log.isDebugEnabled()) {
            log.debug("deleting playerRating with id: " + id);
        }

        this.playerRatingRepository.deleteById(id);
    }

    /**
     * Forwards request and response to given path. Handles any
     * exceptions caused by forward target by printing them to logger.
     *
     * @param request
     * @param response
     * @param path
     */
    protected void forward(final HttpServletRequest request,
            final HttpServletResponse response, final String path) {
        try {
            final RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        }
        catch (final Throwable tr) {
            if (log.isErrorEnabled()) {
                log.error("Cought Exception: " + tr.getMessage());
                log.debug("StackTrace:", tr);
            }
        }
    }
}
