// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Fri Dec 11 06:07:37 EET 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSimilarityController SimilarityController = new controllers.ReverseSimilarityController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSimilarityController SimilarityController = new controllers.javascript.ReverseSimilarityController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
