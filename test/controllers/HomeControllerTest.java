package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
/**
 * this class represents the test class for main controller 
 * application routing. it contains a test method for 
 * controller methods.
 * @author Marwah
 */
public class HomeControllerTest extends WithApplication {

	/**
	 * this method represents Guice dependency injection that can directly 
	 * configure how components and applications are created for tests.
	 * @author Marwah
	 */
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
    
    /***
     * this method represents a test for index page using 
     * http Get request that asserts true if it return 
     * http OK response (200)
     * @author Marwah
     */
    @Test
    public void testIndex() 
    {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}
